package ch.stair.platypus.presentation.feedbackcreation;

import javax.inject.Inject;

import ch.stair.platypus.di.PerActivity;
import ch.stair.platypus.domain.CreateFeedbackModel;
import ch.stair.platypus.domain.FeedbackInteractor;

@PerActivity
public class CreateFeedbackPresenter {
    private final FeedbackInteractor feedbackInteractor;
    private CreateFeedbackView createFeedbackView;

    @Inject
    public CreateFeedbackPresenter(final FeedbackInteractor feedbackInteractor) {
        this.feedbackInteractor = feedbackInteractor;
    }

    public void setView(final CreateFeedbackView createFeedbackView) {
        this.createFeedbackView = createFeedbackView;
    }


    public void createFeedback(final CreateFeedbackModel createFeedbackModel) {
        this.feedbackInteractor.createFeedback(createFeedbackModel);
        this.createFeedbackView.closeView();
    }
}
