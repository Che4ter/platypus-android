package ch.stair.platypus.di.components;

import ch.stair.platypus.card.CardContentFragment;
import ch.stair.platypus.di.PerActivity;
import ch.stair.platypus.di.modules.ActivityModule;
import ch.stair.platypus.di.modules.UserModule;
import dagger.Component;

/**
 * A scope {@link PerActivity} component.
 * Injects user specific Fragments.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, UserModule.class})
public interface UserComponent extends ActivityComponent {
  void inject(CardContentFragment cardContentFragment);
}
