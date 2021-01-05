package pt.ipb.phenolic.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import pt.ipb.phenolic.models.*;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MoleculeRequest {
    private Long id;

    private String name;

    private Relative parent;

    private Set<Relative> children;

    private Set<Lambda> lambdas;

    private Set<MSFragment> msFragments;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Lambda> getLambdas() {
        return lambdas;
    }

    public void setLambdas(Set<Lambda> lambdas) {
        this.lambdas = lambdas;
    }

    public Set<MSFragment> getMsFragments() {
        return msFragments;
    }

    public void setMsFragments(Set<MSFragment> msFragments) {
        this.msFragments = msFragments;
    }

    public Relative getParent() {
        return parent;
    }

    public void setParent(Relative parent) {
        this.parent = parent;
    }

    public Set<Relative> getChildren() {
        return children;
    }

    public void setChildren(Set<Relative> children) {
        this.children = children;
    }

    public Integer getEspectroUV() {
        return espectroUV;
    }

    public void setEspectroUV(Integer espectroUV) {
        this.espectroUV = espectroUV;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getIonMPlus() {
        return ionMPlus;
    }

    public void setIonMPlus(Integer ionMPlus) {
        this.ionMPlus = ionMPlus;
    }

    public Integer getPseudoMHPlus() {
        return pseudoMHPlus;
    }

    public void setPseudoMHPlus(Integer pseudoMHPlus) {
        this.pseudoMHPlus = pseudoMHPlus;
    }

    public Integer getPseudoMHMinus() {
        return pseudoMHMinus;
    }

    public void setPseudoMHMinus(Integer pseudoMHMinus) {
        this.pseudoMHMinus = pseudoMHMinus;
    }

    public Integer getPseudoTwoMHPlus() {
        return pseudoTwoMHPlus;
    }

    public void setPseudoTwoMHPlus(Integer pseudoTwoMHPlus) {
        this.pseudoTwoMHPlus = pseudoTwoMHPlus;
    }

    public Integer getPseudoTwoMHMinus() {
        return pseudoTwoMHMinus;
    }

    public void setPseudoTwoMHMinus(Integer pseudoTwoMHMinus) {
        this.pseudoTwoMHMinus = pseudoTwoMHMinus;
    }

    public Integer getMultiChargedProductsMHTwoMinus() {
        return multiChargedProductsMHTwoMinus;
    }

    public void setMultiChargedProductsMHTwoMinus(Integer multiChargedProductsMHTwoMinus) {
        this.multiChargedProductsMHTwoMinus = multiChargedProductsMHTwoMinus;
    }

    public Integer getMultiChargedProductsMHThreeMinus() {
        return multiChargedProductsMHThreeMinus;
    }

    public void setMultiChargedProductsMHThreeMinus(Integer multiChargedProductsMHThreeMinus) {
        this.multiChargedProductsMHThreeMinus = multiChargedProductsMHThreeMinus;
    }

    public Integer getExactHighResolution() {
        return exactHighResolution;
    }

    public void setExactHighResolution(Integer exactHighResolution) {
        this.exactHighResolution = exactHighResolution;
    }

    public Integer getAducts() {
        return aducts;
    }

    public void setAducts(Integer aducts) {
        this.aducts = aducts;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getMethodology() {
        return methodology;
    }

    public void setMethodology(String methodology) {
        this.methodology = methodology;
    }

    public String getIonizationMethodology() {
        return ionizationMethodology;
    }

    public void setIonizationMethodology(String ionizationMethodology) {
        this.ionizationMethodology = ionizationMethodology;
    }

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    public String getSampleOrigin() {
        return sampleOrigin;
    }

    public void setSampleOrigin(String sampleOrigin) {
        this.sampleOrigin = sampleOrigin;
    }

    public String getSeasonOfCollection() {
        return seasonOfCollection;
    }

    public void setSeasonOfCollection(String seasonOfCollection) {
        this.seasonOfCollection = seasonOfCollection;
    }

    public String getPlantPart() {
        return plantPart;
    }

    public void setPlantPart(String plantPart) {
        this.plantPart = plantPart;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Molecule toEntity(Phenolic phenolic) {
        var molecule = new Molecule();
        molecule.setPhenolic(phenolic);
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
