package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class GreendaoMyClass {
    public static void main(String[] args) {
        //创建数据库的图表
        Schema schema = new Schema(1, "com.lanou3g.an.carhome");
        //添加方法
        addData(schema);

        try {
            new DaoGenerator().generateAll(schema,"./app/src/main/java");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addData(Schema schema) {
        //添加表名
        Entity entity = schema.addEntity("Collection");
        //加入id,并且自增
        entity.addIdProperty().autoincrement().primaryKey();
        entity.addStringProperty("title");
        entity.addStringProperty("url");
        entity.addStringProperty("imageUrl");
    }
}
