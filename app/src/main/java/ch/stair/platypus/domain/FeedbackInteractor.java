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

    public void fetchOnlineFeedbackAndSaveToDB() {
        this.syncFeedbacks.fetchLatestFeedbacksToDB();
    }

    @Override
    protected List<FeedbackModel> execute(Void unused) {
        final List<Feedback> feedbacks = this.repository.getAllFeedbacks();

        final List<FeedbackModel> cardViewModels = feedbacks
                .stream()
                .map(x -> new FeedbackModel(
                        x.getId(),
                        x.getFeedbackText(),
                        x.getCreationDate(),
                        x.getVotesCount(),
                        x.feedbackHashtagses
                        )
                )
                .collect(Collectors.toList());
        return cardViewModels;
    }
}
