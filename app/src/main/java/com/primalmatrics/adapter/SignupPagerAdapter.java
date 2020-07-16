package com.primalmatrics.adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.primalmatrics.fragment.LoginFragment;
import com.primalmatrics.fragment.SignUpFragment;



public class SignupPagerAdapter extends FragmentPagerAdapter {
    String[] title = {"Login", "Signup"};
    public SignupPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new LoginFragment();
            case 1:
                return new SignUpFragment();
                default:
                return null; // Problem occurs at this condition!
        }
    }
    @Override
    public int getCount() {
        return 2;
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
