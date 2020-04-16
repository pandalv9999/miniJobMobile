package com.example.minijobmobile.onboarding;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.minijobmobile.MainActivity;
import com.example.minijobmobile.R;
import com.example.minijobmobile.onboarding.login.LoginFragment;
import com.example.minijobmobile.onboarding.register.RegisterFragment;
import com.example.minijobmobile.util.Config;
import com.example.minijobmobile.util.Utils;
import android.location.LocationListener;

import java.util.ArrayList;

public class OnBoardingActivity extends AppCompatActivity implements LocationListener {

    LocationManager locationManager;

    class OnboardingPageAdapter extends FragmentPagerAdapter {

        ArrayList<Fragment> fragmentList = new ArrayList<>();

        public OnboardingPageAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        void addFragment(Fragment fragment) {
            fragmentList.add(fragment);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);

        ViewPager viewPager = findViewById(R.id.viewPager);
        OnboardingPageAdapter pageAdapter = new OnboardingPageAdapter(getSupportFragmentManager());
        pageAdapter.addFragment(new LoginFragment());
        pageAdapter.addFragment(new RegisterFragment());
        viewPager.setAdapter(pageAdapter);
        getLocation();
    }

    void getLocation() {
        try {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, this);
        }
        catch(SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        Config.latitude = location.getLatitude();
        Config.longitude = location.getLongitude();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {
        Utils.showToast(OnBoardingActivity.this, "Please Enable GPS and Internet").show();
    }


}
