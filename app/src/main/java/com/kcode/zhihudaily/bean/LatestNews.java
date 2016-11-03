package com.kcode.zhihudaily.bean;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by caik on 2016/10/30.
 */

public class LatestNews extends RealmObject {
    private String date;
    private RealmList<Story> stories;
    private RealmList<TopStory> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Story> getStories() {
        return stories;
    }

    public void setStories(RealmList<Story> stories) {
        this.stories = stories;
    }

    public List<TopStory> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(RealmList<TopStory> top_stories) {
        this.top_stories = top_stories;
    }
}
