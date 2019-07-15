package pojo;

//数据库里面的品种的分类表
public class Noneunit {
    private Integer id;
    private String title;
    private Integer parentid;
    private Integer sort;
    private String path;
    private Integer level;
    private String mappingId;
    private Integer page;
    private String pathname;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
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

    public String getPathname() {
        return pathname;
    }

    public void setPathname(String pathname) {
        this.pathname = pathname;
    }
}
