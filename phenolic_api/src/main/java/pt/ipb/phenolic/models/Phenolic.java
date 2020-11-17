package pt.ipb.phenolic.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Phenolic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @OneToMany
    private List<Molecule> molecules;

    public List<Molecule> getMolecules() {
        return molecules;
    }

    public void setMolecules(List<Molecule> molecules) {
        this.molecules = molecules;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
