package com.it.feedassignment.viewmodels;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.it.feedassignment.models.JsonResponse;
import com.it.feedassignment.repositories.FeedRepository;

public class FeedViewModel extends AndroidViewModel {
    private FeedRepository feedRepository;
    private LiveData<JsonResponse> jsonResponseLiveData;

    public FeedViewModel(@NonNull Application application) {
        super(application);
    }

    //Initialize
    public void init() {
        feedRepository = new FeedRepository();
         getFeeds();
        jsonResponseLiveData = feedRepository.getJsonResponseLiveData();
    }

    //Get feeds
    public void getFeeds() {
        feedRepository.getFeeds();
    }

    //Get response
    public LiveData<JsonResponse> getJsonResponseLiveData() {
        return jsonResponseLiveData;
    }
}
