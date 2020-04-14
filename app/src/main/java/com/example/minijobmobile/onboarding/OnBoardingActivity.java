package com.example.minijobmobile.onboarding;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.minijobmobile.R;
import com.example.minijobmobile.onboarding.login.LoginFragment;
import com.example.minijobmobile.onboarding.register.RegisterFragment;

import java.util.ArrayList;

public class OnBoardingActivity extends AppCompatActivity {

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
    }
}
