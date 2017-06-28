package com.example.alexandra.volushkovaalexandra;

import com.example.alexandra.volushkovaalexandra.app.schedulers.BaseSchedulerProvider;
import com.example.alexandra.volushkovaalexandra.data.model.FeedItem;
import com.example.alexandra.volushkovaalexandra.data.model.IFeedItemRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * item list presenter
 * Created by Alexandra on 10.06.2017.
 */

class ItemListPresenter {
    private FeedListView feedListView;
    @Inject
    IFeedItemRepository feedItemRepository;

    @Inject
    BaseSchedulerProvider mSchedulerProvider;
    private CompositeDisposable mSubscriptions;

    @Inject
    public ItemListPresenter() {

    }

    public void setFeedListView(FeedListView feedListView) {
        this.feedListView = feedListView;
        mSubscriptions = new CompositeDisposable();
    }

    public void subscribe() {
        loadConferences(false);
    }

    private void loadConferences(boolean b) {
        Disposable subscription = feedItemRepository
                .getFeedItems()
                .flatMap(item -> Observable.fromArray(item))
                //.toList()
                .subscribeOn(mSchedulerProvider.computation())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(
                        // onNext
                        this::processItemList);
        mSubscriptions.add(subscription);
    }

    public void processItemList(ArrayList<FeedItem> items) {
        this.feedListView.showFeedList(items);
        this.feedListView.disableProgressBar();
    }

    public void unsubscribe() {
        mSubscriptions.clear();
    }
}
