package com.kcode.zhihudaily.base;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;

import com.kcode.zhihudaily.R;

/**
 * Created by caik on 2016/10/27.
 */

public class App extends Application {
    public final String IS_FIRST_IN = "is_first_in";
    public final static String SPF = "ZhiHu_SPF";
    public final static String ACTION_MODEL = "action_model";

    private static final String TAG = "App";

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferences spf = getSharedPreferences(SPF,MODE_PRIVATE);

        if (spf.getBoolean(IS_FIRST_IN , true)){
            spf.edit()
                .putString(ACTION_MODEL , getString(R.string.day_model))
                .apply();

            spf.edit()
                .putBoolean(IS_FIRST_IN , false)
                .apply();
        }

        String action_model = spf.getString(ACTION_MODEL , getString(R.string.day_model));
        Log.i(TAG, "action_model: "+action_model);
        if ((getString(R.string.night_model)).equals(action_model)){
            //夜间模式
            Log.i(TAG, "onCreate: night_model");
            setTheme(R.style.NightTheme);
        }else{
            Log.i(TAG, "onCreate: day_model");
            setTheme(R.style.DayTheme);
        }


    }
}
