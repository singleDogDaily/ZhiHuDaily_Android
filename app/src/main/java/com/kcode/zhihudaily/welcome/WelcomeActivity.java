package com.kcode.zhihudaily.welcome;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.kcode.zhihudaily.R;
import com.kcode.zhihudaily.base.BaseActivity;

/**
 * Created by caik on 2016/10/27.
 */

public class WelcomeActivity extends BaseActivity implements WelcomeContract.View{

    private ImageView mStartImage;
    private WelcomeContract.Presenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new WelcomePresenter(this);
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
        Glide.with(this).load(img).centerCrop().into(mStartImage);
    }
}
