package com.it.feedassignment.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.it.feedassignment.R;
import com.it.feedassignment.models.Feed;
import java.util.ArrayList;
import java.util.List;

public class FeedsAdapter extends RecyclerView.Adapter<FeedsAdapter.FeedsHolder> {
    private List<Feed> feeds = new ArrayList<>();

    @NonNull
    @Override
    public FeedsAdapter.FeedsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_item, parent, false);
        return new FeedsHolder(itemView);
        }

    @Override
    public void onBindViewHolder(@NonNull FeedsAdapter.FeedsHolder holder, int position) {
        Feed feed = feeds.get(position);
        //Set title on textivew
        holder.titleTextView.setText(feed.getTitle());
        //Set description on textview
        holder.descriptionTextView.setText(feed.getDescription());
        //Set image on imageview
        if (feed.getImageHref() != null) {
            String imageUrl = feed.getImageHref().replace("http://", "https://");
            Glide.with(holder.itemView)
                    .load(imageUrl)
                    .placeholder(R.drawable.noimage)
                    .into(holder.feedImage);
        }else{
            holder.feedImage.setImageResource(R.drawable.noimage);
        }
    }

    @Override
    public int getItemCount() {
        return feeds.size();
    }

    //Set data on list
    public void setResults(List<Feed> results) {
        clear();
        this.feeds = results;
        notifyDataSetChanged();
    }

    // Clean all elements of the recycler
    public void clear() {
        this.feeds.clear();
        notifyDataSetChanged();
    }

    public static class FeedsHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private TextView descriptionTextView;
        private ImageView feedImage;

        public FeedsHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.tv_title);
            descriptionTextView = itemView.findViewById(R.id.tv_desc);
            feedImage = itemView.findViewById(R.id.iv_feed_image);
        }
    }
}
