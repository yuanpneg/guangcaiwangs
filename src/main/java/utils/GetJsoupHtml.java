package utils;


import http.HttpUtils;
import okhttp3.Request;

public class GetJsoupHtml {

    public static String getHtml(String url, String charset, String cookie) {
        System.out.println(url);
        if (url == null) return null;
        Request request = null;
        if (cookie.isEmpty()) {
            request = new Request.Builder().url(url)
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36")
                    .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
                    .build();
        } else {

            request = new Request.Builder().url(url)

                    .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36")
                    .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
                    .header("Cookie", cookie).build();
        }

        HttpUtils.ResponseWrap responseWrap = HttpUtils.retryHttpNoProxy(request, charset);

        if (responseWrap.isSuccess()) {
            return responseWrap.body;
        }
        return null;
    }
}
