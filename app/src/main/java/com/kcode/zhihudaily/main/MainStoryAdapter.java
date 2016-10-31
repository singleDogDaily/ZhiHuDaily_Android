package com.kcode.zhihudaily.main;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kcode.zhihudaily.R;
import com.kcode.zhihudaily.bean.Story;
import com.kcode.zhihudaily.utils.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caik on 2016/10/31.
 */

public class MainStoryAdapter extends RecyclerView.Adapter<MainStoryAdapter.ViewHolder> {

    private List<Story> mStories;

    private Context mContext;

    private OnItemClickListener mListener;

    public MainStoryAdapter(Context mContext,OnItemClickListener listener) {
        this.mContext = mContext;
        this.mListener = listener;
    }

    public void addStories(List<Story> stories,boolean isRefresh){

        if (mStories == null) {
            mStories = new ArrayList<>();
        }

        if (isRefresh) {
            mStories.clear();
            mStories.addAll(stories);
        }else {
            mStories.addAll(stories);
        }

        notifyDataSetChanged();
    }

    public void addStories(List<Story> stories){
        addStories(stories,false);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(mContext, R.layout.item_story,null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.mTitle.setText(mStories.get(position).getTitle());
        ImageLoader.getInstance().load(mContext,mStories.get(position).getImages().get(0),holder.mIcon);
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mStories == null ? 0 : mStories.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView mTime;
        public final TextView mTitle;
        public final ImageView mIcon;
        public final CardView mCardView;

        public ViewHolder(View itemView) {
            super(itemView);
            mCardView = (CardView) itemView.findViewById(R.id.cardView);
            mTime = (TextView) itemView.findViewById(R.id.tv_time);
            mTitle = (TextView) itemView.findViewById(R.id.tv_title);
            mIcon = (ImageView) itemView.findViewById(R.id.img_icon);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
}
