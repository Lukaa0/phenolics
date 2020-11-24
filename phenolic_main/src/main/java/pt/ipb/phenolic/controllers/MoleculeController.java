package pt.ipb.phenolic.controllers;

import org.springframework.http.ResponseEntity;
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

    @GetMapping("/{id}")
    public ResponseEntity<Molecule> show(@PathVariable Long id) {
        return moleculeRepo
                .findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Molecule store(@RequestBody Molecule molecule) {
        return moleculeRepo.save(molecule);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Molecule> update(@PathVariable Long id, @RequestBody Molecule moleculeDto) {
        var molecule = moleculeRepo.findById(id);
        if (molecule.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(moleculeRepo.save(moleculeDto));
    }

    @DeleteMapping("/{id}")
    public void destroy(@PathVariable Long id) {
        moleculeRepo.deleteById(id);
    }
}
