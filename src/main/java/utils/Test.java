package utils;


import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class Test {

    public static void main(String[] args) {
        Gson gson = new Gson();
        Map<String, Object> map = new HashMap<>();
        map.put("品牌","苹果");
        map.put("价格","123");
        String jsonStr = gson.toJson(map);
        System.out.println(jsonStr);
    }
}
