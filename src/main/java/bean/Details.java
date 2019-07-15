package bean;


/*
查询数据的封装类
 */
public class Details {
    private int id;
    private String catid;
    private String cattitle;
    private String title;
    private String price;
    private String company;
    private String model;
    private String country;
    private String telephone;
    private String phone;
    private String pics;
    private String unit;
    private String address;
    private String parameter;
    private String paramattr;
    private String alibburl;
    private String kv;
    private String details;
    private String detail_pics;

    public Details() {
    }
    public Details(int id, String catid, String cattitle, String title, String price, String company,
                   String model, String country, String telephone, String pics, String unit,
                   String address, String paramattr, String parameter, String alibburl, String kv,
                   String details, String detail_pics) {
        this.id=id;
        this.catid = catid;
        this.cattitle = cattitle;
        this.title = title;
        this.price = price;
        this.company = company;
        this.model = model;
        this.country = country;
        this.telephone = telephone;
        //this.phone = phone;
        this.pics = pics;
        this.unit = unit;
        this.address = address;
        this.parameter = parameter;
        this.paramattr = paramattr;
        this.alibburl = alibburl;
        this.kv = kv;
        this.details = details;
        this.detail_pics=detail_pics;
    }

    public Details(String catid, String cattitle, String title, String price, String company,
                   String model, String country, String telephone, String pics, String unit,
                   String address, String paramattr, String parameter, String alibburl, String kv,
                   String details, String detail_pics) {
        this.catid = catid;
        this.cattitle = cattitle;
        this.title = title;
        this.price = price;
        this.company = company;
        this.model = model;
        this.country = country;
        this.telephone = telephone;
        //this.phone = phone;
        this.pics = pics;
        this.unit = unit;
        this.address = address;
        this.parameter = parameter;
        this.paramattr = paramattr;
        this.alibburl = alibburl;
        this.kv = kv;
        this.details = details;
        this.detail_pics=detail_pics;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getParamattr() {
        return paramattr;
    }

    public void setParamattr(String paramattr) {
        this.paramattr = paramattr;
    }

    public String getAlibburl() {
        return alibburl;
    }

    public void setAlibburl(String alibburl) {
        this.alibburl = alibburl;
    }

    public String getKv() {
        return kv;
    }

    public void setKv(String kv) {
        this.kv = kv;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDetail_pics() {
        return detail_pics;
    }

    public void setDetail_pics(String detail_pics) {
        this.detail_pics = detail_pics;
    }

    public int getId() {
        return id;
    }

    public Details setId(int id) {
        this.id = id;
        return this;
    }

    public String getCatid() {
        return catid;
    }

    public Details setCatid(String catid) {
        this.catid = catid;
        return this;
    }

    public String getCattitle() {
        return cattitle;
    }

    public Details setCattitle(String cattitle) {
        this.cattitle = cattitle;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Details setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getPrice() {
        return price;
    }

    public Details setPrice(String price) {
        this.price = price;
        return this;
    }

    public String getCompany() {
        return company;
    }

    public Details setCompany(String company) {
        this.company = company;
        return this;
    }

    public String getModel() {
        return model;
    }

    public Details setModel(String model) {
        this.model = model;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public Details setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getTelephone() {
        return telephone;
    }

    public Details setTelephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Details setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getPics() {
        return pics;
    }

    public Details setPics(String pics) {
        this.pics = pics;
        return this;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
