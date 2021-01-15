package pt.ipb.phenolic.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import pt.ipb.phenolic.models.MSFragment;
import pt.ipb.phenolic.models.Molecule;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MSFragmentRequest {
    private Long id;

    private Integer value;

    private Integer percentage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public MSFragment toEntity(Molecule molecule) {
        var msFragment = new MSFragment();
        msFragment.setMolecule(molecule);
        msFragment.setPercentage(this.percentage);
        msFragment.setValue(this.value);
        return msFragment;
    }

    public MSFragmentRequest() {}

    public MSFragmentRequest(Long id, Integer value, Integer percentage) {
        this.id = id;
        this.percentage = percentage;
        this.value = value;
    }
}
