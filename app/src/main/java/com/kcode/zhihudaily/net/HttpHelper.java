package com.kcode.zhihudaily.net;

import com.kcode.zhihudaily.bean.LatestNews;
import com.kcode.zhihudaily.bean.Welcome;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 网络再封装一层。方便以后替换网络框架
 * <p>
 * Created by caik on 2016/10/28.
 */

public class HttpHelper {

    /**
     * 获取加载也图片信息{@link Welcome}
     *
     * @param welcomeResponse 网络回调{@link Response}
     */
    public static void getStartImage(final Response<Welcome> welcomeResponse) {

        request(ApiClient.getClient().getStartImage(), new Observer<Welcome>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                welcomeResponse.onFailed(e.toString());
            }

            @Override
            public void onNext(Welcome welcome) {
                welcomeResponse.onSuccess(welcome);
            }
        });

    }

    /**
     * 获取最新信息{@link Welcome}
     *
     * @param response 网络回调{@link Response}
     */
    public static void getLatestNews(final Response<LatestNews> response){
        request(ApiClient.getClient().getLatestNews(),new Observer<LatestNews>(){

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                response.onFailed(e.toString());
            }

            @Override
            public void onNext(LatestNews latestNews) {
                response.onSuccess(latestNews);
            }
        });
    }

    /**
     * 网络统一出口
     * @param observable    被观察者{@link Observable}
     * @param observer      观察者{@link Observer}
     */
    private static void request(Observable observable, Observer observer) {
        observable
                .subscribeOn(Schedulers.io())//网络请求放到IO线程中执行
                .observeOn(AndroidSchedulers.mainThread())//返回结果运行在主线程
                .subscribe(observer);
    }

}
