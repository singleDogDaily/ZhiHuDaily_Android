package com.kcode.zhihudaily.theme;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kcode.zhihudaily.R;
import com.kcode.zhihudaily.bean.Story;

import java.util.List;

/**
 * Created by caik on 2016/10/31.
 */

public class ThemeAdapter extends RecyclerView.Adapter<ThemeAdapter.ViewHolder> {

    private OnItemClickListener mListener;
    private List<Story> mStories;

    public ThemeAdapter(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public void init(List<Story> stories) {
        mStories = stories;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_theme,parent,false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mTitle.setText(mStories.get(position).getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(holder.getAdapterPosition());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mStories == null ? 0 : mStories.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView mTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.title);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
