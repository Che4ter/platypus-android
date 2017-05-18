package ch.stair.platypus.di.components;

import android.app.Activity;

import ch.stair.platypus.di.PerActivity;
import ch.stair.platypus.di.modules.ActivityModule;
import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
interface ActivityComponent {
  //Exposed to sub-graphs.
  Activity activity();
}
