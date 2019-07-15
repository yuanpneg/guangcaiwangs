package alibaba;

import bean.Details;
import com.google.gson.Gson;
import dao.BaseDao;
import http.HttpUtils;
import okhttp3.Request;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Wuage {

    private BaseDao baseDao = new BaseDao();

    public static void main(String[] args) {
        Wuage wuage = new Wuage();
        wuage.getList();
    }

    /**
     * 获取列表
     */
    public void getList() {

        String key = "钢筋";
        int catid = 1;

        String url = "https://s.wuage.com/product/search?keywords=" + key + "&psa=W1.a211.0.109";
        int pageTotal = getTotalPage(url);
        for (int i = 1; i < pageTotal; i++) {
            url = "https://s.wuage.com/product/search?keywords=" + key +"&page=" + i + "&psa=W1.a211.0.109";
            Request request = new Request.Builder().url(url).build();
            HttpUtils.ResponseWrap responseWrap = HttpUtils.retryHttp(request, "utf-8");
            if (responseWrap.isSuccess()) {
                String html = responseWrap.body;
                Document doc = Jsoup.parse(html);
                Elements elements = doc.getElementsByClass("layout-list").get(0).getElementsByClass("fe-col");
                for (Element element : elements) {
                    String href = element.select("a").attr("href");
                    getDetail(catid, key, href);
                }
                System.out.println(doc);
            }
        }
    }

    /**
     * 获取总页数
     *
     * @param url
     * @return
     */
    public int getTotalPage(String url) {
        int page = 0;
        Request request = new Request.Builder().url(url).build();
        HttpUtils.ResponseWrap responseWrap = HttpUtils.retryHttp(request, "utf-8");
        if (responseWrap.isSuccess()) {
            String html = responseWrap.body;
            Document doc = Jsoup.parse(html);
            page = Integer.parseInt(doc.getElementById("page").attr("total-size"));
            return page;
        }
        return page;
    }

    /**
     * 获取详情信息
     */
    public void getDetail(int catid, String key, String href) {
        Gson gson = new Gson();
        Request request = new Request.Builder().url(href).build();
        HttpUtils.ResponseWrap responseWrap = HttpUtils.retryHttp(request, "utf-8");
        if (responseWrap.isSuccess()) {
            String html = responseWrap.body;
            Document doc = Jsoup.parse(html);
            //标题
            String title = doc.getElementsByClass("info-title").get(0).text();
            //价格
            String price = doc.getElementsByClass("price").get(0).text();
            //单位
            String unit = doc.getElementsByClass("item-content").get(0).select("em").text().replace("/","");
            //规格
            String paramattr = doc.getElementsByClass("sku-main").get(0).getElementsByClass("item-title").text();

            Elements lis = doc.getElementsByClass("item-content").get(0).select("li");
            //获取规格参数
            Map<String,Object> map = new HashMap<>();
            for (Element li : lis) {
                map.put(li.getElementsByClass("sku").text(),li.getElementsByClass("price").text());
            }
            //转json
            String mapStr = gson.toJson(map);

            //获取属性参数
            Map<String,Object> paramsMap = new HashMap<>();
            Elements params = doc.getElementsByClass("details-body").get(0).select("li");
            for (Element param : params) {
                String name = param.select("em").text();
                String value = param.select("apan").attr("title");
                paramsMap.put(name,value);
            }
            String paramStr = gson.toJson(paramsMap);

            //获取图片
            List<String> imgUrlList = new ArrayList<>();
            Elements pics = doc.getElementsByClass("spec-scroll");
            for (Element pic : pics) {
                String pic_href  = "https:" + pic.getElementsByTag("img").attr("href");
                imgUrlList.add(pic_href);
            }
            String fromImg = String.join(",", imgUrlList);

            //获取详情内容
            List<String> imgList = new ArrayList<>();
            String  detailsObject = doc.getElementById("descriptionHtml").html();
            Elements detail_pics = doc.getElementById("descriptionHtml").select("img");
            for (Element detail_pic : detail_pics) {
                String pic_href = detail_pic.attr("src");
                imgList.add(pic_href);
            }
            String detailImg =  String.join(",", imgList);

            //获取公司联系人、地址
            String company = doc.getElementsByClass("header").get(0).select("span").attr("title");
            String contact = doc.getElementsByClass("contact company-contactName").get(0).attr("title");
            String model = doc.getElementsByClass(" company-entType").get(0).attr("title");
            String address = doc.getElementsByClass("address company-address").get(0).attr("title");


            getcontact(doc);
            //创建所有详细信息的带参构造方法
            Details details = new Details();
//            Details details = new Details(catid, key, title, price, shoptitle, management, address, tephone,
//                    fromImg, unit, DetailAddress, paramattr, mapStr, href, map1Str, detailsObject, detailImg);
            baseDao.insertTpDetail(details);
        }
    }

    /**
     * 获取联系方式
     */
    public void getcontact(Document doc){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Administrator\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        try {
            //公司url
            String shop_url = "https:" + doc.getElementsByClass("go-shop").get(0).attr("href");
            Request request = new Request.Builder().url(shop_url).build();
            HttpUtils.ResponseWrap responseWrap = HttpUtils.retryHttp(request, "utf-8");
            if (responseWrap.isSuccess()) {
                String url = "https:" + doc.getElementsByClass("top-nav-contactinfo nav-item ").get(0).select("a").attr("href");
                driver.get(url);
                String html = driver.getPageSource();
                driver.findElements(By.xpath("//*[@id=\"lark-view-content\"]/div[2]/div/div[2]/div/div[1]/div[3]/span[3]")).get(0).click();
                System.out.println(html);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            driver.quit();
            driver.close();
        }
    }

}
