package com.example.minijobmobile.onboarding;

import android.Manifest;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.minijobmobile.R;
import com.example.minijobmobile.databinding.FragmentOnBoardingBinding;
import com.example.minijobmobile.onboarding.login.LoginFragment;
import com.example.minijobmobile.onboarding.register.RegisterFragment;

import java.util.ArrayList;

public class OnBoardingBaseFragment extends Fragment {

    LocationManager locationManager;
    FragmentOnBoardingBinding binding;

    class OnboardingPageAdapter extends FragmentPagerAdapter {

        ArrayList<Fragment> fragmentList = new ArrayList<>();

        public OnboardingPageAdapter(FragmentManager fragmentManager) {
            super(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_on_boarding, container, false);
        ViewPager viewPager = view.findViewById(R.id.viewPager);
        OnboardingPageAdapter pageAdapter = new OnboardingPageAdapter(getChildFragmentManager());
        pageAdapter.addFragment(new LoginFragment());
        pageAdapter.addFragment(new RegisterFragment());
        viewPager.setAdapter(pageAdapter);
        return view;
    }



}
