package ch.stair.platypus;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import ch.stair.platypus.card.CardContentFragment;
import ch.stair.platypus.rest.SyncFeedbacks;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SyncFeedbacks s = new SyncFeedbacks(((App)getApplication()));
        s.fetchLatestFeedbacksToDB();

        this.changeTopLeftIconInToolbarToFunctionAsNavigationBarOpener();
        this.setupViewPagerWith3Fragments();
        this.setupNavigationBarNavigation();
        this.createActionButton();
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

    private void setupViewPagerWith3Fragments() {
        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        final Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new CardContentFragment());
        adapter.addFragment(new ListContentFragment());
        adapter.addFragment(new ListContentFragment());
        viewPager.setAdapter(adapter);

        final TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        tabs.getTabAt(0).setIcon(R.drawable.ic_tab_home);
        tabs.getTabAt(1).setIcon(R.drawable.ic_tab_search);
        tabs.getTabAt(2).setIcon(R.drawable.ic_tab_notifications);
    }

    private void setupNavigationBarNavigation() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        final DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);

        navigationView.setNavigationItemSelectedListener(
                menuItem -> {
                    menuItem.setChecked(true);

                    // TODO: handle navigation

                    mDrawerLayout.closeDrawers();
                    return true;
                });
    }

    private void createActionButton() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(v ->
                Snackbar.make(v, "Hello Snackbar!", Snackbar.LENGTH_LONG).show());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        } else if (id == android.R.id.home) {
            final DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
            mDrawerLayout.openDrawer(GravityCompat.START);
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
