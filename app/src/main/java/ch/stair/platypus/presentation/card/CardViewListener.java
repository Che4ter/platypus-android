package ch.stair.platypus.presentation.card;

import ch.stair.platypus.domain.FeedbackModel;

interface CardViewListener {
    void onCardClicked(final FeedbackModel feedbackModel);
    void voteUpClicked(final FeedbackModel feedbackModel);
    void voteDownClicked(final FeedbackModel feedbackModel);
}