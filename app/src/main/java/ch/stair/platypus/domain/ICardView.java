package ch.stair.platypus.domain;

import java.util.List;

public interface ICardView {
    void showTestSnackbar(CardViewModel cardViewModel);
    void renderCardViewModels(List<CardViewModel> cardViewModels);
}
