package com.kcode.zhihudaily.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.kcode.zhihudaily.R;

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

    protected void initToolBar(String msg,int icon){
        Toolbar toolbar = $(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            toolbar.setTitle(msg);
            toolbar.setLogo(icon);
        }
    }

    protected abstract void setContentView();
    protected abstract void bindView();

    protected <T extends View> T $(int id){
        return (T) findViewById(id);
    }

    protected void startActivity(Class<?> cls){
        Intent intent = new Intent(this,cls);
        startActivity(intent);
    }

    protected void startActivityAndFinish(Class<?> cls){
        startActivity(cls);
        finish();
    }
}
