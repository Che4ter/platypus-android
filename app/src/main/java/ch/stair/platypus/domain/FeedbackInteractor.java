package ch.stair.platypus.domain;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import javax.inject.Inject;

import ch.stair.platypus.Callback;
import ch.stair.platypus.models.Feedback;

import static ch.stair.platypus.Helpers.getReadableDate;

public class FeedbackInteractor {

    private Repository repository;

    @Inject
    public FeedbackInteractor(final Repository repository) {
        this.repository = repository;
    }

    public void getFeedbackList(Callback<List<CardViewModel>> callback) {
        final List<Feedback> feedbacks = this.repository.getAllFeedbacks();
        final List<CardViewModel> cardViewModels = feedbacks
                .stream()
                .map(x -> new CardViewModel(x.getId(), x.getFeedbackText(),getReadableDate(x.getCreationDate()), x.getVotesCount()))
                .collect(Collectors.toList());

        callback.callback(cardViewModels);
    }
}
