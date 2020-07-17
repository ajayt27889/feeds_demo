package com.it.feedassignment.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.it.feedassignment.R;
import com.it.feedassignment.adapters.FeedsAdapter;
import com.it.feedassignment.viewmodels.FeedViewModel;

public class FeedsFragment extends Fragment {

    private FeedViewModel viewModel;
    private FeedsAdapter adapter;
    @SuppressLint("StaticFieldLeak")
    public static SwipeRefreshLayout swipeContainer;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new FeedsAdapter();
        viewModel = ViewModelProviders.of(this).get(FeedViewModel.class);
        viewModel.init();
        viewModel.getJsonResponseLiveData().observe(this, jsonResponse -> {
            if (jsonResponse != null) {
                adapter.setResults(jsonResponse.getFeeds());
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feeds, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.fragment_feeds);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        // Lookup the swipe container view
        swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(() -> {
            // Your code to refresh the list here.
            viewModel.init();
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        return view;
    }

}
