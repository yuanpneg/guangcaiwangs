package bean;

import com.google.gson.annotations.Expose;

public class Category {

    private int id;

    @Expose
    private String title;
    private int parentid;
    private int sort;
    private String path;

    private String pathname;

    private int level;
    @Expose
    private String mappingId;

    // = 0 没有抓取，=-1抓取完毕.
    private Integer page = 0;

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

    public int getParentid() {
        return parentid;
    }

    public void setParentid(int parentid) {
        this.parentid = parentid;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPathname() {
        return pathname;
    }

    public void setPathname(String pathname) {
        this.pathname = pathname;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getMappingId() {
        return mappingId;
    }

    public void setMappingId(String mappingId) {
        this.mappingId = mappingId;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}
