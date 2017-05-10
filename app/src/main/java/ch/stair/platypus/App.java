package ch.stair.platypus;


import android.app.Application;

import ch.stair.platypus.models.MyObjectBox;
import io.objectbox.BoxStore;

/**
 * Created by philipp on 10.05.17.
 */

public class App extends Application {

    public static final String TAG = "Platypus";
    public static final boolean EXTERNAL_DIR = false;

    private BoxStore boxStore;

    @Override
    public void onCreate() {
        super.onCreate();

//        if (EXTERNAL_DIR) {
//            // Example how you could use a custom dir in "external storage"
//            // (Android 6+ note: give the app storage permission in app info settings)
//            File directory = new File(Environment.getExternalStorageDirectory(), "objectbox-notes");
//            boxStore = MyObjectBox.builder().androidContext(App.this).directory(directory).build();
//        } else {
        // This is the minimal setup required on Android
        boxStore = MyObjectBox.builder().androidContext(App.this).build();
//        }
    }

    public BoxStore getBoxStore() {
        return boxStore;
    }
}