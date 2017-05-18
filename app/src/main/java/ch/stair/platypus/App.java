package ch.stair.platypus;


import android.app.Application;

import ch.stair.platypus.di.components.ApplicationComponent;
import ch.stair.platypus.di.components.DaggerApplicationComponent;
import ch.stair.platypus.di.modules.ApplicationModule;
import ch.stair.platypus.models.Comments;
import ch.stair.platypus.models.MyObjectBox;
import io.objectbox.Box;
import io.objectbox.BoxStore;

public class App extends Application {
    private ApplicationComponent applicationComponent;

    BoxStore boxStore;

    @Override
    public void onCreate() {
        super.onCreate();

        this.initializeInjector();

//        final Box<Comments> commentsBox = boxStore.boxFor(Comments.class);
//        addDummyDataToDataBase(commentsBox);
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }

    private void addDummyDataToDataBase(final Box<Comments> commentsBox) {
        commentsBox.removeAll();
        final InsertDummyData insertDummyData = new InsertDummyData(commentsBox);
        insertDummyData.insertComments();
    }

    public BoxStore getBoxStore() {
        return boxStore;
    }
}

