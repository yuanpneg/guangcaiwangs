package bean;

public class Produce {

    private String pName;

    private String unit;

    private String price;

    private String priceTe;// 除税市场价

    private String dutySpan; // 税率

    private String typeName; // 品种

    private int typeId;

    private int categoryId;

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPriceTe() {
        return priceTe;
    }

    public void setPriceTe(String priceTe) {
        this.priceTe = priceTe;
    }

    public String getDutySpan() {
        return dutySpan;
    }

    public void setDutySpan(String dutySpan) {
        this.dutySpan = dutySpan;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public Produce() {
    }

    public Produce(String pName, String unit, String price, String priceTe, String dutySpan, String typeName, int typeId, int categoryId) {
        this.pName = pName;
        this.unit = unit;
        this.price = price;
        this.priceTe = priceTe;
        this.dutySpan = dutySpan;
        this.typeName = typeName;
        this.typeId = typeId;
        this.categoryId = categoryId;
    }
}
