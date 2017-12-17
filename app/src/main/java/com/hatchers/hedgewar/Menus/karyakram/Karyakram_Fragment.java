package com.hatchers.hedgewar.Menus.karyakram;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.hatchers.hedgewar.R;

import java.util.Calendar;


public class Karyakram_Fragment extends Fragment {

    private TextView programDate;
    private ImageButton back;
    private DatePickerDialog dpd;
    private int mYear, mMonth, mDay;

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

        programDate=(TextView)view.findViewById(R.id.karyakram_date);
        back=(ImageButton)view.findViewById(R.id.btn_back);

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
        return view;
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

}
