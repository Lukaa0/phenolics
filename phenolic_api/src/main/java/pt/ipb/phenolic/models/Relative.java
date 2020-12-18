package pt.ipb.phenolic.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Relative {
    private String type;

    private Set<Value> values;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<Value> getValues() {
        return values;
    }

    public void setValues(Set<Value> values) {
        this.values = values;
    }

    public Relative (){}

    public Relative (String type){
        this.type = type;
    }


    public Relative (String type, Set<Value> values){
        this.type = type;
        this.values = values;
    }
}
