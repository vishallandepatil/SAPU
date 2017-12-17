package com.hatchers.hedgewar.user_login.apihelper;

import android.app.Activity;
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

import org.json.JSONException;
import org.json.JSONObject;
import java.util.Hashtable;
import java.util.Map;


public class Login_ApiHelper
{
    public static boolean userLoginApi(final Activity activity)
    {
        StringRequest strReq = new StringRequest(Request.Method.POST, WebServiceUrls.urlUserRegistration, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject responce = new JSONObject(response);
                    if (responce.getString("status").equalsIgnoreCase("sucess")) {
                            new PrefManager(activity).createLogin(new PrefManager(activity).getUserName());
                           // new PrefManager(activity).setRegistration_skipped(false);
                          /*  Intent intent = new Intent(activity, MenuActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
                            activity.startActivity(intent);
                            activity.finish();*/
                    }
                    else
                    {
                          Toast.makeText(activity,"Failed Try again..", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
                , new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //   Toast.makeText(activity,error.toString(),Toast.LENGTH_SHORT).show();

               }
        }) {


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new Hashtable<String, String>();
                //http://hatchers.in/calender/registration.php?
                // name=vishal&
                // mobileno=99999999&
                // birthdate=20/12/2016&
                // gender=M&
                // city=gangapur2&
                // appname=shivaji

                params.put("username", new PrefManager(activity).getUserName());
                params.put("password", new PrefManager(activity).getPassword());
                params.put("appname", "Hedgewar");
                //returning parameters
                return params;
            }

        };

        // Adding request to request queue

        MyApplication.getInstance().addToRequestQueue(strReq);
        return true;


    }

}
