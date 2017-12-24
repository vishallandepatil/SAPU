package com.hatchers.hedgewar.Menus.sampark;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.hatchers.hedgewar.R;


public class Sampark_Fragment extends Fragment {

    private ImageButton backBtn;
    private Toolbar toolbar;
    private TextView address,contact1,contact2,contact3;


    public Sampark_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_sampark, container, false);

        initializations(view);
        onclicklisteners();

        initToolbar(view);

        return view;
    }

    private void initToolbar(View view)
    {
        toolbar = (Toolbar)view.findViewById(R.id.sampark_toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
    }

    private void initializations(View view)
    {
        backBtn = (ImageButton) view.findViewById(R.id.btn_back);
        address=(TextView)view.findViewById(R.id.address);
        contact1=(TextView)view.findViewById(R.id.contact1);
        contact2=(TextView)view.findViewById(R.id.contact2);
        contact3=(TextView)view.findViewById(R.id.contact3);


    }

    private void  onclicklisteners()
    {

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        contact1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:0240 2339866"));
                startActivity(callIntent);

            }
        });

        contact2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:(0240)2331195"));
                startActivity(callIntent);

            }
        });

        contact3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:(0240)2341849"));
                startActivity(callIntent);

            }
        });

        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.google.co.in/maps/place/Dr.+Hedgewar+Rugnalaya/@19.8688892,75.3479277,17z/data=!4m5!3m4!1s0x3bdba28464eee089:0x657bb13ef3cfb849!8m2!3d19.8693141!4d75.3482425?hl=en";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
    }
}
