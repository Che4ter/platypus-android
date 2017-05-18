package ch.stair.platypus.card;

import ch.stair.platypus.domain.FeedbackModel;

public interface CardViewListener {
    void onCardClicked(final FeedbackModel feedbackModel);
    void voteUpClicked(final FeedbackModel feedbackModel);
    void voteDownClicked(final FeedbackModel feedbackModel);
}