package ch.stair.platypus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ch.stair.platypus.di.components.ApplicationComponent;
import ch.stair.platypus.di.modules.ActivityModule;

public abstract class BaseActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.getApplicationComponent().inject(this);
  }

  protected ApplicationComponent getApplicationComponent() {
    return ((App) getApplication()).getApplicationComponent();
  }

  protected ActivityModule getActivityModule() {
    return new ActivityModule(this);
  }
}
