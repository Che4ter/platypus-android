package ch.stair.platypus.card;

import ch.stair.platypus.domain.CardViewModel;

public interface CardViewListener {
    void onCardClicked(final CardViewModel cardViewModel);
}