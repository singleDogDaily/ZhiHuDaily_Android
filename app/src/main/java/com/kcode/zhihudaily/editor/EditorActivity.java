package com.kcode.zhihudaily.editor;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kcode.zhihudaily.R;
import com.kcode.zhihudaily.base.BaseActivity;
import com.kcode.zhihudaily.bean.Editor;

import java.util.List;

public class EditorActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    private List<Editor> editors;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String data = getIntent().getStringExtra("editor");
        Gson gson = new Gson();
        editors = gson.fromJson(data, new TypeToken<List<Editor>>() {}.getType());
//        new BaseAdapter<Editor>(mRecyclerView,getApplicationContext(),1,editors){
//
//            @Override
//            public void setupViewHolder(ViewHolder holder, Editor editor) {
//                ImageView icon = holder.findViewById(R.id.icon);
//                ImageLoader.getInstance().loadCircleView(getApplicationContext(), editor.getAvatar(), icon);
//                TextView name = holder.findViewById(R.id.name);
//                name.setText(editor.getName());
//                TextView bio = holder.findViewById(R.id.bio);
//                bio.setText(editor.getBio());
//            }
//        };
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
        mRecyclerView = $(R.id.recyclerView);
    }
}
