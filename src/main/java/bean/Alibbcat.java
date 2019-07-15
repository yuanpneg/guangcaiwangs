package bean;
/*
用于从数据库读取出来的level=3的数据用的
 */
public class Alibbcat {

    private int id;
    private String alibbcattitle;
    private String alibbcatid;
    private String alibbcatUrl;
    private int level;
    private int aliparentid;
    private int parentid;

    public Alibbcat() {
    }

    public Alibbcat(int id, String alibbcattitle) {
        this.id = id;
        this.alibbcattitle = alibbcattitle;
    }

    public Alibbcat(int id, String alibbcattitle, String alibbcatid, String alibbcatUrl, int level, int aliparentid, int parentid) {
        this.id = id;
        this.alibbcattitle = alibbcattitle;
        this.alibbcatid = alibbcatid;
        this.alibbcatUrl = alibbcatUrl;
        this.level = level;
        this.aliparentid = aliparentid;
        this.parentid = parentid;
    }

    public int getId() {
        return id;
    }

    public Alibbcat setId(int id) {
        this.id = id;
        return this;
    }

    public String getAlibbcattitle() {
        return alibbcattitle;
    }

    public Alibbcat setAlibbcattitle(String alibbcattitle) {
        this.alibbcattitle = alibbcattitle;
        return this;
    }

    public String getAlibbcatid() {
        return alibbcatid;
    }

    public Alibbcat setAlibbcatid(String alibbcatid) {
        this.alibbcatid = alibbcatid;
        return this;
    }

    public String getAlibbcatUrl() {
        return alibbcatUrl;
    }

    public Alibbcat setAlibbcatUrl(String alibbcatUrl) {
        this.alibbcatUrl = alibbcatUrl;
        return this;
    }

    public int getLevel() {
        return level;
    }

    public Alibbcat setLevel(int level) {
        this.level = level;
        return this;
    }

    public int getAliparentid() {
        return aliparentid;
    }

    public Alibbcat setAliparentid(int aliparentid) {
        this.aliparentid = aliparentid;
        return this;
    }

    public int getParentid() {
        return parentid;
    }

    public Alibbcat setParentid(int parentid) {
        this.parentid = parentid;
        return this;
    }
}
