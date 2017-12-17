package com.hatchers.hedgewar.Menus.arogya_feedback;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.hatchers.hedgewar.R;


public class Arogya_Feedback_Fragment extends Fragment {

    private ImageButton back;

    public Arogya_Feedback_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_arogya__bank, container, false);

        initializations(view);

        backClickListner();

        return view;
    }

    private void initializations(View view)
    {
        back=(ImageButton)view.findViewById(R.id.btn_back);
    }

    private void backClickListner()
    {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
    }


}
