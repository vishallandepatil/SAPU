package com.hatchers.hedgewar.Menus.arogya_tapasni;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import com.hatchers.hedgewar.R;

import java.util.Calendar;

public class Question_Fragment extends Fragment {

    private int year,day,month;

    public Question_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_question, container, false);


        return view;
    }

}
