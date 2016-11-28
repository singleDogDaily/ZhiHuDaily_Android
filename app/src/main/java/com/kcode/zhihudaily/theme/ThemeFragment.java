package com.kcode.zhihudaily.theme;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kcode.zhihudaily.R;
import com.kcode.zhihudaily.base.BaseFragment;
import com.kcode.zhihudaily.bean.Other;

/**
 * Created by caik on 2016/11/25.
 */

public class ThemeFragment extends BaseFragment implements ThemeContract.View{

    private final static String THEME = "theme";

    private ThemeContract.Presenter mPresenter;
    private Other theme;

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
        new ThemePresenter(this,theme.getId());
    }

    public void changeTheme(Other theme) {
        mPresenter.loadData(theme.getId());
    }

    @Override
    public void setPresenter(ThemeContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
