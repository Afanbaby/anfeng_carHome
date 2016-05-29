package com.lanou3g.an.carhome.article;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.lanou3g.an.carhome.CollectionDao;
import com.lanou3g.an.carhome.DaoMaster;
import com.lanou3g.an.carhome.DaoSession;
import com.lanou3g.an.carhome.main.MyApplication;

/**
 * Created by anfeng on 16/5/23.
 * GreendaoSingle单例
 */
public class GreendaoSingle {

    private SQLiteDatabase db;//数据库
    private DaoMaster daoMaster;//管理者
    private DaoSession daoSession;//回话者
    private CollectionDao collectionDao;//数据库内相应表的操作对象
    private Context context;
    private DaoMaster.DevOpenHelper helper;

    public DaoMaster.DevOpenHelper getHelper() {
        if (helper == null) {
            helper = new DaoMaster.DevOpenHelper(context, "Collection.db", null);
        }
        return helper;
    }


    private static GreendaoSingle ourInstance = new GreendaoSingle();

    public static GreendaoSingle getInstance() {
        return ourInstance;
    }

    private GreendaoSingle() {
        context = MyApplication.getContext();
    }

    public SQLiteDatabase getDb() {
        if (db == null) {
            db = getHelper().getWritableDatabase();
        }
        return db;
    }

    public DaoMaster getDaoMaster() {
        if (daoMaster == null) {
            daoMaster = new DaoMaster(getDb());
        }
        return daoMaster;
    }

    public DaoSession getDaoSession() {
        if (daoSession == null) {
            daoSession = getDaoMaster().newSession();
        }
        return daoSession;
    }

    public CollectionDao getCollectionDao() {
        if (collectionDao == null) {
            collectionDao = getDaoSession().getCollectionDao();
        }
        return collectionDao;
    }

}
