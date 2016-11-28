package com.kcode.zhihudaily.net;


import com.kcode.zhihudaily.bean.LatestNews;
import com.kcode.zhihudaily.bean.ThemeData;
import com.kcode.zhihudaily.bean.Themes;
import com.kcode.zhihudaily.bean.Welcome;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by caik on 2016/10/27.
 */

public interface ZhiHuDailyService {

    /**获取启动图片地址*/
    @GET("api/4/start-image/1080*1776")
    Observable<Welcome> getStartImage();

    /**获取最新消息*/
    @GET("api/4/news/latest")
    Observable<LatestNews> getLatestNews();

    /**获取消息详情*/
    /**获取过往消息*/
    @GET("api/4/news/before/{date}")
    Observable<LatestNews> getBeforeNews(@Path("date") String date);

    /**获取对应主题的文章列表*/
    @GET("api/4/theme/{id}")
    Observable<ThemeData> getThemesNews(@Path("id") int id);

    /**获取主题列表*/
    @GET("api/4/themes")
    Observable<Themes> getThemes();

    /**获取新闻额外信息（如评论数量，所获的『赞』的数量）*/
    /**新闻对应长评论查看*/
    /**新闻对应短评论查看*/
    /**热门消息*/

}
