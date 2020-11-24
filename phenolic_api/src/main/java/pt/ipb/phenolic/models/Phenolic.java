package pt.ipb.phenolic.models;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Phenolic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "source_id", nullable=false)
    private Source source;

    @OneToMany(mappedBy = "phenolic")
    private Set<Molecule> molecules;

    @NotNull
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
