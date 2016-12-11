package com.kcode.zhihudaily.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.kcode.zhihudaily.R;
import com.kcode.zhihudaily.base.BaseActivity;

/**
 * Created by caik on 2016/12/11.
 */

public class DetailActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initToolBar("详情", new ToolbarOnClickListener() {
            @Override
            public void onClick() {
                finish();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_detail;
    }

    @Override
    protected void bindView() {

    }
}
