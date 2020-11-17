package pt.ipb.phenolic.controllers;


import org.springframework.web.bind.annotation.*;
import pt.ipb.phenolic.models.Phenolic;
import pt.ipb.phenolic.repos.PhenolicRepository;

import java.util.List;

@RestController
@RequestMapping("/phenolic")
public class PhenolicController {
    private final PhenolicRepository phenolicRepo;

    public PhenolicController(PhenolicRepository phenolicRepo) {
        this.phenolicRepo = phenolicRepo;
    }

    @GetMapping
    public List<Phenolic> get() {
        return phenolicRepo.findAll();
    }

    @PostMapping
    public Phenolic post(@RequestBody Phenolic phenolic) {
        return phenolicRepo.save(phenolic);
    }

    @PutMapping("{id}")
    public Phenolic put(@PathVariable Integer id, @RequestBody Phenolic phenolic) {
        return phenolicRepo.save(phenolic);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Integer id) {
        phenolicRepo.deleteById(id);
    }

}
