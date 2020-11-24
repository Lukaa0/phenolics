package pt.ipb.phenolic.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class MoleculeLambda {

    @Id
    private Integer id;

    @Column
    private Integer waveLength;

    @Column
    private Boolean shoulder;

    @OneToOne
    private Molecule molecule;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
}
