package com.example.alexandra.volushkovaalexandra.data.model;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Basic realisation for feed repository
 * Created by Alexandra on 10.06.2017.
 */

public class FeedItemRepository implements IFeedItemRepository {
    private ArrayList<FeedItem> feedItems;
    private PublishSubject<ArrayList<FeedItem>> feedItemsPublish;

    public FeedItemRepository() {
        feedItems = new ArrayList<>();
        feedItemsPublish = PublishSubject.create();
    }

    @Override
    public Observable<ArrayList<FeedItem>> getFeedItems() {
        return feedItemsPublish;
    }

    @Override
    public void replaceFeeds(List<FeedItem> parse) {
        if(feedItems == null) {
            feedItems = new ArrayList<>();
        }
        feedItems.clear();
        feedItems.addAll(parse);

        feedItemsPublish.onNext(feedItems);
    }
}
