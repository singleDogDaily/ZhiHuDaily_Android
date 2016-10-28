package com.kcode.zhihudaily.welcome;

import com.kcode.zhihudaily.bean.Welcome;
import com.kcode.zhihudaily.net.HttpHelper;

/**
 * Created by caik on 2016/10/27.
 */

public class WelcomePresenter implements WelcomeContract.Presenter {

    private WelcomeContract.View mView;

    public WelcomePresenter(WelcomeContract.View mView) {
        this.mView = mView;
        this.mView.setPresenter(this);
    }

    @Override
    public void start() {

        HttpHelper.getStartImage(new com.kcode.zhihudaily.net.Response<Welcome>() {
            @Override
            public void onSuccess(Welcome welcome) {
                mView.loadImage(welcome.getImg());
            }

            @Override
            public void onFailed(String msg) {
                mView.loadFailed();
            }
        });
    }
}
