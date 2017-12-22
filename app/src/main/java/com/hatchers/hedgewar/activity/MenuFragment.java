package com.hatchers.hedgewar.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.hatchers.hedgewar.Menus.arogya_feedback.Arogya_Feedback_Fragment;
import com.hatchers.hedgewar.Menus.arogya_tapasni.Arogya_Tapasni_Fragment;
import com.hatchers.hedgewar.Menus.janma_nond.BirthFragment;
import com.hatchers.hedgewar.Menus.karyakram.Karyakram_Fragment;
import com.hatchers.hedgewar.Menus.sahayyta.Sahayata_Fragment;
import com.hatchers.hedgewar.Menus.sampark.Sampark_Fragment;
import com.hatchers.hedgewar.R;
import com.hatchers.hedgewar.user_login.User_Details_Fragment;


public class MenuFragment extends Fragment implements View.OnClickListener{

    private ImageView arogya,karyakram,birth,bank,help,contact;
    Toolbar toolbar;


    public MenuFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_menu, container, false);

        initToolbar(view);
        setHasOptionsMenu(true);


        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.menus_status_color));
        }
        arogya=(ImageView)view.findViewById(R.id.arogya_image);
        karyakram=(ImageView)view.findViewById(R.id.karyakram_image);
        birth=(ImageView)view.findViewById(R.id.janma_nond_image);
        bank=(ImageView)view.findViewById(R.id.arogya_feedback_image);
        help=(ImageView)view.findViewById(R.id.sahayta_image);
        contact=(ImageView)view.findViewById(R.id.sampark_image);


        arogya.setOnClickListener(this);
        birth.setOnClickListener(this);
        bank.setOnClickListener(this);
        karyakram.setOnClickListener(this);
        help.setOnClickListener(this);
        contact.setOnClickListener(this);


        return view;
    }


    private void initToolbar(View view)
    {
        toolbar = (Toolbar)view.findViewById(R.id.menu_toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
    }
    @Override
    public void onClick(View v) {

        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
       // FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (v.getId())
        {

            case R.id.arogya_image:
                Arogya_Tapasni_Fragment arogyaFragment = new Arogya_Tapasni_Fragment();
                fragmentTransaction.replace(R.id.frame_layout,arogyaFragment).addToBackStack(null).commit();
                break;

            case R.id.karyakram_image:
                Karyakram_Fragment karyakramFragment = new Karyakram_Fragment();
                fragmentTransaction.replace(R.id.frame_layout,karyakramFragment).addToBackStack(null).commit();
                break;

            case R.id.janma_nond_image:
               BirthFragment birthFragment= new BirthFragment();
                fragmentTransaction.replace(R.id.frame_layout,birthFragment).addToBackStack(null).commit();
                break;

            case R.id.arogya_feedback_image:
                Arogya_Feedback_Fragment arogyaBankFragment = new Arogya_Feedback_Fragment();
                fragmentTransaction.replace(R.id.frame_layout,arogyaBankFragment).addToBackStack(null).commit();
                break;

            case R.id.sahayta_image:
                Sahayata_Fragment sahayataFragment = new Sahayata_Fragment();
                fragmentTransaction.replace(R.id.frame_layout,sahayataFragment).addToBackStack(null).commit();
                break;

            case R.id.sampark_image:
                Sampark_Fragment sampark_fragment = new Sampark_Fragment();
                fragmentTransaction.replace(R.id.frame_layout,sampark_fragment).addToBackStack(null).commit();
                break;
        }
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.profile:
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                User_Details_Fragment detailsFragment = new User_Details_Fragment();
                fragmentTransaction.replace(R.id.frame_layout,detailsFragment).commit();

                break;

            case R.id.sync:
                break;

            case R.id.logout:
                    System.exit(0);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

