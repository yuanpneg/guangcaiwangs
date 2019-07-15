package alibaba;

import bean.LngLat;
import dao.BaseDao;
import utils.GetLngLatUtils;

import java.util.List;

/**
 * 修改商品经纬度
 */
public class UpdateGoodShopLngLat {
    private BaseDao baseDao = new BaseDao();

    public void dsa(){
      List<String> stringList = baseDao.selectCaddressFromGJCom();
        for (String s : stringList) {
            System.out.println(s);
            LngLat lngLat = GetLngLatUtils.getLngLat(s);
            if (lngLat!=null){
                LngLat lngLat1 = new LngLat();
                lngLat1.setCity(s);
                lngLat1.setLng(lngLat.getLng());
                lngLat1.setLat(lngLat.getLat());
                baseDao.updateLngLatInShop(lngLat1);
            }
        }
    }

    public static void main(String[] args) {
        UpdateGoodShopLngLat updateGoodShopLngLat = new UpdateGoodShopLngLat();
        updateGoodShopLngLat.dsa();
    }
//
//    @Test
//    public void aTes(){
//        try {
//           baseDao.updateDataAll();
//           dsa();
//           baseDao.updateDataAllRemain();
//           baseDao.updatePic();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    @Test
//    public  void fffff(){
//        String[] strings = {"不锈钢管","叉车","打桩机","电力管","镀锌钢","镀锌卷","发动机","防火电缆","防坠器","风管","感温探测器","感烟探测器","固化剂","环氧地坪漆","缓降器","轮胎挖掘机","履带挖掘机","灭火器","墙面漆","乳胶漆","手提式灭火器","推土机","外墙涂料","橡塑板","液压挖掘机","异径管","应急灯","真石漆"};
//
//        //插入数据
////        for (String string : strings) {
////            System.out.println(string);
////            baseDao.inSertGoods(string);
////        }
//        for (String string : strings) {
//            System.out.println(string);
//            baseDao.insertMongodb(string);
//        }
////        baseDao.updateGoods();
////        s删表
////        for (String string : strings) {
////            System.out.println(string);
////            baseDao.dropGoods(string);
////        }
//    }
//
//    @Test
//    public void  bbb(){
//      String a =  zipString("aaassssdddfaffeeewww");
//        System.out.println(a);
//    }
//
//        public static String zipString(String iniString) {
//            // write code here
//            int low = 0 , high = 0 ;
//            int len = iniString.length();
//            StringBuilder sb = new StringBuilder();
//            char c = ' ';
//            int count = 0;
//            while(low < len){
//                high = low;
//                c = iniString.charAt(low);
//                while((high < len)&&(iniString.charAt(high) == c)){
//                    high ++;
//                }
//                count = high - low ;
//                sb.append(c);
//                if (count!=1){
//                    sb.append(count);
//                }
//                low = high;
//            }
//            return (sb.toString().length() < len)?sb.toString():iniString;
//        }
//

}


