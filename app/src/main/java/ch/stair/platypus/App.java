package ch.stair.platypus;


import android.app.Application;

import ch.stair.platypus.models.MyObjectBox;
import io.objectbox.BoxStore;

public class App extends Application {
    private BoxStore boxStore;

    @Override
    public void onCreate() {
        super.onCreate();

        //Initialize ObjectBox DB
        this.boxStore = MyObjectBox.builder().androidContext(App.this).build();

        //Initialize Shared Preferences Manager
        PreferencesManager.initializeInstance(this.getApplicationContext());
    }

    public BoxStore getBoxStore() {
        return boxStore;
    }
}