package alibaba;

import bean.AlibbdateilJson;
import bean.Details;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.BaseDao;
import http.HttpUtils;
import okhttp3.Request;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.GetJsoupHtml;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class alibabaUrl {

    private static Integer maxpage;
    private static WebDriver driver;
    private static String key = "";
    private static String keyid = "";
    private static String url = "";
    private static String DetailAddress = "";
    private BaseDao baseDao = new BaseDao();
    private static String keyEncoder = "";

    private String cookie = "UM_distinctid=1698081698d8d2-03af22e9e5c55f-414c042a-15f900-1698081698e456; " +
            "cna=tlISFVlH+XkCAd67LbaYgDZa; ali_ab=114.234.159.222.1552639160657.4; " +
            "ali_beacon_id=222.187.45.182.1553671046899.957414.5; cookie2=1d262906b04a564506c01318ce0bd83a; " +
            "t=e4b690e0c4e598ae3408b6041e077ee0; _tb_token_=e0eebede73776; __cn_logon__=false; " +
            "h_keys=\"%u783c%u7ba1#%u8def%u706f%u6746#%u5355%u81c2%u8def%u706f#%u8def%u706f#%u94a2%u5e26%u589e%u5f3a%u6ce2%u7eb9%u7ba1#%u82b1%u5c97%u5ca9#%u6bdb%u9762%u82b1%u5c97%u5ca9#pe%u7ba1#%u710a%u63a5%u94a2%u7ba1#%u94a2%u7b4b\"; ad_prefer=\"2019/04/08 17:32:45\"; alicnweb=homeIdttS%3D95353286413309343774283810468514941277%7Ctouch_tb_at%3D1554771319476%7ChomeIdttSAction%3Dtrue%7Chp_newbuyerguide%3Dtrue; CNZZDATA1253659577=90154511-1552694034-%7C1554768221; _csrf_token=1554773145089; l=bBazx1Trvmv9wMW2BOCiquI8L__OHIRAguPRwC0Di_5IgOYsZBQOlG2nPHv6Vj5R_N8p4FNLRKy9-etlj; isg=BFxc8iMSbMTG0Ri4gvGM_gRuLXrOfQF3BsxOAjZd6ccqgf0LX-GPjdF74ancCThX";


    static {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Administrator\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe");
//        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver();
    }

    public static void main(String[] args) {
        alibabaUrl alibabaUrl = new alibabaUrl();
        alibabaUrl.detail();
    }

    public void detail() {
        //查询level=3的集合
        //List<Alibbcat> keys = baseDao.selectAlibbcatlevel();
        //循环遍历集合得到想要搜索的数据
        //     for (Alibbcat k : keys) {
        //Alibbcat alibbcat = new Alibbcat();
        //获取的key的值
        key = "球阀";
        //获取的id的值
        keyid = "1401";
        System.out.println(key);

        try {
            keyEncoder = URLEncoder.encode(key, "gbk");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String login_url = "";
//        url = "https://s.1688.com/selloffer/offer_search.htm?keywords=" + keyEncoder +
//                "&n=y&netType=1%2C11&spm=a260k.635.3262836.d102";
        url = "https://www.1688.com/";
        driver.get(url);
        try {
            Thread.sleep(3000);
        }catch (Exception e){
            e.printStackTrace();
        }
        WebElement search = driver.findElement(By.id("alisearch-keywords"));
        search.sendKeys(key);
        //driver.findElement(By.cssSelector("#alisearch-submit")).click();
        get();
        if (maxpage > 1) {
            for (int i = 2; i <= maxpage; i++) {
                try {
                    Thread.sleep(5 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i);
                WebElement a = driver.findElement(By.cssSelector(".fui-paging-input"));
                a.clear();
                a.sendKeys(String.valueOf(i));
                driver.findElement(By.cssSelector(".fui-paging-btn")).click();
                get();
            }
        }
    }

    public void get() {
        try {
            Gson gsons = new Gson();
            //下拉网页，用于加载网页的（线程每两秒下拉一次）
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2800)");
            Thread.sleep(2 * 1000);
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 6000)");
            Thread.sleep(3 * 1000);
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 10000)");
            Thread.sleep(3 * 1000);
            //获取最大页数的，因为每次程序只能最多抓取50页，超过的部分做判断
            maxpage = Integer.parseInt(driver.findElement(By.cssSelector(".fui-paging-num")).getText());
            if (maxpage > 50) {
                maxpage = 50;
            }
            //通过sm-offer-item选择器获取每一块作为一个集合
            List<WebElement> element = driver.findElements(By.cssSelector(".sm-offer-item"));
            if (element.size() == 0) {
                System.out.println("没了");
                System.exit(0);
            }
            for (WebElement webElement : element) {
                //循环这个集合获取每一块的详细信息(text)
                String c = webElement.getText();
                //文本中如果有'产地精选'和‘摩登好货’这样的不需要的关键字就执行下一快
                if (c.indexOf("产地精选") != -1) continue;
                if (c.indexOf("摩登好货") != -1) continue;
                //得到每一块的文本后通过属性选择'href' 标签的内容

                //String a = "https://detail.1688.com/offer/547186777897.html";
                String a = webElement.findElement(By.cssSelector(".sw-dpl-offer-photoLink")).getAttribute("href");
                //String a = "https://detail.1688.com/offer/534891030593.html?spm=b26110380.sw1688.mof001/.1.b6934cdezAlbBp";
                //这行代码的作用使用链式调用生成一个 Request 对象，每一个HTTP请求包含一个URL、一个方法（GET或POST或其他）、一些HTTP头。请求还可能包含一个特定内容类型的数据类的主体部分。
                Request request = new Request.Builder().header("cooike",cookie).url(a).build();
                HttpUtils.ResponseWrap responseWrap = HttpUtils.retryHttp(request, "gbk");

                if (responseWrap.isSuccess()) {
                    String html = responseWrap.body;
                    Document document = Jsoup.parse(html);
                    //判断商品是否下架的
                    String cc = document.select(".mod-Detail-offline-title").text();
                    if (cc.indexOf("下架") != -1) continue;
                    //判断页面存不存在的
                    String error = document.select("#content").text();
                    if (error.indexOf("您要访问的页面不存在") != -1) continue;
                    //获取标题的
                    String title = document.select("h1.d-title").text();
                    //获取图片集合的
                    Elements lis = document.select("ul.nav.nav-tabs.fd-clr").select("li");
                    List<String> imgUrlList = new ArrayList<>();
                    for (Element li : lis) {
                        String data_imgs = li.attr("data-imgs");
                        if (data_imgs == null) continue;
                        //跳过html中的特殊符号转码的方法
                        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
                        //fromJson 的作用是实现从Json相关对象到Java实体的方法
                        AlibbdateilJson alibbdateilJson = gson.fromJson(data_imgs, AlibbdateilJson.class);
                        if (alibbdateilJson == null) continue;
                        String imgurl = alibbdateilJson.getOriginal();
                        imgUrlList.add(imgurl);
                    }
                    String fromImg = String.join(",", imgUrlList);
                    //获取价格
                    String price = document.select("div.price-original-sku").text();
                    if (price == null || price.isEmpty()) price = document.select(".price").text();
                    price = price.replace("价格", "");
                    String unit = document.select("#mod-detail-price > div > table > tbody > tr.amount >" +
                            " td.ladder-1-1 > span.unit").text();
                    String management = document.getElementsByClass("biz-type-model").text();
                    //获取公司名称
                    String shoptitle1 = document.select(".company-name").text();
                    String[] str = shoptitle1.split(" ");
                    String shoptitle = str[0];
                    //获取地址的
                    String address = document.select(".address").select(".disc").text();
                    //联系人
                    String contact = document.select(".contactSeller").select(".link").text();
                    //获取电话
                    String tephone = document.select(".m-mobilephone").select(".mobile-number").text();
                    String contactpage = document.select("li.contactinfo-page").select("a").attr("href");
                    String mapStr = "";
                    String map1Str = "";
                    Map<Object, String> map = new HashMap<>();
                    String paramattr = "";
                    if (!contactpage.isEmpty() && tephone.isEmpty() || tephone.indexOf("登录") != -1) {
                        if (contactpage == "" || contactpage == null) continue;
                        tephone = getcontact(contactpage);
                    }
                    //获取参数的
                    //先判断有没有obj-sku这个class，
                    if (html.indexOf("obj-sku") != -1) {
                        //如果有就先获取下面的参数的属性
                        paramattr = document.select(".obj-sku > div.obj-header > span").text();
                        //再获取参数的name,price
                        Elements params = document.select(".obj-sku > div.obj-content > table > tbody > tr");
                        for (int t = 0; t < params.size(); t++) {
                            String paramname = params.select(".name").get(t).text();
                            //在获取参数的name的时候，要判断是不是空，如果是空就获取里面的alt属性的值
                            if (paramname == "" || paramname == null || paramname.equals("")) {
                                paramname = params.select(".name > span").get(t).attr("title");
                            }
                            String paramprice = params.select(".price").get(t).text();
                            map.put(paramname, paramprice);
                            mapStr = gsons.toJson(map);
                        }
                    }
                    Map<Object, String> map1 = new HashMap<>();
                    //选取key和value的值存储成json格式，存在一个字段里面
                    Elements trs = document.select("#mod-detail-attributes > div.obj-content > table > tbody > tr");
                    for (Element tr : trs) {
                        Elements de_feature = tr.select("td.de-feature");
                        Elements de_value = tr.select("td.de-value");
                        for (int i = 0; i < de_feature.size(); i++) {
                            String k = de_feature.get(i).text();
                            if (k.isEmpty()) continue;
                            String v = de_value.get(i).text();
                            map1.put(k, v);
                            map1Str = gsons.toJson(map1);
                        }
                    }

                    //获取详情信息与详情中的图片 开始 =================================
                    String detail_pic = "";
                    String detailsObject = "";
                    if (document.html().indexOf("desc-lazyload-container") != -1) {
                        String detailUrl = document.getElementById("desc-lazyload-container").attr("data-tfs-url");
                        Document doc = Jsoup.parse(getDetail(detailUrl));
                        detailsObject = doc.html();

                        if (doc.html().indexOf("img") != -1) {
                            Elements elements = doc.getElementsByTag("img");
                            StringBuilder detail_pics = new StringBuilder();
                            for (Element el : elements) {
                                detail_pics.append(el.attr("src")).append(",");
                            }
                            if(detail_pics.toString().length() > 0) {
                                detail_pic = detail_pics.toString().substring(0, detail_pics.toString().length() - 1);
                            }
                        }
                    }
                    //获取详情信息与详情中的图片 结束 =================================

                    //创建所有详细信息的带参构造方法
                    Details details = new Details(keyid, key, title, price, shoptitle, management, address, tephone,
                            fromImg, unit, DetailAddress, paramattr, mapStr, a, map1Str, detailsObject, detail_pic);
                    baseDao.insertTpDetail(details);
                }

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取商品详情
     *
     * @param href
     * @return
     */
    public String getDetail(String href) {
        Request requestDetail = new Request.Builder().url(href).build();
        HttpUtils.ResponseWrap responseDetail = HttpUtils.retryHttp(requestDetail, "gbk");
        String doc = "";
        if (responseDetail.isSuccess()) {
            String html = responseDetail.body;
            doc = html.replace("var offer_details=", "").replace("\\", "").
                    replace("{\"content\":\"", "").replace("\"};", "");
            return doc;
        }
        return doc;
    }


    public String getcontact(String contactpage) {

        String html = GetJsoupHtml.getHtml(contactpage, "gbk", cookie);
        if (html == null) return null;
        Document document = Jsoup.parse(html);
        Elements dls = document.select("div.contcat-desc").select("dl");
        String tephone = "";
        for (Element dl : dls) {
            String dttext = dl.select("dt").text();
            String ddtext = dl.select("dd").text();
            if (dttext.equals("移动电话：")) {
                tephone = ddtext;
            }
            if (dttext.trim().equals("地      址：")) {
                DetailAddress = ddtext;
            }
        }
        return tephone;
    }


}
