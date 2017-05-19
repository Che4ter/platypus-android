package ch.stair.platypus.presentation.feedbackcreation;

import javax.inject.Inject;

import ch.stair.platypus.di.PerActivity;
import ch.stair.platypus.domain.CreateFeedbackModel;

@PerActivity
public class CreateFeedbackPresenter {
    private CreateFeedbackView createFeedbackView;

    @Inject
    public CreateFeedbackPresenter() {
    }

    public void setView(final CreateFeedbackView createFeedbackView) {
        this.createFeedbackView = createFeedbackView;
    }


    public void createFeedback(CreateFeedbackModel createFeedbackModel) {
        this.createFeedbackView.closeView();
    }
}
