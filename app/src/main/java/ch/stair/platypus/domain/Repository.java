package ch.stair.platypus.domain;

import java.util.Date;
import java.util.List;

public interface Repository {
    List<FeedbackModel> getAllFeedbacks();
    void saveFeedbacks(final List<FeedbackModel> feedbacks);
    Date getLastSyncDate();
    void saveLastSyncDate(final Date lastSync);
}
