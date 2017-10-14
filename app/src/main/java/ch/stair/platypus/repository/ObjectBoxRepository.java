package ch.stair.platypus.repository;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import ch.stair.platypus.domain.FeedbackModel;
import ch.stair.platypus.domain.Repository;
import ch.stair.platypus.repository.models.Feedback;
import io.objectbox.BoxStore;

public class ObjectBoxRepository implements Repository {
    private final BoxStore boxStore;

    @Inject
    public ObjectBoxRepository(final BoxStore boxStore) {
        this.boxStore = boxStore;
    }

    @Override
    public List<FeedbackModel> getAllFeedbacks() {
        final List<FeedbackModel> feedbacks = this.boxStore
            .boxFor(Feedback.class)
            .getAll()
            .stream()
            .map(x -> new FeedbackModel(
                    x.getId(),
                    x.getFeedbackText(),
                    x.getCreationDate(),
                    x.getVotesCount(),
                    x.feedbackHashtagses
                )
            )
            .collect(Collectors.toList());

        return feedbacks;
    }
}
