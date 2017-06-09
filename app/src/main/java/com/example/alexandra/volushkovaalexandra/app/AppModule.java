package com.example.alexandra.volushkovaalexandra.app;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.alexandra.volushkovaalexandra.app.schedulers.BaseSchedulerProvider;
import com.example.alexandra.volushkovaalexandra.app.schedulers.SchedulerProvider;
import com.example.alexandra.volushkovaalexandra.util.FeedParser;
import com.example.alexandra.volushkovaalexandra.util.IParser;

import javax.inject.Singleton;
import javax.xml.parsers.ParserConfigurationException;

import dagger.Module;
import dagger.Provides;

/**
 * application module
 * Created by Volushkova on 7/06/2017.
 */

@Module
class AppModule {
    private Context appContext;

    public  AppModule(@NonNull Context context) {
        appContext = context;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return appContext;
    }

    @Provides
    @Singleton
    IParser provideParser() {
        try {
            return new FeedParser();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Provides
    @Singleton
    BaseSchedulerProvider provideSchedulerProvider() {
        return new SchedulerProvider();
    }

}
