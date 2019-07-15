package bean;

public class Attribute {
    private int id;
    private String title;
    private int catid;
    private String vals;
    private int sort;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCatid() {
        return catid;
    }

    public void setCatid(int catid) {
        this.catid = catid;
    }

    public String getVals() {
        return vals;
    }

    public void setVals(String vals) {
        this.vals = vals;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public Attribute(String title, int catid, String vals, int sort) {
        this.title = title;
        this.catid = catid;
        this.vals = vals;
        this.sort = sort;
    }

}
