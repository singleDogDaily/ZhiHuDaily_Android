package com.kcode.zhihudaily.net;


import com.kcode.zhihudaily.bean.Welcome;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by caik on 2016/10/27.
 */

public interface ZhiHuDailyService {

    /**获取启动图片地址*/
    @GET("api/4/start-image/1080*1776")
    Observable<Welcome> getStartImage();

    /**获取最新消息*/
    /**获取消息详情*/
    /**获取过往消息*/
    /**获取新闻额外信息（如评论数量，所获的『赞』的数量）*/
    /**新闻对应长评论查看*/
    /**新闻对应短评论查看*/
    /**热门消息*/

}
