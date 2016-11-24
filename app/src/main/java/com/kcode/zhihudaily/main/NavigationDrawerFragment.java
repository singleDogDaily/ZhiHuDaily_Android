package com.kcode.zhihudaily.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kcode.zhihudaily.R;
import com.kcode.zhihudaily.base.BaseFragment;
import com.kcode.zhihudaily.bean.Other;
import com.kcode.zhihudaily.utils.L;
import com.kcode.zhihudaily.utils.LogFactory;

import java.util.List;

/**
 * Created by caik on 2016/10/30.
 */

public class NavigationDrawerFragment extends BaseFragment implements NavigationDrawerContract.View,NavigationDrawerAdapter.OnNavigationItemClickListener {

    private final static L log = LogFactory.create(NavigationDrawerFragment.class);

    private RecyclerView mRecyclerView;
    private NavigationDrawerAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.drawer_main,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new NavigationDrawerAdapter(getContext(),this);
        mRecyclerView.setAdapter(mAdapter);

        new NavigationDrawerPresenter(this);
    }

    @Override
    public void setupRecyclerView(List<Other> others) {
        mAdapter.init(others);
    }

    @Override
    public void setPresenter(NavigationDrawerContract.Presenter presenter) {

    }

    @Override
    public void onItemClick(int position, Other other) {
        ((MainActivity)getActivity()).closeDrawer(Gravity.LEFT);
    }
}
