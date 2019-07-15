package bean;

import java.io.Serializable;

public class Keys implements Serializable {

    private Integer id;
    private String unit_key;
    private Integer shopid;
    private String cattitle;


    public Keys() {
    }

    public Keys(Integer id, String unit_key, Integer shopid, String cattitle) {
        this.id = id;
        this.unit_key = unit_key;
        this.shopid = shopid;
        this.cattitle = cattitle;
    }

    public Keys(String unit_key, Integer shopid, String cattitle) {
        this.unit_key = unit_key;
        this.shopid = shopid;
        this.cattitle = cattitle;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUnit_key() {
        return unit_key;
    }

    public void setUnit_key(String unit_key) {
        this.unit_key = unit_key;
    }

    public Integer getShopid() {
        return shopid;
    }

    public void setShopid(Integer shopid) {
        this.shopid = shopid;
    }

    public String getCattitle() {
        return cattitle;
    }

    public void setCattitle(String cattitle) {
        this.cattitle = cattitle;
    }
}
