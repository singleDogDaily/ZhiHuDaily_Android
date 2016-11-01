package com.kcode.zhihudaily.bean;

import java.util.List;

/**
 * Created by caik on 2016/10/30.
 */

public class Story {

    /**
     * images : ["http://pic2.zhimg.com/608c2a3c9afc8c714e3b394f3b4defad.jpg"]
     * type : 0
     * id : 8932658
     * ga_prefix : 103020
     * title : 眼睛是心灵的窗户这事儿，是有科学依据的
     */

    private int type;
    private int id;
    private String ga_prefix;
    private String title;
    private List<String> images;
    private String date;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
