package pt.ipb.phenolic.models;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Value {
    private Long id;

    private String name;

    private Integer waveLength;

    private Boolean shoulder;

    private Integer value;

    private Integer percentage;

    public Value(Long id, Integer percentage, Integer value) {
        this.id = id;
        this.percentage = percentage;
        this.value = value;
    }

    public Value(Long id, Integer waveLength, Boolean shoulder) {
        this.id = id;
        this.waveLength = waveLength;
        this.shoulder = shoulder;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Value (Long id){
        this.id = id;
    }

    public Value (Long id, String name){
        this.id = id;
        this.name = name;
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
}
