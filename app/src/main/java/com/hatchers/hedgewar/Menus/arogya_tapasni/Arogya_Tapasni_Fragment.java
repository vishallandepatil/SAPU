package com.hatchers.hedgewar.Menus.arogya_tapasni;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hatchers.hedgewar.R;
import com.hatchers.hedgewar.database.Answer_Table;
import com.hatchers.hedgewar.database.Answer_Table_Helper;
import com.hatchers.hedgewar.database.Question_Table;
import com.hatchers.hedgewar.database.Question_Table_Helper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Arogya_Tapasni_Fragment extends Fragment {
    private TextView uniqueIdTxt;
    private ArrayList<Question_Table> questionTableArrayList;
    private Button nextBtn;
    private TextView questionTxt, questinNumberTxt;
    int mIfCounter = 0;
    LinearLayout ansLayout;
    Question_Table question;
    View view;
    ArrayList<Answer_Table> answers;
    private TextView tapasani_date;
    private DatePickerDialog dpd;
    private int year, day, month;
    private Toolbar arogya_toolbar;
    private SweetAlertDialog sweetAlertDialog;


    public Arogya_Tapasni_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tapasni, container, false);

        initializations(view);
        setQuestionList();
        generateUniqueId();
        changeQuestionOnClick();

        arogya_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        return view;
    }

    private void initializations(View view) {

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.all_status_color));
        }

        arogya_toolbar = (Toolbar) view.findViewById(R.id.arogya_toolbar);
        tapasani_date = (TextView) view.findViewById(R.id.tapasani_date);
        uniqueIdTxt = (TextView) view.findViewById(R.id.unique_id);
        nextBtn = (Button) view.findViewById(R.id.btn_next);
        questionTxt = (TextView) view.findViewById(R.id.question);
        questinNumberTxt = (TextView) view.findViewById(R.id.question_number);
        ansLayout = (LinearLayout) view.findViewById(R.id.ansLayout);


        tapasani_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayDatePicker();
            }
        });

    }

    private void setQuestionList() {
        answers = new ArrayList<Answer_Table>();
        questionTableArrayList = Question_Table_Helper.getQuestionList(getActivity(), Question_Table.CATEGORY_HEALTH);
        try {
            changeQuestion();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean checkValidation() {
        boolean result = true;

        if (tapasani_date.getText().toString().trim().length() == 0) {
            tapasani_date.setError("तपासणीची तारीख टाका");
            result = false;
        } else {
            tapasani_date.setError(null);
        }

        return result;
    }

    public void displayDatePicker() {
        Calendar mcurrentDate = Calendar.getInstance();
        year = mcurrentDate.get(Calendar.YEAR);
        month = mcurrentDate.get(Calendar.MONTH);
        day = mcurrentDate.get(Calendar.DAY_OF_MONTH);

        dpd = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                monthOfYear = monthOfYear + 1;
                tapasani_date.setText(dayOfMonth + "/" + monthOfYear + "/" + year);
                tapasani_date.setError(null);
            }
        }, year, month, day);
        final Calendar calender1 = Calendar.getInstance();
        calender1.set(2016, Calendar.JANUARY, 1);
        dpd.getDatePicker().setMinDate(calender1.getTimeInMillis());
        dpd.getDatePicker().setMaxDate(System.currentTimeMillis());
        dpd.show();
    }

    private void generateUniqueId() {
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMs");
        String datetime = ft.format(dNow);
        uniqueIdTxt.setText(datetime);
    }

    private String getCurrentDateTime() {
        String datetime;
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        datetime = ft.format(dNow);
        return datetime;
    }

    private void changeQuestionOnClick() {
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkValidation()) {

                    changeQuestion();
                }
            }
        });
    }

    private void changeQuestion() {
        if (mIfCounter > 0) {
            EditText et = (EditText) view;
            String a = et.getText().toString();

            try {
                //String a = et.getText().toString();
                String b = question.getQUESTION_ID_VALUE();
                String c = question.getTYPE_VALUE();

                Answer_Table answer = new Answer_Table();
                answer.setAnswer_countValue(a);
                answer.setCategoryValue(question.getCATEGORY_ENGLISH_VALUE());
                answer.setAns_dateValue(tapasani_date.getText().toString());
                answer.setCurrent_datetimeValue(getCurrentDateTime());
                answer.setQuestion_idValue(b);
                answer.setLocal_servey_idValue(uniqueIdTxt.getText().toString());
                answer.setUpload_statusValue("0");
                answers.add(answer);
            } catch (Exception e) {

            }

            if (mIfCounter == questionTableArrayList.size())
            {

                ///insert all ans in ans table

                /*for (Answer_Table ans : answers)
                {
                    if (a.equalsIgnoreCase("")) {
                        // a="0";
                    }
                    else
                    {
                        Answer_Table_Helper.insertAnswer(getActivity(), ans);
                    }


                }*/


              sweetAlertDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE)
                        .setTitleText("कृपया थांबा");
                sweetAlertDialog.setCancelable(false);
                sweetAlertDialog.show();

                int i=0;
              for (i =0;i<answers.size();i++)
              {
                  Answer_Table answerTable =answers.get(i);
                  if (a.equalsIgnoreCase("")) {
                      // a="0";
                  }
                  else {
                      Answer_Table_Helper.insertAnswer(getActivity(), answerTable);
                  }

                  if(i++ == answers.size() - 1)
                  {
                      sweetAlertDialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                      sweetAlertDialog.setTitleText("आरोग्य तपासणी समाप्त");
                      sweetAlertDialog.setConfirmText("Ok");
                      sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                          @Override
                          public void onClick(SweetAlertDialog sweetAlertDialog) {

                              sweetAlertDialog.dismissWithAnimation();
                              getActivity().onBackPressed();

                          }
                      });

                  }
              }


            }

        }
        if (mIfCounter < questionTableArrayList.size()) {

            question = questionTableArrayList.get(mIfCounter);

            questionTxt.setText(question.getQUESTION_MARATHI_VALUE());
            questinNumberTxt.setText(String.valueOf("प्रश्न क्र.: " + question.getSR_NO_VALUE()));
            if (question.getTYPE_VALUE() != null) {
                switch (question.getTYPE_VALUE()) {

                    case "I":
                        final LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        EditText et = new EditText(getActivity());
                        view = et;
                        //et.setInputType;
                        et.setLayoutParams(lparams);
                        et.setInputType(InputType.TYPE_CLASS_NUMBER);

                        ansLayout.removeAllViews();
                        ansLayout.addView(et, 0);
                        ;
                        break;

                    case "":

                        final LinearLayout.LayoutParams lparams1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        EditText et1 = new EditText(getActivity());
                        view = et1;
                        et1.setInputType(InputType.TYPE_CLASS_NUMBER);
                        et1.setLayoutParams(lparams1);
                        ansLayout.removeAllViews();
                        ansLayout.addView(et1, 0);
                        ;
                        break;


                }

                if (mIfCounter < questionTableArrayList.size() - 1) {
                    mIfCounter++;

                } else {
                    nextBtn.setText("थांबा");
                    mIfCounter++;
                    //nextBtn.setClickable(false);

                }
            }
        }
    }
}


