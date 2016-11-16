package com.kcode.zhihudaily.bean;

import java.util.List;

/**
 * Created by caik on 2016/11/16.
 */

public class Themes {


    /**
     * limit : 1000
     * subscribed : []
     * others : []
     */

    private int limit;
    private List subscribed;
    private List<Other> others;

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public List<?> getSubscribed() {
        return subscribed;
    }

    public void setSubscribed(List<?> subscribed) {
        this.subscribed = subscribed;
    }

    public List<Other> getOthers() {
        return others;
    }

    public void setOthers(List<Other> others) {
        this.others = others;
    }
}
