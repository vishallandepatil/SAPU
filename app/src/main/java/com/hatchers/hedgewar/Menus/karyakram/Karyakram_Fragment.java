package com.hatchers.hedgewar.Menus.karyakram;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.hatchers.hedgewar.R;
import com.hatchers.hedgewar.database.Answer_Table;
import com.hatchers.hedgewar.database.Answer_Table_Helper;

import java.util.Calendar;


public class Karyakram_Fragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private TextInputEditText programDate,program_holder_name,present_people_count;
    private ImageButton back;
    private DatePickerDialog dpd;
    private Spinner program_name;
    private int mYear, mMonth, mDay;
    private Button next;
    Answer_Table answer;
    Context context;

    private String[] Program_array={"कार्यक्रमाचे नाव ","डोहाळजेवण ","नामकरण ","उष्टावण ","नवदंपती ","सुद्रुढ बालक स्पर्धा ",
                                    "मातांशी संवाद / अन्य कार्यक्रम ","प्रकल्प प्रमुख भेटीत उपस्थिती ","शाळेतील आरोग्य शिक्षण कार्यक्रम ",
                                    "आर. सी. एच. समिती बैठक ","गावात दाखविलेले विडिओ शो ",};

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

        program_name.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.delivery_place, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(R.layout.spinner_item);

        program_name.setAdapter(adapter);


        return view;
    }

    private void initializations(View view)
    {
        back=(ImageButton)view.findViewById(R.id.btn_back);
        next=(Button) view.findViewById(R.id.next);
        program_name=(Spinner) view.findViewById(R.id.program_name);
        program_holder_name=(TextInputEditText)view.findViewById(R.id.program_holder_name);
        present_people_count=(TextInputEditText)view.findViewById(R.id.present_people_count);
        programDate=(TextInputEditText)view.findViewById(R.id.karyakram_date);
    }

    private void onclicklisterns()
    {
        programDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayDatepicker();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAnswerData();
                if(checkValidation())
                {
                   Answer_Table_Helper.insertAnswer(context,answer);
                    getActivity().onBackPressed();
                }
            }
        });

    }

    public void displayDatepicker()
    {
        final Calendar calender = Calendar.getInstance();
        calender.set(1980, Calendar.JANUARY, 1);
        mYear = calender.get(Calendar.YEAR);
        mMonth = calender.get(Calendar.MONTH);
        mDay = calender.get(Calendar.DAY_OF_MONTH);

        dpd = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                monthOfYear = monthOfYear + 1;
                programDate.setText(dayOfMonth + "/" + monthOfYear + "/" + year);

            }
        }, mYear, mMonth, mDay);
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

        return response;
    }

    private void setAnswerData()
    {
        answer=new Answer_Table();

        answer.setProgram_holderValue(program_holder_name.getText().toString());
        answer.setProgram_topicValue(program_name.getSelectedItem().toString());
        answer.setAnswer_countValue(present_people_count.getText().toString());
        answer.setAns_dateValue(programDate.getText().toString());

    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
