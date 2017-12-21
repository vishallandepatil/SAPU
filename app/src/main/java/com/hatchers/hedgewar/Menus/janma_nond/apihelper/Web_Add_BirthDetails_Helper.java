package com.hatchers.hedgewar.Menus.janma_nond.apihelper;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.hatchers.hedgewar.Pref_Manager.PrefManager;
import com.hatchers.hedgewar.activity.MenuActivity;
import com.hatchers.hedgewar.app.MyApplication;
import com.hatchers.hedgewar.constants.WebServiceUrls;
import com.hatchers.hedgewar.database.Birth_Table;
import com.hatchers.hedgewar.database.Birth_Table_Helper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Hashtable;
import java.util.Map;

import static com.hatchers.hedgewar.database.Web_AnswerHelper.inserAnswers;

/**
 * Created by Ashwin on 20-Dec-17.
 */

public class Web_Add_BirthDetails_Helper
{
    public static boolean addBirthToServer(final Activity activity, final ProgressDialog progressDialog)
    {
         final Birth_Table birth_table =Birth_Table_Helper.getBirthdata(activity);
        if(birth_table==null)
        {
            inserAnswers(activity, progressDialog);
            return false;
        }

        StringRequest strReq = new StringRequest(Request.Method.POST, WebServiceUrls.urlAddBirthData, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                try {
                    JSONObject responce = new JSONObject(response);
                    if (responce.getString("status").equalsIgnoreCase("success"))
                    {
                        if(responce.getString("message").equalsIgnoreCase("Birth added successfully"))
                        {
                            JSONArray result = responce.getJSONArray("result");
                            JSONObject jsonObject = result.getJSONObject(0);

                            //birth_table.setBirth_idValue(jsonObject.getString("birth_id"));
                            birth_table.setName_of_motherValue(jsonObject.getString("name_of_mother"));
                            birth_table.setAgeValue(jsonObject.getString("age"));
                            birth_table.setdelivery_countValue(jsonObject.getString("delivery_count"));
                            birth_table.setMonth_of_registrationValue(jsonObject.getString("month_of_registration"));
                            birth_table.setBlood_urine_testValue(jsonObject.getString("blood_urine_test"));
                            birth_table.setDelivery_dateValue(jsonObject.getString("delivery_date"));
                            birth_table.setPlaceValue(jsonObject.getString("place"));
                            birth_table.setGenderValue(jsonObject.getString("gender"));
                            birth_table.setBirth_weightValue(jsonObject.getString("birth_weight"));
                            birth_table.setDate_of_period(jsonObject.getString("date_of_period"));
                            birth_table.setUserIdValue(jsonObject.getString("user_id"));
                            birth_table.setVillage_idValue(jsonObject.getString("village_id"));
                            birth_table.setUploadStatusValue("1");

                            if(Birth_Table_Helper.updateBirth(activity,birth_table))
                            {
                                Toast.makeText(activity,"Data saved...", Toast.LENGTH_SHORT).show();
                                addBirthToServer( activity,progressDialog);

                            }
                            else
                            {
                                progressDialog.dismiss();
                                Toast.makeText(activity,"Local Data update failed...", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            progressDialog.dismiss();
                            Toast.makeText(activity,"Data not saved...", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        progressDialog.dismiss();
                        Toast.makeText(activity,"Data not saved...", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    progressDialog.dismiss();
                    e.printStackTrace();
                }
            }
        }
                , new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(activity,error.toString(),Toast.LENGTH_SHORT).show();

            }
        }) {


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new Hashtable<String, String>();

                params.put("name_of_mother", birth_table.getName_of_motherValue());
                params.put("age", birth_table.getAgeValue());
                params.put("delivery_count", birth_table.getdelivery_countValue());
                params.put("month_of_registration", birth_table.getMonth_of_registrationValue());
                params.put("blood_urine_test", birth_table.getBlood_urine_testValue());
                params.put("delivery_date", birth_table.getDelivery_dateValue());
                params.put("place", birth_table.getPlaceValue());
                params.put("gender", birth_table.getGenderValue());
                params.put("birth_weight", birth_table.getBirth_weightValue());
                params.put("date_of_period", birth_table.getDate_of_period());
                params.put("user_id", new PrefManager(activity).getUserId());
                params.put("village_id", new PrefManager(activity).getVillageId());
                params.put("mobile", new PrefManager(activity).getMobile());
                params.put("password", new PrefManager(activity).getPassword());
                params.put("format", "json");

                //returning parameters
                return params;
            }

        };
        MyApplication.getInstance().addToRequestQueue(strReq);
        return true;


    }

}
