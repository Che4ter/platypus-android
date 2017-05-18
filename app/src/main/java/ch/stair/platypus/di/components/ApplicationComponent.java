package ch.stair.platypus.di.components;

import android.content.Context;

import ch.stair.platypus.BaseActivity;
import ch.stair.platypus.di.modules.ApplicationModule;
import ch.stair.platypus.domain.Repository;
import dagger.Component;
import javax.inject.Singleton;

/**
 * A component whose lifetime is the life of the application.
 */
@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
  void inject(BaseActivity baseActivity);

  //Exposed to sub-graphs.
  Context context();
  Repository repository();
}
