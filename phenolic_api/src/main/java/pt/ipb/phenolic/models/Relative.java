package pt.ipb.phenolic.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import pt.ipb.phenolic.dtos.LambdaRequest;
import pt.ipb.phenolic.dtos.MSFragmentRequest;

import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Relative {
    private String type;

    private Set<Value> values;

    private Set<MSFragmentRequest> msfragments;

    private Set<LambdaRequest> lambdas;

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

    public Relative (String type, Set<Value> values){
        this.type = type;
        this.values = values;
    }

    public Set<MSFragmentRequest> getMsfragments() {
        return msfragments;
    }

    public void setMsfragments(Set<MSFragmentRequest> msfragments) {
        this.msfragments = msfragments;
    }

    public Set<LambdaRequest> getLambdas() {
        return lambdas;
    }

    public void setLambdas(Set<LambdaRequest> lambdas) {
        this.lambdas = lambdas;
    }

    public Relative (){}

    public Relative (String type){
        this.type = type;
    }

}
