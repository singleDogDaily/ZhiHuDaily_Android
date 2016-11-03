package com.kcode.zhihudaily.main;

import com.kcode.zhihudaily.bean.LatestNews;
import com.kcode.zhihudaily.db.RealmHelper;
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
    public void loadData(final boolean isRefresh) {
        mView.showLoading();
        HttpHelper.getLatestNews(new Response<LatestNews>() {
            @Override
            public void onSuccess(LatestNews latestNews) {
                mView.setUpRecyclerView(latestNews.getStories(),isRefresh);
                mView.setUpViewPager(latestNews.getTop_stories());
                mView.dismissLoading();
                if (isRefresh) {
                    mView.onFinishRefresh();
                }
            }

            @Override
            public void onFailed(String msg) {
                mView.dismissLoading();
            }
        });
    }

    @Override
    public void onRefresh() {
        loadData(true);
    }

    @Override
    public void loadMore(String date) {
        HttpHelper.getBeforeNews(date,new Response<LatestNews>() {
            @Override
            public void onSuccess(LatestNews latestNews) {
                mView.setUpRecyclerView(latestNews.getStories(),false);
            }

            @Override
            public void onFailed(String msg) {
                mView.dismissLoading();
            }
        });
    }

    @Override
    public LatestNews loadFromRealm() {
        return RealmHelper.getInstance().getLatestNews();
    }

    @Override
    public void start() {
        loadData(false);
    }
}
