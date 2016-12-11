package com.kcode.zhihudaily.bean;

/**
 * Created by caik on 2016/10/30.
 */

public class TopStory {

    /**
     * image : http://pic3.zhimg.com/f4a178eee2d2b5701f1723ccc6285492.jpg
     * type : 0
     * id : 8925601
     * ga_prefix : 103011
     * title : 胃不用养，别作就行
     */

    private String image;
    private int type;
    private int id;
    private String ga_prefix;
    private String title;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

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
}
