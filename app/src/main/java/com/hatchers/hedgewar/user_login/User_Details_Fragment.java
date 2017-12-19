package com.hatchers.hedgewar.user_login;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hatchers.hedgewar.R;


public class User_Details_Fragment extends Fragment {

    private ImageView visiblPasswd;
    private TextView passwdTxt;

    public User_Details_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_user__details, container, false);

        initialization(view);

        passwdClick();

        return view;
    }

    private void initialization(View view)
    {
        passwdTxt=(TextView)view.findViewById(R.id.password);
        visiblPasswd=(ImageView)view.findViewById(R.id.passwrd_visible);
    }

    private void passwdClick()
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
}
