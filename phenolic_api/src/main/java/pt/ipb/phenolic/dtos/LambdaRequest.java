package pt.ipb.phenolic.dtos;

import pt.ipb.phenolic.models.Lambda;
import pt.ipb.phenolic.models.Molecule;

public class LambdaRequest {

    private Integer waveLength;
    private Boolean shoulder;

    public Integer getWaveLength() {
        return waveLength;
    }

    public Boolean getShoulder() {
        return shoulder;
    }

    public Lambda toEntity(Molecule molecule) {
        var lambda = new Lambda();
        lambda.setMolecule(molecule);
        lambda.setShoulder(this.shoulder);
        lambda.setWaveLength(this.waveLength);
        return lambda;
    }
}
