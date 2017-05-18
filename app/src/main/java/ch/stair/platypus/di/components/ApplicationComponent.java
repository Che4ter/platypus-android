package ch.stair.platypus.di.components;

import android.content.Context;

import ch.stair.platypus.BaseActivity;
import ch.stair.platypus.di.modules.ApplicationModule;
import ch.stair.platypus.domain.Repository;
import dagger.Component;
import io.objectbox.BoxStore;

import javax.inject.Singleton;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
  void inject(BaseActivity baseActivity);

  Context context();
  Repository repository();
  BoxStore boxStore();
}
