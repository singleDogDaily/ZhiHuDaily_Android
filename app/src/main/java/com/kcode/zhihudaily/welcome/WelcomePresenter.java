package com.kcode.zhihudaily.welcome;

import android.util.Log;

import com.kcode.zhihudaily.bean.Welcome;
import com.kcode.zhihudaily.net.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by caik on 2016/10/27.
 */

public class WelcomePresenter implements WelcomeContract.Presenter {

    private WelcomeContract.View mView;

    public WelcomePresenter(WelcomeContract.View mView) {
        this.mView = mView;
        this.mView.setPresenter(this);
    }

    @Override
    public void start() {

        Call<Welcome> call = ApiClient.getClient().getStartImage();
        call.enqueue(new Callback<Welcome>() {
            @Override
            public void onResponse(Call<Welcome> call, Response<Welcome> response) {
                Log.i("caik",response.body().getImg());
                mView.loadImage(response.body().getImg());
            }

            @Override
            public void onFailure(Call<Welcome> call, Throwable t) {

            }
        });
    }
}
