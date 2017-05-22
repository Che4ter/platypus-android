package ch.stair.platypus.presentation;

import ch.stair.platypus.domain.CreateFeedbackModel;

public interface RemoteService {
    boolean createFeedback(final CreateFeedbackModel createFeedbackModel);
}
