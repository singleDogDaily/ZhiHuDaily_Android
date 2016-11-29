package com.kcode.zhihudaily.base;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by caik on 2016/11/29.
 */

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseAdapter.ViewHolder> {

    private int resource;
    private List<T> data;
    private OnItemClickListener mListener;

    public BaseAdapter(RecyclerView recyclerView, Context context,int resource, List<T> data) {
        this(recyclerView, context, resource, data, new LinearLayoutManager(context));
    }

    public BaseAdapter(RecyclerView recyclerView, Context context,
                       int resource, List<T> data,
                       LinearLayoutManager layoutManager) {
        if (resource <= 0){
            throw new RuntimeException("resource may can be null");
        }

        this.resource = resource;
        this.data = data;

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(this);
    }

    public void setListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(resource, parent, false));
    }

    public void onBindViewHolder(final ViewHolder holder, int position) {
        setupViewHolder(holder, data.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(data.get(holder.getAdapterPosition()), holder.getAdapterPosition());
                }
            }
        });
    }

    public abstract void setupViewHolder(ViewHolder holder, T t);

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final View itemView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
        }

        public <T extends View> T findViewById(int id) {
            return (T)itemView.findViewById(id);
        }
    }

    public interface OnItemClickListener<T> {
        void onItemClick(T t, int position);
    }
}
