package ch.stair.platypus.domain;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import ch.stair.platypus.Callback;
import ch.stair.platypus.models.Feedback;
import ch.stair.platypus.rest.SyncFeedbacks;

public class FeedbackInteractor {

    private final Repository repository;
    private final SyncFeedbacks syncFeedbacks;

    @Inject
    public FeedbackInteractor(
        final Repository repository,
        final SyncFeedbacks syncFeedbacks) {
        this.repository = repository;
        this.syncFeedbacks = syncFeedbacks;
    }

    public void fetchOnlineFeedbackAndSaveToDB() {
        this.syncFeedbacks.fetchLatestFeedbacksToDB();
    }

    public void getFeedbackList(Callback<List<FeedbackModel>> callback) {
        final List<Feedback> feedbacks = this.repository.getAllFeedbacks();
        final List<FeedbackModel> cardViewModels = feedbacks
                .stream()
                .map(x -> new FeedbackModel(x.getId(), x.getFeedbackText(), x.getCreationDate(), x.getVotesCount()))
                .collect(Collectors.toList());

        callback.callback(cardViewModels);
    }
}
