package com.hatchers.hedgewar.Menus.arogya_feedback;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

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


public class Arogya_Feedback_Fragment extends Fragment
{
    private ImageButton back;
    private TextView quesnNumbetTxt, quesnTxt,feedbackDateTxt;
    private ArrayList<Question_Table> questionTableArrayList;
    private Button nextBtn;
    private LinearLayout ansLayout;
    private int mIfCounter = 0;
    private View view;
    private Question_Table question;
    private DatePickerDialog dpd;
    private int year,day,month;
    private ArrayList<Answer_Table> answerTableArrayList;


    public Arogya_Feedback_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_arogya__bank, container, false);

        initializations(view);

        clickListner();

        changeQuestinClickListner();

        return view;
    }

    private void initializations(View view)
    {
        ansLayout = (LinearLayout)view.findViewById(R.id.anslayuot);
        nextBtn = (Button) view.findViewById(R.id.next_btn);
        quesnNumbetTxt = (TextView)view.findViewById(R.id.quesn_no);
        quesnTxt=(TextView)view.findViewById(R.id.quesn);
        feedbackDateTxt = (TextView)view.findViewById(R.id.feedback_date);
        back=(ImageButton)view.findViewById(R.id.btn_back);
        questionTableArrayList = Question_Table_Helper.getQuestionList(getActivity(),Question_Table.CATEGORY_FEEDBACK);

        answerTableArrayList = new ArrayList<Answer_Table>();
        try {
            changeQuestion();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void clickListner()
    {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        feedbackDateTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayDatePicker();
            }
        });
    }

    public void displayDatePicker()
    {
        Calendar mcurrentDate=Calendar.getInstance();
        year=mcurrentDate.get(Calendar.YEAR);
        month=mcurrentDate.get(Calendar.MONTH);
        day=mcurrentDate.get(Calendar.DAY_OF_MONTH);

        dpd=new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                monthOfYear=monthOfYear+1;
                feedbackDateTxt.setText(dayOfMonth+"/"+monthOfYear+"/"+year);

            }
        },year,month,day);
        final Calendar calender1 = Calendar.getInstance();
        calender1.set(2016, Calendar.JANUARY, 1);
        dpd.getDatePicker().setMinDate(calender1.getTimeInMillis());
        dpd.getDatePicker().setMaxDate(System.currentTimeMillis());
        dpd.show();
    }

    private String generateUniqueId()
    {
        String datetime;
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMs");
        datetime = ft.format(dNow);

        return datetime;
    }

    private String getCurrentDateTime()
    {
        String datetime;
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        datetime = ft.format(dNow);
        return datetime;
    }

    private void changeQuestinClickListner()
    {
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkValidation()) {
                    SweetAlertDialog sweetAlertDialog =new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE)
                            .setTitleText(" थांबा  ");
                    sweetAlertDialog.show();

                    changeQuestion();
                }
            }
        });
    }



    private boolean checkValidation()
    {
        boolean response = true;

        if(feedbackDateTxt.getText().toString().trim().length()==0) {
            feedbackDateTxt.setError("तारीख टाका");
            response = false;
        }
        else {
            feedbackDateTxt.setError(null);
            }
        return response;
    }

    private void changeQuestion()
    {
        if (mIfCounter > 0) {
            EditText et = (EditText) view;
            String a = et.getText().toString();

            try {
                String b = question.getQUESTION_ID_VALUE();
                String c = question.getTYPE_VALUE();

                Answer_Table answer = new Answer_Table();
                answer.setAnswer_countValue(a);
                answer.setCategoryValue(question.getCATEGORY_ENGLISH_VALUE());
                answer.setAns_dateValue(feedbackDateTxt.getText().toString());
                answer.setCurrent_datetimeValue(getCurrentDateTime());
                answer.setQuestion_idValue(b);
                answer.setLocal_servey_idValue(generateUniqueId());
                answer.setUpload_statusValue("0");
                answerTableArrayList.add(answer);
            } catch (Exception e)
            {

            }

            if (mIfCounter == questionTableArrayList.size()) {
                ///insert all ans in ans table
                for(Answer_Table ans : answerTableArrayList)
                {
                    if(a.equalsIgnoreCase(""))
                    {
                        //a="0";
                    }
                    else
                    {
                        Answer_Table_Helper.insertAnswer(getActivity(), ans);

                    }
                }
            }

        }
        if (mIfCounter < questionTableArrayList.size() )
        {
            {
                question = questionTableArrayList.get(mIfCounter);

                quesnTxt.setText(question.getQUESTION_MARATHI_VALUE());
                quesnNumbetTxt.setText(String.valueOf("प्रश्न क्र.: " + question.getSR_NO_VALUE()));
                if (question.getTYPE_VALUE() != null)
                {
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

                    if (mIfCounter < questionTableArrayList.size() - 1)
                    {
                        mIfCounter++;
                    }
                    else
                    {
                        nextBtn.setText("थांबा ");
                        mIfCounter++;
                    }
                }
            }

        }
    }

}
