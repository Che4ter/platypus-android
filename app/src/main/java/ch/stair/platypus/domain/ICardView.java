package ch.stair.platypus.domain;

import java.util.List;

public interface ICardView {
    void showTestSnackbar(FeedbackModel feedbackModel);
    void renderFeedbackModels(List<FeedbackModel> feedbackModelList);
}
