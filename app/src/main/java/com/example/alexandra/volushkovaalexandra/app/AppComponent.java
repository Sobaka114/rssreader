package com.example.alexandra.volushkovaalexandra.app;

import com.example.alexandra.volushkovaalexandra.MainActivity;
import com.example.alexandra.volushkovaalexandra.data.model.FeedRepositoryModule;
import com.example.alexandra.volushkovaalexandra.service.FeedReceiver;

import javax.inject.Singleton;

import dagger.Component;

/**
 * application component
 * Created by Volushkova on 7/06/2017.
 */
@Singleton
@Component(modules = {AppModule.class, FeedRepositoryModule.class})
public interface AppComponent {
    void inject(FeedReceiver feedReceiver);

    void inject(MainActivity mainActivity);
}
