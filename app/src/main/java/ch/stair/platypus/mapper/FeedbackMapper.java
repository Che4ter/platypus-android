package ch.stair.platypus.mapper;

import ch.stair.platypus.models.Feedback;
import ch.stair.platypus.models.FeedbackPOJO;

public final class FeedbackMapper {
    private FeedbackMapper(){}

    public static Feedback mapFeedbackPOJOToFeedback(FeedbackPOJO feedbackPOJO){
        return new Feedback(feedbackPOJO.getId(),
                feedbackPOJO.getFeedbackText(),
                feedbackPOJO.getParentId(),
                feedbackPOJO.getLastModified(),
                feedbackPOJO.getCreationDate(),
                feedbackPOJO.getVotesCount(),
                0);
    }
}
