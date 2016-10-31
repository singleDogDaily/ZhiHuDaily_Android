package com.kcode.zhihudaily.main;

import com.kcode.zhihudaily.base.BasePresenter;
import com.kcode.zhihudaily.base.BaseView;
import com.kcode.zhihudaily.bean.Story;
import com.kcode.zhihudaily.bean.TopStory;

import java.util.List;

/**
 * Created by caik on 2016/10/30.
 */

public interface MainContract {
    interface View extends BaseView<Presenter>{
        /**
         * 初始化滚动图片
         * @param urls  图片url地址
         */
        void setUpViewPager(List<TopStory> urls);
        /**开始加载*/
        void showLoading();
        /**加载结束*/
        void dismissLoading();
        /**展示列表数据*/
        void setUpRecyclerView(List<Story> stories,boolean isRefresh);
        /**刷新完成*/
        void onFinishRefresh();
    }

    interface Presenter extends BasePresenter{
        void loadData(boolean isRefresh);

        void onRefresh();
    }
}
