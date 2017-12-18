package com.hatchers.hedgewar.Menus.arogya_tapasni;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import com.hatchers.hedgewar.R;

import java.util.Calendar;


public class Arogya_Fragment extends Fragment {

    private TextView tapasani_date;
    private Button fill_info;
    private DatePickerDialog dpd;
    private int year,day,month;

    public Arogya_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view=inflater.inflate(R.layout.fragment_arogya, container, false);

        fill_info=(Button)view.findViewById(R.id.fill_info);
        tapasani_date=(TextView)view.findViewById(R.id.tapasani_date);

        fill_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                Question_Fragment  questionFragment = new  Question_Fragment();
                fragmentTransaction.replace(R.id.frame_layout,questionFragment).addToBackStack(null).commit();
            }
        });

        tapasani_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayDatePicker();
            }
        });


        return view;
    }

    public void displayDatePicker()
    {
        Calendar mcurrentDate=Calendar.getInstance();
        year=mcurrentDate.get(Calendar.YEAR);
        month=mcurrentDate.get(Calendar.MONTH);
        day=mcurrentDate.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd=new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                monthOfYear=monthOfYear+1;
                tapasani_date.setText(dayOfMonth+"/"+monthOfYear+"/"+year);

            }
        },year,month,day);
        dpd.getDatePicker().setMaxDate(System.currentTimeMillis());
        dpd.show();
    }

}
