package com.hatchers.hedgewar.Menus.janma_nond;


import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.hatchers.hedgewar.R;
import com.hatchers.hedgewar.database.Birth_Table;
import com.hatchers.hedgewar.database.Birth_Table_Helper;

import java.util.ArrayList;
import java.util.Calendar;

public class BirthFragment extends Fragment {


    private FloatingActionButton fab;
    BirthAdapter birthAdapter;
    ListView listView;
    Spinner select_year;
    private Toolbar birthfragmentToolbar;
    private TextView txtNoList;
    private ImageView imageNoList;

    ArrayList<Birth_Table> birthTableArrayList;


    public BirthFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_birth, container, false);

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.all_status_color));
        }


        birthfragmentToolbar=(Toolbar)view.findViewById(R.id.birthfragment_toolbar);
        select_year=(Spinner) view.findViewById(R.id.select_year);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        txtNoList=(TextView)view.findViewById(R.id.txt_no_list);
        imageNoList=(ImageView)view.findViewById(R.id.image_no_list);


        birthTableArrayList= Birth_Table_Helper.getBirthdataList(getContext());
        birthAdapter= new BirthAdapter(getContext(), R.layout.list_row,birthTableArrayList);

        listView = (ListView)view.findViewById(R.id.birth_listview);
        listView.setAdapter(birthAdapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                Janm_Nond_Fragment janmNondFragment= new Janm_Nond_Fragment();
                fragmentTransaction.replace(R.id.frame_layout,janmNondFragment).addToBackStack(null).commit();
            }
        });

        birthfragmentToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        ArrayList<String> years = new ArrayList<String>();
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 2011; i <= thisYear; i++) {
            years.add(Integer.toString(i));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, years);
        select_year.setAdapter(adapter);


        if(birthTableArrayList.size() <=0)
        {
            listView.setVisibility(View.GONE);
            imageNoList.setImageResource(R.mipmap.no_list_image);
            imageNoList.setVisibility(View.VISIBLE);
            txtNoList.setVisibility(View.VISIBLE);

        }
        else
        {
            listView.setVisibility(View.VISIBLE);
            imageNoList.setVisibility(View.GONE);
            txtNoList.setVisibility(View.GONE);
        }
        return view;
    }


    public class BirthAdapter extends ArrayAdapter<Birth_Table> {

        private Context context;
        private ArrayList<Birth_Table> birthTableArrayList;

        public BirthAdapter(Context context, int textViewResourceId, ArrayList<Birth_Table> birthTableArrayList) {

            super(context,textViewResourceId, birthTableArrayList);

            this.context= context;
            this.birthTableArrayList=birthTableArrayList;

        }

        private class ViewHolder
        {
            TextView mother_name,delivery_date,gender,place;
            ImageView list_image;

        }

        @Override
        public int getCount() {
            try {
               return birthTableArrayList.size();
            }
            catch (Exception e)
            {
                return 0;
            }

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            ViewHolder holder=null;
            if (convertView == null)
            {
                LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.list_row, null);

                holder = new ViewHolder();
                holder.mother_name = (TextView) convertView.findViewById(R.id.mother_name);
                holder.delivery_date = (TextView) convertView.findViewById(R.id.delivery_date);
                holder.gender = (TextView) convertView.findViewById(R.id.gender);
                holder.list_image=(ImageView)convertView.findViewById(R.id.list_image);
                holder.place=(TextView) convertView.findViewById(R.id.place);

                convertView.setTag(holder);

            }
            else {
                holder = (ViewHolder) convertView.getTag();
            }
            Birth_Table birthTable=birthTableArrayList.get(position);
            holder.mother_name.setText("मातेचे नाव: "+String.valueOf(birthTable.getName_of_motherValue() + ""));
            holder.delivery_date.setText("बाळंतपणाची तारीख: "+String.valueOf(birthTable.getDelivery_dateValue()+" "));
            holder.place.setText("ठिकाण: "+String.valueOf(birthTable.getPlaceValue()+" "));

            if(birthTable.getGenderValue().equalsIgnoreCase("M")) {
                holder.gender.setText("लिंग: मुलगा");
                holder.list_image.setImageResource(R.drawable.baby_boy);
            }
            else
            {
                holder.gender.setText("लिंग: मुलगी");
                holder.list_image.setImageResource(R.drawable.baby_girl);
            }


            if(position %2 == 1)
            {
                convertView.setBackgroundColor(Color.parseColor("#ffffff"));
            }
            else
            {
                convertView.setBackgroundColor(Color.parseColor("#79139e9d"));
            }
            return convertView;
        }
    }

}
