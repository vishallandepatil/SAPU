package com.hatchers.hedgewar.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.hatchers.hedgewar.Pref_Manager.PrefManager;
import com.hatchers.hedgewar.R;
import com.hatchers.hedgewar.user_login.LoginActivity;


public class SplashActivity extends AppCompatActivity {

    private PrefManager prefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        prefManager = new PrefManager(this);

        int SPLASH_TIME_OUT = 3000;

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                check();
            }
        }, SPLASH_TIME_OUT);
    }


    public void check()
    {
        prefManager = new PrefManager(this);
        if (prefManager.isLoggedIn()) {

            Intent intent=new Intent(SplashActivity.this, MenuActivity.class);
            startActivity(intent);
            finish();

        }
        else
        {
            Intent intent=new Intent(SplashActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

}

