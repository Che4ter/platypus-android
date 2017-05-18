package ch.stair.platypus.card;

import ch.stair.platypus.domain.FeedbackModel;

public interface CardViewListener {
    void onCardClicked(final FeedbackModel cardViewModel);
}