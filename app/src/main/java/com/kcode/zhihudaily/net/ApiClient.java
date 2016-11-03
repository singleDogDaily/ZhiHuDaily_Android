package com.kcode.zhihudaily.net;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.kcode.zhihudaily.BuildConfig;
import com.kcode.zhihudaily.bean.RealmString;
import com.kcode.zhihudaily.utils.RealmStringListTypeAdapter;

import io.realm.RealmList;
import io.realm.RealmObject;
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

            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(new TypeToken<RealmList<RealmString>>(){}.getType(),
                            RealmStringListTypeAdapter.INSTANCE)
                    .setExclusionStrategies(new ExclusionStrategy() {
                        // This is required to make Gson work with RealmObjects
                        @Override
                        public boolean shouldSkipField(FieldAttributes f) {
                            return f.getDeclaringClass().equals(RealmObject.class);
                        }

                        @Override
                        public boolean shouldSkipClass(Class<?> clazz) {
                            return false;
                        }
                    }).create();

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
