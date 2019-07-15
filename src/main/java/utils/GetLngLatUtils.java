package utils;


import bean.LngLat;
import bean.LngLatBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import http.HttpUtils;
import okhttp3.Request;



public class GetLngLatUtils {
    /**
     * 获取经纬度的方法
     */
    public static LngLat getLngLat(String city){
        //RGrKcxQ061jq53rA6xNpay0jYyNNkMZB
//        LeGFyYciwxkh2CzlNH70qnGDbx1s17TG
//
        String url = "http://api.map.baidu.com/geocoder/v2/?address="+city+"&output=json&ak=LeGFyYciwxkh2CzlNH70qnGDbx1s17TG";
        Request request = new Request.Builder().url(url).build();
        HttpUtils.ResponseWrap responseWrap = HttpUtils.retryHttpNoProxy(request, "utf-8");
        if (responseWrap.isSuccess()) {
            String html = responseWrap.body;
            Gson gson = new GsonBuilder().disableHtmlEscaping().create();
            LngLat lngLat = new LngLat();
            LngLatBean lngLatBean = gson.fromJson(html,LngLatBean.class);
            if (lngLatBean.getResult()!=null){
                String lng = String.valueOf(lngLatBean.getResult().getLocation().getLng());
                String lat = String.valueOf(lngLatBean.getResult().getLocation().getLat());
                lngLat.setLat(lat);
                lngLat.setLng(lng);
                lngLat.setCity(city);
                return lngLat;
            }else {
                return null;
            }
        }
        return null;
    }
}
