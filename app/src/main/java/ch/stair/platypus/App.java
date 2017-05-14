package ch.stair.platypus;


import android.app.Application;

import ch.stair.platypus.models.Comments;
import ch.stair.platypus.models.MyObjectBox;
import io.objectbox.Box;
import io.objectbox.BoxStore;

public class App extends Application {
    private BoxStore boxStore;

    @Override
    public void onCreate() {
        super.onCreate();

        this.boxStore = MyObjectBox.builder().androidContext(App.this).build();
        final Box<Comments> commentsBox = boxStore.boxFor(Comments.class);
        addDummyDataToDataBase(commentsBox);
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