package com.primalmatrics.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.mrgames13.jimdo.splashscreen.App.SplashScreenBuilder;
import com.primalmatrics.R;
import com.primalmatrics.utils.AppDataPref;

public class SplashActivity extends AppCompatActivity {
    AppDataPref appDataPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initData();
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.transparent));
    }

    private void initData() {
        appDataPref = AppDataPref.getInstance();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //  if (consumerDetailsPref.getIntPref (SplashScreenActivity.this, ConsumerDetailsPref.CONSUMER_ID) == 0) {
                if (appDataPref.getIntPref(SplashActivity.this, AppDataPref.INTRO_SCREEN) == 0) {
                    Intent intent = new Intent(SplashActivity.this, SigninSignupActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                } else {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                }
            }
        }, 3000); //3s delay


    }



    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SplashScreenBuilder.SPLASH_SCREEN_FINISHED) {
            if (resultCode == RESULT_OK) {

            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "SplashScreen finished, but canceled", Toast.LENGTH_SHORT).show();
            }
        }
    }
}