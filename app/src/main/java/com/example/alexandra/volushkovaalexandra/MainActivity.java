package com.example.alexandra.volushkovaalexandra;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.alexandra.volushkovaalexandra.app.App;
import com.example.alexandra.volushkovaalexandra.data.model.FeedItem;
import com.example.alexandra.volushkovaalexandra.service.FeedReceiver;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements FeedListView {

    private RecyclerView feedList;

    @Inject
    ItemListPresenter presenter;

    private ItemListAdapter itemListAdapter;

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

        /////get data
        Intent intent = new Intent(this, FeedReceiver.class);
        this.startService(intent);
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
    public void showFeedList(List<FeedItem> items) {
        itemListAdapter.replaceData(items);
    }
}
