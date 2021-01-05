package pt.ipb.phenolic.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pt.ipb.phenolic.dtos.MoleculeRequest;
import pt.ipb.phenolic.dtos.PhenolicRequest;
import pt.ipb.phenolic.models.*;
import pt.ipb.phenolic.repos.LambdaRepository;
import pt.ipb.phenolic.repos.MSFragmentRepository;
import pt.ipb.phenolic.repos.MoleculeRepository;
import pt.ipb.phenolic.repos.PhenolicRepository;
import java.util.*;

@RestController
@RequestMapping("/phenolics")
public class PhenolicController {
    private final PhenolicRepository phenolicRepo;
    private final MoleculeRepository moleculeRepo;
    private final LambdaRepository lambdaRepo;
    private final MSFragmentRepository msFragmentRepo;


    public PhenolicController(
            PhenolicRepository phenolicRepo,
            MoleculeRepository moleculeRepo,
            LambdaRepository lambdaRepo,
            MSFragmentRepository msFragmentRepo
    ) {
        this.phenolicRepo = phenolicRepo;
        this.moleculeRepo = moleculeRepo;
        this.lambdaRepo = lambdaRepo;
        this.msFragmentRepo = msFragmentRepo;
    }

    @GetMapping
    public HashSet<PhenolicRequest> getPhenolics(@RequestParam(defaultValue = "all") String name){
        var listPhenolic = phenolicRepo.findAll();
        var listPhenolicRequest = new HashSet<PhenolicRequest>();
        if (name.equals("all")){
            listPhenolic.forEach((phenolic) -> {
                var converted = new PhenolicRequest();
                converted.setId(phenolic.getId());
                converted.setName(phenolic.getName());
                listPhenolicRequest.add(converted);
            });
        }else{
            listPhenolic.forEach((phenolic) -> {
                if (phenolic.getName().toUpperCase().contains(name.toUpperCase())){
                    var converted = new PhenolicRequest();
                    converted.setId(phenolic.getId());
                    converted.setName(phenolic.getName());
                    listPhenolicRequest.add(converted);
                }
            });
        }

        return listPhenolicRequest;
    }

    @GetMapping("/{id}")
    public PhenolicRequest showPhenolic(@PathVariable Long id) {
        var phenolicFind = phenolicRepo.findById(id);
        var phenolicRequest = new PhenolicRequest();
        if (phenolicFind.isPresent()){
            var phenolic = phenolicFind.get();
            phenolicRequest.setId(phenolic.getId());
            phenolicRequest.setName(phenolic.getName());
            var parent = new Relative();
            if (phenolic.getSource() != null){
                parent.setType("source");
                var parentValue = new HashSet<Value>();
                parentValue.add(new Value(phenolic.getSource().getId(),phenolic.getSource().getName()));
                parent.setValues(parentValue);
                phenolicRequest.setParent(parent);
            }
            else if (phenolic.getPhenolic() != null){
                parent.setType("phenolic");
                var parentValue = new HashSet<Value>();
                parentValue.add(new Value(phenolic.getPhenolic().getId(),phenolic.getPhenolic().getName()));
                parent.setValues(parentValue);
                phenolicRequest.setParent(parent);
            }

            var children = new Relative();
            if (!phenolic.getPhenolics().isEmpty()){
                children.setType("phenolic");
                var childrenValue = new HashSet<Value>();
                phenolic.getPhenolics().forEach((phenolicChild) -> {
                    childrenValue.add(new Value(phenolicChild.getId(),phenolicChild.getName()));
                });
                children.setValues(childrenValue);
                phenolicRequest.setChildren(children);
            }
            else if (!phenolic.getMolecules().isEmpty()){
                children.setType("molecule");
                var childrenValue = new HashSet<Value>();
                phenolic.getMolecules().forEach((moleculeChild) -> {
                    childrenValue.add(new Value(moleculeChild.getId(),moleculeChild.getName()));
                });
                children.setValues(childrenValue);
                phenolicRequest.setChildren(children);
            }
        }
        return phenolicRequest;
    }

