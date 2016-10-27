package com.kcode.zhihudaily.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.kcode.zhihudaily.R;
import com.kcode.zhihudaily.bean.Welcome;
import com.kcode.zhihudaily.net.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Call<Welcome> call = ApiClient.getClient().getStartImage();
        call.enqueue(new Callback<Welcome>() {
            @Override
            public void onResponse(Call<Welcome> call, Response<Welcome> response) {
                Log.i("caik",response.body().getImg());
            }

            @Override
            public void onFailure(Call<Welcome> call, Throwable t) {

            }
        });

    }
}
