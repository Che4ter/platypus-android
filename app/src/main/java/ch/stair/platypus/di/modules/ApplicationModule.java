package ch.stair.platypus.di.modules;

import android.content.Context;
import ch.stair.platypus.App;
import ch.stair.platypus.ObjectBoxRepository;
import ch.stair.platypus.domain.Repository;
import ch.stair.platypus.models.MyObjectBox;
import dagger.Module;
import dagger.Provides;
import io.objectbox.BoxStore;

import javax.inject.Singleton;

@Module
public class ApplicationModule {
  private final App application;

  public ApplicationModule(App application) {
    this.application = application;
  }

  @Provides @Singleton Context provideApplicationContext() {
    return this.application;
  }

  @Provides @Singleton Repository provideRepository(final ObjectBoxRepository objectBoxRepository) {
    return objectBoxRepository;
  }

  @Provides
  @Singleton
  BoxStore provideBoxStore() {
    return MyObjectBox.builder()
            .androidContext(this.application)
            .build();
  }
}
