package ch.stair.platypus.domain;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

import javax.inject.Inject;

import ch.stair.platypus.repository.models.Feedback;
import ch.stair.platypus.rest.SyncFeedbacks;

public class FeedbackInteractor extends Interactor<List<FeedbackModel>, Void> {

    private final Repository repository;
    private final SyncFeedbacks syncFeedbacks;

    @Inject
    public FeedbackInteractor(
            final Executor executor,
            final Repository repository,
            final SyncFeedbacks syncFeedbacks) {
        super(executor);
        this.repository = repository;
        this.syncFeedbacks = syncFeedbacks;
    }

    public void fetchRemoteFeedbacks() {
        this.syncFeedbacks.fetchLatestFeedbacks(new Observer<List<FeedbackModel>>() {
            @Override
            public void onFinished(List<FeedbackModel> feedbackModels) {
                FeedbackInteractor.this.repository.saveFeedbacks(feedbackModels);
            }
        });
    }

    @Override
    protected List<FeedbackModel> execute(Void unused) {
        final List<FeedbackModel> feedbacks = this.repository.getAllFeedbacks();
        return feedbacks;
    }
}
