package guangcaiwang;

import dao.BaseDao;
import http.HttpUtils;
import okhttp3.Request;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pojo.Noneunit;
import pojo.UnitKey;
import pojo.UnitValue;

import java.util.List;

/**
 * 获取参数
 */
public class Specification {

    public static void main(String[] args) {
        BaseDao baseDao = new BaseDao();
        try{
        //查询数据库
        //List<Variety> list = baseDao.selectVarietyList();
            List<Noneunit> list = baseDao.selectNoneunit(2);
            //循环遍历
            for (int i = 1; i < 2; i++) {
                System.out.println("====>>> 抓取到第 " + i + " 个品种");
                //判断三级分类是否在pinzhogn表中存在
                String title = "拼装式内隔墙";
                //int count = baseDao.selectVarietyName(title);
                int count  = 0;
                if(count == 0) {
                    //通过 url 请求页面
                    //String url = "https://www.gldjc.com/scj/so.html?l=1&terms=[{id:\"category2_id\",name:\"" + list.get(i).getMappingId() + "\"}]&q=Precise";
                    String url = "https://www.gldjc.com/scj/so.html?l=1&keyword=%E6%8B%BC%E8%A3%85%E5%BC%8F%E5%86%85%E9%9A%94%E5%A2%99&terms=%5B%7Bid%3A%22category2_id%22%2Cname%3A%22205%22%7D%5D&q=Precise&is=0";
                    Request request = new Request.Builder().url(url).build();
                    HttpUtils.ResponseWrap responseWrap = HttpUtils.retryHttpNoProxy(request);
                    if (responseWrap.isSuccess()) {
                        //抓取页面头部参数  属性、参数
                         String html = responseWrap.body;
                        Document document = Jsoup.parse(html);
                        Elements elements = document.getElementsByClass("classifysBox");
                        for (Element element : elements) {
                            String key = element.getElementsByClass("a-key").get(0).text();
                           // if ("品种:".equals(key)) continue;
                            UnitKey unitKey = new UnitKey();
                            unitKey.setUnitKey(key);
                            unitKey.setCatid(2370);
                            unitKey.setCattitle(title);
                            baseDao.insertUnitKey(unitKey);
                            //key 的 id
                            int id = unitKey.getId();
                            Elements els = element.getElementsByTag("li").select("a");
                            //循环值
                            for (Element el : els) {
                                //值
                                String name = el.attr("name");
                                UnitValue unitValue = new UnitValue();
                                unitValue.setKeyid(id);
                                unitValue.setCatid(list.get(i).getId());
                                unitValue.setUnitValue(name);
                                baseDao.insertUnitValue(unitValue);
                            }
                            Thread.sleep(2000);
                        }
                    }
                }
            }
            //存入数据库
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
