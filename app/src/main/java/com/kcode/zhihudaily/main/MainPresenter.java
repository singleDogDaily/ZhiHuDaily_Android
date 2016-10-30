package com.kcode.zhihudaily.main;

import com.kcode.zhihudaily.bean.LatestNews;
import com.kcode.zhihudaily.net.HttpHelper;
import com.kcode.zhihudaily.net.Response;

/**
 * Created by caik on 2016/10/30.
 */

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mView;

    public MainPresenter(MainContract.View mView) {
        this.mView = mView;
        this.mView.setPresenter(this);
    }

    @Override
    public void loadData() {
        mView.showLoading();
        HttpHelper.getLatestNews(new Response<LatestNews>() {
            @Override
            public void onSuccess(LatestNews latestNews) {
                mView.setUpViewPager(latestNews.getTop_stories());
                mView.setUpRecyclerView();
                mView.dismissLoading();
            }

            @Override
            public void onFailed(String msg) {
                mView.dismissLoading();
            }
        });
    }

    @Override
    public void start() {

    }
}
