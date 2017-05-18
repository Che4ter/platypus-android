package ch.stair.platypus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ch.stair.platypus.di.components.ApplicationComponent;
import ch.stair.platypus.di.modules.ActivityModule;

/**
 * Base {@link android.app.Activity} class for every Activity in this application.
 */
public abstract class BaseActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.getApplicationComponent().inject(this);
  }

  /**
   * Get the Main Application component for dependency injection.
   *
   * @return {@link ApplicationComponent}
   */
  protected ApplicationComponent getApplicationComponent() {
    return ((App) getApplication()).getApplicationComponent();
  }

  /**
   * Get an Activity module for dependency injection.
   *
   * @return {@link ActivityModule}
   */
  protected ActivityModule getActivityModule() {
    return new ActivityModule(this);
  }
}
