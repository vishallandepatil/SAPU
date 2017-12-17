package com.hatchers.hedgewar.activity;


import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.hatchers.hedgewar.R;
import com.hatchers.hedgewar.user_login.User_Details_Fragment;


public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        callMenuFragment();
    }
    private void callMenuFragment()
    {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        MenuFragment menuFragment = new MenuFragment();
        fragmentTransaction.replace(R.id.frame_layout,menuFragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.profile:
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                User_Details_Fragment detailsFragment = new User_Details_Fragment();
                fragmentTransaction.replace(R.id.frame_layout,detailsFragment).commit();

                break;

            case R.id.sync:
                break;

            case R.id.logout:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
