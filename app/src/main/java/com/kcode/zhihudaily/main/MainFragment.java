package com.kcode.zhihudaily.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.kcode.autoscrollviewpager.view.AutoScrollViewPager;
import com.kcode.autoscrollviewpager.view.BaseViewPagerAdapter;
import com.kcode.zhihudaily.R;
import com.kcode.zhihudaily.base.BaseFragment;
import com.kcode.zhihudaily.bean.Story;
import com.kcode.zhihudaily.bean.TopStory;
import com.kcode.zhihudaily.utils.ImageLoader;
import com.kcode.zhihudaily.utils.L;
import com.kcode.zhihudaily.utils.LogFactory;

import java.util.List;

/**
 * Created by caik on 2016/10/30.
 */

public class MainFragment extends BaseFragment implements MainContract.View ,SwipeRefreshLayout.OnRefreshListener,MainStoryAdapter.OnItemClickListener{

    private final static L log = LogFactory.create(MainFragment.class);

    private MainContract.Presenter mPresenter;
    private ProgressBar mProgressBar;
    private AutoScrollViewPager mViewPager;
    private BaseViewPagerAdapter<TopStory> mAdapter;

    private RecyclerView mRecyclerView;
    private MainStoryAdapter mMainStoryAdapter;

    private SwipeRefreshLayout mSwipeLayout;

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewPager = (AutoScrollViewPager) view.findViewById(R.id.viewPager);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mSwipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeLayout);
        mSwipeLayout.setOnRefreshListener(this);

        initViewPager();
        initRecyclerView();

        mPresenter.start();

    }

    private void initRecyclerView() {
        mMainStoryAdapter = new MainStoryAdapter(getContext(),this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mMainStoryAdapter);
    }

    private void initViewPager(){
        mAdapter = new BaseViewPagerAdapter<TopStory>(getActivity(),mListener) {
            @Override
            public void loadImage(ImageView view, int position, TopStory topStory) {
                ImageLoader.getInstance().load(MainFragment.this, topStory.getImage(), view);
            }

            @Override
            public void setSubTitle(TextView textView, int position, TopStory topStory) {
                textView.setText(topStory.getTitle());
            }
        };

        mViewPager.setAdapter(mAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        mViewPager.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mViewPager.onPause();
    }

    @Override
    public void setUpViewPager(final List<TopStory> topStories) {
        if (mAdapter != null) {
            mAdapter.init(topStories);
        }
    }

    @Override
    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissLoading() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void setUpRecyclerView(List<Story> stories,boolean isRefresh) {
        if (mMainStoryAdapter != null) {
            mMainStoryAdapter.addStories(stories,isRefresh);
        }
    }

    @Override
    public void onFinishRefresh() {
        if (mSwipeLayout.isRefreshing()) {
            mSwipeLayout.setRefreshing(false);
        }
    }

    @Override
    public void onRefresh() {
        mPresenter.onRefresh();
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        mPresenter = presenter;
    }

    private BaseViewPagerAdapter.OnAutoViewPagerItemClickListener mListener = new BaseViewPagerAdapter.OnAutoViewPagerItemClickListener() {
        @Override
        public void onItemClick(int position, Object o) {

        }
    };

    @Override
    public void onItemClick(int position) {

    }
}
