package com.kcode.zhihudaily.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.kcode.autoscrollviewpager.view.BaseViewPagerAdapter;
import com.kcode.zhihudaily.R;
import com.kcode.zhihudaily.base.BaseFragment;
import com.kcode.zhihudaily.bean.Story;
import com.kcode.zhihudaily.bean.TopStory;
import com.kcode.zhihudaily.utils.DateUtils;
import com.kcode.zhihudaily.utils.L;
import com.kcode.zhihudaily.utils.LogFactory;

import java.util.List;

/**
 * Created by caik on 2016/10/30.
 */

public class MainFragment extends BaseFragment implements MainContract.View,
        SwipeRefreshLayout.OnRefreshListener, MainStoryAdapter.OnItemClickListener {

    private final static L log = LogFactory.create(MainFragment.class);

    private MainContract.Presenter mPresenter;
    private ProgressBar mProgressBar;

    public RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private MainStoryAdapter mMainStoryAdapter;

    private SwipeRefreshLayout mSwipeLayout;
    private int mCurrentPage = 0;//当前时间，今日热闻为0

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
        mProgressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mSwipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeLayout);
        mSwipeLayout.setColorSchemeColors(getResources().getColor(R.color.base_blue));
        mSwipeLayout.setOnRefreshListener(this);

        initRecyclerView();
        if (mPresenter != null) {
            mPresenter.start();
        }

    }

    private void initRecyclerView() {
        mMainStoryAdapter = new MainStoryAdapter(getContext(), this);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mMainStoryAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE:
                        log.i("SCROLL_STATE_IDLE");
                        int index = mLayoutManager.findFirstVisibleItemPosition();
                        setToolbarTitle(mMainStoryAdapter.getItem(index).getHideDate());
                        float f = (float) index / mMainStoryAdapter.getItemCount();
                        if ((float) index / mMainStoryAdapter.getItemCount() > 0.5f) {
                            //滑动距离过半，继续加载更多数据
                            loadMore();
                        }
                        break;
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void setUpViewPager(final List<TopStory> topStories) {
        if (mMainStoryAdapter != null) {
            mMainStoryAdapter.addHeaderView(topStories);
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
    public void setUpRecyclerView(List<Story> stories, boolean isRefresh) {
        if (mMainStoryAdapter != null) {
            if (stories != null && stories.size() != 0) {
                if (mCurrentPage == 0) {
                    stories.get(0).setDate(getToolbarTitle());
                    setHideDate(stories, getToolbarTitle());
                } else {
                    stories.get(0).setDate(DateUtils.long2MMdd(DateUtils.str2Long(DateUtils.getToday()) + (mCurrentPage * 24 * 60 * 60 * 1000)));
                    setHideDate(stories, stories.get(0).getDate());
                }
            }

            mMainStoryAdapter.addStories(stories, isRefresh);

        }
    }

    private void loadMore() {
        mCurrentPage--;

        String date = DateUtils.long2Str(DateUtils.str2Long(DateUtils.getToday()) + (mCurrentPage * 24 * 60 * 60 * 1000));
        mPresenter.loadMore(date);
    }

    @Override
    public void onFinishRefresh() {
        if (mSwipeLayout.isRefreshing()) {
            mSwipeLayout.setRefreshing(false);
        }
    }

    @Override
    public void setToolbarTitle(String title) {
        ((MainActivity) getActivity()).setToolbarTitle(title);
    }

    @Override
    public void onRefresh() {
        mCurrentPage = 0;
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

    public String getToolbarTitle() {
        return ((MainActivity) getActivity()).getToolbarTitle();
    }

    private void setHideDate(List<Story> stories, String hideDate) {
        if (stories == null) {
            return;
        }
        for (Story story : stories) {
            story.setHideDate(hideDate);
        }
    }
}
