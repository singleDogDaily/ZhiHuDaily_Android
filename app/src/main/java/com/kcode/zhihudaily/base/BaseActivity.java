package com.kcode.zhihudaily.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by caik on 2016/10/27.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();
        bindView();
    }

    protected abstract void setContentView();
    protected abstract void bindView();

    protected <T extends View> T $(int id){
        return (T) findViewById(id);
    }
}
