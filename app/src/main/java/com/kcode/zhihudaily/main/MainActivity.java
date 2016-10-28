package com.kcode.zhihudaily.main;

import android.os.Bundle;

import com.kcode.zhihudaily.R;
import com.kcode.zhihudaily.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main);
        initToolBar("首页",R.drawable.ic_menu_black_24dp);
    }

    @Override
    protected void bindView() {

    }
}
