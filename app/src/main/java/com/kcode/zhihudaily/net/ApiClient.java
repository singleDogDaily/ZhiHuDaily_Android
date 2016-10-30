package com.kcode.zhihudaily.net;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by caik on 2016/10/27.
 */

public class ApiClient {

    private final static String BASE_URL = "http://news-at.zhihu.com/";
    private static Retrofit retrofit;

    private static Retrofit getRetrofit() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }


    private static ZhiHuDailyService service;

    public static ZhiHuDailyService getClient() {

        if (service == null) {
            service = getRetrofit().create(ZhiHuDailyService.class);
        }

        return service;
    }
}
