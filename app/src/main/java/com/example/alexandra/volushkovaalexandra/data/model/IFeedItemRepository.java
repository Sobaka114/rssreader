package com.example.alexandra.volushkovaalexandra.data.model;

import java.util.List;

import io.reactivex.Observable;

/**
 * Feed repository
 * Created by Alexandra on 10.06.2017.
 */

public interface IFeedItemRepository {
    Observable<List<FeedItem>> getFeedItems();

    void replaceFeeds(List<FeedItem> parse);
}
