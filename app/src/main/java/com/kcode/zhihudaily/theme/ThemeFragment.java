package com.kcode.zhihudaily.theme;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.kcode.zhihudaily.R;
import com.kcode.zhihudaily.base.BaseFragment;
import com.kcode.zhihudaily.bean.Other;
import com.kcode.zhihudaily.bean.ThemeData;
import com.kcode.zhihudaily.utils.ImageLoader;

/**
 * Created by caik on 2016/11/25.
 */

public class ThemeFragment extends BaseFragment implements ThemeContract.View,ThemeAdapter.OnItemClickListener{

    private final static String THEME = "theme";

    private ThemeContract.Presenter mPresenter;
    private Other theme;
    private ImageView mImgThemeIcon;
    private RecyclerView mRecyclerView;
    private ThemeAdapter mAdapter;

    public static ThemeFragment newInstance(Other theme) {
        
        Bundle args = new Bundle();
        args.putSerializable(THEME,theme);
        
        ThemeFragment fragment = new ThemeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            theme = (Other) getArguments().getSerializable(THEME);
        }

        if (theme == null) {
            return;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_theme,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mImgThemeIcon = (ImageView) view.findViewById(R.id.themeView);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        initRecyclerView();

        new ThemePresenter(this,theme.getId());
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new ThemeAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void changeTheme(Other theme) {
        mPresenter.loadData(theme.getId());
    }

    @Override
    public void setPresenter(ThemeContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void setupContent(ThemeData themeData) {
        setupThemeIcon(themeData.getBackground());
        mAdapter.init(themeData.getStories());
    }

    private void setupThemeIcon(String imageUrl) {
        ImageLoader.getInstance().load(this,imageUrl,mImgThemeIcon);
    }
}
