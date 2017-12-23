package com.hatchers.hedgewar.Menus.janma_nond;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.hatchers.hedgewar.R;
import com.hatchers.hedgewar.database.Birth_Table;
import com.hatchers.hedgewar.database.Birth_Table_Helper;

import java.util.ArrayList;

public class BirthFragment extends Fragment {


    private FloatingActionButton fab;
    BirthAdapter birthAdapter;
    ListView listView;
    TextView select_year;
    ArrayList<Birth_Table> birthTables;


    public BirthFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_birth, container, false);
        select_year=(TextView)view.findViewById(R.id.select_year);

        fab = (FloatingActionButton) view.findViewById(R.id.fab);


        birthTables= Birth_Table_Helper.getBirthdataList(getContext());
        birthAdapter= new BirthAdapter(getContext(), R.layout.list_row,birthTables);

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
            TextView mother_name;
            TextView delivery_date;
            TextView gender;
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

                convertView.setTag(holder);

            }
            else {
                holder = (ViewHolder) convertView.getTag();
            }
            Birth_Table birthTable=birthTableArrayList.get(position);
            holder.mother_name.setText(String.valueOf(birthTable.getName_of_motherValue() + ""));
            holder.delivery_date.setText(String.valueOf(birthTable.getDelivery_dateValue()+" "));
            holder.gender.setText(String.valueOf(birthTable.getUploadStatusValue()+" "));

            if(birthTable.getGenderValue().equalsIgnoreCase("M")) {
                holder.list_image.setImageResource(R.drawable.baby_boy);
            }
            else
            {
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
