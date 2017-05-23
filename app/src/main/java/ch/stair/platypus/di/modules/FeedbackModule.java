package ch.stair.platypus.di.modules;

import ch.stair.platypus.presentation.RemoteService;
import ch.stair.platypus.rest.RemoteServiceImpl;
import dagger.Module;
import dagger.Provides;

@Module
public class FeedbackModule {

    public FeedbackModule() {}

    @Provides
    RemoteService provideRemoteService(final RemoteServiceImpl remoteService) {
    return remoteService;
    }
}
