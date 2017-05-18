package ch.stair.platypus.domain;

import java.util.List;

import ch.stair.platypus.models.Feedback;

public interface Repository {
    List<Feedback> getAllFeedbacks();
}
