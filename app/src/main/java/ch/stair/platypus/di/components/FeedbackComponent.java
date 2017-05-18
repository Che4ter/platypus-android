package ch.stair.platypus.di.components;

import ch.stair.platypus.card.CardContentFragment;
import ch.stair.platypus.di.PerActivity;
import ch.stair.platypus.di.modules.ActivityModule;
import ch.stair.platypus.di.modules.FeedbackModule;
import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, FeedbackModule.class})
public interface FeedbackComponent extends ActivityComponent {
  void inject(CardContentFragment cardContentFragment);
}
