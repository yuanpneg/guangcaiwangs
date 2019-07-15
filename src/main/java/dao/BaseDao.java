package dao;

import bean.*;
import com.google.gson.Gson;
import org.apache.ibatis.session.SqlSession;
import pojo.*;
import pojo.Alias;

import java.util.List;
import java.util.Map;

public class BaseDao {


    public List<Details> selectDetails() {
        SqlSession sqlSession = null;
        List<Details> result = null;
        try {
            //调用
            sqlSession = MyBatisUtils.openSession(false);
            BaseMapper mapper = sqlSession.getMapper(BaseMapper.class);
            return mapper.selectDetails();
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
        return result;
    }



    //获取详情
    public void updateDetail(Details details) {
        //创建一个session对象为空
        SqlSession sqlSession = null;
        try {
            //调用
            sqlSession = MyBatisUtils.openSession(false);
            BaseMapper mapper = sqlSession.getMapper(BaseMapper.class);
            mapper.updateDetail(details);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }

    }



    /**
     * 查询钢筋公司地址
     *
     * @return
     */
    public List<String> selectCaddressFromGJCom() {
        SqlSession sqlSession = null;
        try {
            //openSession底层就是做各种成员变量的初始化
            sqlSession = MyBatisUtils.openSession(false);
            //创建对象 引入sql映射
            BaseMapper mapper = sqlSession.getMapper(BaseMapper.class);
            //返回通过sql语句查询出来的结果集
            return mapper.selectCaddressFromGJCom();

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            //无论执行成功与否  都需要手动关闭session
            MyBatisUtils.closeSession(sqlSession);

        }
        return null;
    }

    /**
     * 更改钢筋商铺经纬度
     *
     * @param lngLat
     */
    public void updateLngLatInShop(LngLat lngLat) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession(false);
            BaseMapper mapper = sqlSession.getMapper(BaseMapper.class);
            mapper.updateLngLatInShop(lngLat);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            MyBatisUtils.closeSession(sqlSession);

        }
    }

    /**
    * 插入到数据库的详情信息的主表的
    */
    public void insertTpDetail(Details details) {
        //创建一个session对象为空
        SqlSession sqlSession = null;
        try {
            //调用
            sqlSession = MyBatisUtils.openSession(false);
            BaseMapper mapper = sqlSession.getMapper(BaseMapper.class);
            mapper.insertTpDetail(details);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }

    }

    public void insertkey(Keys keys) {
        //创建一个session对象为空
        SqlSession sqlSession = null;
        try {
            //调用
            sqlSession = MyBatisUtils.openSession(false);
            BaseMapper mapper = sqlSession.getMapper(BaseMapper.class);
            mapper.insertkey(keys);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }

    }

    public void insertlist(AlibbList alibbList) {
        //创建一个session对象为空
        SqlSession sqlSession = null;
        try {
            //调用
            sqlSession = MyBatisUtils.openSession(false);
            BaseMapper mapper = sqlSession.getMapper(BaseMapper.class);
            mapper.insertlist(alibbList);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }

    }

    public void insertvalue(Values values) {
        //创建一个session对象为空
        SqlSession sqlSession = null;
        try {
            //调用
            sqlSession = MyBatisUtils.openSession(false);
            BaseMapper mapper = sqlSession.getMapper(BaseMapper.class);
            mapper.insertvalue(values);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }

    }
    /**
     * 插入类型
     */
    public void categoryInsert(Category category) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession(false);
            BaseMapper mapper = sqlSession.getMapper(BaseMapper.class);
            mapper.replaceCategory(category);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }

    //查询三级mappingid
    public List<Noneunit> selectSanJiLevel(String query) {
        SqlSession sqlSession = null;
        List<Noneunit> result = null;
        try {
            sqlSession = MyBatisUtils.openSession(false);
            BaseMapper mapper = sqlSession.getMapper(BaseMapper.class);
            result = mapper.selectSanJiLevel(query);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }

        return result;
    }

    //获取详情
    public void insertAlias(Alias alias) {
        //创建一个session对象为空
        SqlSession sqlSession = null;
        try {
            //调用
            sqlSession = MyBatisUtils.openSession(false);
            BaseMapper mapper = sqlSession.getMapper(BaseMapper.class);
            mapper.insertAlias(alias);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }

    }

    public int selectVarietyExist(String pinzhongid) {
        SqlSession sqlSession = null;
        Integer result = null;
        try {
            sqlSession = MyBatisUtils.openSession(false);
            BaseMapper mapper = sqlSession.getMapper(BaseMapper.class);
            result = mapper.selectVarietyExist(pinzhongid);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
        return result;
    }


    public int selectVarietyName(String title) {
        SqlSession sqlSession = null;
        Integer result = null;
        try {
            sqlSession = MyBatisUtils.openSession(false);
            BaseMapper mapper = sqlSession.getMapper(BaseMapper.class);
            result = mapper.selectVarietyName(title);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
        return result;
    }

    /**
     * 修改页数
     *
     * @param noneunit
     */
    public void updatePage(Noneunit noneunit) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession(false);
            BaseMapper mapper = sqlSession.getMapper(BaseMapper.class);
            mapper.updatePage(noneunit);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }

    public void insertUnitKey(UnitKey unitKey) {
        //创建一个session对象为空
        SqlSession sqlSession = null;
        try {
            //调用
            sqlSession = MyBatisUtils.openSession(false);
            BaseMapper mapper = sqlSession.getMapper(BaseMapper.class);
            mapper.insertUnitKey(unitKey);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }

    public void insertUnitValue(UnitValue unitValue) {
        //创建一个session对象为空
        SqlSession sqlSession = null;
        try {
            //调用
            sqlSession = MyBatisUtils.openSession(false);
            BaseMapper mapper = sqlSession.getMapper(BaseMapper.class);
            mapper.insertUnitValue(unitValue);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }

    //查询三级分类
    public List<Noneunit> selectNoneunit(int level) {
        //创建一个session对象为空
        SqlSession sqlSession = null;
        List<Noneunit> result = null;
        try {
            //调用
            sqlSession = MyBatisUtils.openSession(false);
            BaseMapper mapper = sqlSession.getMapper(BaseMapper.class);
            result = mapper.selectNoneunit(level);
            return result;
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
        return null;
    }


    /**
     * 查询品种表
     *
     * @return
     */
    public List<Variety> selectVarietyList() {
        SqlSession sqlSession = null;
        try {
            //openSession底层就是做各种成员变量的初始化
            sqlSession = MyBatisUtils.openSession(false);
            //创建对象 引入sql映射
            BaseMapper mapper = sqlSession.getMapper(BaseMapper.class);
            //返回通过sql语句查询出来的结果集
            return mapper.selectVarietyList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //无论执行成功与否  都需要手动关闭session
            MyBatisUtils.closeSession(sqlSession);
        }
        return null;
    }

    public List<Map<String, Object>> selectMappings() {
        SqlSession sqlSession = null;
        try {
            //openSession底层就是做各种成员变量的初始化
            sqlSession = MyBatisUtils.openSession(false);
            //创建对象 引入sql映射
            BaseMapper mapper = sqlSession.getMapper(BaseMapper.class);
            //返回通过sql语句查询出来的结果集
            return mapper.selectMappings();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //无论执行成功与否  都需要手动关闭session
            MyBatisUtils.closeSession(sqlSession);
        }
        return null;
    }

    //插入品种表
    public void insertPinZhong(Variety variety) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession(false);
            BaseMapper mapper = sqlSession.getMapper(BaseMapper.class);
            mapper.insertPinZhong(variety);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }

    /**
     * 插入类型
     */
    public void attributeInsert(Attribute attr) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession(false);
            BaseMapper mapper = sqlSession.getMapper(BaseMapper.class);
            mapper.insertAttribute(attr);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }

    public void insertType(Type type) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession(false);
            BaseMapper mapper = sqlSession.getMapper(BaseMapper.class);
            mapper.insertType(type);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }

    /**
     * 插入类型
     */
    public void insertUnit(Unit unit) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession(false);
            BaseMapper mapper = sqlSession.getMapper(BaseMapper.class);
            mapper.insertUnit(unit);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }

    /**
     * 删除类型
     */
    public void deleteCategory(String delete) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession(false);
            BaseMapper mapper = sqlSession.getMapper(BaseMapper.class);
            mapper.deleteCategory(delete);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }


    /**
     * 插入产品
     */
    public void produceInsert(List<Produce> produces) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession(false);
            BaseMapper mapper = sqlSession.getMapper(BaseMapper.class);
            mapper.insertProduceList(produces);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
            System.out.println(new Gson().toJson(produces));
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }

    public List<Category> selectCategoryList(String query) {
        SqlSession sqlSession = null;
        List<Category> result = null;
        try {
            sqlSession = MyBatisUtils.openSession(false);
            BaseMapper mapper = sqlSession.getMapper(BaseMapper.class);
            result = mapper.selectCategoryList(query);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }

        return result;
    }


    public List<Type> selectTypeList(int catid) {
        SqlSession sqlSession = null;
        List<Type> result = null;
        try {
            sqlSession = MyBatisUtils.openSession(false);
            BaseMapper mapper = sqlSession.getMapper(BaseMapper.class);
            result = mapper.selectTypeList(catid);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }

        return result;
    }

    public int selectCategoryExists(Integer parentId, String title) {
        SqlSession sqlSession = null;
        Integer result = null;
        try {
            sqlSession = MyBatisUtils.openSession(false);
            BaseMapper mapper = sqlSession.getMapper(BaseMapper.class);
            result = mapper.selectCategoryExists(parentId, title);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
        return result;
    }


    public void changeExecute(int catId) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession(false);
            BaseMapper mapper = sqlSession.getMapper(BaseMapper.class);
            mapper.changeExecute(catId);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }

    }

    public List<String> selectNames(String pinZhong) {
        SqlSession sqlSession = null;
        List<String> result = null;
        try {
            sqlSession = MyBatisUtils.openSession(false);
            BaseMapper mapper = sqlSession.getMapper(BaseMapper.class);
            result = mapper.selectNames(pinZhong);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
        return result;
    }

    public List<String> selectUnits(String pinZhong) {
        SqlSession sqlSession = null;
        List<String> result = null;
        try {
            sqlSession = MyBatisUtils.openSession(false);
            BaseMapper mapper = sqlSession.getMapper(BaseMapper.class);
            result = mapper.selectUnits(pinZhong);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
        return result;
    }

    public void deleteCategoryByParentId(int id) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession(false);
            BaseMapper mapper = sqlSession.getMapper(BaseMapper.class);
            mapper.deleteCategoryByParentId(id);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }

    public void deleteProduceByCatId(int id) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession(false);
            BaseMapper mapper = sqlSession.getMapper(BaseMapper.class);
            mapper.deleteProduceByCatId(id);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }

    public void deleteUnitByCatId(int id) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession(false);
            BaseMapper mapper = sqlSession.getMapper(BaseMapper.class);
            mapper.deleteUnitByCatId(id);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }

}
