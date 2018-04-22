package com.hatchers.hedgewar.Menus.janma_nond.apihelper;

import android.app.Activity;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.hatchers.hedgewar.Pref_Manager.PrefManager;
import com.hatchers.hedgewar.app.MyApplication;
import com.hatchers.hedgewar.constants.WebServiceUrls;
import com.hatchers.hedgewar.database.Birth_Table;
import com.hatchers.hedgewar.database.Birth_Table_Helper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.hatchers.hedgewar.database.Web_AnswerHelper.inserAnswers;


public class Web_Add_BirthDetails_Helper
{
    public static boolean addBirthToServer(final Activity activity, final SweetAlertDialog sweetAlertDialog)
    {
        final Birth_Table birth_table =Birth_Table_Helper.getBirthdata(activity);
        if(birth_table==null)
        {
            inserAnswers(activity, sweetAlertDialog);
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

                            //{"status":"success","count":1,"type":"addNewAns",
                            // "result":[{"birth_id":"32",
                            // "name_of_mother":"voiajaya",
                            // "age":"25","delivery_count":"1","month_of_registration":"1",
                            // "blood_urine_test":"Y","delivery_date":"2017-12-22",
                            // "place":"abad","gender":"M","birth_weight":"2",
                            // "date_of_period":"2017-12-22","user_id":"1","village_id":"1",
                            // "upload_status":null,"sonography_test":"Y","iron_calcium":"Y",
                            // "child_death":"Y","mother_death":"Y","critical_condition":"Y",
                            // "pregnancy_death":"Y"}],"message":"Birth added successfully"}

                            //birth_table.setBirth_idValue(jsonObject.getString("birth_id"));
                            birth_table.setNameOfMotherValue(jsonObject.getString("name_of_mother"));
                            birth_table.setAgeValue(jsonObject.getString("age"));
                            birth_table.setDeliveryCountValue(jsonObject.getString("delivery_count"));
                            birth_table.setMonthOfRegistrationValue(jsonObject.getString("month_of_registration"));
                            birth_table.setBloodUrineTestValue(jsonObject.getString("blood_urine_test"));
                            birth_table.setDeliveryDateValue(jsonObject.getString("delivery_date"));
                            birth_table.setPlaceValue(jsonObject.getString("place"));
                            birth_table.setGenderValue(jsonObject.getString("gender"));
                            birth_table.setBirthWeightValue(jsonObject.getString("birth_weight"));
                            birth_table.setDateOfPeriod(jsonObject.getString("date_of_period"));
                            birth_table.setUserIdValue(jsonObject.getString("user_id"));
                            birth_table.setVillageIdValue(jsonObject.getString("village_id"));
                            birth_table.setUploadStatusValue("1");
                            birth_table.setSonographyTestValue(jsonObject.getString("sonography_test"));
                            birth_table.setIronCalciumValue(jsonObject.getString("iron_calcium"));
                            birth_table.setCriticalConditionValue(jsonObject.getString("critical_condition"));
                            birth_table.setChildDeathValue(jsonObject.getString("child_death"));
                            birth_table.setMotherDeathValue(jsonObject.getString("mother_death"));
                            birth_table.setPregnancyDeathValue(jsonObject.getString("pregnancy_death"));

                            if(Birth_Table_Helper.updateBirth(activity,birth_table))
                            {
                                Toast.makeText(activity,"Data saved...", Toast.LENGTH_SHORT).show();
                                addBirthToServer( activity,sweetAlertDialog);

                            }
                            else
                            {
                                sweetAlertDialog.dismissWithAnimation();
                                Toast.makeText(activity,"Local Data update failed...", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            sweetAlertDialog.dismissWithAnimation();
                            Toast.makeText(activity,"Data not saved...", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        sweetAlertDialog.dismissWithAnimation();
                        Toast.makeText(activity,"Data not saved...", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    sweetAlertDialog.dismissWithAnimation();
                    e.printStackTrace();
                }
            }
        }
                , new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                sweetAlertDialog.dismissWithAnimation();
                Toast.makeText(activity,error.toString(),Toast.LENGTH_SHORT).show();

            }
        }) {


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new Hashtable<String, String>();
                SimpleDateFormat formatterin = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat formatterout = new SimpleDateFormat("yyyy-MM-dd");

                //http://hatchers.in/sapu/index.php/api/v1/addbirth?
                // name_of_mother=voiajaya
                // &age=25  &delivery_count=1
                // &month_of_registration=1 &blood_urine_test=Y
                // &delivery_date=2017-12-22 &place=abad&gender=M
                // &birth_weight=2 &date_of_period=2017-12-22
                // &user_id=1 &village_id=1 &mobile=9975294782
                // &password=user@123 &format=json &sonography_test=Y
                // &iron_calcium=Y&child_death=Y&mother_death=Y
                // &critical_condition=Y&pregnancy_death=Y
                params.put("name_of_mother", birth_table.getNameOfMotherValue());
                params.put("age", birth_table.getAgeValue());
                params.put("delivery_count", birth_table.getDeliveryCountValue());
                try {
                    Date strDate= formatterin.parse(birth_table.getMonthOfRegistrationValue());
                    params.put("month_of_registration", formatterout.format(strDate));

                }
                catch (Exception e)
                {

                }

                params.put("blood_urine_test", birth_table.getBloodUrineTestValue());


                try {
                   Date strDate= formatterin.parse(birth_table.getDeliveryDateValue());
                   params.put("delivery_date", formatterout.format(strDate));

               }
               catch (Exception e)
               {

               }
                params.put("place", birth_table.getPlaceValue());
                params.put("gender", birth_table.getGenderValue());
                params.put("birth_weight", birth_table.getBirthWeightValue());

                try {
                    Date strDate= formatterin.parse(birth_table.getDateOfPeriod());
                    params.put("date_of_period", formatterout.format(strDate));

                }
                catch (Exception e)
                {

                }

                params.put("user_id", new PrefManager(activity).getUserId());
                params.put("village_id", new PrefManager(activity).getVillageId());
                params.put("mobile", new PrefManager(activity).getMobile());
                params.put("password", new PrefManager(activity).getPassword());
                params.put("format", "json");
                params.put("sonography_test", birth_table.getSonographyTestValue());
                params.put("iron_calcium", birth_table.getIronCalciumValue());
                params.put("child_death", birth_table.getChildDeathValue());
                params.put("mother_death", birth_table.getMotherDeathValue());
                params.put("critical_condition", birth_table.getCriticalConditionValue());
                params.put("pregnancy_death", birth_table.getPregnancyDeathValue());

                //returning parameters
                return params;
            }

        };

        MyApplication.getInstance().addToRequestQueue(strReq);
        return true;
    }

}
