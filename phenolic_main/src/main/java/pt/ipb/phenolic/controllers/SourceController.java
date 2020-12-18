package pt.ipb.phenolic.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pt.ipb.phenolic.dtos.PhenolicRequest;
import pt.ipb.phenolic.dtos.SourceRequest;
import pt.ipb.phenolic.models.Relative;
import pt.ipb.phenolic.models.Value;
import pt.ipb.phenolic.repos.PhenolicRepository;
import pt.ipb.phenolic.repos.SourceRepository;
import java.util.HashSet;

@RestController
@RequestMapping("/sources")
public class SourceController {

    private final SourceRepository sourceRepo;
    private final PhenolicRepository phenolicRepo;

    public SourceController(
            SourceRepository sourceRepo,
            PhenolicRepository phenolicRepo
    ) {
        this.sourceRepo = sourceRepo;
        this.phenolicRepo = phenolicRepo;
    }

    @GetMapping
    public HashSet<SourceRequest> getSources(){
        var listSources = sourceRepo.findAll();
        var listSourceRequest = new HashSet<SourceRequest>();
        listSources.forEach((source) -> {
            var converted = new SourceRequest();
            converted.setId(source.getId());
            converted.setName(source.getName());
            listSourceRequest.add(converted);
        });
        return listSourceRequest;
    }


    @GetMapping("/{id}")
    public SourceRequest showSource(@PathVariable Long id) {
        var sourceFind = sourceRepo.findById(id);
        var sourceRequest = new SourceRequest();
        if (sourceFind.isPresent()){
            var source = sourceFind.get();
            sourceRequest.setId(source.getId());
            sourceRequest.setName(source.getName());
            if (!source.getPhenolics().isEmpty()){
                var children = new Relative("phenolic");
                var values = new HashSet<Value>();
                source.getPhenolics().forEach((phenolic -> {
                    values.add(new Value(phenolic.getId(),phenolic.getName()));
                }));
                children.setValues(values);
                sourceRequest.setChildren(children);
            }
        }
        return sourceRequest;
    }

    @PostMapping
    public SourceRequest storeSource(@RequestBody SourceRequest sourceRequest) {
        var source = sourceRepo.save(sourceRequest.toEntity());
        var sourceRequestReturn = new SourceRequest();
        sourceRequestReturn.setId(source.getId());
        sourceRequestReturn.setName(source.getName());
        return sourceRequestReturn;
    }

    @PostMapping("/{id}/phenolics")
    @Transactional
    public PhenolicRequest storeAndLinkPhenolic(@PathVariable Long id, @RequestBody PhenolicRequest phenolicRequest) {
        var sourceFind = sourceRepo.findById(id);
        var phenolicRequestReturn = new PhenolicRequest();
        if (sourceFind.isPresent()) {
            var source = sourceFind.get();
            var phenolic = phenolicRepo.save(phenolicRequest.toEntity(source));
            phenolicRequestReturn.setId(source.getId());
            phenolicRequestReturn.setName(source.getName());
            var children = new Relative("phenolic");
            var setValue = new HashSet<Value>();
            var value = new Value(phenolic.getId(),phenolic.getName());
            setValue.add(value);
            children.setValues(setValue);
            phenolicRequestReturn.setChildren(children);
        }
        return phenolicRequestReturn;
    }

    @PutMapping("/{id}")
    public SourceRequest updateSource(@PathVariable Long id, @RequestBody SourceRequest sourceRequest) {
        var sourceFind = sourceRepo.findById(id);
        var sourceRequestReturn = new SourceRequest();
        if (sourceFind.isPresent()) {
            var source = sourceFind.get();
            source.setName(sourceRequest.getName());
            var sourceResponse = sourceRepo.save(source);
            sourceRequestReturn.setId(sourceResponse.getId());
            sourceRequestReturn.setName(sourceResponse.getName());
        }
        return sourceRequestReturn;
    }

    @PutMapping("/{id1}/phenolics/{id2}")
    public SourceRequest updateSourceParentPhenolic(@PathVariable("id1") Long id1,@PathVariable("id2") Long id2) {
        var sourceFind = sourceRepo.findById(id1);
        var phenolicFind = phenolicRepo.findById(id2);
        var sourceRequestReturn = new SourceRequest();
        if (sourceFind.isPresent() && phenolicFind.isPresent()) {
            var source = sourceFind.get();
            var phenolic = phenolicFind.get();
            if (phenolic.getSource() == null && phenolic.getPhenolic() == null){
                phenolic.setSource(source);
                phenolicRepo.save(phenolic);
                sourceRequestReturn.setId(source.getId());
                sourceRequestReturn.setName(source.getName());
                var children = new Relative("phenolic");
                var setValue = new HashSet<Value>();
                var value = new Value(phenolic.getId(),phenolic.getName());
                setValue.add(value);
                children.setValues(setValue);
                sourceRequestReturn.setChildren(children);
            }
        }
        return sourceRequestReturn;
    }


    @DeleteMapping("/{id}")
    public ResponseEntity destroy(@PathVariable Long id) {
        var sourceFind = sourceRepo.findById(id);
        if (sourceFind.isPresent()){
            var source = sourceFind.get();
            if (!source.getPhenolics().isEmpty()){
                source.getPhenolics().forEach((phenolic -> {
                    phenolic.setSource(null);
                    phenolicRepo.save(phenolic);
                }));
            }
            sourceRepo.deleteById(id);
            return new ResponseEntity("Success", HttpStatus.ACCEPTED);
        }else{
            return new ResponseEntity("Fail", HttpStatus.NO_CONTENT);
        }
    }
}
