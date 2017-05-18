package ch.stair.platypus;

import ch.stair.platypus.models.MyObjectBox;
import io.objectbox.BoxStore;

public class ObjectBoxStoreProvider {
    private static BoxStore boxStore;

    public static void setup(final BoxStore b) {
        boxStore = b;
    }

    public static BoxStore provide() {
        return boxStore;
    }
}
