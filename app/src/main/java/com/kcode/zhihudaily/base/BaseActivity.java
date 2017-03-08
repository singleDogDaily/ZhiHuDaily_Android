package com.kcode.zhihudaily.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by caik on 2016/10/27.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getLayoutId() <= 0) {
            throw new RuntimeException("布局文件没有指定");
        }
        setContentView(getLayoutId());
        bindView();
    }

    protected abstract int getLayoutId();

    protected void initToolBar(int toolbarId,String msg) {
        Toolbar toolbar = $(toolbarId);
        if (toolbar != null) {
            toolbar.setTitle(msg);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    protected abstract void bindView();

    protected <T extends View> T $(int id) {
        return (T) findViewById(id);
    }

    protected void startActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

    protected void startActivityAndFinish(Class<?> cls) {
        startActivity(cls);
        finish();
    }

}
