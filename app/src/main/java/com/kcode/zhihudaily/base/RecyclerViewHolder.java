package com.kcode.zhihudaily.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by caik on 2016/11/29.
 */

public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    public final View itemView;
    public RecyclerViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
    }

    public <T extends View> T findViewById(int id) {
        return (T)itemView.findViewById(id);
    }
}
