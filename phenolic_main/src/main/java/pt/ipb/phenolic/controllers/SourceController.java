package pt.ipb.phenolic.controllers;


import org.springframework.web.bind.annotation.*;
import pt.ipb.phenolic.models.Phenolic;
import pt.ipb.phenolic.models.Source;
import pt.ipb.phenolic.repos.SourceRepository;

import java.util.List;


@RestController
@RequestMapping("/source")
public class SourceController {
    private final SourceRepository sourceRepo;

    public SourceController(SourceRepository sourceRepo) {
        this.sourceRepo = sourceRepo;
    }

    @GetMapping
    public List<Source> get() {
        return sourceRepo.findAll();
    }

    @PostMapping
    public Source post(@RequestBody Source source) {
        var phelonlics = source.getPhenolics();

        return sourceRepo.save(source);
    }

    @PutMapping("{id}")
    public Source put(@PathVariable Integer id,
                      @RequestBody Source source) {
        return sourceRepo.save(source);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Integer id) {
        sourceRepo.deleteById(id);
    }


}
