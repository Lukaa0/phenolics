package pt.ipb.phenolic.models;

import javax.persistence.*;

@Entity
public class MSFragment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Molecule molecule;

    private Integer value;

    private Integer percentage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Molecule getMolecule() {
        return molecule;
    }

    public void setMolecule(Molecule molecule) {
        this.molecule = molecule;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }
}
