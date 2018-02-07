package com.hatchers.hedgewar.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
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
import com.hatchers.hedgewar.Pref_Manager.PrefManager;
import com.hatchers.hedgewar.R;
import com.hatchers.hedgewar.activity.slideradapter.SliderAdapter;
import com.hatchers.hedgewar.user_login.LoginActivity;
import com.hatchers.hedgewar.user_login.User_Details_Fragment;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import cn.pedant.SweetAlert.SweetAlertDialog;
import me.relex.circleindicator.CircleIndicator;

import static com.hatchers.hedgewar.Menus.janma_nond.apihelper.Web_Add_BirthDetails_Helper.addBirthToServer;


public class MenuFragment extends Fragment implements View.OnClickListener{

    private ImageView arogya,karyakram,birth,bank,help,contact;
    Toolbar menu_toolbar;
    PrefManager prefManager;

    private static ViewPager mPager;
    private static int currentPage = 0;
    private static final Integer[] images= {R.mipmap.hedgewar_image,R.mipmap.hedgewar_image,R.mipmap.hedgewar_image};

    private ArrayList<Integer> imagesArray = new ArrayList<Integer>();




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
            window.setStatusBarColor(this.getResources().getColor(R.color.all_status_color));
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

        init(view);
        return view;
    }


    private void initToolbar(View view)
    {
        menu_toolbar = (Toolbar)view.findViewById(R.id.menu_toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(menu_toolbar);
        prefManager=new PrefManager(getActivity());
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
                fragmentTransaction.replace(R.id.frame_layout,detailsFragment).addToBackStack(null).commit();
                break;

            case R.id.sync:
                SweetAlertDialog sweetAlertDialog =new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE)
                        .setTitleText("कृपया थांबा");
                sweetAlertDialog.show();

                if(addBirthToServer(getActivity(),sweetAlertDialog))
                {
                    sweetAlertDialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                    sweetAlertDialog.setTitleText("माहिती समक्रमित झाली ");
                    sweetAlertDialog.setConfirmText("Ok");
                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismissWithAnimation();
                        }
                    });
                }
                else
                {

                    sweetAlertDialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                    sweetAlertDialog.setTitleText("माहिती समक्रमित नाही झाली");
                    sweetAlertDialog.setConfirmText("Ok");
                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismissWithAnimation();
                        }
                    });
                }
                break;

            case R.id.logout:
                    prefManager.setLogOut();
                    Intent i= new Intent(getActivity(), LoginActivity.class);
                    startActivity(i);
                    getActivity().finish();
                    break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void init(View view) {
        for(int i=0;i<images.length;i++)
            imagesArray.add(images[i]);

        mPager = (ViewPager)view.findViewById(R.id.pager);
        mPager.setAdapter(new SliderAdapter(getActivity(),imagesArray));
        CircleIndicator indicator = (CircleIndicator)view.findViewById(R.id.indicator);
        indicator.setViewPager(mPager);

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == images.length) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 2500, 2500);
    }
}

