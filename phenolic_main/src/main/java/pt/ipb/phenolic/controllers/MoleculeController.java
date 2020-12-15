package pt.ipb.phenolic.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pt.ipb.phenolic.dtos.MoleculeRequest;
import pt.ipb.phenolic.models.Molecule;
import pt.ipb.phenolic.repos.LambdaRepository;
import pt.ipb.phenolic.repos.MSFragmentRepository;
import pt.ipb.phenolic.repos.MoleculeRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/molecules")
public class MoleculeController {

    private final MoleculeRepository moleculeRepo;
    private final LambdaRepository lambdaRepo;
    private final MSFragmentRepository msFragmentRepo;

    public MoleculeController(
            MoleculeRepository moleculeRepo,
            LambdaRepository lambdaRepo,
            MSFragmentRepository msFragmentRepo
    ) {
        this.moleculeRepo = moleculeRepo;
        this.lambdaRepo = lambdaRepo;
        this.msFragmentRepo = msFragmentRepo;
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
    @Transactional
    public Molecule store(@RequestBody MoleculeRequest moleculeRequest) {
        Molecule molecule = moleculeRepo.save(moleculeRequest.toEntity());

        moleculeRequest
                .getLambdas()
                .forEach((lambda) -> lambdaRepo.save(lambda.toEntity(molecule)));

        moleculeRequest
                .getMsFragments()
                .forEach((msFragment -> msFragmentRepo.save(msFragment.toEntity(molecule))));

        return molecule;
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
