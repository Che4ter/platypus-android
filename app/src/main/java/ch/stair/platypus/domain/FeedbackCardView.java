package ch.stair.platypus.domain;

import java.util.List;

public interface FeedbackCardView {
    void renderFeedbackModels(List<FeedbackModel> feedbackModelList);
    void showTestSnackbar(FeedbackModel feedbackModel);
    void showTestSnackbarForVoteUp(FeedbackModel feedbackModel);
    void showTestSnackbarForVoteDown(FeedbackModel feedbackModel);
}
