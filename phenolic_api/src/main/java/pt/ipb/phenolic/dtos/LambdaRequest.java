package pt.ipb.phenolic.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import pt.ipb.phenolic.models.Lambda;
import pt.ipb.phenolic.models.Molecule;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LambdaRequest {
    private Long id;

    private Integer waveLength;

    private Boolean shoulder;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getWaveLength() {
        return waveLength;
    }

    public void setWaveLength(Integer waveLength) {
        this.waveLength = waveLength;
    }

    public Boolean getShoulder() {
        return shoulder;
    }

    public void setShoulder(Boolean shoulder) {
        this.shoulder = shoulder;
    }

    public Lambda toEntity(Molecule molecule) {

        var lambda = new Lambda();
        lambda.setMolecule(molecule);
        lambda.setShoulder(this.shoulder);
        lambda.setWaveLength(this.waveLength);
        return lambda;
    }
}
