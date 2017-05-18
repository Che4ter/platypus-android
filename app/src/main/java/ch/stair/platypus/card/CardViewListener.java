package ch.stair.platypus.card;

import android.view.View;

import ch.stair.platypus.domain.FeedbackModel;

public interface CardViewListener {
    void onCardClicked(final FeedbackModel feedbackModel);
    void voteUpClicked(final View cardView, final FeedbackModel feedbackModel);
    void voteDownClicked(final View cardView, final FeedbackModel feedbackModel);
}