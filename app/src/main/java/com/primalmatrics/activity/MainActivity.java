package com.primalmatrics.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.primalmatrics.R;
import com.primalmatrics.fragment.FeedFragment;
import com.primalmatrics.fragment.MoreFragment;
import com.primalmatrics.fragment.PrimalDailyFragment;
import com.primalmatrics.fragment.PrimalMapFragment;
import com.primalmatrics.fragment.PrimalReflectionFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static BottomNavigationView bottomNavigationView;
    CoordinatorLayout clMain;
    ArrayList<Integer> screenList = new ArrayList<>();


    public static void changeFragment(int position) {
        switch (position) {
            case 0:
                bottomNavigationView.setSelectedItemId(R.id.primal_map);
                break;
            case 1:
                bottomNavigationView.setSelectedItemId(R.id.primal_daily);
                break;
            case 2:
                bottomNavigationView.setSelectedItemId(R.id.feed);
                break;
            case 3:
                bottomNavigationView.setSelectedItemId(R.id.primal_reflection);
                break;
            case 4:
                bottomNavigationView.setSelectedItemId(R.id.more);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initListener();
        initFirstFragment();

    }

    private void initFirstFragment() {
        bottomNavigationView.getMenu().findItem(R.id.primal_map).setIcon(R.drawable.business);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, PrimalMapFragment.newInstance(false));
        transaction.commit();
    }


    private void initView() {
        clMain = (CoordinatorLayout) findViewById(R.id.clMain);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
    }

    private void initData() {
        Menu menu = bottomNavigationView.getMenu();
    }


    private void initListener() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Menu menu = bottomNavigationView.getMenu();
                menu.findItem(R.id.primal_map).setIcon(R.drawable.business);
                menu.findItem(R.id.primal_daily).setIcon(R.drawable.check);
                menu.findItem(R.id.feed).setIcon(R.drawable.megaphone);
                menu.findItem(R.id.primal_reflection).setIcon(R.drawable.bars_graphic);
                menu.findItem(R.id.more).setIcon(R.drawable.more);

                Fragment selectedFragment = null;

                switch (item.getItemId()) {
                    case R.id.primal_map:
                        if (screenList.contains(R.id.primal_map)) {
                            selectedFragment = PrimalMapFragment.newInstance(false);
                        } else {
                            selectedFragment = PrimalMapFragment.newInstance(true);
                        }
                        screenList.add(R.id.primal_map);
                        break;
                    case R.id.primal_daily:
                        if (screenList.contains(R.id.primal_daily)) {
                            selectedFragment = PrimalDailyFragment.newInstance(false);
                        } else {
                            selectedFragment = PrimalDailyFragment.newInstance(true);
                        }
                        screenList.add(R.id.primal_daily);
                        break;


                    case R.id.feed:
                        if (screenList.contains(R.id.feed)) {
                            selectedFragment = FeedFragment.newInstance(false);
                        } else {
                            selectedFragment = FeedFragment.newInstance(true);
                        }
                        screenList.add(R.id.feed);
                        break;

                    case R.id.primal_reflection:
                        // if (consumerDetailsPref.getIntPref(MainActivity.this, ConsumerDetailsPref.CONSUMER_ID) != -1) {
                        if (screenList.contains(R.id.primal_reflection)) {
                            selectedFragment = PrimalReflectionFragment.newInstance(false);
                        } else {
                            selectedFragment = PrimalReflectionFragment.newInstance(true);
                        }
                        screenList.add(R.id.primal_reflection);

                        break;

                    case R.id.more:
                        if (screenList.contains(R.id.more)) {
                                selectedFragment = MoreFragment.newInstance(false);
                            } else {
                                selectedFragment = MoreFragment.newInstance(true);
                            }
                            screenList.add(R.id.more);


                        break;
                }

                if (selectedFragment != null) {
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_layout, selectedFragment);
                    transaction.commit();
                }
                return true;
            }
        });
    }
}