    @PostMapping
    public PhenolicRequest store(@RequestBody PhenolicRequest phenolicRequest) {
        var phenolic = phenolicRepo.save(phenolicRequest.toEntity());
        var phenolicRequestReturn = new PhenolicRequest();
        phenolicRequestReturn.setId(phenolic.getId());
        phenolicRequestReturn.setName(phenolic.getName());
        return phenolicRequestReturn;
    }

    @PostMapping("/{id}/phenolics")
    @Transactional
    public PhenolicRequest storeAndLinkPhenolic(@PathVariable Long id, @RequestBody PhenolicRequest phenolicRequest) {
        var phenolicFind = phenolicRepo.findById(id);
        var phenolicRequestReturn = new PhenolicRequest();
        if (phenolicFind.isPresent()) {
            var phenolic = phenolicFind.get();
            if (phenolic.getMolecules().isEmpty()){
                var phenolicSaved = phenolicRepo.save(phenolicRequest.toEntity(phenolic));
                phenolicRequestReturn.setId(phenolic.getId());
                phenolicRequestReturn.setName(phenolic.getName());
                var children = new Relative("phenolic");
                var setValue = new HashSet<Value>();
                var value = new Value(phenolicSaved.getId(), phenolicSaved.getName());
                setValue.add(value);
                children.setValues(setValue);
                phenolicRequestReturn.setChildren(children);
            }
        }
        return phenolicRequestReturn;
    }

    @PostMapping("/{id}/molecules")
    @Transactional
    public PhenolicRequest storeAndLinkMolecule(@PathVariable Long id, @RequestBody MoleculeRequest moleculeRequest) {
        var phenolicFind = phenolicRepo.findById(id);
        var phenolicRequestReturn = new PhenolicRequest();
        if (phenolicFind.isPresent()) {
            var phenolic = phenolicFind.get();
            if (phenolic.getPhenolics().isEmpty()){
                var moleculeSaved = moleculeRepo.save(moleculeRequest.toEntity(phenolic));
                if (!moleculeRequest.getLambdas().isEmpty()){
                    moleculeRequest.getLambdas().forEach((lambda -> {
                        lambda.setMolecule(moleculeSaved);
                        lambdaRepo.save(lambda);
                    }));
                }

                if (!moleculeRequest.getMsFragments().isEmpty()){
                    moleculeRequest.getMsFragments().forEach((msFragment -> {
                        msFragment.setMolecule(moleculeSaved);
                        msFragmentRepo.save(msFragment);
                    }));
                }

                phenolicRequestReturn.setId(phenolic.getId());
                phenolicRequestReturn.setName(phenolic.getName());
                var children = new Relative("molecule");
                var setValue = new HashSet<Value>();
                var value = new Value(moleculeSaved.getId(),moleculeSaved.getName());
                setValue.add(value);
                children.setValues(setValue);
                phenolicRequestReturn.setChildren(children);
            }
        }
        return phenolicRequestReturn;
    }

    @PutMapping("/{id}")
    public PhenolicRequest updatePhenolic(@PathVariable Long id, @RequestBody Phenolic phenolicRequest) {
        var phenolicFind = phenolicRepo.findById(id);
        var phenolicRequestReturn = new PhenolicRequest();
        if (phenolicFind.isPresent()) {
            var phenolic = phenolicFind.get();
            phenolic.setName(phenolicRequest.getName());
            var phenolicResponse = phenolicRepo.save(phenolic);
            phenolicRequestReturn.setId(phenolicResponse.getId());
            phenolicRequestReturn.setName(phenolicResponse.getName());
        }
        return phenolicRequestReturn;
    }

