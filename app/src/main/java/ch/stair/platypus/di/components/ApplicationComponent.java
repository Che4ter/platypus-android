package ch.stair.platypus.di.components;

import javax.inject.Singleton;

import ch.stair.platypus.BaseActivity;
import ch.stair.platypus.di.modules.ApplicationModule;
import ch.stair.platypus.domain.Repository;
import dagger.Component;
import io.objectbox.BoxStore;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
  void inject(BaseActivity baseActivity);
  Repository repository();
  BoxStore boxStore();
}
