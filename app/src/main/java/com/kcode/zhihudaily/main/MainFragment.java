package com.kcode.zhihudaily.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.kcode.autoscrollviewpager.view.AutoScrollViewPager;
import com.kcode.autoscrollviewpager.view.BaseViewPagerAdapter;
import com.kcode.zhihudaily.R;
import com.kcode.zhihudaily.base.BaseFragment;
import com.kcode.zhihudaily.bean.TopStory;
import com.kcode.zhihudaily.utils.ImageLoader;
import com.kcode.zhihudaily.utils.L;
import com.kcode.zhihudaily.utils.LogFactory;

import java.util.List;

/**
 * Created by caik on 2016/10/30.
 */

public class MainFragment extends BaseFragment implements MainContract.View {

    private final static L log = LogFactory.create(MainFragment.class);

    private MainContract.Presenter mPresenter;
    private ProgressBar mProgressBar;
    private AutoScrollViewPager mViewPager;
    private BaseViewPagerAdapter<TopStory> mAdapter;

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

        initViewPager();

        mPresenter.loadData();

    }

    private void initViewPager(){
        mAdapter = new BaseViewPagerAdapter<TopStory>(getActivity(),mListener) {
            @Override
            public void loadImage(ImageView view, int position, TopStory topStory) {
                ImageLoader.getInstance().load(MainFragment.this, topStory.getImage(), view);
            }
        };

        mViewPager.setAdapter(mAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mViewPager != null) {
            mViewPager.onDestroy();
        }
    }

    @Override
    public void setUpViewPager(final List<TopStory> topStories) {
        if (mAdapter != null) {
            mAdapter.add(topStories);
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
    public void setUpRecyclerView() {

    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        log.i("init presenter");
        mPresenter = presenter;
    }

    private BaseViewPagerAdapter.OnAutoViewPagerItemClickListener mListener = new BaseViewPagerAdapter.OnAutoViewPagerItemClickListener() {
        @Override
        public void onItemClick(int position, Object o) {

        }
    };
}
