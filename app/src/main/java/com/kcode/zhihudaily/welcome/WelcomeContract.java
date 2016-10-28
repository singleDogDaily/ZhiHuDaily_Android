package com.kcode.zhihudaily.welcome;

import com.kcode.zhihudaily.base.BasePresenter;
import com.kcode.zhihudaily.base.BaseView;

/**
 * Created by caik on 2016/10/27.
 */

public interface WelcomeContract {

    interface View extends BaseView<Presenter>{

        void loadImage(String img);

        void loadFailed();
    }

    interface Presenter extends BasePresenter{

    }
}
