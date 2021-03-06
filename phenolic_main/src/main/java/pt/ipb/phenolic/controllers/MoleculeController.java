package pt.ipb.phenolic.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pt.ipb.phenolic.dtos.*;
import pt.ipb.phenolic.models.*;
import pt.ipb.phenolic.repos.LambdaRepository;
import pt.ipb.phenolic.repos.MSFragmentRepository;
import pt.ipb.phenolic.repos.MoleculeRepository;
import pt.ipb.phenolic.repos.PhenolicRepository;
import java.util.HashSet;

@RestController
@RequestMapping("/molecules")
public class MoleculeController {

    private final PhenolicRepository phenolicRepo;
    private final MoleculeRepository moleculeRepo;
    private final LambdaRepository lambdaRepo;
    private final MSFragmentRepository msFragmentRepo;

    public MoleculeController(
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
    public HashSet<MoleculeRequest> getMolecules(@RequestParam(defaultValue = "all") String name){
        var listMolecule = moleculeRepo.findAll();
        var listMoleculeRequest = new HashSet<MoleculeRequest>();
        if (name.equals("all")){
            listMolecule.forEach((molecule) -> {
                var converted = new MoleculeRequest();
                converted.setId(molecule.getId());
                converted.setName(molecule.getName());
                listMoleculeRequest.add(converted);
            });
        }else{
            listMolecule.forEach((molecule) -> {
                if (molecule.getName().toUpperCase().contains(name.toUpperCase())) {
                    var converted = new MoleculeRequest();
                    converted.setId(molecule.getId());
                    converted.setName(molecule.getName());
                    listMoleculeRequest.add(converted);
                }
            });
        }

        return listMoleculeRequest;
    }



    @GetMapping("/{id}")
    public MoleculeRequest showMolecule(@PathVariable Long id) {
        var moleculeFind = moleculeRepo.findById(id);
        var moleculeRequest = new MoleculeRequest();
        if (moleculeFind.isPresent()){
            var molecule = moleculeFind.get();
            moleculeRequest.setId(molecule.getId());
            moleculeRequest.setName(molecule.getName());
            moleculeRequest.setAducts(molecule.getAducts());
            moleculeRequest.setEquipment(molecule.getEquipment());
            moleculeRequest.setEspectroUV(molecule.getEspectroUV());
            moleculeRequest.setExactHighResolution(molecule.getExactHighResolution());
            moleculeRequest.setIonizationMethodology(molecule.getIonizationMethodology());
            moleculeRequest.setMethodology(molecule.getMethodology());
            moleculeRequest.setWeight(molecule.getWeight());
            moleculeRequest.setIonMPlus(molecule.getIonMPlus());
            moleculeRequest.setPseudoMHMinus(molecule.getPseudoMHMinus());
            moleculeRequest.setPseudoMHPlus(molecule.getPseudoMHPlus());
            moleculeRequest.setPseudoTwoMHMinus(molecule.getPseudoTwoMHMinus());
            moleculeRequest.setPseudoTwoMHPlus(molecule.getPseudoTwoMHPlus());
            moleculeRequest.setMultiChargedProductsMHThreeMinus(molecule.getMultiChargedProductsMHThreeMinus());
            moleculeRequest.setMultiChargedProductsMHTwoMinus(molecule.getMultiChargedProductsMHTwoMinus());
            moleculeRequest.setVariety(molecule.getVariety());
            moleculeRequest.setSampleOrigin(molecule.getSampleOrigin());
            moleculeRequest.setSeasonOfCollection(molecule.getSeasonOfCollection());
            moleculeRequest.setPlantPart(molecule.getPlantPart());
            moleculeRequest.setReference(molecule.getReference());

            var parent = new Relative();
            if (molecule.getPhenolic() != null){
                parent.setType("phenolic");
                var parentValue = new HashSet<Value>();
                parentValue.add(new Value(molecule.getPhenolic().getId(),molecule.getPhenolic().getName()));
                parent.setValues(parentValue);
                moleculeRequest.setParent(parent);
            }

            var details = new Relative();
            var detailsLambda = new HashSet<LambdaRequest>();
            var detailsMsFragment = new HashSet<MSFragmentRequest>();
            if (!molecule.getLambdas().isEmpty()){
                molecule.getLambdas().forEach((moleculeLambda) -> {
                    detailsLambda.add(new LambdaRequest(moleculeLambda.getId(), moleculeLambda.getWaveLength(), moleculeLambda.getShoulder()));
                });
                details.setLambdas(detailsLambda);
                moleculeRequest.setDetails(details);
            }
            if (!molecule.getMsFragments().isEmpty()){
                molecule.getMsFragments().forEach((moleculeMsfragment) -> {
                    detailsMsFragment.add(new MSFragmentRequest(moleculeMsfragment.getId(), moleculeMsfragment.getValue(), moleculeMsfragment.getPercentage()));
                });
                details.setMsfragments(detailsMsFragment);
                moleculeRequest.setDetails(details);
            }


        }
        return moleculeRequest;
    }

    @PostMapping
    public MoleculeRequest storeMolecule(@RequestBody MoleculeRequest moleculeRequest) {
        var molecule = moleculeRepo.save(moleculeRequest.toEntity());
        var moleculeRequestReturn = new MoleculeRequest();
        moleculeRequestReturn.setId(molecule.getId());
        moleculeRequestReturn.setName(molecule.getName());
        return moleculeRequestReturn;
    }

    @PostMapping("/{id}/lambdas")
    @Transactional
    public MoleculeRequest storeAndLinkLambda(@PathVariable Long id, @RequestBody LambdaRequest lambdaRequest) {
        var moleculeFind = moleculeRepo.findById(id);
        var moleculeRequestReturn = new MoleculeRequest();
        if (moleculeFind.isPresent()) {
            var molecule = moleculeFind.get();
            var lambdaSaved = lambdaRepo.save(lambdaRequest.toEntity(molecule));

            moleculeRequestReturn.setId(molecule.getId());
            moleculeRequestReturn.setName(molecule.getName());
            var details = new Relative();
            var detailsLambda = new HashSet<LambdaRequest>();
            detailsLambda.add(new LambdaRequest(lambdaSaved.getId(), lambdaSaved.getWaveLength(), lambdaSaved.getShoulder()));
            details.setLambdas(detailsLambda);
            moleculeRequestReturn.setDetails(details);
        }
        return moleculeRequestReturn;
    }

    @PostMapping("/{id}/msfragments")
    @Transactional
    public MoleculeRequest storeAndLinkMsFragment(@PathVariable Long id, @RequestBody MSFragmentRequest msFragmentRequest) {
        var moleculeFind = moleculeRepo.findById(id);
        var moleculeRequestReturn = new MoleculeRequest();
        if (moleculeFind.isPresent()) {
            var molecule = moleculeFind.get();
            var msFragmentSaved = msFragmentRepo.save(msFragmentRequest.toEntity(molecule));

            moleculeRequestReturn.setId(molecule.getId());
            moleculeRequestReturn.setName(molecule.getName());
            var details = new Relative();
            var detailsMsfragment = new HashSet<MSFragmentRequest>();
            detailsMsfragment.add(new MSFragmentRequest(msFragmentSaved.getId(), msFragmentSaved.getValue(), msFragmentSaved.getPercentage()));
            details.setMsfragments(detailsMsfragment);
            moleculeRequestReturn.setDetails(details);
        }
        return moleculeRequestReturn;
    }



    @PutMapping("/{id}")
    public MoleculeRequest updateMolecule(@PathVariable Long id, @RequestBody MoleculeRequest moleculeRequest) {
        var moleculeFind = moleculeRepo.findById(id);
        var moleculeRequestReturn = new MoleculeRequest();
        if (moleculeFind.isPresent()) {
            var molecule = moleculeFind.get();
            molecule.setName(moleculeRequest.getName());
            molecule.setName(moleculeRequest.getName());
            molecule.setAducts(moleculeRequest.getAducts());
            molecule.setEquipment(moleculeRequest.getEquipment());
            molecule.setEspectroUV(moleculeRequest.getEspectroUV());
            molecule.setExactHighResolution(moleculeRequest.getExactHighResolution());
            molecule.setIonizationMethodology(moleculeRequest.getIonizationMethodology());
            molecule.setMethodology(moleculeRequest.getMethodology());
            molecule.setWeight(moleculeRequest.getWeight());
            molecule.setIonMPlus(moleculeRequest.getIonMPlus());
            molecule.setPseudoMHMinus(moleculeRequest.getPseudoMHMinus());
            molecule.setPseudoMHPlus(moleculeRequest.getPseudoMHPlus());
            molecule.setPseudoTwoMHMinus(moleculeRequest.getPseudoTwoMHMinus());
            molecule.setPseudoTwoMHPlus(moleculeRequest.getPseudoTwoMHPlus());
            molecule.setMultiChargedProductsMHThreeMinus(moleculeRequest.getMultiChargedProductsMHThreeMinus());
            molecule.setMultiChargedProductsMHTwoMinus(moleculeRequest.getMultiChargedProductsMHTwoMinus());
            molecule.setVariety(moleculeRequest.getVariety());
            molecule.setSampleOrigin(moleculeRequest.getSampleOrigin());
            molecule.setSeasonOfCollection(moleculeRequest.getSeasonOfCollection());
            molecule.setPlantPart(moleculeRequest.getPlantPart());
            molecule.setReference(moleculeRequest.getReference());
            var moleculeResponse = moleculeRepo.save(molecule);
            moleculeRequestReturn.setId(moleculeResponse.getId());
            moleculeRequestReturn.setName(moleculeResponse.getName());
        }
        return moleculeRequestReturn;
    }


    @PutMapping("/{id1}/lambdas/{id2}")
    @Transactional
    public MoleculeRequest removeLambda(@PathVariable("id1") Long id1,@PathVariable("id2") Long id2){
        var moleculeFind = moleculeRepo.findById(id1);
        var lambdaFind = lambdaRepo.findById(id2);
        var moleculeRequestReturn = new MoleculeRequest();
        if (moleculeFind.isPresent() && lambdaFind.isPresent()) {
            var molecule = moleculeFind.get();
            var lamba = lambdaFind.get();
            if (lamba.getMolecule() != null){
                if (lamba.getMolecule().getId().equals(molecule.getId())){
                    lambdaRepo.deleteById(id2);
                    moleculeRequestReturn.setId(molecule.getId());
                    moleculeRequestReturn.setName(molecule.getName());
                }

            }
        }
        return moleculeRequestReturn;
    }


    @PutMapping("/{id1}/msfragments/{id2}")
    @Transactional
    public MoleculeRequest removeMsfregment(@PathVariable("id1") Long id1,@PathVariable("id2") Long id2){
        var moleculeFind = moleculeRepo.findById(id1);
        var msfragmentFind = msFragmentRepo.findById(id2);
        var moleculeRequestReturn = new MoleculeRequest();
        if (moleculeFind.isPresent() && msfragmentFind.isPresent()) {
            var molecule = moleculeFind.get();
            var msfragment = msfragmentFind.get();
            if (msfragment.getMolecule() != null){
                if (msfragment.getMolecule().getId().equals(molecule.getId())){
                    msFragmentRepo.deleteById(id2);
                    moleculeRequestReturn.setId(molecule.getId());
                    moleculeRequestReturn.setName(molecule.getName());
                }
            }
        }
        return moleculeRequestReturn;
    }


    @DeleteMapping("/{id}")
    public ResponseEntity destroy(@PathVariable Long id) {
        var moleculeFind = moleculeRepo.findById(id);
        if (!moleculeFind.isEmpty()){
            moleculeRepo.deleteById(id);
            return new ResponseEntity("Success", HttpStatus.ACCEPTED);
        }else{
            return new ResponseEntity("Fail", HttpStatus.NO_CONTENT);
        }
    }
}
