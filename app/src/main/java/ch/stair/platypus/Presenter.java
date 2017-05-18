package ch.stair.platypus;

import java.util.List;

import javax.inject.Inject;

import ch.stair.platypus.di.PerActivity;
import ch.stair.platypus.domain.CardViewModel;
import ch.stair.platypus.domain.FeedbackInteractor;
import ch.stair.platypus.domain.ICardView;

@PerActivity
public class Presenter {
    private final FeedbackInteractor feedbackInteractor;
    private ICardView cardView;

    @Inject public Presenter(final FeedbackInteractor feedbackInteractor) {
        this.feedbackInteractor = feedbackInteractor;
    }

    public void setView(ICardView cardView) {
        this.cardView = cardView;
    }

    public void onCardViewClicked(CardViewModel cardViewModel) {
        this.cardView.showTestSnackbar(cardViewModel);
    }

    public void initialize() {
        this.feedbackInteractor.getFeedbackList(this::getFeedbackCallback);
    }

    private void getFeedbackCallback(List<CardViewModel> viewModels) {
        this.cardView.renderCardViewModels(viewModels);
    }
}
