package com.hatchers.hedgewar.user_login;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.style.TextAppearanceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hatchers.hedgewar.Pref_Manager.PrefManager;
import com.hatchers.hedgewar.R;


public class User_Details_Fragment extends Fragment {

    private TextView name,dateOfBirthday,mobileNumber,villageName;
    private Toolbar userDetailsToolbar;
    private PrefManager perfManager;
    public User_Details_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_user__details, container, false);

        initialization(view);
        setUserDetails();

        userDetailsToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        //passwdClick();
        return view;
    }

    private void initialization(View view)
    {
        perfManager=new PrefManager(getActivity());
        ((AppCompatActivity)getActivity()).setSupportActionBar(userDetailsToolbar);
        userDetailsToolbar=(Toolbar) view.findViewById(R.id.userDetailsToolbar);

        name=(TextView)view.findViewById(R.id.name_Txt);
        dateOfBirthday=(TextView)view.findViewById(R.id.date_of_birth);
        mobileNumber=(TextView)view.findViewById(R.id.mobile_number);
        villageName=(TextView)view.findViewById(R.id.village_name);
    }

   /* private void passwdClick()
    {
        visiblPasswd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(passwdTxt.getInputType()==InputType.TYPE_TEXT_VARIATION_PASSWORD) {
                    passwdTxt.setInputType(InputType.TYPE_CLASS_TEXT);
                    visiblPasswd.setImageResource(R.drawable.eye_hidden);
                }
                else
                {
                    passwdTxt.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    visiblPasswd.setImageResource(R.drawable.eye_show);

                }
            }
        });
    }
*/

    private void setUserDetails()
    {
        name.setText(String.valueOf("  " + perfManager.getUserName()));
        mobileNumber.setText(String.valueOf("  " + perfManager.getMobile()));
        villageName.setText(String.valueOf("  " + perfManager.getVillageName()));
        dateOfBirthday.setText(String.valueOf("  " + "NA"));
    }

}
