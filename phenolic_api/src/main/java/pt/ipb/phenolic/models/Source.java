package pt.ipb.phenolic.models;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Source {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToMany(mappedBy = "source")
    private Set<Phenolic> phenolics;

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

    public Set<Phenolic> getPhenolics() {
        return phenolics;
    }

    public void setPhenolics(Set<Phenolic> phenolics) {
        this.phenolics = phenolics;
    }
}
