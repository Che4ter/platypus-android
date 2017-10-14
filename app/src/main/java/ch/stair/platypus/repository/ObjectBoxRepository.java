package ch.stair.platypus.repository;

import android.util.Log;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import ch.stair.platypus.domain.FeedbackModel;
import ch.stair.platypus.domain.Repository;
import ch.stair.platypus.repository.models.Feedback;
import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.exception.DbException;

public class ObjectBoxRepository implements Repository {
    private final Box<Feedback> feedbackBox;

    @Inject
    public ObjectBoxRepository(final BoxStore boxStore) {
        this.feedbackBox = boxStore.boxFor(Feedback.class);
    }

    @Override
    public List<FeedbackModel> getAllFeedbacks() {
        final List<FeedbackModel> feedbacks = this.feedbackBox
            .getAll()
            .stream()
            .map(x -> new FeedbackModel(
                x.getId(),
                x.getFeedbackText(),
                x.getCreationDate(),
                x.getVotesCount(),
                x.feedbackHashtagses)
            )
            .collect(Collectors.toList());

        return feedbacks;
    }

    public void saveFeedbacks(List<FeedbackModel> feedbacks) {
        try {
            for (FeedbackModel feedbackModel : feedbacks) {
                Feedback updatedFeedback = new Feedback(
                    feedbackModel.getId(),
                    feedbackModel.getText(),
                    feedbackModel.getCreatedOn(),
                    feedbackModel.getVoteCount());

                this.feedbackBox.put(updatedFeedback);
            }
        } catch (DbException e) {
            Log.d("Error", "Feedback ID not unique: " + e.getMessage());
        } catch (Exception ex) {
            Log.d("Error", ex.getMessage());
        }
    }
}
