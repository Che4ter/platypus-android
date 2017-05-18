package ch.stair.platypus;


import android.app.Application;

import ch.stair.platypus.di.components.ApplicationComponent;
import ch.stair.platypus.di.components.DaggerApplicationComponent;
import ch.stair.platypus.di.modules.ApplicationModule;
import io.objectbox.BoxStore;

public class App extends Application {
    private ApplicationComponent applicationComponent;

    BoxStore boxStore;

    @Override
    public void onCreate() {
        super.onCreate();

        this.initializeInjector();
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }

    public BoxStore getBoxStore() {
        return boxStore;
    }
}

