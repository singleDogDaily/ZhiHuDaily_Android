package com.kcode.zhihudaily.main;

import android.os.Bundle;

import com.kcode.zhihudaily.R;
import com.kcode.zhihudaily.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initToolBar("首页");

    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void bindView() {

    }
}
