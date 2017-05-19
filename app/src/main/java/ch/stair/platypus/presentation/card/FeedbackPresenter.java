package ch.stair.platypus.presentation.card;

import java.util.List;

import javax.inject.Inject;

import ch.stair.platypus.di.PerActivity;
import ch.stair.platypus.domain.FeedbackModel;
import ch.stair.platypus.domain.FeedbackInteractor;

@PerActivity
public class FeedbackPresenter {
    private final FeedbackInteractor feedbackInteractor;
    private FeedbackCardView cardView;

    @Inject
    public FeedbackPresenter(final FeedbackInteractor feedbackInteractor) {
        this.feedbackInteractor = feedbackInteractor;
    }

    public void setView(final FeedbackCardView cardView) {
        this.cardView = cardView;
    }

    public void onCardViewClicked(final FeedbackModel feedbackModel) {
        this.cardView.showTestSnackbar(feedbackModel);
    }

    public void initialize() {
        this.feedbackInteractor.fetchOnlineFeedbackAndSaveToDB();
    }

    public void showFeedbacks() {
        this.feedbackInteractor.getFeedbackList(this::getFeedbackCallback);
    }

    private void getFeedbackCallback(List<FeedbackModel> viewModels) {
        this.cardView.renderFeedbackModels(viewModels);
    }

    public void onVoteUpClicked(FeedbackModel feedbackModel) {
        this.cardView.showTestSnackbarForVoteUp(feedbackModel);
    }

    public void onVoteDownClicked(FeedbackModel feedbackModel) {
        this.cardView.showTestSnackbarForVoteDown(feedbackModel);
    }
}
