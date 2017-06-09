package com.example.alexandra.volushkovaalexandra.data.model;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Module for feed repository
 * Created by Alexandra on 10.06.2017.
 */

@Module
public class FeedRepositoryModule {
    @Singleton
    @Provides
    public IFeedItemRepository provideFeedItemRepository() {
        return new FeedItemRepository();
    }
}
