package com.it.feedassignment.api;

import com.it.feedassignment.models.JsonResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface FeedsService {

    //Get feeds from url
    @GET("/s/2iodh4vg0eortkl/facts.json")
    Call<JsonResponse> getFeeds();
}
