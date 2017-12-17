package com.hatchers.hedgewar.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

public class Birth_Table_Helper {

    public static boolean insertBirth(Context context, Birth_Table birthTable)
    {
        try {
                SQLiteDatabase db = AssetDatabaseHelper.getDataHelper(context).getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(Birth_Table.NAME_OF_MOTHER, birthTable.getName_of_motherValue());
                values.put(Birth_Table.AGE, Integer.parseInt(birthTable.getAgeValue()));
            try {
                values.put(Birth_Table.DELIVERY_COUNT, Integer.parseInt(birthTable.getdelivery_countValue()));
            }
            catch (Exception e)
            {

            }
                values.put(Birth_Table.MONTH_OF_REGISTRATION, birthTable.getMonth_of_registrationValue());
                values.put(Birth_Table.BLOOD_URINE_TEST, birthTable.getBlood_urine_testValue());
               try {
                   values.put(Birth_Table.BIRTH_WEIGHT, Integer.parseInt(birthTable.getBirth_weightValue()));
               }
               catch (Exception e)
               {

               }
                   values.put(Birth_Table.PLACE, birthTable.getPlaceValue());
                values.put(Birth_Table.GENDER, birthTable.getGenderValue());
                values.put(Birth_Table.DATE_OF_PERIOD, birthTable.getDate_of_period());
                values.put(Birth_Table.DELIVERY_DATE, birthTable.getDelivery_dateValue());
            try {
                values.put(Birth_Table.VILLAGE_ID, Integer.parseInt(birthTable.getVillage_idValue()));
            }
            catch (Exception e)
            {
                 }

            try{
                values.put(Birth_Table.USER_ID, Integer.parseInt(birthTable.getUserIdValue()));
            }
        catch (Exception e)
        {
        }

            try{
                values.put(Birth_Table.UPLOAD_STATUS, Integer.parseInt(birthTable.getUploadStatusValue()));
            }
        catch (Exception e)
        {
        }

            if (db.insert(Birth_Table.BIRTH_TABLE, null, values) > 0)
                {
                    Toast.makeText(context,"Birth data inserted",Toast.LENGTH_LONG).show();
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
                e.printStackTrace();
                return false;
            }
}

    public static boolean updateBirth(Context context, Birth_Table birthTable)
    {
        try {
            SQLiteDatabase db =  AssetDatabaseHelper.getDataHelper(context).getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(Birth_Table.NAME_OF_MOTHER, birthTable.getName_of_motherValue());
            values.put(Birth_Table.AGE, birthTable.getAgeValue());
            values.put(Birth_Table.DELIVERY_COUNT, birthTable.getdelivery_countValue());
            values.put(Birth_Table.MONTH_OF_REGISTRATION, birthTable.getMonth_of_registrationValue());
            values.put(Birth_Table.BLOOD_URINE_TEST, birthTable.getBlood_urine_testValue());
            values.put(Birth_Table.BIRTH_WEIGHT, birthTable.getBirth_weightValue());
            values.put(Birth_Table.PLACE, birthTable.getPlaceValue());
            values.put(Birth_Table.GENDER, birthTable.getGenderValue());
            values.put(Birth_Table.DATE_OF_PERIOD, birthTable.getDate_of_period());
            values.put(Birth_Table.DELIVERY_DATE, birthTable.getDelivery_dateValue());
            values.put(Birth_Table.VILLAGE_ID, birthTable.getVillage_idValue());
            values.put(Birth_Table.USER_ID, Integer.parseInt(birthTable.getUserIdValue()));
            values.put(Birth_Table.UPLOAD_STATUS, Integer.parseInt(birthTable.getUploadStatusValue()));

            // upadating Row
            if(db.update(Birth_Table.BIRTH_TABLE, values, Birth_Table.BIRTH_ID+"="+birthTable.getBirth_idValue(), null)>0)
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
    Cursor cursor = db.rawQuery("SELECT * FROM "+Birth_Table.BIRTH_TABLE,null);
    try
    {
            Birth_Table birth = new Birth_Table();
            cursor.moveToFirst();
            while (cursor.isAfterLast() == false)
            {
                birth.setBirth_idValue(cursor.getString(cursor.getColumnIndex(Birth_Table.BIRTH_ID)));
                birth.setName_of_motherValue(cursor.getString(cursor.getColumnIndex(Birth_Table.NAME_OF_MOTHER)));
                birth.setAgeValue(cursor.getString(cursor.getColumnIndex(Birth_Table.AGE)));
                birth.setdelivery_countValue(cursor.getString(cursor.getColumnIndex(Birth_Table.DELIVERY_COUNT)));
                birth.setMonth_of_registrationValue(cursor.getString(cursor.getColumnIndex(Birth_Table.MONTH_OF_REGISTRATION)));
                birth.setBlood_urine_testValue(cursor.getString(cursor.getColumnIndex(Birth_Table.BLOOD_URINE_TEST)));
                birth.setDelivery_dateValue(cursor.getString(cursor.getColumnIndex(Birth_Table.DELIVERY_DATE)));
                birth.setPlaceValue(cursor.getString(cursor.getColumnIndex(Birth_Table.PLACE)));
                birth.setGenderValue(cursor.getString(cursor.getColumnIndex(Birth_Table.GENDER)));
                birth.setBirth_weightValue(cursor.getString(cursor.getColumnIndex(Birth_Table.BIRTH_WEIGHT)));
                birth.setDate_of_period(cursor.getString(cursor.getColumnIndex(Birth_Table.DATE_OF_PERIOD)));
                birth.setVillage_idValue(cursor.getString(cursor.getColumnIndex(Birth_Table.VILLAGE_ID)));
                birth.setUserIdValue(cursor.getString(cursor.getColumnIndex(Birth_Table.USER_ID)));
                birth.setUploadStatusValue(cursor.getString(cursor.getColumnIndex(Birth_Table.UPLOAD_STATUS)));

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
        Cursor cursor = db.rawQuery("SELECT * FROM "+Birth_Table.BIRTH_TABLE,null);
        try
        {
            cursor.moveToFirst();
            while (cursor.isAfterLast() == false)
            {
                Birth_Table birth = new Birth_Table();
                birth.setBirth_idValue(cursor.getString(cursor.getColumnIndex(Birth_Table.BIRTH_ID)));
                birth.setName_of_motherValue(cursor.getString(cursor.getColumnIndex(Birth_Table.NAME_OF_MOTHER)));
                birth.setAgeValue(cursor.getString(cursor.getColumnIndex(Birth_Table.AGE)));
                birth.setdelivery_countValue(cursor.getString(cursor.getColumnIndex(Birth_Table.DELIVERY_COUNT)));
                birth.setMonth_of_registrationValue(cursor.getString(cursor.getColumnIndex(Birth_Table.MONTH_OF_REGISTRATION)));
                birth.setBlood_urine_testValue(cursor.getString(cursor.getColumnIndex(Birth_Table.BLOOD_URINE_TEST)));
                birth.setDelivery_dateValue(cursor.getString(cursor.getColumnIndex(Birth_Table.DELIVERY_DATE)));
                birth.setPlaceValue(cursor.getString(cursor.getColumnIndex(Birth_Table.PLACE)));
                birth.setGenderValue(cursor.getString(cursor.getColumnIndex(Birth_Table.GENDER)));
                birth.setBirth_weightValue(cursor.getString(cursor.getColumnIndex(Birth_Table.BIRTH_WEIGHT)));
                birth.setDate_of_period(cursor.getString(cursor.getColumnIndex(Birth_Table.DATE_OF_PERIOD)));
                birth.setVillage_idValue(cursor.getString(cursor.getColumnIndex(Birth_Table.VILLAGE_ID)));
                birth.setUserIdValue(cursor.getString(cursor.getColumnIndex(Birth_Table.USER_ID)));
                birth.setUploadStatusValue(cursor.getString(cursor.getColumnIndex(Birth_Table.UPLOAD_STATUS)));

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
