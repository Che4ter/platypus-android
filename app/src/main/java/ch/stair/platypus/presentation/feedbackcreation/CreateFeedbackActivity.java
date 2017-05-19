package ch.stair.platypus.presentation.feedbackcreation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import ch.stair.platypus.R;
import ch.stair.platypus.di.HasComponent;
import ch.stair.platypus.di.components.CreateFeedbackComponent;
import ch.stair.platypus.di.components.DaggerCreateFeedbackComponent;
import ch.stair.platypus.presentation.BaseActivity;

public class CreateFeedbackActivity extends BaseActivity implements HasComponent<CreateFeedbackComponent> {

    private CreateFeedbackComponent createFeedbackComponent;

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, CreateFeedbackActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);
        this.initializeInjector();
        this.addFragment(R.id.fragmentContainer, new CreateFeedbackFragment());
    }

    private void initializeInjector() {
        this.createFeedbackComponent = DaggerCreateFeedbackComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }

    @Override
    public CreateFeedbackComponent getComponent() {
        return this.createFeedbackComponent;
    }
}
