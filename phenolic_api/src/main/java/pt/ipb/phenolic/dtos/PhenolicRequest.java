package pt.ipb.phenolic.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import pt.ipb.phenolic.models.Phenolic;
import pt.ipb.phenolic.models.Relative;
import pt.ipb.phenolic.models.Source;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PhenolicRequest {
    private Long id;

    private String name;

    private Relative parent;

    private Relative children;

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Relative getParent() {
        return parent;
    }

    public void setParent(Relative parent) {
        this.parent = parent;
    }

    public Relative getChildren() {
        return children;
    }

    public void setChildren(Relative children) {
        this.children = children;
    }

    public Phenolic toEntity(Source source){
        var phenolic = new Phenolic();
        phenolic.setSource(source);
        phenolic.setName(this.name);
        return phenolic;
    }

    public Phenolic toEntity(Phenolic phenolic){
        var phenolicAux = new Phenolic();
        phenolicAux.setPhenolic(phenolic);
        phenolicAux.setName(this.name);
        return phenolicAux;
    }

    public Phenolic toEntity() {
        var phenolic = new Phenolic();
        phenolic.setName(this.name);
        return phenolic;
    }

}
