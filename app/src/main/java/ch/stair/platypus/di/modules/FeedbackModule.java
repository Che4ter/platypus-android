package ch.stair.platypus.di.modules;

import ch.stair.platypus.domain.RemoteService;
import ch.stair.platypus.rest.PlatypusClient;
import ch.stair.platypus.rest.RemoteServiceImpl;
import ch.stair.platypus.rest.ServiceGenerator;
import dagger.Module;
import dagger.Provides;

@Module
public class FeedbackModule {

    public FeedbackModule() {}

    @Provides
    RemoteService provideRemoteService(final RemoteServiceImpl remoteService) {
        return remoteService;
    }

    @Provides
    PlatypusClient providePlatypusClient() {
        return ServiceGenerator.createService(PlatypusClient.class);
    }
}
