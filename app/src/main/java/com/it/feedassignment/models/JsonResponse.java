package com.it.feedassignment.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class JsonResponse {

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("rows")
    @Expose
    List<Feed> feeds = null;

    public String getTitle() {
        return title;
    }

    public List<Feed> getFeeds() {
        return feeds;
    }
}
