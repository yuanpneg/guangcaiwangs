package guangcaiwang;

import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.util.Cookie;
import dao.BaseDao;
import org.apache.log4j.Logger;
import pojo.Noneunit;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GuangCaiWang {
    private static final Logger log = Logger.getLogger(GuangCaiWang.class);

    public static List<String> VIP_NAMES = null;

    public static void main(String[] args) {
        // 产生日志目录
        File logDir = new File("C:\\guangcaiwang");
        if (!logDir.exists()) {
            logDir.mkdirs();
        }

        if (args.length > 1) {
            // 装载电话
            String arg1 = args[1];
            VIP_NAMES = new ArrayList<>();
            String[] phones = arg1.split(",");
            for (String phone : phones) {
                VIP_NAMES.add(phone);
                log.info("成功装载 VIP_NAME = " + phone);
            }


            BaseDao baseDao = new BaseDao();
            TestCategory testCategory = new TestCategory();
            //大类id
            String arg0 = args[0];

            String[] paths = arg0.split(",");

            //登陆账号
            WebClient webClient = null;
            try {
                webClient = testCategory.changeUser();
            } catch (IOException e) {
                e.printStackTrace();
                log.error(e.getMessage(), e);
            }
            //获取cookie
            CookieManager CM = webClient.getCookieManager();
            Set<Cookie> cookies = CM.getCookies();
            String cks = "";
            for (Cookie c : cookies) {
                cks = cks + c.getName() + "=" + c.getValue() + ";";
            }
            //循环大类
            try {
                for (String path : paths) {
                    log.error("===>>> 开始进行 ID = " + path + " 的一级分类采集.");

                    // 所有三级查询出来
                    List<Noneunit> list = baseDao.selectSanJiLevel(path);
                    for (Noneunit noneunit : list) {
                        log.error("===>>> 开始进行  " + noneunit.getTitle() + " 的采集.");

                        if (noneunit.getPage() != -1) {

                            boolean res = testCategory.requestPage(cks, noneunit);

                            if (res) {
                                noneunit.setPage(-1);
                                baseDao.updatePage(noneunit);
                                log.error("===>>> 完成  " + noneunit.getTitle() + " 的采集.");
                            }

                        }


                    }
                    log.error("===>>> ID = " + path + " 的一级分类采集... 结束!");
                }

            } catch (Exception e) {
                e.printStackTrace();
                log.error(e.getMessage(), e);
            }
        }
    }
}
