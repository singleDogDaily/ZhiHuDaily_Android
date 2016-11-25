package com.kcode.zhihudaily.main;

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

public class ThemeFragment extends BaseFragment {

    private final static String THEME = "theme";

    public static ThemeFragment newInstance(Other theme) {
        
        Bundle args = new Bundle();
        args.putSerializable(THEME,theme);
        
        ThemeFragment fragment = new ThemeFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_theme,container,false);
    }

    public void changeTheme(Other theme) {

    }
}
