package pt.ipb.phenolic.dtos;

import pt.ipb.phenolic.models.Molecule;

import java.util.Set;

public class MoleculeRequest {
    private String name;

    private Set<LambdaRequest> lambdas;

    private Set<MSFragmentRequest> msFragments;

    private Integer espectroUV;

    private Integer weight;

    private Integer ionMPlus;

    private Integer pseudoMHPlus;

    private Integer pseudoMHMinus;

    private Integer pseudoTwoMHPlus;

    private Integer pseudoTwoMHMinus;

    private Integer multiChargedProductsMHTwoMinus;

    private Integer multiChargedProductsMHThreeMinus;

    private Integer exactHighResolution;

    private Integer aducts;

    private String equipment;

    private String methodology;

    private String ionizationMethodology;

    private String variety;

    private String sampleOrigin;

    private String seasonOfCollection;

    private String plantPart;

    private String reference;

    public String getName() {
        return name;
    }

    public Set<LambdaRequest> getLambdas() {
        return lambdas;
    }

    public Set<MSFragmentRequest> getMsFragments() {
        return msFragments;
    }

    public Integer getEspectroUV() {
        return espectroUV;
    }

    public Integer getWeight() {
        return weight;
    }

    public Integer getIonMPlus() {
        return ionMPlus;
    }

    public Integer getPseudoMHPlus() {
        return pseudoMHPlus;
    }

    public Integer getPseudoMHMinus() {
        return pseudoMHMinus;
    }

    public Integer getPseudoTwoMHPlus() {
        return pseudoTwoMHPlus;
    }

    public Integer getPseudoTwoMHMinus() {
        return pseudoTwoMHMinus;
    }

    public Integer getMultiChargedProductsMHTwoMinus() {
        return multiChargedProductsMHTwoMinus;
    }

    public Integer getMultiChargedProductsMHThreeMinus() {
        return multiChargedProductsMHThreeMinus;
    }

    public Integer getExactHighResolution() {
        return exactHighResolution;
    }

    public Integer getAducts() {
        return aducts;
    }

    public String getEquipment() {
        return equipment;
    }

    public String getMethodology() {
        return methodology;
    }

    public String getIonizationMethodology() {
        return ionizationMethodology;
    }

    public String getVariety() {
        return variety;
    }

    public String getSampleOrigin() {
        return sampleOrigin;
    }

    public String getSeasonOfCollection() {
        return seasonOfCollection;
    }

    public String getPlantPart() {
        return plantPart;
    }

    public String getReference() {
        return reference;
    }

    public Molecule toEntity() {
        var molecule = new Molecule();
        molecule.setName(this.name);
        molecule.setAducts(this.aducts);
        molecule.setEquipment(this.equipment);
        molecule.setEspectroUV(this.espectroUV);
        molecule.setExactHighResolution(this.exactHighResolution);
        molecule.setIonizationMethodology(this.ionizationMethodology);
        molecule.setIonMPlus(this.ionMPlus);
        molecule.setMethodology(this.methodology);
        molecule.setMultiChargedProductsMHThreeMinus(this.multiChargedProductsMHThreeMinus);
        molecule.setMultiChargedProductsMHTwoMinus(this.multiChargedProductsMHTwoMinus);
        molecule.setPlantPart(this.plantPart);
        molecule.setPseudoMHMinus(this.pseudoMHMinus);
        molecule.setPseudoMHPlus(this.pseudoMHPlus);
        molecule.setPseudoTwoMHMinus(this.pseudoTwoMHMinus);
        molecule.setPseudoTwoMHPlus(this.pseudoTwoMHPlus);
        molecule.setReference(this.reference);
        molecule.setSampleOrigin(this.sampleOrigin);
        molecule.setSeasonOfCollection(this.seasonOfCollection);
        molecule.setVariety(this.variety);
        molecule.setWeight(this.weight);
        return molecule;
    }
}
