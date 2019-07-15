package pojo;

/**
 * 品种类
 */
public class Variety {

    private String id;
    private String name;
    private String url;
    private int titid;
    private String path;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getTitid() {
        return titid;
    }

    public void setTitid(int titid) {
        this.titid = titid;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
