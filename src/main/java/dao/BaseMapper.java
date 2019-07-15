package dao;

import bean.*;
import org.apache.ibatis.annotations.Param;
import pojo.*;
import pojo.Alias;

import java.util.List;
import java.util.Map;

public interface BaseMapper {

    void replaceCategory(@Param("category") Category category);

    void insertProduceList(List<Produce> produces);

    List<Category> selectCategoryList(@Param("query") String query);

    void deleteCategory(@Param("query") String query);

    void insertAttribute(@Param("attribute") Attribute attribute);

    void insertUnit(@Param("unit") Unit unit);

    void changeExecute(@Param("id") int id);

    List<String> selectNames(@Param("pinzhong") String pinZhong);

    Integer selectCategoryExists(@Param("parentId") int parentId, @Param("title") String title);

    List<String> selectUnits(@Param("pinzhong") String pinZhong);


    void deleteCategoryByParentId(@Param("id") int id);

    void deleteProduceByCatId(@Param("id") int id);

    void deleteUnitByCatId(@Param("id") int id);

    //插入tp_type表数据
    void insertType(Type type);

    //查询tp_type的catid
    List<Type> selectTypeList(int catid);

    List<Map<String, Object>> selectMappings();

    //查询品种是否存在
    Integer selectVarietyExist(String pinzhongid);

    Integer selectVarietyName(String title);

    //插入品种表
    void insertPinZhong(Variety variety);

    //获取详情
    void insertAlias(Alias alias);

    List<Noneunit> selectSanJiLevel(String query);

    //修改页数
    void updatePage(Noneunit noneunit);

    //查询品种表数据
    List<Variety> selectVarietyList();

    //插入
    void insertUnitKey(UnitKey unitKey);

    void insertUnitValue(UnitValue unitValue);

    List<Noneunit> selectNoneunit(int query);

    void insertTpDetail(Details details);

    void insertkey(Keys keys);

    void insertvalue(Values values);

    void insertlist(AlibbList alibbList);

    List<String> selectCaddressFromGJCom();

    void updateLngLatInShop(LngLat lngLat);

    //查询未有参数的数据
    List<Details> selectDetails();

    void updateDetail(Details details);
}
