package guangcaiwang;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import com.gargoylesoftware.htmlunit.util.Cookie;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dao.BaseDao;
import http.HttpUtils;
import okhttp3.Request;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pojo.Alias;
import pojo.Noneunit;
import pojo.Variety;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

public class TestCategory {

    private static final Logger log = Logger.getLogger(TestCategory.class);

    private BaseDao baseDao = new BaseDao();

    //private static final String cookie = "location_name=%25E5%2585%25A8%25E5%259B%25BD; location_code=1; ";

    private static final String PASS = "qwertyuiop123";


    /**
     * 读取一页,成功读取完成返回 true ,未读取完成返回false
     *
     * @param noneunit
     */
    public boolean requestPage(String cookie, Noneunit noneunit) {
        boolean res = false;

        String mappingIdEncode = "";
        try {
            mappingIdEncode = URLEncoder.encode(noneunit.getMappingId(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
        }

        String url = "https://www.gldjc.com/scj/so.html?l=1&terms=%5B%7Bid%3A%22category2_id%22%2Cname%3A%22" + mappingIdEncode + "%22%7D%5D&q=Precise&is=0";
        Request request = new Request.Builder().url(url).build();
        HttpUtils.ResponseWrap responseWrap = HttpUtils.retryHttpNoProxy(request);

        if (responseWrap.isSuccess()) {
            try {
                String html = responseWrap.body;
                org.jsoup.nodes.Document document = Jsoup.parse(html);
                String pageTotalStr = document.getElementById("hid_pageTotle").attr("value").trim();

                int pageTotal = Integer.parseInt(pageTotalStr);
                if (pageTotal > 100) {
                    pageTotal = 100;
                }

                int beginPage = noneunit.getPage() - 3;
                if (beginPage < 1) {
                    beginPage = 1;
                }

                for (int i = beginPage; i <= pageTotal; i++) {
                    String href = "https://www.gldjc.com/scj/so.html?l=1&p=" + i + "&terms=%5B%7Bid%3A%22category2_id%22%2Cname%3A%22" + noneunit.getMappingId() + "%22%7D%5D&q=Precise&is=0";

                    log.info("===>> 读取 " + noneunit.getTitle() + " 的 第 " + i + " 页 开始. , 共 : " + pageTotal + " 页  ");
                    log.info(href);

                    // 读取一页
                    int success = get(href, cookie, noneunit);

                    if (success == -1) {
                        i-- ;
                        try {
                            WebClient webClient = changeUser();
                            //获取cookie
                            CookieManager CM = webClient.getCookieManager();
                            Set<Cookie> cookies = CM.getCookies();
                            String cks = "";
                            for (Cookie c : cookies) {
                                cks = cks + c.getName() + "=" + c.getValue() + ";";
                            }
                            cookie = cks;

                        } catch (IOException e) {
                            e.printStackTrace();
                            log.error(e.getMessage(), e);
                        }

                        log.error("====> 获取页面数据未成功！！！！！账号封杀！！ 切换账号， 继续执行本页面");
                        continue;
                    }

                    // 如果没有成功
                    if (success < 1) {
                        i--;
                        log.error("====> 获取页面数据未成功！！！！！，继续执行本页面");
                        continue;
                    }

                    //页面无异常情况下
                    noneunit.setPage(i);
                    baseDao.updatePage(noneunit);

                    log.info("===>> 读取 " + noneunit.getTitle() + " 的 第 " + i + " 页 结束.");
                    log.info("===============================================================");

                    Thread.sleep((long) (Math.random() * 1 + 3) * 1000);
                }

                res = true;

            } catch (Exception ex) {
                ex.printStackTrace();
                log.error(ex.getMessage(), ex);
            }
            // page ++
            // category.setpage(++page);
            // baseDao.updatePage (category)

        }

        return res;
    }

    /**
     * 获取品种并入库
     *
     * @param href
     * @param cookie
     * @param noneunit
     */
    public int get(String href, String cookie, Noneunit noneunit) {
        int res = 0;

        Request request = new Request.Builder().url(href).headers(HttpUtils.getCommonHeaders()).header("Cookie", cookie).build();
        HttpUtils.ResponseWrap responseWrap = HttpUtils.retryHttpNoProxy(request);

        if (responseWrap.isSuccess()) {
            String html = responseWrap.body;
            org.jsoup.nodes.Document document = Jsoup.parse(html);

            // 判断是否被封杀
            List<Element> fengShaNodes = document.select(".antiReptileBg");
            if (fengShaNodes != null && fengShaNodes.size() > 0) {
                log.error("===>> 账号被封 ! 请求切换VIP账号... ");
                return -1;
            }

            //获取头部
            Elements tous = document.getElementsByAttributeValue("field", "attr_品种");

            Elements trs = document.select(".tcontent");

            log.info("===>> 发现数据 : " + trs.size() + "条");

            //判断头部有品种
            if (tous.size() > 0) {
                for (Element tr : trs) {
                    String parameters = tr.select(".introduceName").text().split("\\|")[0];
                    //详情有品种
                    if (parameters.indexOf("品种") != -1) {

                        int flag = 1;

                        String title = parameters.split(":")[1].trim();

                        String pinzhongid = noneunit.getId() + "_" + title.trim();

                        int count = baseDao.selectVarietyExist(pinzhongid);
                        //品种表存在
                        //如果存在就正常写进数据库
                        if (count == 1) {
                            // 查询详情
                            getDetail(tr, noneunit.getId(), pinzhongid, flag, title, noneunit.getTitle());
                            //品种表不存在
                        } else {
                            //创建品种对像
                            Variety variety = new Variety();
                            variety.setId(pinzhongid);
                            variety.setName(title);
                            variety.setTitid(noneunit.getId());
                            variety.setPath(noneunit.getPathname() + "," + noneunit.getTitle());
                            variety.setUrl("https://www.gldjc.com/scj/so.html?l=1&terms=[{id:\"category2_id\",name:\"" + noneunit.getMappingId() + "\"},{id:\"attr_品种\",name:\"" + title + "\"}]&q=Precise&is=0");
                            baseDao.insertPinZhong(variety);

                            // 查询详情
                            getDetail(tr, noneunit.getId(), pinzhongid, flag, title, noneunit.getTitle());
                        }
                        //详情没有品种
                    } else {
                        int flag = 2;
                        String pinzhongid = "";
                        getDetail(tr, noneunit.getId(), pinzhongid, flag, "", noneunit.getTitle());
                    }
                }

                res = 1;
            } else {
                String pinzhongid = noneunit.getId() + "_" + noneunit.getTitle().trim();
                int flag = 3;
                int count = baseDao.selectVarietyExist(pinzhongid);
                if (count < 1) {
                    //不存在就直接当做分类写入
                    //创建品种对像
                    Variety variety = new Variety();
                    variety.setId(pinzhongid);
                    variety.setName(noneunit.getTitle());
                    variety.setTitid(noneunit.getId());
                    variety.setPath(noneunit.getPathname());
                    variety.setUrl("https://www.gldjc.com/scj/so.html?terms=[{id:\"category2_id\",name:\"" + noneunit.getMappingId() + "\"}]");
                    baseDao.insertPinZhong(variety);
                }
                for (Element tr : trs) {
                    getDetail(tr, noneunit.getId(), pinzhongid, flag, noneunit.getTitle(), noneunit.getTitle());
                }

                res = 1;
            }
        }

        return res;
    }

    /**
     * 获取详情
     */
    public void getDetail(Element tr, Integer titid, String PinZhongId, int flag, String title, String cattitle) {
        try {
            //获取其他属性的集合
            Elements coms = tr.select("td.suppliersTd > ul > li");

            // document.querySelector("body > div.w1200 > div.spu_list_wrap > table > tbody > tr:nth-child(3) > td.suppliersTd > ul > li:nth-child(1)")
            //品种别名
            String aliasName = tr.select("#a_productName").text();

            //获取参数
            String parameters = tr.select(".introduceName").text();

            //遍历集合，取其中的值
            for (Element com : coms) {
                // String qq = com.text();

                //品牌
                String brand = com.select(".brandSpan").text();
                //单位
                //先判断有没有下拉小框，如果有就获取里面的值，如果没有就直接获取
                String unit = com.select(".unitSpan").text();
                //含税市场价
                String hsscjprice = com.select(".fontPrice").attr("price");
                //含税面价
                String hsmjprice = com.select(".marPriSpan").attr("price_te");
                //税率
                String sl = com.select(".dutySpan").text();
                //时间
                String date = com.select(".timeSpan").text();

                if (unit.equals("")) {
                    Elements vals = com.select(".pop-modal-list li");
                    List<String> v = new ArrayList<>();
                    for (Element val : vals) {
                        v.add(val.text());
                    }
                    unit = String.join(",", v);
                }

                //公司
                String company = com.select(".supplierSpan.rel.other-icon > div > div > div:nth-child(2) > div > p:nth-child(1) > font").text();
                //联系人
                String contacts = com.select(".supplierSpan.rel.other-icon > div > div > div:nth-child(2) > div > p:nth-child(2) > font").text();
                //电话
                String telephone = com.select(".supplierSpan.rel.other-icon > div > div > div:nth-child(2) > div > p:nth-child(3) > font").text();
                //地址
                String address = com.select(".supplierSpan.rel.other-icon > div > div > div:nth-child(2) > div > p:nth-child(4) > font").text();

                String fndata = com.getElementsByClass("companySpans").select("a").attr("fndata");

                JsonElement je = new JsonParser().parse(fndata);

                String id = "";
                if (je.isJsonObject()) {
                    JsonObject jsonObject = je.getAsJsonObject();
                    String dataid = jsonObject.get("data_id").getAsString();
                    String companyid = jsonObject.get("company_id").getAsString();
                    id = dataid + "-" + companyid;
                }

                Alias alias = new Alias();
                alias.setId(id);
                alias.setAddress(address);
                alias.setAlias(aliasName);
                alias.setBrand(brand);
                alias.setCompany(company);
                alias.setParameters(parameters);
                alias.setDate(date);
                alias.setFlag(flag);
                alias.setSl(sl);
                alias.setHsmjprice(hsmjprice);
                alias.setHsscjprice(hsscjprice);
                alias.setUnit(unit);
                alias.setContacts(contacts);
                alias.setTelephone(telephone);
                alias.setTitle(cattitle);
                alias.setTitid(titid);
                alias.setPinZhongId(PinZhongId);
                alias.setUrl("");
                //品种
                alias.setPinzhong(title.trim());
                baseDao.insertAlias(alias);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 切换账号
     *
     * @param
     * @return
     * @throws IOException
     */
    public WebClient changeUser() throws IOException {
        java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);
        java.util.logging.Logger.getLogger("org.apache.http.client").setLevel(Level.OFF);
        java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);
        LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");

        String url = "https://www.gldjc.com/login?hostUrl=https%3A%2F%2Fwww.gldjc.com%2F";

        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setUseInsecureSSL(true);
        webClient.getOptions().setRedirectEnabled(true);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setDownloadImages(false);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getCookieManager().setCookiesEnabled(true);
        webClient.getCookieManager().addCookie(new Cookie("www.gldjc.com", "location_name", "%25E5%2585%25A8%25E5%259B%25BD"));
        webClient.getCookieManager().addCookie(new Cookie("www.gldjc.com", "location_code", "1"));

        boolean f = true;
        xunhaun:
        while (f) {

            if (GuangCaiWang.VIP_NAMES.size() < 1) {
                log.info("！！！！！！！！！！！！！没有可用的VIP账号！！！！！！！！！！！！！");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    log.error(e.getMessage(), e);
                }
                continue;
            }

            String name = GuangCaiWang.VIP_NAMES.get(0);

            log.info("==>> " + name + " , 正在尝试中.... ");

            try {
                HtmlPage page = webClient.getPage(url);

                HtmlTextInput userNameInput = (HtmlTextInput) page.getElementById("userName");
                userNameInput.setValueAttribute(name);

                HtmlPasswordInput passwordInput = (HtmlPasswordInput) page.getElementById("password");
                passwordInput.setValueAttribute("qwertyuiop123");

                HtmlDivision div = (HtmlDivision) page.getElementById("loginBtn");

                HtmlPage a = div.click();

                webClient.waitForBackgroundJavaScript(1666);

                List<DomNode> fengShaNodes = a.querySelectorAll(".antiReptileBg");

                if (fengShaNodes != null && fengShaNodes.size() > 0) {
                    log.error("==>> " + name + " , 该账号被封杀了!!");
                    GuangCaiWang.VIP_NAMES.remove(0);
                    webClient.getCookieManager().clearCookies();
                    f = true;
                } else {
                    // 判断是不是VIP
                    DomNode vipImg = a.querySelector("#userHeadInfo img");

                    if (vipImg == null) {
                        continue;
                    }

                    String src = vipImg.getAttributes().getNamedItem("src").getNodeValue();
                    if (src.contains("shiyong")) {
                        log.info("==>> " + name + " , 账号可用，已切换.... ");
                        f = false;
                        GuangCaiWang.VIP_NAMES.remove(0);
                        break xunhaun;
                    } else {
                        log.error("==>> " + name + " , 账号不是 VIP .");
                        GuangCaiWang.VIP_NAMES.remove(0);
                        webClient.getCookieManager().clearCookies();
                        f = true;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                log.error(e.getMessage(), e);
            }
        }
        return webClient;
    }


}
