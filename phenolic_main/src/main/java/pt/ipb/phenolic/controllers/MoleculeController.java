package pt.ipb.phenolic.controllers;

import org.springframework.web.bind.annotation.*;
import pt.ipb.phenolic.models.Molecule;
import pt.ipb.phenolic.repos.MoleculeRepository;

import java.util.List;

@RestController
@RequestMapping("/molecules")
public class MoleculeController {

    private final MoleculeRepository moleculeRepo;

    public MoleculeController(MoleculeRepository moleculeRepo) {
        this.moleculeRepo = moleculeRepo;
    }

    @GetMapping
    public List<Molecule> get() {
        return moleculeRepo.findAll();
    }

    @PostMapping
    public Molecule post(@RequestBody Molecule molecule){
        return moleculeRepo.save(molecule);
    }
}
