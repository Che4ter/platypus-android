package ch.stair.platypus;

import java.util.List;

import javax.inject.Inject;

import ch.stair.platypus.domain.Repository;
import ch.stair.platypus.models.Feedback;
import io.objectbox.BoxStore;

public class ObjectBoxRepository implements Repository {
    private final BoxStore boxStore;

    @Inject
    public ObjectBoxRepository(final BoxStore boxStore) {
        this.boxStore = boxStore;
    }

    @Override
    public List<Feedback> getAllFeedbacks() {
        final List<Feedback> allFeedbacks = this.boxStore
                .boxFor(Feedback.class)
                .getAll();
        return allFeedbacks;
    }
}
