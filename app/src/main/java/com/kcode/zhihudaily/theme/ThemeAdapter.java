package com.kcode.zhihudaily.theme;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kcode.zhihudaily.R;
import com.kcode.zhihudaily.bean.Editors;
import com.kcode.zhihudaily.bean.Story;
import com.kcode.zhihudaily.bean.ThemeData;
import com.kcode.zhihudaily.utils.CommonData;
import com.kcode.zhihudaily.utils.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caik on 2016/10/31.
 */

public class ThemeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public final static int HEADER = 1;
    public final static int ITEM = 2;

    private OnItemClickListener mListener;
    private ThemeData mThemeData;
    private List<Story> mStories;
    private Context mContext;

    public ThemeAdapter(Context context,OnItemClickListener listener) {
        this.mContext = context;
        this.mListener = listener;
    }

    public void addHeader(ThemeData themeData) {
        if (mStories == null) {
            mStories = new ArrayList<>();
        }
        mThemeData = themeData;
        Story story = new Story();
        story.setTitle(CommonData.HEADER_VIEW);
        mStories.add(0, story);
        notifyDataSetChanged();

    }

    public void init(List<Story> stories) {
        mStories = stories;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case HEADER:
                return new HeaderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_theme_header,parent,false));
            default:
                return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_theme,parent,false));
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        switch (getItemViewType(position)) {
            case HEADER:
                HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
                ImageLoader.getInstance().load(mContext,mThemeData.getBackground(),headerViewHolder.mThemeIcon);
                headerViewHolder.mSubTitle.setText(mThemeData.getDescription());
                setupEditorLayout(headerViewHolder.mEditorLayout,mThemeData.getEditors());
                break;
            case ITEM:
                ViewHolder viewHolder = (ViewHolder) holder;
                viewHolder.mTitle.setText(mStories.get(position).getTitle());
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mListener != null) {
                            mListener.onItemClick(holder.getAdapterPosition());
                        }
                    }
                });
                break;
        }



    }

    private void setupEditorLayout(LinearLayout mEditorLayout, final List<Editors> editors) {
        if (editors == null) {
            return;
        }

        ImageView circleView;
        mEditorLayout.removeAllViews();
        for (Editors editor :
                editors) {
            circleView = (ImageView) LayoutInflater.from(mContext).inflate(R.layout.circleview, null);
            ImageLoader.getInstance().loadCircleView(mContext,editor.getAvatar(),circleView);
            mEditorLayout.addView(circleView);
        }
        mEditorLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onEditorLayoutClick(editors);
                }
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        switch (mStories.get(position).getTitle()) {
            case CommonData.HEADER_VIEW:
                return HEADER;
            default:
                return ITEM;
        }
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

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {

        private final TextView mSubTitle;
        private final ImageView mThemeIcon;
        private final LinearLayout mEditorLayout;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            mSubTitle = (TextView) itemView.findViewById(R.id.tv_title);
            mThemeIcon = (ImageView) itemView.findViewById(R.id.themeView);
            mEditorLayout = (LinearLayout) itemView.findViewById(R.id.editorLayout);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);

        void onEditorLayoutClick(List<Editors> editors);
    }
}
