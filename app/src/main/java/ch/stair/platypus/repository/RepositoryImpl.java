package ch.stair.platypus.repository;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import ch.stair.platypus.domain.FeedbackModel;
import ch.stair.platypus.domain.Repository;

public class RepositoryImpl implements Repository {
    private final ObjectBoxRepository objectBoxRepository;
    private final PreferencesRepository preferencesRepository;

    @Inject
    public RepositoryImpl(
        final ObjectBoxRepository objectBoxRepository,
        final PreferencesRepository preferencesRepository) {
        this.objectBoxRepository = objectBoxRepository;
        this.preferencesRepository = preferencesRepository;
    }

    @Override
    public List<FeedbackModel> getAllFeedbacks() {
        return this.objectBoxRepository.getAllFeedbacks();
    }

    public void saveFeedbacks(final List<FeedbackModel> feedbacks) {
        this.objectBoxRepository.saveFeedbacks(feedbacks);
    }

    @Override
    public Date getLastSyncDate() {
        return this.preferencesRepository.getLastSyncDate();
    }

    @Override
    public void saveLastSyncDate(Date lastSync) {
        this.preferencesRepository.saveLastSyncDate(lastSync);
    }
}
