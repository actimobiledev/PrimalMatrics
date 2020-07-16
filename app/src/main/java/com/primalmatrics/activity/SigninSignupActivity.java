package com.primalmatrics.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.primalmatrics.R;
import com.primalmatrics.adapter.SignupPagerAdapter;
import com.primalmatrics.utils.Utils;
import com.primalmatrics.utils.WrapContentHeightViewPager;

public class SigninSignupActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private WrapContentHeightViewPager viewPager;
    private SignupPagerAdapter adapter;





    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin_signup);
        initView();
        initData();
        initListener();

    }

    public void initView(){
        tabLayout=(TabLayout)findViewById(R.id.tabLayout);
        viewPager=(WrapContentHeightViewPager)findViewById(R.id.viewPager);

    }

    public void initData(){
        adapter = new SignupPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


    }


    private void initListener() {

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}
            @Override
            public void onPageSelected(int position) {
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) viewPager.getLayoutParams();
                params.height = viewPager.getHeightAt(position);
                viewPager.setLayoutParams(params);
            }
            @Override
            public void onPageScrollStateChanged(int state) {}
        });


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                switch (position) {
                    case 0:
                       // getOrders();
                        break;
                    case 1:
                       // getOrders();
                        break;

                    case 2:
                        break;
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }






}
