package br.com.dfn.bottomnavigation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import br.com.dfn.bottomnavigation.ui.DashboardFragment;
import br.com.dfn.bottomnavigation.ui.HomeFragment;
import br.com.dfn.bottomnavigation.ui.ProfileFragment;

public class MainActivity extends AppCompatActivity {

    private HomeFragment homeFragment;
    private DashboardFragment dashboardFragment;
    private ProfileFragment profileFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener
            mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    changeFragment(homeFragment);
                    return true;
                case R.id.navigation_dashboard:
                    changeFragment(dashboardFragment);
                    return true;
                case R.id.navigation_profile:
                    changeFragment(profileFragment);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.findFragmentById(R.id.content) == null) {
            homeFragment = new HomeFragment();
            dashboardFragment = new DashboardFragment();
            profileFragment = new ProfileFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.content, homeFragment)
                    .add(R.id.content, dashboardFragment)
                    .add(R.id.content, profileFragment)
                    .hide(dashboardFragment)
                    .hide(profileFragment)
                    .commit();
        } else {
            buildFragments(fragmentManager);
        }

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    private void buildFragments(FragmentManager fragmentManager) {

        homeFragment = (HomeFragment) fragmentManager.getFragments().get(0);
        dashboardFragment = (DashboardFragment)fragmentManager.getFragments().get(1);
        profileFragment = (ProfileFragment) fragmentManager.getFragments().get(2);
    }


    private void changeFragment(Fragment frg) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .hide(homeFragment)
                .hide(dashboardFragment)
                .hide(profileFragment)
                .show(frg).commit();
    }
}
