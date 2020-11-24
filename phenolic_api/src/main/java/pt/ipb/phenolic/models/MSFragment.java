package pt.ipb.phenolic.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class MSFragment {

    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Molecule molecule;

    private Integer value;

    private Integer percentage;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
