package pt.ipb.phenolic.dtos;

import pt.ipb.phenolic.models.MSFragment;
import pt.ipb.phenolic.models.Molecule;

public class MSFragmentRequest {

    private Integer value;
    private Integer percentage;

    public Integer getValue() {
        return value;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public MSFragment toEntity(Molecule molecule) {
        var msFragment = new MSFragment();
        msFragment.setMolecule(molecule);
        msFragment.setPercentage(this.percentage);
        msFragment.setValue(this.value);
        return msFragment;
    }
}
