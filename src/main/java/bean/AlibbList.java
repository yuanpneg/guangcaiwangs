package bean;

public class AlibbList {
    private int id ;
    private String title ;
    private String url ;
    private String cate ;
    private String cateid;

    public AlibbList() {
    }

    public AlibbList(int id, String title, String url, String cate) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.cate = cate;
    }

    public AlibbList(String title, String url, String cate, String cateid) {
        this.title = title;
        this.url = url;
        this.cate = cate;
        this.cateid = cateid;
    }

    public AlibbList(String title, String url, String cate) {
        this.title = title;
        this.url = url;
        this.cate = cate;
    }

    public AlibbList(int id, String title, String url, String cate, String cateid) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.cate = cate;
        this.cateid = cateid;
    }

    public String getCateid() {
        return cateid;
    }

    public void setCateid(String cateid) {
        this.cateid = cateid;
    }

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCate() {
        return cate;
    }

    public void setCate(String cate) {
        this.cate = cate;
    }
}
