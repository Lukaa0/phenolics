package pt.ipb.phenolic.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Source {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @OneToMany
    private List<Phenolic> phenolics;

    public List<Phenolic> getPhenolics() {
        return phenolics;
    }

    public void setPhenolics(List<Phenolic> phenolics) {
        this.phenolics = phenolics;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
