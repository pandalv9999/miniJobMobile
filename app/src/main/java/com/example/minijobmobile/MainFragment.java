package com.example.minijobmobile;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.example.minijobmobile.databinding.NavHeaderBinding;
import com.example.minijobmobile.main.favorite.FavoriteFragment;
import com.example.minijobmobile.main.nearby.NearbyFragment;
import com.example.minijobmobile.main.recommendation.RecommendationFragment;
import com.example.minijobmobile.onboarding.OnBoardingBaseFragment;
import com.example.minijobmobile.util.Config;
import com.example.minijobmobile.util.Utils;
import com.google.android.material.navigation.NavigationView;

public class MainFragment extends Fragment {

    private DrawerLayout mDrawer;
    private NavigationManager navigationManager;

    // Make sure to be using androidx.appcompat.app.ActionBarDrawerToggle version.
    private ActionBarDrawerToggle drawerToggle;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        navigationManager = (NavigationManager) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        NavHeaderBinding headerBinding = NavHeaderBinding.inflate(getLayoutInflater());


        // Set a Toolbar to replace the ActionBar.
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        // This will display an Up icon (<-), we will replace it with hamburger later
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FragmentManager fragmentManager = getChildFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, new NearbyFragment()).commit();

        // Find our drawer view
        mDrawer = (DrawerLayout) view.findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(getActivity(), mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        NavigationView nvDrawer = (NavigationView) view.findViewById(R.id.nvView);
        nvDrawer.setNavigationItemSelectedListener( item -> {
            selectDrawerItem(item);
            return true;
        });

        headerBinding.headerFirstName.setText(Config.getInstance().getFirstName());
        headerBinding.headerLastName.setText(Config.getInstance().getLastName());
        headerBinding.headerLatitude.setText(String.valueOf(Config.latitude));
        headerBinding.headerLongitude.setText(String.valueOf(Config.longitude));
        Utils.showToast(getContext(), "text" + headerBinding.headerLongitude.getText().toString()).show();
        return view;
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
                navigationManager.navigateTo(new OnBoardingBaseFragment());
                return;
            default:
                fragmentClass = NearbyFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getChildFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        getActivity().setTitle(menuItem.getTitle());
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
