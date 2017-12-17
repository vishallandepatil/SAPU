package com.hatchers.hedgewar.Menus.janma_nond;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hatchers.hedgewar.R;
import com.hatchers.hedgewar.database.Birth_Table;
import com.hatchers.hedgewar.database.Birth_Table_Helper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class Janm_Nond_Fragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private EditText mother_name, age, consignment,birthweight,delivery_date,
            registration_month, date_of_periods;
    private CheckBox urine_blood_test;
    private RadioGroup gender_group;
    private RadioButton male,female;
    private Spinner place;
    private DatePickerDialog dpd;
    private int year,day,month;
    private TextView datePickerTxt;
    private Button save;
    private ImageButton backBtn;
    private Toolbar toolbar;
    Birth_Table birth;
    Context context;
    private String selected_gender = "";



    public Janm_Nond_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_janm_nond, container, false);
        context=getContext();
        initToolbar(view);
        initialization(view);
        onClickListners();

        place.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.delivery_place, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(R.layout.spinner_item);

        place.setAdapter(adapter);

        return view;
    }


    private void initToolbar(View view)
    {
        toolbar = (Toolbar)view.findViewById(R.id.janmaNond_toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
    }

    private void initialization(View view) {

        registration_month = (EditText) view.findViewById(R.id.registration_month);
        date_of_periods = (EditText)view. findViewById(R.id.date_of_periods);
        delivery_date = (EditText)view. findViewById(R.id.delivery_date);

        backBtn = (ImageButton) view.findViewById(R.id.btn_back);
        mother_name = (EditText)view. findViewById(R.id.mother_name);
        age = (EditText)view. findViewById(R.id.age);
        consignment = (EditText)view. findViewById(R.id.consignment);
        place = (Spinner)view. findViewById(R.id.place);
        birthweight=(EditText)view.findViewById(R.id.birthweight);
        urine_blood_test = (CheckBox)view. findViewById(R.id.urine_blood_test);
        save = (Button)view. findViewById(R.id.save);
        gender_group=(RadioGroup)view.findViewById(R.id.gender_group);
        male=(RadioButton)view.findViewById(R.id.radiobtnmale);
        female=(RadioButton)view.findViewById(R.id.radiobtnfemale);


    }

    private void onClickListners() {

        registration_month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayDatePicker2();
            }
        });
        date_of_periods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayDatePicker();
            }
        });
        delivery_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayDatePicker1();
            }
        });

       place.setOnItemSelectedListener(this);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBirthData();
                if(checkValidation())
                {
                    Birth_Table_Helper.insertBirth(context, birth);
                }
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
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
                date_of_periods.setText(dayOfMonth+"/"+monthOfYear+"/"+year);

            }
        },year,month,day);
        dpd.getDatePicker().setMaxDate(System.currentTimeMillis());
        dpd.show();
    }

    public void displayDatePicker1()
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
                delivery_date.setText(dayOfMonth+"/"+monthOfYear+"/"+year);

            }
        },year,month,day);
        dpd.getDatePicker().setMaxDate(System.currentTimeMillis());
        dpd.show();
    }

    public void displayDatePicker2()
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
                registration_month.setText(dayOfMonth+"/"+monthOfYear+"/"+year);

            }
        },year,month,day);
        dpd.getDatePicker().setMaxDate(System.currentTimeMillis());
        dpd.show();
    }


    private boolean checkValidation()
    {
        boolean response=true;

        if(mother_name.getText().toString().trim().length()==0) {
            mother_name.setError("गरोदर मातेचे नाव टाका");
            response = false;
        }
        else
        {
            mother_name.setError(null);
        }


        if(urine_blood_test.getText().toString().trim().length()==0) {
            urine_blood_test.setError("रक्त लघवी तपासणी");
            response = false;
        }
        else
        {
            urine_blood_test.setError(null);
        }

        if(age.getText().toString().trim().length()==0) {
            age.setError("वय टाका");
            response = false;
        }
        else
        {
            age.setError(null);
        }

        if(consignment.getText().toString().trim().length()==0) {
            consignment.setError("खेप टाका");
            response = false;
        }
        else
        {
            consignment.setError(null);
        }

        if(birthweight.getText().toString().trim().length()==0) {
            birthweight.setError("जन्मवजन टाका");
            response = false;
        }
        else
        {
          birthweight.setError(null);
        }

        if(registration_month.getText().toString().trim().length()==0) {
            registration_month.setError("नोंदणीचा महिना टाका");
            response = false;
        }
        else
        {
            registration_month.setError(null);
        }

        if(date_of_periods.getText().toString().trim().length()==0) {
            date_of_periods.setError("पाळीचा महिना टाका");
            response = false;
        }
        else
        {
            date_of_periods.setError(null);
        }

        if(delivery_date.getText().toString().trim().length()==0) {
            delivery_date.setError("बाळंतपणाची तारीख टाका");
            response = false;
        }
        else
        {
            delivery_date.setError(null);
        }

        return response;
    }


    private void setBirthData()
    {
        birth=new Birth_Table();

        birth.setName_of_motherValue(mother_name.getText().toString());
        birth.setBlood_urine_testValue(urine_blood_test.getText().toString());
        birth.setAgeValue(age.getText().toString());
        birth.setdelivery_countValue(consignment.getText().toString());
        birth.setBirth_weightValue(birthweight.getText().toString());
        birth.setMonth_of_registrationValue(registration_month.getText().toString());
        birth.setDate_of_period(date_of_periods.getText().toString());
        birth.setDelivery_dateValue(delivery_date.getText().toString());
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //String item = parent.getItemAtPosition(position).toString();
        //Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
