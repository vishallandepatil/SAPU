package com.hatchers.hedgewar.Menus.karyakram;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.hatchers.hedgewar.R;
import com.hatchers.hedgewar.database.Answer_Table;
import com.hatchers.hedgewar.database.Answer_Table_Helper;
import com.hatchers.hedgewar.database.Question_Table;
import com.hatchers.hedgewar.database.Question_Table_Helper;


import java.util.ArrayList;
import java.util.Calendar;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class Karyakram_Fragment extends Fragment implements AdapterView.OnItemSelectedListener {


    private TextInputEditText programDate,program_holder_name,present_people_count;
    private DatePickerDialog dpd;
    private Spinner program_name;
    private int mYear, mMonth, mDay;
    private Button save;
    Answer_Table answer;
    Question_Table question_table;
    private ArrayList<Question_Table> questionTableArrayList;
    private Toolbar toolbarKrayakram;

    public Karyakram_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_karyakram, container, false);

        initializations(view);
        onclicklisterns();

        return view;
    }

    private void initializations(View view)
    {

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.all_status_color));
        }

        toolbarKrayakram=(Toolbar)view.findViewById(R.id.toolbar_krayakram);
        save=(Button) view.findViewById(R.id.save);
        program_name=(Spinner) view.findViewById(R.id.program_name);
        program_holder_name=(TextInputEditText)view.findViewById(R.id.program_holder_name);
        present_people_count=(TextInputEditText)view.findViewById(R.id.present_people_count);
        programDate=(TextInputEditText)view.findViewById(R.id.karyakram_date);
        questionTableArrayList = Question_Table_Helper.getQuestionList(getContext(),Question_Table.CATEGORY_EVENT);

        program_name.setOnItemSelectedListener(this);

        ArrayAdapter<Question_Table> adapter =new ArrayAdapter<Question_Table>(getActivity(),
                R.layout.support_simple_spinner_dropdown_item, questionTableArrayList);

        program_name.setAdapter(adapter);

    }


    private void onclicklisterns()
    {
        programDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayDatepicker();
            }
        });

        toolbarKrayakram.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAnswerData();
                if(checkValidation())
                {
                    SweetAlertDialog sweetAlertDialog =new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE)
                            .setTitleText("कृपया थांबा");
                    sweetAlertDialog.show();

                    if(Answer_Table_Helper.insertAnswer(getContext(),answer))
                    {
                        sweetAlertDialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                        sweetAlertDialog.setTitleText(" माहिती जतन झाली");
                        sweetAlertDialog.setConfirmText("ठीक आहे");
                        sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismissWithAnimation();
                                getActivity().onBackPressed();
                            }
                        });
                    }
                    else
                    {
                        sweetAlertDialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                        sweetAlertDialog.setTitleText(" माहिती जतन झाली नाही  ");
                        sweetAlertDialog.setConfirmText("ठीक आहे");
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

    }

    public void displayDatepicker()
    {
        final Calendar calender = Calendar.getInstance();
        mYear = calender.get(Calendar.YEAR);
        mMonth = calender.get(Calendar.MONTH);
        mDay = calender.get(Calendar.DAY_OF_MONTH);

        dpd = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                monthOfYear = monthOfYear + 1;
                programDate.setText(dayOfMonth + "/" + monthOfYear + "/" + year);
                programDate.setError(null);
            }
        }, mYear, mMonth, mDay);
        final Calendar calender1 = Calendar.getInstance();
        calender1.set(2016, Calendar.JANUARY, 1);
        dpd.getDatePicker().setMinDate(calender1.getTimeInMillis());
        dpd.getDatePicker().setMaxDate(System.currentTimeMillis());
        dpd.show();
    }

    private boolean checkValidation()
    {
        boolean response=true;

        if(program_holder_name.getText().toString().trim().length()==0) {
            program_holder_name.setError("वक्त्याचे नाव टाका");
            response = false;
        }
        else
        {
            program_holder_name.setError(null);
        }
        if(present_people_count.getText().toString().trim().length()==0) {
            present_people_count.setError("उपस्थित संख्या टाका");
            response = false;
        }
        else
        {
            present_people_count.setError(null);
        }

        if(programDate.getText().toString().trim().length()==0) {
            programDate.setError("दिनांक टाका");
            response = false;
        }
        else
        {
            programDate.setError(null);
        }

        View selectedView = program_name.getSelectedView();
            if (selectedView != null && selectedView instanceof TextView) {
                TextView selectedTextView = (TextView) selectedView;
                if (program_name.getSelectedItemPosition() == 0) {
                    String errorString = "कार्यक्रमाचे नाव निवडा";
                    selectedTextView.setError(errorString);

                } else {
                    selectedTextView.setError(null);
                }
            }
        return response;
    }

    private void setAnswerData()
    {
        answer=new Answer_Table();

        answer.setProgram_holderValue(program_holder_name.getText().toString());
        answer.setProgram_topicValue(program_name.getSelectedItem().toString());
        answer.setAnswer_countValue(present_people_count.getText().toString());
        answer.setAns_dateValue(programDate.getText().toString());
        answer.setQuestion_idValue(question_table.getQUESTION_ID_VALUE());

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
         question_table=questionTableArrayList.get(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
