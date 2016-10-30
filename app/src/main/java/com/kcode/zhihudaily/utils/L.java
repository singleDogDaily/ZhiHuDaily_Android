package com.kcode.zhihudaily.utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Log 日志工具类
 * Created by caik on 2016/10/29.
 */

public class L {

    private String tag = "ZhiHu";

    private int level = LogFactory.DEBUG;

    public L(String tag) {
        this.tag = tag;
    }

    public L() {
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void i(int msg) {
        if (level <= LogFactory.INFO) {
            Log.i(tag, "" + msg);
        }
    }

    public void i(String msg) {
        if (level <= LogFactory.INFO) {
            Log.i(tag, msg == null ? "msg is null" : msg);
        }
    }

    public void i(JSONObject object) {
        if (level <= LogFactory.INFO) {
            Log.i(tag, object == null ? "object is null" : object.toString());
        }
    }

    public void i(JSONArray array) {
        if (level <= LogFactory.INFO) {
            Log.i(tag, array == null ? "array is null" : array.toString());
        }
    }

    public void d(String msg) {
        if (level <= LogFactory.DEBUG) {
            Log.d(tag, msg == null ? "msg is null" : msg);
        }
    }

    public void d(JSONObject object) {
        if (level <= LogFactory.DEBUG) {
            Log.d(tag, object == null ? "object is null" : object.toString());
        }
    }

    public void d(JSONArray array) {
        if (level <= LogFactory.DEBUG) {
            Log.d(tag, array == null ? "array is null" : array.toString());
        }
    }

    public void e(String msg) {
        if (level <= LogFactory.ERROR) {
            Log.e(tag, msg == null ? "msg is null" : msg);
        }
    }

    public void e(JSONObject object) {
        if (level <= LogFactory.ERROR) {
            Log.e(tag, object == null ? "object is null" : object.toString());
        }
    }

    public void e(JSONArray array) {
        if (level <= LogFactory.ERROR) {
            Log.e(tag, array == null ? "array is null" : array.toString());
        }
    }

}