    @PutMapping("/{id1}/phenolics/{id2}")
    public PhenolicRequest updatePhenolicParentPhenolic(@PathVariable("id1") Long id1,@PathVariable("id2") Long id2, @RequestParam(defaultValue = "add") String operation) {
        var phenolic1Find = phenolicRepo.findById(id1);
        var phenolic2Find = phenolicRepo.findById(id2);
        var phenolicRequestReturn = new PhenolicRequest();

        if (phenolic1Find.isPresent() && phenolic2Find.isPresent() && !id1.equals(id2)) {
            var phenolic1 = phenolic1Find.get();
            var phenolic2 = phenolic2Find.get();
            if (operation.equals("add")){
                if (phenolic2.getSource() == null && phenolic2.getPhenolic() == null){
                    // Need to verify if the phenolic2 that will be the son of phenolic1 is not parent of phenolic1
                    phenolic2.setPhenolic(phenolic1);
                    phenolicRepo.save(phenolic2);
                }
                phenolicRequestReturn.setId(phenolic1.getId());
                phenolicRequestReturn.setName(phenolic1.getName());
                var children = new Relative("phenolic");
                var setValue = new HashSet<Value>();
                var value = new Value(phenolic2.getId(),phenolic2.getName());
                setValue.add(value);
                children.setValues(setValue);
                phenolicRequestReturn.setChildren(children);
            } else if (operation.equals("remove")){
                if (phenolic2.getPhenolic() != null){
                    if (phenolic2.getPhenolic().getId().equals(phenolic1.getId())){
                        phenolic2.setPhenolic(null);
                        phenolicRepo.save(phenolic2);
                        phenolicRequestReturn.setId(phenolic1.getId());
                        phenolicRequestReturn.setName(phenolic1.getName());
                    }
                }
            }
        }
        return phenolicRequestReturn;
    }

    @PutMapping("/{id1}/molecules/{id2}")
    public PhenolicRequest updatePhenolicParentMolecule(@PathVariable("id1") Long id1,@PathVariable("id2") Long id2, @RequestParam(defaultValue = "add") String operation) {
        var phenolicFind = phenolicRepo.findById(id1);
        var moleculeFind = moleculeRepo.findById(id2);
        var phenolicRequestReturn = new PhenolicRequest();
        if (phenolicFind.isPresent() && moleculeFind.isPresent()) {
            var phenolic = phenolicFind.get();
            var molecule = moleculeFind.get();
            if (operation.equals("add")){
                if (molecule.getPhenolic() == null){
                    molecule.setPhenolic(phenolic);
                    moleculeRepo.save(molecule);
                    phenolicRequestReturn.setId(phenolic.getId());
                    phenolicRequestReturn.setName(phenolic.getName());
                    var children = new Relative("phenolic");
                    var setValue = new HashSet<Value>();
                    var value = new Value(molecule.getId(),molecule.getName());
                    setValue.add(value);
                    children.setValues(setValue);
                    phenolicRequestReturn.setChildren(children);
                }
            } else if (operation.equals("remove")){
                if (molecule.getPhenolic() != null){
                    if (molecule.getPhenolic().getId().equals(phenolic.getId())){
                        molecule.setPhenolic(null);
                        moleculeRepo.save(molecule);
                        phenolicRequestReturn.setId(phenolic.getId());
                        phenolicRequestReturn.setName(phenolic.getName());
                    }
                }
            }


        }
        return phenolicRequestReturn;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity destroy(@PathVariable Long id) {
        var phenolicFind = phenolicRepo.findById(id);
        if (!phenolicFind.isEmpty()){
            var phenolic = phenolicFind.get();
            if (!phenolic.getPhenolics().isEmpty()){
                phenolic.getPhenolics().forEach((phenolicS -> {
                    phenolicS.setPhenolic(null);
                    phenolicRepo.save(phenolicS);
                }));
            }
            if (!phenolic.getMolecules().isEmpty()){
                phenolic.getMolecules().forEach((molecule -> {
                    molecule.setPhenolic(null);
                    moleculeRepo.save(molecule);
                }));
            }
            phenolicRepo.deleteById(id);
            return new ResponseEntity("Success", HttpStatus.ACCEPTED);
        }else{
            return new ResponseEntity("Fail", HttpStatus.NO_CONTENT);
        }
    }

}
