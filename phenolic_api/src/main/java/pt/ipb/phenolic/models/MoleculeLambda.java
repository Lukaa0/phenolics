package pt.ipb.phenolic.models;

import javax.persistence.*;

@Entity
public class MoleculeLambda {

    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Molecule molecule;

    @Column
    private Integer waveLength;

    @Column
    private Boolean shoulder;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Molecule getMolecule() {
        return molecule;
    }

    public void setMolecule(Molecule molecule) {
        this.molecule = molecule;
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
