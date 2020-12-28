package pt.ipb.phenolic.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Phenolic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "source_id")
    @JsonIgnore
    private Source source;

    @ManyToOne
    @JoinColumn(name = "phenolic_id")
    @JsonIgnore
    private Phenolic phenolic;

    @OneToMany(mappedBy = "phenolic")
    private Set<Phenolic> phenolics;

    @OneToMany(mappedBy = "phenolic")
    private Set<Molecule> molecules;

    @NotBlank
    private String name;

    public Phenolic() {
    }

    public Phenolic(Phenolic phenolic, @NotBlank String name) {
        this.phenolic = phenolic;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Molecule> getMolecules() {
        return molecules;
    }

    public void setMolecules(Set<Molecule> molecules) {
        this.molecules = molecules;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Phenolic> getPhenolics() {
        return phenolics;
    }

    public void setPhenolics(Set<Phenolic> phenolics) {
        this.phenolics = phenolics;
    }

    public Phenolic getPhenolic() {
        return phenolic;
    }

    public void setPhenolic(Phenolic phenolic) {
        this.phenolic = phenolic;
    }
}
