package com.kcode.zhihudaily.theme;

import com.kcode.zhihudaily.bean.ThemeData;
import com.kcode.zhihudaily.net.HttpHelper;
import com.kcode.zhihudaily.net.Response;
import com.kcode.zhihudaily.utils.L;
import com.kcode.zhihudaily.utils.LogFactory;

/**
 * Created by caik on 2016/11/28.
 */

public class ThemePresenter implements ThemeContract.Presenter {
    private static final L log = LogFactory.create(ThemePresenter.class);
    private ThemeContract.View mView;

    public ThemePresenter(ThemeContract.View mView,int themeId) {
        this.mView = mView;
        this.mView.setPresenter(this);
        loadData(themeId);

    }

    @Override
    public void start() {

    }

    @Override
    public void loadData(int id) {
        HttpHelper.getThemeData(id, new Response<ThemeData>() {
            @Override
            public void onSuccess(ThemeData themeData) {
               mView.setupContent(themeData);
            }

            @Override
            public void onFailed(String msg) {

            }
        });
    }
}
