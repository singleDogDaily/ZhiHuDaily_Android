package com.kcode.zhihudaily.net;

/**
 * 网络回调
 * Created by caik on 2016/10/28.
 */

public interface Response<T> {
    void onSuccess(T t);
    void onFailed(String msg);
}
