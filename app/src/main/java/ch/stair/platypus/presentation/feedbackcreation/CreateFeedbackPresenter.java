package ch.stair.platypus.presentation.feedbackcreation;

import javax.inject.Inject;

import ch.stair.platypus.di.PerActivity;
import ch.stair.platypus.domain.CreateFeedbackInteractor;
import ch.stair.platypus.domain.CreateFeedbackModel;
import ch.stair.platypus.domain.Observer;

@PerActivity
public class CreateFeedbackPresenter {
    private final CreateFeedbackInteractor createFeedbackInteractor;
    private CreateFeedbackView createFeedbackView;
    @Inject
    public CreateFeedbackPresenter(final CreateFeedbackInteractor createFeedbackInteractor) {
        this.createFeedbackInteractor = createFeedbackInteractor;
    }

    public void setView(final CreateFeedbackView createFeedbackView) {
        this.createFeedbackView = createFeedbackView;
    }

    public void createFeedback(final CreateFeedbackModel createFeedbackModel) {
        this.createFeedbackInteractor.execute(this.observer, createFeedbackModel);
    }

    private Observer<Boolean> observer = new Observer<Boolean>() {
        @Override
        public void onFinished(Boolean aBoolean) {
            CreateFeedbackPresenter.this.createFeedbackView.closeView();
        }
    };
}
