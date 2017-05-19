package ch.stair.platypus;

import java.util.List;

import javax.inject.Inject;

import ch.stair.platypus.di.PerActivity;
import ch.stair.platypus.domain.FeedbackModel;
import ch.stair.platypus.domain.FeedbackInteractor;
import ch.stair.platypus.domain.FeedbackCardView;

@PerActivity
public class Presenter {
    private final FeedbackInteractor feedbackInteractor;
    private FeedbackCardView cardView;

    @Inject
    public Presenter(final FeedbackInteractor feedbackInteractor) {
        this.feedbackInteractor = feedbackInteractor;
    }

    public void setView(FeedbackCardView cardView) {
        this.cardView = cardView;
    }

    public void onCardViewClicked(FeedbackModel feedbackModel) {
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
