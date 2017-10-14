package ch.stair.platypus.domain;

import java.util.concurrent.Executor;

import javax.inject.Inject;

public class CreateFeedbackInteractor extends Interactor<Boolean, CreateFeedbackModel> {

    private final RemoteService remoteService;

    @Inject
    public CreateFeedbackInteractor(final Executor executor, final RemoteService remoteService) {
        super(executor);
        this.remoteService = remoteService;
    }

    @Override
    protected Boolean execute(CreateFeedbackModel createFeedbackModel) {
        return this.remoteService.createFeedback(createFeedbackModel);
    }
}
