package ch.stair.platypus;

import android.content.Context;

public class ABC {
    private static App app;

    public static void set(App a) {
        app = a;
    }

    public static Context get() {
        return app;
    }
}
