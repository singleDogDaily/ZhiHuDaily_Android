package com.kcode.zhihudaily.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.File;

/**
 * 在图片加载框架上封装一层，方便以后替换为其他加载框架
 * Created by caik on 2016/10/28.
 */

public class ImageLoader {

    private static ImageLoader mImageLoader;

    public static ImageLoader getInstance(){
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader();
        }

        return mImageLoader;
    }

    /**设置为private，防止从外面直接new ImageLoader()破坏单例*/
    private ImageLoader() {
    }

    public void load(Context context,String url, ImageView view){
        Glide.with(context).load(url).into(view);
    }

    public void load(Context context, File file, ImageView view){
        Glide.with(context).load(file).into(view);
    }
}
