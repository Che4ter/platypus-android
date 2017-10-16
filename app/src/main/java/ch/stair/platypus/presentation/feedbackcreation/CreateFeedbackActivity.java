package ch.stair.platypus.presentation.feedbackcreation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import ch.stair.platypus.R;
import ch.stair.platypus.di.HasComponent;
import ch.stair.platypus.di.components.DaggerFeedbackComponent;
import ch.stair.platypus.di.components.FeedbackComponent;
import ch.stair.platypus.presentation.BaseActivity;

public class CreateFeedbackActivity extends BaseActivity implements HasComponent<FeedbackComponent> {

    private FeedbackComponent feedbackComponent;

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, CreateFeedbackActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_feedback);
        this.initializeInjector();
        this.addFragment(R.id.fragmentContainer, new CreateFeedbackFragment());
    }

    private void initializeInjector() {
        this.feedbackComponent = DaggerFeedbackComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }

    @Override
    public FeedbackComponent getComponent() {
        return this.feedbackComponent;
    }
}
