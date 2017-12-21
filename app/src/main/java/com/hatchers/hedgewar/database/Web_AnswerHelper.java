package com.hatchers.hedgewar.database;

import android.app.Activity;
import android.app.ProgressDialog;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.hatchers.hedgewar.Pref_Manager.PrefManager;
import com.hatchers.hedgewar.app.MyApplication;
import com.hatchers.hedgewar.constants.WebServiceUrls;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by Ashwin on 20-Dec-17.
 */

public class Web_AnswerHelper
{
    public static boolean inserAnswers(final Activity activity, final ProgressDialog progressDialog)
    {
        final Answer_Table answer_table =Answer_Table_Helper.getAnswerdata(activity);
        if(answer_table==null)
        {
            progressDialog.dismiss();
            return false;
        }

        StringRequest strReq = new StringRequest(Request.Method.POST, WebServiceUrls.urlInsertAnswer, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                try {
                    JSONObject responce = new JSONObject(response);
                    if (responce.getString("status").equalsIgnoreCase("success"))
                    {
                        if(responce.getString("message").equalsIgnoreCase("ans added successfully"))
                        {
                            JSONArray result = responce.getJSONArray("result");
                            JSONObject jsonObject = result.getJSONObject(0);

                            //birth_table.setBirth_idValue(jsonObject.getString("birth_id"));
                            answer_table.setAns_idValue(jsonObject.getString("ans_id"));
                            answer_table.setQuestion_idValue(jsonObject.getString("question_id"));
                            answer_table.setAnswer_countValue(jsonObject.getString("answer_count"));
                            answer_table.setAns_dateValue(jsonObject.getString("ans_date"));
                            answer_table.setLocal_servey_idValue(jsonObject.getString("local_servey_id"));
                            answer_table.setProgram_topicValue(jsonObject.getString("program_topic"));
                            answer_table.setUser_idValue(jsonObject.getString("user_id"));
                            answer_table.setVillage_idValue(jsonObject.getString("village_id"));
                            answer_table.setCategoryValue(jsonObject.getString("category"));
                            answer_table.setProgram_holderValue(jsonObject.getString("profram_holder"));
                            answer_table.setCurrent_datetimeValue(jsonObject.getString("current_datetime"));
                            answer_table.setUpload_statusValue("1");

                            if(Answer_Table_Helper.updateAnswer(activity,answer_table))
                            {
                                Toast.makeText(activity,"Data saved...", Toast.LENGTH_SHORT).show();
                                inserAnswers( activity,progressDialog);

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

                SimpleDateFormat formain=new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat formatout = new SimpleDateFormat("yyyy-MM-dd");

                params.put("mobile", new PrefManager(activity).getMobile());
                params.put("password", new PrefManager(activity).getPassword());
                params.put("question_id", answer_table.getQuestion_idValue());
                params.put("answer_count", answer_table.getAnswer_countValue());
                try {
                    Date stdDate =formain.parse(answer_table.getAns_dateValue());
                    params.put("ans_date", formatout.format(stdDate));

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

                params.put("local_servey_id", answer_table.getLocal_servey_idValue());
                params.put("program_topic", answer_table.getProgram_topicValue());
                params.put("user_id", new PrefManager(activity).getUserId());
                params.put("village_id", new PrefManager(activity).getVillageId());
                params.put("category", answer_table.getCategoryValue());
                params.put("profram_holder", answer_table.getProgram_holderValue());
                try {
                    Date stdDate =formain.parse(answer_table.getCurrent_datetimeValue());
                    params.put("current_datetime",formatout.format(stdDate));

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

                params.put("format", "json");

                //returning parameters
                return params;
            }

        };
        MyApplication.getInstance().addToRequestQueue(strReq);
        return true;


    }

}
