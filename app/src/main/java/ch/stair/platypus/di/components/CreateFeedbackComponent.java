package ch.stair.platypus.di.components;

import ch.stair.platypus.di.PerActivity;
import ch.stair.platypus.di.modules.ActivityModule;
import ch.stair.platypus.di.modules.CreateFeedbackModule;
import ch.stair.platypus.di.modules.FeedbackModule;
import ch.stair.platypus.presentation.card.CardContentFragment;
import ch.stair.platypus.presentation.feedbackcreation.CreateFeedbackFragment;
import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, CreateFeedbackModule.class})
public interface CreateFeedbackComponent extends ActivityComponent {
  void inject(CreateFeedbackFragment createFeedbackFragment);
}
