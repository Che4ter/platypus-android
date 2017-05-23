package ch.stair.platypus.rest;

import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;

import java.io.IOException;

import javax.inject.Inject;

import ch.stair.platypus.authentication.AuthenticationManager;
import ch.stair.platypus.domain.CreateFeedbackModel;
import ch.stair.platypus.presentation.RemoteService;

public class RemoteServiceImpl implements RemoteService{

    private final AuthenticationManager authenticationManager;

    @Inject
    public RemoteServiceImpl(final AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
    }

    @Override
    public boolean createFeedback(final CreateFeedbackModel createFeedbackModel) {
        boolean wasSuccessful = false;

        try {
            final String token = this.authenticationManager.getToken();
            if (token != null) {
                wasSuccessful = true;
            }
        } catch (AuthenticatorException | OperationCanceledException | IOException e) {
            e.printStackTrace();
        }

        return wasSuccessful;
    }
}
