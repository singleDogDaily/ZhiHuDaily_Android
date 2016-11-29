package com.kcode.zhihudaily.editor;

import android.os.Bundle;

import com.kcode.zhihudaily.R;
import com.kcode.zhihudaily.base.BaseActivity;

public class EditorActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_editor);
        initToolBar("主编", new ToolbarOnClickListener() {
            @Override
            public void onClick() {
                finish();
            }
        });
    }

    @Override
    protected void bindView() {

    }
}
