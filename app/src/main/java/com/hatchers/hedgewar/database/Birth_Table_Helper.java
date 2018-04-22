package com.hatchers.hedgewar.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

public class Birth_Table_Helper {

    public static boolean insertBirth(Context context, Birth_Table birthTable) {

        try {
            SQLiteDatabase db = AssetDatabaseHelper.getDataHelper(context).getWritableDatabase();
            ContentValues values = new ContentValues();

            //values.put(Birth_Table.BIRTH_ID, birthTable.getBirthIdValue());
            values.put(Birth_Table.NAME_OF_MOTHER, birthTable.getNameOfMotherValue());

            try {
                values.put(Birth_Table.AGE, Integer.parseInt(birthTable.getAgeValue()));
                values.put(Birth_Table.DELIVERY_COUNT, Integer.parseInt(birthTable.getDeliveryCountValue()));
            } catch (Exception e) {

            }
            values.put(Birth_Table.MONTH_OF_REGISTRATION, birthTable.getMonthOfRegistrationValue());
            values.put(Birth_Table.BLOOD_URINE_TEST, birthTable.getBloodUrineTestValue());


            values.put(Birth_Table.DELIVERY_DATE, birthTable.getDeliveryDateValue());
            values.put(Birth_Table.PLACE, birthTable.getPlaceValue());
            values.put(Birth_Table.GENDER, birthTable.getGenderValue());

            try {
                values.put(Birth_Table.BIRTH_WEIGHT, Integer.parseInt(birthTable.getBirthWeightValue()));
            } catch (Exception e) {

            }

            values.put(Birth_Table.DATE_OF_PERIOD, birthTable.getDateOfPeriod());

            try {
                values.put(Birth_Table.USER_ID, Integer.parseInt(birthTable.getUserIdValue()));
            } catch (Exception e) {
            }


            try {
                values.put(Birth_Table.VILLAGE_ID, Integer.parseInt(birthTable.getVillageIdValue()));
            } catch (Exception e) {
            }


            try {
                values.put(Birth_Table.UPLOAD_STATUS, Integer.parseInt(birthTable.getUploadStatusValue()));
            } catch (Exception e) {
            }



            values.put(Birth_Table.SONOGRAPHY_TEST, birthTable.getSonographyTestValue());
            values.put(Birth_Table.IRON_CALCIUM, birthTable.getIronCalciumValue());
            values.put(Birth_Table.CHILD_DEATH, birthTable.getChildDeathValue());
            values.put(Birth_Table.MOTHER_DEATH, birthTable.getMotherDeathValue());
            values.put(Birth_Table.CRITICAL_CONDITION, birthTable.getCriticalConditionValue());
            values.put(Birth_Table.PREGNANCY_DEATH, birthTable.getPregnancyDeathValue());



        if (db.insert(Birth_Table.BIRTH_TABLE, null, values) > 0) {
            //Toast.makeText(context,"Birth data inserted",Toast.LENGTH_LONG).show();
            db.close();
            return true;
        } else {
            db.close();
            return false;
        }
    }
         catch (Exception e) {
            e.printStackTrace();
            return false;
        }


    }

