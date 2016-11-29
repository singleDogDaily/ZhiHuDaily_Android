package com.kcode.zhihudaily.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kcode.zhihudaily.R;
import com.kcode.zhihudaily.bean.Other;

import java.util.List;

/**
 * Created by caik on 2016/11/24.
 */

public class NavigationDrawerAdapter extends RecyclerView.Adapter<NavigationDrawerAdapter.ViewHolder> {

    private List<Other> data;
    private Context context;
    private OnNavigationItemClickListener mListener;

    public NavigationDrawerAdapter( Context context,OnNavigationItemClickListener listener) {
        this.context = context;
        this.mListener = listener;
    }

    public void init(List<Other> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_drawer,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.mName.setText(data.get(position).getName());
        holder.mItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(position,data.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView mName;
        private final View mItemView;

        public ViewHolder(View itemView) {
            super(itemView);
            mName = (TextView) itemView.findViewById(R.id.tv_title);
            mItemView = itemView;
        }
    }

    public interface OnNavigationItemClickListener{
        void onItemClick(int position,Other other);
    }
}
