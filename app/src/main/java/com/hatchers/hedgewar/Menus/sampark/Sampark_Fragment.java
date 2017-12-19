package com.hatchers.hedgewar.Menus.sampark;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.hatchers.hedgewar.R;


public class Sampark_Fragment extends Fragment {

    private ImageButton backBtn;
    private Toolbar toolbar;

    public Sampark_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_sampark, container, false);

        backBtn = (ImageButton) view.findViewById(R.id.btn_back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        initToolbar(view);

        return view;
    }

    private void initToolbar(View view)
    {
        toolbar = (Toolbar)view.findViewById(R.id.sampark_toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
    }


}
