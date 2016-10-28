package com.kcode.zhihudaily.net;

import com.kcode.zhihudaily.bean.Welcome;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * 网络再封装一层。方便以后替换网络框架
 *
 * Created by caik on 2016/10/28.
 */

public class HttpHelper {

    public static void getStartImage(final Response<Welcome> welcomeResponse){
        Call<Welcome> call = ApiClient.getClient().getStartImage();
        call.enqueue(new Callback<Welcome>() {
            @Override
            public void onResponse(Call<Welcome> call, retrofit2.Response<Welcome> response) {
                if (response != null) {
                    welcomeResponse.onSuccess(response.body());
                }else {
                    welcomeResponse.onFailed("加载失败");
                }

            }

            @Override
            public void onFailure(Call<Welcome> call, Throwable t) {
                welcomeResponse.onFailed(t.toString());
            }
        });
    }
}
