package com.hatchers.hedgewar.Menus.janma_nond;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.hatchers.hedgewar.R;
import com.hatchers.hedgewar.database.Birth_Table;
import com.hatchers.hedgewar.database.Birth_Table_Helper;

import java.util.Calendar;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class Janm_Nond_Fragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private TextInputEditText mother_name, age, consignment,birthweight,delivery_date,
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
    private Toolbar janmaNondToolbar;
    Birth_Table birth;
    Context context;
    private String selected_gender = "", bloodUrineTest="";
    private ProgressDialog progressDialog;


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
        setGender();

        place.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.delivery_place, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(R.layout.spinner_item);

        place.setAdapter(adapter);

        return view;
    }


    private void initToolbar(View view)
    {
        janmaNondToolbar = (Toolbar)view.findViewById(R.id.janmaNond_toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(janmaNondToolbar);
    }

    private void initialization(View view) {

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.all_status_color));
        }

        registration_month = (TextInputEditText) view.findViewById(R.id.registration_month);
        date_of_periods = (TextInputEditText)view. findViewById(R.id.date_of_periods);
        delivery_date = (TextInputEditText)view. findViewById(R.id.delivery_date);

        backBtn = (ImageButton) view.findViewById(R.id.btn_back);
        mother_name = (TextInputEditText)view. findViewById(R.id.mother_name);
        age = (TextInputEditText)view. findViewById(R.id.age);
        consignment = (TextInputEditText)view. findViewById(R.id.consignment);
        place = (Spinner)view. findViewById(R.id.place);
        birthweight=(TextInputEditText)view.findViewById(R.id.birthweight);
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
                    SweetAlertDialog sweetAlertDialog =new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE)
                            .setTitleText(" थांबा  ");
                    sweetAlertDialog.show();
                    if(Birth_Table_Helper.insertBirth(getActivity(),birth))
                    {
                        sweetAlertDialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                        sweetAlertDialog.setTitleText(" जन्म नोंद झाली");
                        sweetAlertDialog.setConfirmText("Ok");
                        sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismissWithAnimation();
                            }
                        });
                        getActivity().onBackPressed();
                    }
                    else
                    {
                        sweetAlertDialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                        sweetAlertDialog.setTitleText(" जन्म नोंद झाली नाही  ");
                        sweetAlertDialog.setConfirmText("Ok");
                        sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismissWithAnimation();
                            }
                        });

                    }
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
        final Calendar calender1 = Calendar.getInstance();
        calender1.set(2016, Calendar.JANUARY, 1);
        dpd.getDatePicker().setMinDate(calender1.getTimeInMillis());
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
        final Calendar calender1 = Calendar.getInstance();
        calender1.set(2016, Calendar.JANUARY, 1);
        dpd.getDatePicker().setMinDate(calender1.getTimeInMillis());
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
        final Calendar calender1 = Calendar.getInstance();
        calender1.set(2016, Calendar.JANUARY, 1);
        dpd.getDatePicker().setMinDate(calender1.getTimeInMillis());
        dpd.getDatePicker().setMaxDate(System.currentTimeMillis());
        dpd.show();
    }

    public void setGender()
    {
        gender_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId== R.id.radiobtnmale)
                {
                    selected_gender="M";
                }
                else if(checkedId == R.id.radiobtnfemale)
                {
                    selected_gender="F";
                }
            }
        });

        urine_blood_test.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    bloodUrineTest="Y";
                }
                else
                {
                    bloodUrineTest="N";
                }
            }
        });
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
        if (gender_group.getCheckedRadioButtonId() == -1)
        {
            Toast.makeText(getActivity(),"लिंग निवडा",Toast.LENGTH_SHORT).show();
            response = false;
            // no radio buttons are checked
        }
        else
        {
            // one of the radio buttons is checked
        }
        if(!urine_blood_test.isChecked())
        {
            urine_blood_test.setError("चाचणी निवडा");
            response=false;
        }
        else
        {
           urine_blood_test.setError(null);
        }

        if (place.getSelectedItem().toString().trim().equalsIgnoreCase("ठिकाण")) {

            View selectedView = place.getSelectedView();
            if (selectedView != null && selectedView instanceof TextView) {
                TextView selectedTextView = (TextView) selectedView;
                if (place.getSelectedItemPosition() == 0) {
                    String errorString = "ठिकाण निवडा";
                    selectedTextView.setError(errorString);

                } else {
                    selectedTextView.setError(null);
                }
            }
            response = false;
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
        birth.setGenderValue(selected_gender);
        birth.setBlood_urine_testValue(bloodUrineTest);
        birth.setPlaceValue(place.getSelectedItem().toString());
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
