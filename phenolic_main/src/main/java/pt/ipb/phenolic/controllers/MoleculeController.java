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
    public HashSet<MoleculeRequest> getMolecules(){
        var listMolecule = moleculeRepo.findAll();
        var listMoleculeRequest = new HashSet<MoleculeRequest>();
        listMolecule.forEach((molecule) -> {
            var converted = new MoleculeRequest();
            converted.setId(molecule.getId());
            converted.setName(molecule.getName());
            listMoleculeRequest.add(converted);
        });
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

            var children = new HashSet<Relative>();
            var childrenLambda = new Relative();
            var childrenMsFragment = new Relative();
            if (!molecule.getLambdas().isEmpty()){
                childrenLambda.setType("lambda");
                var childrenLambdaValue = new HashSet<Value>();
                molecule.getLambdas().forEach((moleculeChild) -> {
                    childrenLambdaValue.add(new Value(moleculeChild.getId(), moleculeChild.getWaveLength(), moleculeChild.getShoulder()));
                });
                childrenLambda.setValues(childrenLambdaValue);
                children.add(childrenLambda);
            }
            if (!molecule.getMsFragments().isEmpty()){
                childrenMsFragment.setType("msfragment");
                var childrenMsFragmentValue = new HashSet<Value>();
                molecule.getMsFragments().forEach((moleculeChil) -> {
                    childrenMsFragmentValue.add(new Value(moleculeChil.getId(), moleculeChil.getPercentage(), moleculeChil.getValue()));
                });
                childrenMsFragment.setValues(childrenMsFragmentValue);
                children.add(childrenMsFragment);
            }

            if (!children.isEmpty()){
                moleculeRequest.setChildren(children);
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
            var childrens = new HashSet<Relative>();
            var children = new Relative("lambda");
            var setValue = new HashSet<Value>();
            var value = new Value(lambdaSaved.getId());
            setValue.add(value);
            children.setValues(setValue);
            childrens.add(children);
            moleculeRequestReturn.setChildren(childrens);
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
            var setChildren = new HashSet<Relative>();
            var children = new Relative("msfragment");
            var setValue = new HashSet<Value>();
            var value = new Value(msFragmentSaved.getId());
            value.setPercentage(msFragmentRequest.getPercentage());
            value.setValue(msFragmentRequest.getValue());
            setValue.add(value);
            children.setValues(setValue);
            setChildren.add(children);
            moleculeRequestReturn.setChildren(setChildren);
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
            var moleculeResponse = moleculeRepo.save(molecule);
            moleculeRequestReturn.setId(moleculeResponse.getId());
            moleculeRequestReturn.setName(moleculeResponse.getName());
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
