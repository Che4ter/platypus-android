package ch.stair.platypus.di.components;

import ch.stair.platypus.di.PerActivity;
import ch.stair.platypus.di.modules.ActivityModule;
import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
interface ActivityComponent {
}
