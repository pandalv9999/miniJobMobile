package com.example.minijobmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.minijobmobile.databinding.ActivityMainBinding;
import com.example.minijobmobile.databinding.NavHeaderBinding;
import com.example.minijobmobile.main.favorite.FavoriteFragment;
import com.example.minijobmobile.main.nearby.NearbyFragment;
import com.example.minijobmobile.main.recommendation.RecommendationFragment;
import com.example.minijobmobile.onboarding.OnBoardingActivity;
import com.example.minijobmobile.util.Config;
import com.example.minijobmobile.util.Utils;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawer;

    // Make sure to be using androidx.appcompat.app.ActionBarDrawerToggle version.
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        NavHeaderBinding headerBinding = NavHeaderBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Intent intent = getIntent();
        String firstName = intent.getStringExtra("firstName");
        String lastName = intent.getStringExtra("lastName");
        Config.getInstance(intent.getStringExtra("userId"), firstName, lastName);

        // Set a Toolbar to replace the ActionBar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // This will display an Up icon (<-), we will replace it with hamburger later
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, new NearbyFragment()).commit();

        // Find our drawer view
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView nvDrawer = (NavigationView) findViewById(R.id.nvView);
        binding.nvView.setNavigationItemSelectedListener( item -> {
            selectDrawerItem(item);
            return true;
        });

        headerBinding.headerFirstName.setText(Config.getInstance().getFirstName());
        headerBinding.headerLastName.setText(Config.getInstance().getLastName());
        headerBinding.headerLatitude.setText(String.valueOf(Config.latitude));
        headerBinding.headerLongitude.setText(String.valueOf(Config.longitude));
        Utils.showToast(MainActivity.this, "text" + headerBinding.headerLongitude.getText().toString()).show();
    }


    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        Fragment fragment = null;
        Class fragmentClass;
        switch(menuItem.getItemId()) {
            case R.id.nav_fav_fragment:
                fragmentClass = FavoriteFragment.class;
                break;
            case R.id.nav_recommendation_fragment:
                fragmentClass = RecommendationFragment.class;
                break;
            case R.id.logout:
                Intent intent = new Intent(MainActivity.this, OnBoardingActivity.class);
                startActivity(intent);
                finish();
            default:
                fragmentClass = NearbyFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        mDrawer.closeDrawers();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}