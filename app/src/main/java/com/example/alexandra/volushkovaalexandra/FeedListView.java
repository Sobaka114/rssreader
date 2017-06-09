package com.example.alexandra.volushkovaalexandra;

import com.example.alexandra.volushkovaalexandra.data.model.FeedItem;

import java.util.List;

/**
 * Basic methods for feed list view
 * Created by Alexandra on 10.06.2017.
 */

interface FeedListView {
    void showFeedList(List<FeedItem> conferences);
}
