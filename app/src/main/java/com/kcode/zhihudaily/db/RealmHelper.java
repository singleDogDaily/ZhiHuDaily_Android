package com.kcode.zhihudaily.db;

import com.kcode.zhihudaily.bean.LatestNews;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmModel;
import io.realm.RealmQuery;

/**
 * realm帮助类
 * Created by caik on 2016/11/3.
 */

public class RealmHelper {

    private final static String REALM_NAME = "data.realm";

    private static RealmHelper instance;

    private static Realm mRealm;

    private RealmHelper() {
    }

    public static RealmHelper getInstance() {
        if (instance == null) {
            instance = new RealmHelper();

            mRealm = Realm.getInstance(new RealmConfiguration.Builder()
                    .name(REALM_NAME)
                    .build());
        }

        return instance;
    }

    public <E extends RealmModel> void insertOrUpdate(E e) {
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(e);
        mRealm.commitTransaction();
    }

    public LatestNews getLatestNews(){
        mRealm.beginTransaction();
        mRealm.delete(LatestNews.class);
        RealmQuery<LatestNews> query =  mRealm.where(LatestNews.class);
        mRealm.commitTransaction();
        List<LatestNews> result = query.findAll();

        return (result == null || result.size() == 0) ? null : query.findAll().get(0);
    }
}
