package ch.stair.platypus.domain;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;

public class FeedbackInteractor extends Interactor<List<FeedbackModel>, Void> {

    private final Repository repository;
    private final RemoteService remoteService;

    @Inject
    public FeedbackInteractor(
            final Executor executor,
            final Repository repository,
            final RemoteService remoteService) {
        super(executor);
        this.repository = repository;
        this.remoteService = remoteService;
    }

    public void fetchRemoteFeedbacks() {
        this.remoteService.fetchFeedbacksBefore(this.repository.getLastSyncDate(), new Observer<List<FeedbackModel>>() {
            @Override
            public void onFinished(List<FeedbackModel> feedbackModels) {
                final int FiveMinutesServerAndClientTimeDifferenceBuffer = 5 * 60;
                final long lastSync = (System.currentTimeMillis() / 1000L) - FiveMinutesServerAndClientTimeDifferenceBuffer;
                final Date lastSyncDate = new Date(lastSync);
                FeedbackInteractor.this.repository.saveLastSyncDate(lastSyncDate);
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
