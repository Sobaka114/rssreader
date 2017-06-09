package com.example.alexandra.volushkovaalexandra.service;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.alexandra.volushkovaalexandra.app.App;
import com.example.alexandra.volushkovaalexandra.data.model.FeedItem;
import com.example.alexandra.volushkovaalexandra.data.model.IFeedItemRepository;
import com.example.alexandra.volushkovaalexandra.util.IParser;

import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import javax.inject.Inject;

import okhttp3.OkHttpClient;

/**
 * service for receiving news from rss urls
 * Created by Alexandra on 10.06.2017.
 */

public class FeedReceiver extends Service {

    final private String GAZETA_URL = "https://www.gazeta.ru/export/rss/lenta.xml";
    final private String LENTA_URL = "https://lenta.ru/rss";
    OkHttpClient client = new OkHttpClient();

    @Inject
    IFeedItemRepository feedItemRepository;
    @Inject
    IParser feedParser;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        App.getComponent().inject(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(feedParser == null) {
            throw new IllegalStateException("parser is null");
        }
        Loader loader = new Loader();
        loader.execute();
        return super.onStartCommand(intent, flags, startId);
    }

    class Loader extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            try {
                InputStream inputStream = new URL(LENTA_URL).openStream();
                List<FeedItem> parse = feedParser.parse(inputStream, LENTA_URL);

                inputStream = new URL(GAZETA_URL).openStream();
                parse.addAll(feedParser.parse(inputStream, GAZETA_URL));

                feedItemRepository.replaceFeeds(parse);
            } catch (IOException | SAXException | XmlPullParserException e) {
                Log.e(getClass().getName(), e.getMessage());
            }
            return null;
        }
    }
}
