package ch.stair.platypus.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import ch.stair.platypus.R;
import ch.stair.platypus.authentication.AccountGeneral;
import ch.stair.platypus.authentication.AccountHandling;
import ch.stair.platypus.di.HasComponent;
import ch.stair.platypus.di.components.DaggerFeedbackComponent;
import ch.stair.platypus.di.components.FeedbackComponent;
import ch.stair.platypus.presentation.card.CardContentFragment;
import ch.stair.platypus.presentation.feedbackcreation.CreateFeedbackActivity;

public class MainActivity extends BaseActivity implements HasComponent<FeedbackComponent> {

    private FeedbackComponent feedbackComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initializeInjector();

        this.changeTopLeftIconInToolbarToFunctionAsNavigationBarOpener();
        this.setupTabs();
        this.setupSwipeRefresh();
        this.setupNavigationBar();
        this.setupCreateFeedbackActionButton();
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

    private void changeTopLeftIconInToolbarToFunctionAsNavigationBarOpener() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            VectorDrawableCompat indicator
                    = VectorDrawableCompat.create(getResources(), R.drawable.ic_menu, getTheme());
            indicator.setTint(ResourcesCompat.getColor(getResources(),R.color.white,getTheme()));
            supportActionBar.setHomeAsUpIndicator(indicator);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setupTabs() {
        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        final Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new CardContentFragment());
        viewPager.setAdapter(adapter);

        final TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        tabs.getTabAt(0).setIcon(R.drawable.ic_tab_home);
    }

    private void setupSwipeRefresh() {
        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        final Adapter adapter = (Adapter) viewPager.getAdapter();
        final SwipeRefreshLayout refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        refreshLayout.setOnRefreshListener(() -> {
            Refreshable r = (Refreshable) adapter.getItem(viewPager.getCurrentItem());
            r.refresh();
            refreshLayout.setRefreshing(false);
        });
    }

    private void setupNavigationBar() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        final DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);

        navigationView.setNavigationItemSelectedListener(
                menuItem -> {
                    menuItem.setChecked(true);

                    // TODO: handle navigation
                    AccountHandling auth = new AccountHandling(this);
                    auth.getTokenForAccountCreateIfNeeded(
                            AccountGeneral.ACCOUNT_TYPE,
                            AccountGeneral.AUTHTOKEN_TYPE_STUDENT_ACCESS,
                            this,
                            findViewById(R.id.drawer));
                    mDrawerLayout.closeDrawers();
                    return true;
                });
    }

    private void setupCreateFeedbackActionButton() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            // TODO wtjerry: decide whether a login screen shall be shown first and then forward to the create feedback screen, or if being logged in is a precondition
            Intent intentToLaunch = CreateFeedbackActivity.getCallingIntent(this);
            this.startActivity(intentToLaunch);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_refresh) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();

        public Adapter(final FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(final int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(final Fragment fragment) {
            mFragmentList.add(fragment);
        }

        @Override
        public CharSequence getPageTitle(final int position) {
            return "";
        }
    }
}
