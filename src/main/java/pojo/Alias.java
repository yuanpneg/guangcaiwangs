package pojo;

import java.io.Serializable;

/*
抓取详情的实体类
 */
public class Alias implements Serializable {
    private String id;
    private String title;
    private int titid;
    private String alias;
    private String unit;
    private String company;
    private String telephone;
    private String brand;
    private String parameters;
    private String contacts;
    private String address;
    //标记
    private int flag;
    private String hsscjprice; //含税市场价
    private String hsmjprice;  //含税面价
    private String sl;  //税率
    private String date;
    private String PinZhongId; //品种id
    private String pinzhong; //品种
    private String url; //

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParameters() {
        return parameters;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPinzhong() {
        return pinzhong;
    }

    public void setPinzhong(String pinzhong) {
        this.pinzhong = pinzhong;
    }

    public int getTitid() {
        return titid;
    }

    public void setTitid(int titid) {
        this.titid = titid;
    }

    public String getPinZhongId() {
        return PinZhongId;
    }

    public void setPinZhongId(String pinZhongId) {
        PinZhongId = pinZhongId;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getParameters(String parameters) {
        return this.parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getHsscjprice() {
        return hsscjprice;
    }

    public void setHsscjprice(String hsscjprice) {
        this.hsscjprice = hsscjprice;
    }

    public String getHsmjprice() {
        return hsmjprice;
    }

    public void setHsmjprice(String hsmjprice) {
        this.hsmjprice = hsmjprice;
    }

    public String getSl() {
        return sl;
    }

    public void setSl(String sl) {
        this.sl = sl;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