    public static boolean updateBirth(Context context, Birth_Table birthTable)
    {
        try {
            SQLiteDatabase db =  AssetDatabaseHelper.getDataHelper(context).getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(Birth_Table.NAME_OF_MOTHER, birthTable.getNameOfMotherValue());
            values.put(Birth_Table.AGE, birthTable.getAgeValue());
            values.put(Birth_Table.DELIVERY_COUNT, birthTable.getDeliveryCountValue());
            values.put(Birth_Table.MONTH_OF_REGISTRATION, birthTable.getMonthOfRegistrationValue());
            values.put(Birth_Table.BLOOD_URINE_TEST, birthTable.getBloodUrineTestValue());
            values.put(Birth_Table.BIRTH_WEIGHT, birthTable.getBirthWeightValue());
            values.put(Birth_Table.PLACE, birthTable.getPlaceValue());
            values.put(Birth_Table.GENDER, birthTable.getGenderValue());
            values.put(Birth_Table.DATE_OF_PERIOD, birthTable.getDateOfPeriod());
            values.put(Birth_Table.DELIVERY_DATE, birthTable.getDeliveryDateValue());
            values.put(Birth_Table.VILLAGE_ID, birthTable.getVillageIdValue());
            values.put(Birth_Table.USER_ID, Integer.parseInt(birthTable.getUserIdValue()));
            values.put(Birth_Table.UPLOAD_STATUS, Integer.parseInt(birthTable.getUploadStatusValue()));
            values.put(Birth_Table.SONOGRAPHY_TEST, birthTable.getSonographyTestValue());
            values.put(Birth_Table.IRON_CALCIUM, birthTable.getIronCalciumValue());
            values.put(Birth_Table.CRITICAL_CONDITION, birthTable.getCriticalConditionValue());
            values.put(Birth_Table.CHILD_DEATH, birthTable.getChildDeathValue());
            values.put(Birth_Table.MOTHER_DEATH, birthTable.getMotherDeathValue());
            values.put(Birth_Table.PREGNANCY_DEATH, birthTable.getPregnancyDeathValue());

            // upadating Row
            if(db.update(Birth_Table.BIRTH_TABLE, values, Birth_Table.BIRTH_ID+"="+birthTable.getBirthIdValue(), null)>0)
            {
                Toast.makeText(context,"Birth data updated",Toast.LENGTH_LONG).show();
                db.close();
                return true;
            }
            else
            {
                db.close();
                return false;
            }
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public static Birth_Table getBirthdata(Context context)
    {
    SQLiteDatabase db =  AssetDatabaseHelper.getDataHelper(context).getWritableDatabase();
        // Cursor cursor = db.rawQuery("SELECT * FROM " + Message_Table.TABLE_MESSAGE, null);
    Cursor cursor = db.rawQuery("SELECT * FROM "+Birth_Table.BIRTH_TABLE+" WHERE "+Birth_Table.UPLOAD_STATUS+"='0' LIMIT 1",null);
    try
    {
            cursor.moveToFirst();
            while (cursor.isAfterLast() == false)
            {
                Birth_Table birth = new Birth_Table();

                birth.setBirthIdValue(cursor.getString(cursor.getColumnIndex(Birth_Table.BIRTH_ID)));
                birth.setNameOfMotherValue(cursor.getString(cursor.getColumnIndex(Birth_Table.NAME_OF_MOTHER)));
                birth.setAgeValue(cursor.getString(cursor.getColumnIndex(Birth_Table.AGE)));
                birth.setDeliveryCountValue(cursor.getString(cursor.getColumnIndex(Birth_Table.DELIVERY_COUNT)));
                birth.setMonthOfRegistrationValue(cursor.getString(cursor.getColumnIndex(Birth_Table.MONTH_OF_REGISTRATION)));
                birth.setBloodUrineTestValue(cursor.getString(cursor.getColumnIndex(Birth_Table.BLOOD_URINE_TEST)));
                birth.setDeliveryDateValue(cursor.getString(cursor.getColumnIndex(Birth_Table.DELIVERY_DATE)));
                birth.setPlaceValue(cursor.getString(cursor.getColumnIndex(Birth_Table.PLACE)));
                birth.setGenderValue(cursor.getString(cursor.getColumnIndex(Birth_Table.GENDER)));
                birth.setBirthWeightValue(cursor.getString(cursor.getColumnIndex(Birth_Table.BIRTH_WEIGHT)));
                birth.setDateOfPeriod(cursor.getString(cursor.getColumnIndex(Birth_Table.DATE_OF_PERIOD)));
                birth.setVillageIdValue(cursor.getString(cursor.getColumnIndex(Birth_Table.VILLAGE_ID)));
                birth.setUserIdValue(cursor.getString(cursor.getColumnIndex(Birth_Table.USER_ID)));
                birth.setUploadStatusValue(cursor.getString(cursor.getColumnIndex(Birth_Table.UPLOAD_STATUS)));
                birth.setSonographyTestValue(cursor.getString(cursor.getColumnIndex(Birth_Table.SONOGRAPHY_TEST)));
                birth.setIronCalciumValue(cursor.getString(cursor.getColumnIndex(Birth_Table.IRON_CALCIUM)));
                birth.setCriticalConditionValue(cursor.getString(cursor.getColumnIndex(Birth_Table.CRITICAL_CONDITION)));
                birth.setChildDeathValue(cursor.getString(cursor.getColumnIndex(Birth_Table.CHILD_DEATH)));
                birth.setMotherDeathValue(cursor.getString(cursor.getColumnIndex(Birth_Table.MOTHER_DEATH)));
                birth.setPregnancyDeathValue(cursor.getString(cursor.getColumnIndex(Birth_Table.PREGNANCY_DEATH)));
                cursor.moveToNext();

                return  birth;
            }
            return null;
        }
        catch (Exception e)

        {
            return null;
        }
    }

    public static ArrayList<Birth_Table> getBirthdataList(Context context)
    {

        ArrayList<Birth_Table> birthTableArrayList = new ArrayList<Birth_Table>();
        SQLiteDatabase db =  AssetDatabaseHelper.getDataHelper(context).getWritableDatabase();
        // Cursor cursor = db.rawQuery("SELECT * FROM " + Message_Table.TABLE_MESSAGE, null);

        String sort = " ORDER BY " + Birth_Table.DELIVERY_DATE + " DESC";

        Cursor cursor = db.rawQuery("SELECT * FROM "+Birth_Table.BIRTH_TABLE +sort ,null);
        try
        {
            cursor.moveToFirst();
            while (cursor.isAfterLast() == false)
            {
                Birth_Table birth = new Birth_Table();
                birth.setBirthIdValue(cursor.getString(cursor.getColumnIndex(Birth_Table.BIRTH_ID)));
                birth.setNameOfMotherValue(cursor.getString(cursor.getColumnIndex(Birth_Table.NAME_OF_MOTHER)));
                birth.setAgeValue(cursor.getString(cursor.getColumnIndex(Birth_Table.AGE)));
                birth.setDeliveryCountValue(cursor.getString(cursor.getColumnIndex(Birth_Table.DELIVERY_COUNT)));
                birth.setMonthOfRegistrationValue(cursor.getString(cursor.getColumnIndex(Birth_Table.MONTH_OF_REGISTRATION)));
                birth.setBloodUrineTestValue(cursor.getString(cursor.getColumnIndex(Birth_Table.BLOOD_URINE_TEST)));
                birth.setDeliveryDateValue(cursor.getString(cursor.getColumnIndex(Birth_Table.DELIVERY_DATE)));
                birth.setPlaceValue(cursor.getString(cursor.getColumnIndex(Birth_Table.PLACE)));
                birth.setGenderValue(cursor.getString(cursor.getColumnIndex(Birth_Table.GENDER)));
                birth.setBirthWeightValue(cursor.getString(cursor.getColumnIndex(Birth_Table.BIRTH_WEIGHT)));
                birth.setDateOfPeriod(cursor.getString(cursor.getColumnIndex(Birth_Table.DATE_OF_PERIOD)));
                birth.setVillageIdValue(cursor.getString(cursor.getColumnIndex(Birth_Table.VILLAGE_ID)));
                birth.setUserIdValue(cursor.getString(cursor.getColumnIndex(Birth_Table.USER_ID)));
                birth.setUploadStatusValue(cursor.getString(cursor.getColumnIndex(Birth_Table.UPLOAD_STATUS)));
                birth.setSonographyTestValue(cursor.getString(cursor.getColumnIndex(Birth_Table.SONOGRAPHY_TEST)));
                birth.setIronCalciumValue(cursor.getString(cursor.getColumnIndex(Birth_Table.IRON_CALCIUM)));
                birth.setCriticalConditionValue(cursor.getString(cursor.getColumnIndex(Birth_Table.CRITICAL_CONDITION)));
                birth.setChildDeathValue(cursor.getString(cursor.getColumnIndex(Birth_Table.CHILD_DEATH)));
                birth.setMotherDeathValue(cursor.getString(cursor.getColumnIndex(Birth_Table.MOTHER_DEATH)));
                birth.setPregnancyDeathValue(cursor.getString(cursor.getColumnIndex(Birth_Table.PREGNANCY_DEATH)));

                birthTableArrayList.add(birth);
                cursor.moveToNext();
            }
            return birthTableArrayList;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    public static boolean deleteBirthById(Context context, String birth_id)
    {
        try {
            SQLiteDatabase db =  AssetDatabaseHelper.getDataHelper(context).getWritableDatabase();

            db.execSQL("DELETE FROM " + Birth_Table.BIRTH_TABLE +
                    " WHERE " + Birth_Table.BIRTH_ID + " = '" + birth_id + "'");
            //delete all rows in titlebackground table

            Toast.makeText(context, "Birth Deleted Successfully", Toast.LENGTH_SHORT).show();


            db.close();


            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean deleteBirthData(Context context)
    {
        try {
            SQLiteDatabase db =  AssetDatabaseHelper.getDataHelper(context).getWritableDatabase();

            db.execSQL("DELETE FROM " + Birth_Table.BIRTH_TABLE ); //delete all rows in titlebackground table


            Toast.makeText(context,"Birth data Deleted Successfully",Toast.LENGTH_SHORT).show();


            db.close();


            return true;
        } catch (Exception e) {
            return false;
        }
    }




}
