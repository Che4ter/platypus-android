package ch.stair.platypus.domain;

import java.util.Date;
import java.util.List;

public interface RemoteService {
    boolean createFeedback(final CreateFeedbackModel createFeedbackModel);
    void fetchFeedbacksBefore(final Date date, Observer<List<FeedbackModel>> observer);
}
