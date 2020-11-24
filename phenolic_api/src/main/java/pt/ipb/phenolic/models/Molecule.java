package pt.ipb.phenolic.models;

import javax.persistence.*;
import java.util.Set;
import javax.validation.constraints.NotBlank;

@Entity
public class Molecule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "phenolic_id", nullable = false)
    private Phenolic phenolic;

    @NotBlank
    private String name;

    @OneToMany(mappedBy = "molecule")
    private Set<MoleculeLambda> lambdas;

    @ManyToMany(mappedBy = "molecules")
    private Set<Food> foods;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<MoleculeLambda> getLambdas() {
        return lambdas;
    }

    public void setLambdas(Set<MoleculeLambda> lambdas) {
        this.lambdas = lambdas;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Food> getFoods() {
        return foods;
    }

    public void setFoods(Set<Food> foods) {
        this.foods = foods;
    }
}
