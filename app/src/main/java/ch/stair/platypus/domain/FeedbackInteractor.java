package ch.stair.platypus.domain;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import ch.stair.platypus.presentation.Callback;
import ch.stair.platypus.presentation.RemoteService;
import ch.stair.platypus.presentation.feedbackcreation.CreateFeedbackActivity;
import ch.stair.platypus.repository.models.Feedback;
import ch.stair.platypus.rest.SyncFeedbacks;

public class FeedbackInteractor {

    private final Repository repository;
    private final SyncFeedbacks syncFeedbacks;
    private final RemoteService remoteService;

    @Inject
    public FeedbackInteractor(
            final Repository repository,
            final SyncFeedbacks syncFeedbacks,
            final RemoteService remoteService) {
        this.repository = repository;
        this.syncFeedbacks = syncFeedbacks;
        this.remoteService = remoteService;
    }

    public void fetchOnlineFeedbackAndSaveToDB() {
        this.syncFeedbacks.fetchLatestFeedbacksToDB();
    }

    public void getFeedbackList(final Callback<List<FeedbackModel>> callback) {
        final List<Feedback> feedbacks = this.repository.getAllFeedbacks();
        final List<FeedbackModel> cardViewModels = feedbacks
                .stream()
                .map(x -> new FeedbackModel(x.getId(), x.getFeedbackText(), x.getCreationDate(), x.getVotesCount()))
                .collect(Collectors.toList());

        callback.callback(cardViewModels);
    }

    public void createFeedback(final CreateFeedbackModel createFeedbackModel) {
        this.remoteService.createFeedback(createFeedbackModel);
    }
}
