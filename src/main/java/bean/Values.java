package bean;

import java.io.Serializable;

public class Values implements Serializable {

    private Integer id;
    private String unit_value;
    private Integer keyid;
    private Integer catid;


    public Values() {
    }

    public Values(Integer id, String unit_value, Integer keyid, Integer catid) {
        this.id = id;
        this.unit_value = unit_value;
        this.keyid = keyid;
        this.catid = catid;
    }

    public Values(String unit_value, Integer keyid, Integer catid) {
        this.unit_value = unit_value;
        this.keyid = keyid;
        this.catid = catid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUnit_value() {
        return unit_value;
    }

    public void setUnit_value(String unit_value) {
        this.unit_value = unit_value;
    }

    public Integer getKeyid() {
        return keyid;
    }

    public void setKeyid(Integer keyid) {
        this.keyid = keyid;
    }

    public Integer getCatid() {
        return catid;
    }

    public void setCatid(Integer catid) {
        this.catid = catid;
    }
}
