package com.it.feedassignment.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.it.feedassignment.api.FeedsService;
import com.it.feedassignment.models.JsonResponse;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;
import static com.it.feedassignment.fragments.FeedsFragment.swipeContainer;


public class FeedRepository {

    private static final String FEED_SERVICE_BASE_URL = "https://dl.dropboxusercontent.com/";
    private FeedsService feedsService;
    private MutableLiveData<JsonResponse> responseMutableLiveData;

    public FeedRepository() {
        responseMutableLiveData = new MutableLiveData<>();
        feedsService = new retrofit2.Retrofit.Builder()
                .baseUrl(FEED_SERVICE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(FeedsService.class);
        }

    // Calling API to get feeds
    public void getFeeds() {
        feedsService.getFeeds()
                .enqueue(new Callback<JsonResponse>() {
                    @Override
                    public void onResponse(@NotNull Call<JsonResponse> call, @NotNull Response<JsonResponse> response) {
                        if (response.body() != null) {
                            responseMutableLiveData.postValue(response.body());
                            swipeContainer.setRefreshing(false);
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<JsonResponse> call, @NotNull Throwable t) {
                        responseMutableLiveData.postValue(null);
                        swipeContainer.setRefreshing(false);
                    }
                });
            }

    public LiveData<JsonResponse> getJsonResponseLiveData() {
        return responseMutableLiveData;
        }

}
