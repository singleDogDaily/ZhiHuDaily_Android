package com.kcode.zhihudaily.main;

import android.util.Log;

import com.kcode.zhihudaily.bean.Other;
import com.kcode.zhihudaily.net.HttpHelper;
import com.kcode.zhihudaily.net.Response;

import java.util.List;

/**
 * Created by caik on 2016/11/15.
 */

public class NavigationDrawerPresenter implements NavigationDrawerContract.Presenter {

    private static final String TAG = "NavigationDrawerPresent";

    private NavigationDrawerContract.View mView;

    public NavigationDrawerPresenter(NavigationDrawerContract.View mView) {
        this.mView = mView;
        this.mView.setPresenter(this);

        loadData();
    }

    @Override
    public void loadData() {
        HttpHelper.getThemes(new Response<List<Other>>() {
            @Override
            public void onSuccess(List<Other> others) {
                mView.setupRecyclerView(others);
            }

            @Override
            public void onFailed(String msg) {
                Log.d(TAG, msg);
            }
        });
    }

    @Override
    public void start() {

    }
}
