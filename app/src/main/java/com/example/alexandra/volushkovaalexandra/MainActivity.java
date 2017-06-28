package com.example.alexandra.volushkovaalexandra;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.alexandra.volushkovaalexandra.app.App;
import com.example.alexandra.volushkovaalexandra.data.model.FeedItem;
import com.example.alexandra.volushkovaalexandra.service.FeedReceiver;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements FeedListView {

    private static final String NEWS_ITEMS = "news_items";
    private RecyclerView feedList;
    private ProgressBar progressBar;

    @Inject
    ItemListPresenter presenter;

    private ItemListAdapter itemListAdapter;

    private ArrayList<FeedItem> feedItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getComponent().inject(this);
        setContentView(R.layout.activity_main);
        feedList = (RecyclerView) findViewById(R.id.item_list);
        presenter.setFeedListView(this);
        itemListAdapter = new ItemListAdapter();

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        feedList.setLayoutManager(layoutManager);
        feedList.setHasFixedSize(true);
        feedList.setAdapter(itemListAdapter);

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        if (savedInstanceState == null) {
            /////get data
            Intent intent = new Intent(this, FeedReceiver.class);
            this.startService(intent);
        } else {
            ArrayList<FeedItem> o = savedInstanceState.getParcelableArrayList(NEWS_ITEMS);
            showFeedList(o);
            disableProgressBar();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.subscribe();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.unsubscribe();
    }

    @Override
    public void showFeedList(ArrayList<FeedItem> items) {
        itemListAdapter.replaceData(items);
        feedItems = items;
    }

    @Override
    public void disableProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(feedItems != null) {
            outState.putParcelableArrayList(NEWS_ITEMS, feedItems);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (feedItems == null) {
            feedItems = savedInstanceState.getParcelableArrayList(NEWS_ITEMS);
            feedList.setAdapter(itemListAdapter);
        }
    }
}
