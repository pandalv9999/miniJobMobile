package com.example.minijobmobile;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.minijobmobile.databinding.ActivityMainBinding;
import com.example.minijobmobile.databinding.NavHeaderBinding;
import com.example.minijobmobile.main.favorite.FavoriteFragment;
import com.example.minijobmobile.main.nearby.NearbyFragment;
import com.example.minijobmobile.main.recommendation.RecommendationFragment;
import com.example.minijobmobile.onboarding.OnBoardingBaseFragment;
import com.example.minijobmobile.util.Config;
import com.example.minijobmobile.util.Utils;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements LocationListener, NavigationManager {
    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        getLocation();
        navigateTo(new OnBoardingBaseFragment());

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
        Utils.showToast(MainActivity.this, "Please Enable GPS and Internet").show();
    }

    @Override
    public void navigateTo(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_view, fragment, null)
                .addToBackStack(null)
                .commit();
    }
}