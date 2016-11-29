package com.kcode.zhihudaily.theme;

import com.kcode.zhihudaily.base.BasePresenter;
import com.kcode.zhihudaily.base.BaseView;
import com.kcode.zhihudaily.bean.ThemeData;

/**
 * Created by caik on 2016/11/28.
 */

public interface ThemeContract {

    interface View extends BaseView<Presenter>{
        void setupContent(ThemeData themeData);
    }

    interface Presenter extends BasePresenter{
        void loadData(int id);
    }
}
