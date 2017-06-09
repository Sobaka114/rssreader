package com.example.alexandra.volushkovaalexandra;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alexandra.volushkovaalexandra.data.model.FeedItem;

import java.util.ArrayList;
import java.util.List;

/**
 * adapter for item list
 * Created by Alexandra on 10.06.2017.
 */

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.FeedItemViewHolder> {
    private List<FeedItem> feedItems;

    @Override
    public FeedItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.news_tem_snapshot;
        LayoutInflater inflater = LayoutInflater.from(context);

        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        return new FeedItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FeedItemViewHolder holder, int position) {
        if(feedItems != null && feedItems.size() > 0) {
            FeedItem feedItem = feedItems.get(position);
            holder.title.setText(feedItem.getTitle());
            holder.source.setText(feedItem.getSource());
            holder.image.setImageBitmap(feedItem.getBmp());
        }
    }

    @Override
    public int getItemCount() {
        return feedItems == null ? 0 : feedItems.size();
    }

    public void replaceData(List<FeedItem> items) {
        if(feedItems == null) {
            feedItems = new ArrayList<>();
        }
        feedItems.clear();
        feedItems.addAll(items);
        notifyDataSetChanged();
    }

    public class FeedItemViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView source;
        private ImageView image;
        public FeedItemViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.news_title);
            source = (TextView) itemView.findViewById(R.id.source);
            image = (ImageView) itemView.findViewById(R.id.image_item);
        }
    }
}
