package utils;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;

public enum MongoUtil {

    instance;

    private static MongoClient mongoClient;

    static {
        System.out.println("===============MongoDBUtil初始化========================");
        String ip = "127.0.0.1";
        int port = 27017;
        mongoClient = new MongoClient(ip, port);
        // 大部分用户使用mongodb都在安全内网下，但如果将mongodb设为安全验证模式，就需要在客户端提供用户名和密码：
        // boolean auth = db.authenticate(myUserName, myPassword);
        MongoClientOptions.Builder options = new MongoClientOptions.Builder();
        options.cursorFinalizerEnabled(true);
        // options.autoConnectRetry(true);// 自动重连true
        // options.maxAutoConnectRetryTime(10); // the maximum auto connect retry time
        // 连接池设置为300个连接,默认为100
        options.connectionsPerHost(300);
        // 连接超时，推荐>3000毫秒
        options.connectTimeout(30000);
        options.maxWaitTime(5000);
        // 套接字超时时间，0无限制
        options.socketTimeout(0);
        // 线程队列数，如果连接线程排满了队列就会抛出“Out of semaphores to get db”错误。
        options.threadsAllowedToBlockForConnectionMultiplier(5000);
        options.writeConcern(WriteConcern.SAFE);
        options.build();
    }

    /**
     * 获取DB实例 - 指定DB
     *
     * @param dbName
     * @return
     */
    public MongoDatabase getDB(String dbName) {
        if (dbName != null && !"".equals(dbName)) {
            MongoDatabase database = mongoClient.getDatabase(dbName);
            return database;
        }
        return null;
    }

    /**
     * 获取collection对像
     *
     * @param dbName
     * @param collName
     * @return
     */
    public MongoCollection<org.bson.Document> getCollection(String dbName, String collName) {
        if (null == collName || "".equals(collName)) {
            return null;
        }
        if (null == dbName || "".equals(dbName)) {
            return null;
        }
        MongoCollection<org.bson.Document> collection = mongoClient.getDatabase(dbName).getCollection(collName);
        return collection;

    }

    /**
     * 获取所有DB下的所有表名
     *
     * @param dbName
     * @return
     */
    public List<String> getAllCollections(String dbName) {
        MongoIterable<String> colls = getDB(dbName).listCollectionNames();
        List<String> _list = new ArrayList<>();
        for (String s : colls) {
            _list.add(s);
        }
        return _list;
    }

    /**
     * 获取所有数据库名称列表
     *
     * @return
     */
    public MongoIterable<String> gerAllDBNames() {
        MongoIterable<String> s = mongoClient.listDatabaseNames();
        return s;
    }

    /**
     * 删除数据库
     *
     * @param dbName
     */
    public void dropDB(String dbName) {
        getDB(dbName).drop();
    }

    /**
     * 查找对像 - 根据主键_id
     */
    public Document findById(MongoCollection<Document> coll, String id) {
        ObjectId _idobj = null;
        try {
            _idobj = new ObjectId(id);
        } catch (Exception e) {
            return null;
        }
        Document myDoc = coll.find(Filters.eq("_id", _idobj)).first();
        return myDoc;
    }

    /**
     * 统计数
     *
     * @param coll
     * @return
     */
    public int getCount(MongoCollection<Document> coll) {
        int count = (int) coll.count();
        return count;
    }

    /**
     * 条件查询
     */
    public MongoCursor<Document> find(MongoCollection<Document> coll, Bson filter) {
        return coll.find(filter).iterator();
    }

    /**
     * 通过id 删除数据
     * @param coll
     * @param id
     * @return
     */
    public int deleteById(MongoCollection<Document> coll, String id) {
        int count = 0;
        ObjectId _id = null;
        try {
            _id = new ObjectId(id);
        } catch (Exception e) {
            return 0;
        }
        Bson filter = Filters.eq("_id", _id);
        DeleteResult deleteResult = coll.deleteOne(filter);
        count = (int) deleteResult.getDeletedCount();
        return count;
    }

    /**
     * 删除集合
     * @param dbName
     * @param collName
     */
    public void dropCollection(String dbName, String collName) {
        getDB(dbName).getCollection(collName).drop();
    }

    /**
     * 关闭mongodb
     */
    public void close() {
        if (mongoClient != null) {
            mongoClient.close();
            mongoClient = null;
        }
    }


}
