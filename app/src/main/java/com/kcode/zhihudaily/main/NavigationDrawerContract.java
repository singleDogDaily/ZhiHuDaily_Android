package com.kcode.zhihudaily.main;

import com.kcode.zhihudaily.base.BasePresenter;
import com.kcode.zhihudaily.base.BaseView;
import com.kcode.zhihudaily.bean.Other;

import java.util.List;

/**
 * Created by caik on 2016/11/15.
 */

public interface NavigationDrawerContract {
    interface View extends BaseView<Presenter>{
        void setupRecyclerView(List<Other> others);
    }

    interface Presenter extends BasePresenter{
        void loadData();
    }


}
