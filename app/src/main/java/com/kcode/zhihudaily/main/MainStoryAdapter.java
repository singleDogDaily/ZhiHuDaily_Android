package com.kcode.zhihudaily.main;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kcode.autoscrollviewpager.view.AutoScrollViewPager;
import com.kcode.autoscrollviewpager.view.BaseViewPagerAdapter;
import com.kcode.zhihudaily.R;
import com.kcode.zhihudaily.bean.Story;
import com.kcode.zhihudaily.bean.TopStory;
import com.kcode.zhihudaily.utils.CommonData;
import com.kcode.zhihudaily.utils.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caik on 2016/10/31.
 */

public class MainStoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public final static int HEADER = 1;
    public final static int ITEM = 2;

    private List<Story> mStories;
    private List<TopStory> mTopStories;

    private Context mContext;

    private OnItemClickListener mListener;
    private BaseViewPagerAdapter<TopStory> mAdapter;

    public MainStoryAdapter(Context mContext, OnItemClickListener listener) {
        this.mContext = mContext;
        this.mListener = listener;
    }

    public void addHeaderView(List<TopStory> topStories){

        this.mTopStories = topStories;
        Story story = new Story();
        story.setTitle(CommonData.HEADER_VIEW);
        mStories.add(0,story);
        notifyDataSetChanged();
    }

    public void addStories(List<Story> stories, boolean isRefresh) {

        if (mStories == null) {
            mStories = new ArrayList<>();
        }

        if (isRefresh) {
            mStories.clear();
            mStories.addAll(stories);
        } else {
            mStories.addAll(stories);
        }

        notifyDataSetChanged();
    }

    public void addStories(List<Story> stories) {
        addStories(stories, false);
    }

    public void notifyHeaderView(List<TopStory> topStories){
        mTopStories = topStories;
        Story story = new Story();
        story.setTitle(CommonData.HEADER_VIEW);
        story.setId(11);
        notifyItemChanged(0);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case HEADER:
                return new HeaderViewHolder(View.inflate(mContext, R.layout.main_header_view, null));
            case ITEM:
                return new ViewHolder(View.inflate(mContext, R.layout.item_story, null));
        }
        return new ViewHolder(View.inflate(mContext, R.layout.item_story, null));
    }

    @Override
    public int getItemViewType(int position) {
        switch (mStories.get(position).getTitle()) {
            case CommonData.HEADER_VIEW:
                return HEADER;
            default:
                break;
        }
        return ITEM;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        switch (getItemViewType(position)) {
            case HEADER:

                HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
                if (headerViewHolder.mViewPager.getViewPager().getAdapter() != null) {
                    mAdapter.init(mTopStories);
                }else {
                    mAdapter = new BaseViewPagerAdapter<TopStory>(mContext, new BaseViewPagerAdapter.OnAutoViewPagerItemClickListener<TopStory>() {
                        @Override
                        public void onItemClick(int i, TopStory topStory) {
                            Toast.makeText(mContext, "" + topStory.getId() + topStory.getTitle(), Toast.LENGTH_SHORT).show();
                        }
                    }) {
                        @Override
                        public void loadImage(ImageView view, int position, TopStory topStory) {
                            ImageLoader.getInstance().load(mContext, topStory.getImage(), view);
                        }

                        @Override
                        public void setSubTitle(TextView textView, int position, TopStory topStory) {
                            textView.setText(topStory.getTitle());
                        }
                    };

                    headerViewHolder.mViewPager.setAdapter(mAdapter);
                    if (mTopStories != null && mTopStories.size() > 0) {
                        mAdapter.init(mTopStories);
                    }
                }

                break;
            case ITEM:

                ViewHolder viewHolder = (ViewHolder) holder;
                viewHolder.mTitle.setText(mStories.get(position).getTitle());
                ImageLoader.getInstance().load(mContext, mStories.get(position).getImages().get(0), viewHolder.mIcon);
                if (TextUtils.isEmpty(mStories.get(position).getDate())) {
                    viewHolder.mDate.setVisibility(View.GONE);
                }else {
                    viewHolder.mDate.setText(mStories.get(position).getDate());
                    viewHolder.mDate.setVisibility(View.VISIBLE);
                }

                viewHolder.mCardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mListener != null) {
                            mListener.onItemClick(position);
                        }
                    }
                });
                break;
            default:
                break;
        }


    }

    @Override
    public int getItemCount() {
        return mStories == null ? 0 : mStories.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView mDate;
        public final TextView mTitle;
        public final ImageView mIcon;
        public final CardView mCardView;

        public ViewHolder(View itemView) {
            super(itemView);
            mCardView = (CardView) itemView.findViewById(R.id.cardView);
            mDate = (TextView) itemView.findViewById(R.id.tv_time);
            mTitle = (TextView) itemView.findViewById(R.id.tv_title);
            mIcon = (ImageView) itemView.findViewById(R.id.img_icon);
        }
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {

        public final AutoScrollViewPager mViewPager;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            mViewPager = (AutoScrollViewPager) itemView.findViewById(R.id.viewPager);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
