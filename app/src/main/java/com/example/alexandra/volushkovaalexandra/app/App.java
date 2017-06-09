package com.example.alexandra.volushkovaalexandra.app;

import android.app.Application;

import com.example.alexandra.volushkovaalexandra.data.model.FeedRepositoryModule;

/**
 * application class
 * Created by Volushkova on 7/06/2017.
 */

public class App extends Application {
    private static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = buildComponent();
    }

    protected AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .feedRepositoryModule(new FeedRepositoryModule())
                .build();
    }

    public static AppComponent getComponent() {
        return component;
    }

}
