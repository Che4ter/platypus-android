package ch.stair.platypus.rest;

import javax.inject.Inject;

import ch.stair.platypus.domain.CreateFeedbackModel;
import ch.stair.platypus.presentation.RemoteService;

public class RemoteServiceImpl implements RemoteService{

    @Inject
    public RemoteServiceImpl(){
    }

    @Override
    public boolean createFeedback(CreateFeedbackModel createFeedbackModel) {
        return false;
    }
}
