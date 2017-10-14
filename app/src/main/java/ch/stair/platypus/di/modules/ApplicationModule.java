package ch.stair.platypus.di.modules;

import android.accounts.AccountManager;
import android.content.Context;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import ch.stair.platypus.App;
import ch.stair.platypus.domain.Repository;
import ch.stair.platypus.repository.RepositoryImpl;
import ch.stair.platypus.repository.models.MyObjectBox;
import dagger.Module;
import dagger.Provides;
import io.objectbox.BoxStore;

@Module
public class ApplicationModule {
  private final App application;

  public ApplicationModule(App application) {
    this.application = application;
  }

  @Provides
  @Singleton
  Context provideApplicationContext() {
    return this.application;
  }

  @Provides
  @Singleton
  Repository provideRepository(final RepositoryImpl repository) {
    return repository;
  }

  @Provides
  @Singleton
  BoxStore provideBoxStore() {
    return MyObjectBox.builder()
            .androidContext(this.application)
            .build();
  }

  @Provides
  AccountManager provideAccountManager(final Context context) {
    return AccountManager.get(context);
  }

  @Provides
  Executor provideExecutor() {
    return Executors.newCachedThreadPool();
  }
}
