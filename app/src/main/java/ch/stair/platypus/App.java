package ch.stair.platypus;


import android.app.Application;

import ch.stair.platypus.models.MyObjectBox;
import io.objectbox.BoxStore;

public class App extends Application {
    private BoxStore boxStore;

    @Override
    public void onCreate() {
        super.onCreate();

        boxStore = MyObjectBox.builder().androidContext(App.this).build();
    }

    public BoxStore getBoxStore() {
        return boxStore;
    }
}