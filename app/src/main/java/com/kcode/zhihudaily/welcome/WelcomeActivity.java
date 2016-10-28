package com.kcode.zhihudaily.welcome;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.kcode.zhihudaily.R;
import com.kcode.zhihudaily.base.BaseActivity;
import com.kcode.zhihudaily.main.MainActivity;
import com.kcode.zhihudaily.utils.ImageLoader;

/**
 * Created by caik on 2016/10/27.
 */

public class WelcomeActivity extends BaseActivity implements WelcomeContract.View {

    private ImageView mStartImage;
    private WelcomeContract.Presenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setFullScreen();
        new WelcomePresenter(this);
    }

    private void setFullScreen() {
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_welcome);
    }

    @Override
    protected void bindView() {
        mStartImage = $(R.id.img_start);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(WelcomeContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void loadImage(String img) {
        ImageLoader.getInstance().load(this,img,mStartImage);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(0);
            }
        },3000);
    }

    @Override
    public void loadFailed() {
        //图片加载失败后直接进入主页面
        start2Main();
    }

    private void start2Main(){
        startActivityAndFinish(MainActivity.class);
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            start2Main();
        }
    };


}
