package pt.ipb.phenolic.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Molecule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "phenolic_id", nullable = false)
    private Phenolic phenolic;


    @ManyToMany(mappedBy = "molecules")
    private Set<Food> foods;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Set<Food> getFoods() {
        return foods;
    }

    public void setFoods(Set<Food> foods) {
        this.foods = foods;
    }
}
