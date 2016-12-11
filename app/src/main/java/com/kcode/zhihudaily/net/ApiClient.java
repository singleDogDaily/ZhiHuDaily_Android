package com.kcode.zhihudaily.net;

import com.google.gson.Gson;
import com.kcode.zhihudaily.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by caik on 2016/10/27.
 */

public class ApiClient {

    private final static String BASE_URL = "http://news-at.zhihu.com/";
    private static Retrofit retrofit;

    private static OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();

    private static Retrofit getRetrofit() {

        if (retrofit == null) {

            if (BuildConfig.DEBUG) {
                // enable logging for debug builds
                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                httpClientBuilder.addInterceptor(loggingInterceptor);
            }

            Gson gson = new Gson();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .callFactory(httpClientBuilder.build())
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
