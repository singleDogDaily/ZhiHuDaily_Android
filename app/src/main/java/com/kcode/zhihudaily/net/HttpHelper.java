package com.kcode.zhihudaily.net;

import com.kcode.zhihudaily.bean.LatestNews;
import com.kcode.zhihudaily.bean.Other;
import com.kcode.zhihudaily.bean.ThemeData;
import com.kcode.zhihudaily.bean.Themes;
import com.kcode.zhihudaily.bean.Welcome;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
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
     * 获取最新信息{@link LatestNews}
     *
     * @param response 网络回调{@link Response}
     */
    public static void getLatestNews(final Response<LatestNews> response) {

        ApiClient.getClient().getLatestNews()
                .subscribeOn(Schedulers.io())
                .doOnNext(new Action1<LatestNews>() {
                    @Override
                    public void call(LatestNews latestNews) {
//                        RealmHelper.getInstance().insertOrUpdate(latestNews);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LatestNews>() {

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
     * 获取之前信息{@link LatestNews}
     *
     * @param date     日期（格式：yyyyMMdd）
     * @param response 网络回调{@link Response}
     */
    public static void getBeforeNews(String date, final Response<LatestNews> response) {

        ApiClient.getClient().getBeforeNews(date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LatestNews>() {

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

    public static void getThemes(final Response<List<Other>> response){
        ApiClient.getClient().getThemes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Themes>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        response.onFailed(e.toString());
                    }

                    @Override
                    public void onNext(Themes theme) {
                        response.onSuccess(theme.getOthers());
                    }
                });
    }

    public static void getThemeData(int id, final Response<ThemeData> response) {
        request(ApiClient.getClient().getThemesNews(id), new Observer<ThemeData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                response.onFailed(e.toString());
            }

            @Override
            public void onNext(ThemeData themeData) {
                response.onSuccess(themeData);
            }
        });
    }

    /**
     * 网络统一出口
     *
     * @param observable 被观察者{@link Observable}
     * @param observer   观察者{@link Observer}
     */
    private static void request(Observable observable, Observer observer) {
        observable
                .subscribeOn(Schedulers.io())//网络请求放到IO线程中执行
                .observeOn(AndroidSchedulers.mainThread())//返回结果运行在主线程
                .subscribe(observer);
    }

}
