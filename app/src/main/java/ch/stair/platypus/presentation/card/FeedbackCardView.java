package ch.stair.platypus.presentation.card;

import java.util.List;

import ch.stair.platypus.domain.FeedbackModel;

public interface FeedbackCardView {
    void renderFeedbackModels(List<FeedbackModel> feedbackModelList);
    void showTestSnackbar(FeedbackModel feedbackModel);
    void showTestSnackbarForVoteUp(FeedbackModel feedbackModel);
    void showTestSnackbarForVoteDown(FeedbackModel feedbackModel);
}
