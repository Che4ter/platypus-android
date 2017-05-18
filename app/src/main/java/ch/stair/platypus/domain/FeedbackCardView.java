package ch.stair.platypus.domain;

import java.util.List;

public interface FeedbackCardView {
    void showTestSnackbar(FeedbackModel feedbackModel);
    void renderFeedbackModels(List<FeedbackModel> feedbackModelList);
}
