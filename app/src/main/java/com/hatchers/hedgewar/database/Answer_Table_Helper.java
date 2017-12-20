package com.hatchers.hedgewar.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

public class Answer_Table_Helper {

    public static boolean insertAnswer(Context context, Answer_Table answer_table)
    {
        try {
            SQLiteDatabase db = AssetDatabaseHelper.getDataHelper(context).getWritableDatabase();
            ContentValues values = new ContentValues();

        try {
            values.put(Answer_Table.ANS_ID, Integer.parseInt(answer_table.getAns_idValue()));
            values.put(Answer_Table.QUESTION_ID, Integer.parseInt(answer_table.getQuestion_idValue()));
        }
        catch(Exception e)
        {

        }
            values.put(Answer_Table.ANSWER_COUNT,answer_table.getAnswer_countValue());
            values.put(Answer_Table.ANS_DATE, answer_table.getAns_dateValue());

            try {
                values.put(Answer_Table.LOCAL_SERVEY_ID, Integer.parseInt(answer_table.getLocal_servey_idValue()));
            }
            catch(Exception e)
            {

            }
            values.put(Answer_Table.PROGRAM_TOPIC, answer_table.getProgram_topicValue());
            values.put(Answer_Table.PROGRAM_HOLDER, answer_table.getProgram_holderValue());
            try {
                values.put(Answer_Table.USER_ID, Integer.parseInt(answer_table.getUser_idValue()));
                values.put(Answer_Table.VILLAGE_ID, Integer.parseInt(answer_table.getVillage_idValue()));
            }
            catch(Exception e)
            {

            }
            values.put(Answer_Table.CATEGORY, answer_table.getCategoryValue());
            values.put(Answer_Table.CURRENT_DATETIME,answer_table.getCurrent_datetimeValue());
            values.put(Answer_Table.UPLOAD_STATUS,answer_table.getUpload_statusValue());


            if (db.insert(Answer_Table.ANSWER_TABLE, null, values) > 0)
            {
                Toast.makeText(context,"Answer data inserted" +answer_table.getQuestion_idValue(),Toast.LENGTH_LONG).show();
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


    public static boolean updateAnswer(Context context, Answer_Table answer_table)
    {
        try {
            SQLiteDatabase db =  AssetDatabaseHelper.getDataHelper(context).getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(Answer_Table.ANS_ID, Integer.parseInt(answer_table.getAns_idValue()));
            values.put(Answer_Table.QUESTION_ID, Integer.parseInt(answer_table.getQuestion_idValue()));
            values.put(Answer_Table.ANSWER_COUNT,answer_table.getAnswer_countValue());
            values.put(Answer_Table.ANS_DATE, answer_table.getAns_dateValue());
            values.put(Answer_Table.LOCAL_SERVEY_ID, Integer.parseInt(answer_table.getLocal_servey_idValue()));
            values.put(Answer_Table.PROGRAM_TOPIC, answer_table.getProgram_topicValue());
            values.put(Answer_Table.PROGRAM_HOLDER, answer_table.getProgram_holderValue());
            values.put(Answer_Table.USER_ID,Integer.parseInt(answer_table.getUser_idValue()));
            values.put(Answer_Table.VILLAGE_ID,Integer.parseInt(answer_table.getVillage_idValue()));
            values.put(Answer_Table.CATEGORY, answer_table.getCategoryValue());
            values.put(Answer_Table.CURRENT_DATETIME,answer_table.getCurrent_datetimeValue());
            values.put(Answer_Table.UPLOAD_STATUS,answer_table.getUpload_statusValue());
            // upadating Row
            if(db.update(Answer_Table.ANSWER_TABLE, values, Answer_Table.ANS_ID+"="+answer_table.getAns_idValue(), null)>0)
            {
                Toast.makeText(context,"Krayakram data updated",Toast.LENGTH_LONG).show();
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


    public static Answer_Table getAnswerdata(Context context)
    {
        SQLiteDatabase db =  AssetDatabaseHelper.getDataHelper(context).getWritableDatabase();
        // Cursor cursor = db.rawQuery("SELECT * FROM " + Message_Table.TABLE_MESSAGE, null);
        Cursor cursor = db.rawQuery("SELECT * FROM "+Answer_Table.ANSWER_TABLE,null);
        try
        {
           Answer_Table answer = new Answer_Table();
            cursor.moveToFirst();
            while (cursor.isAfterLast() == false)
            {
                answer.setAns_idValue(cursor.getString(cursor.getColumnIndex(Answer_Table.ANS_ID)));
                answer.setQuestion_idValue(cursor.getString(cursor.getColumnIndex(Answer_Table.QUESTION_ID)));
                answer.setAnswer_countValue(cursor.getString(cursor.getColumnIndex(Answer_Table.ANSWER_COUNT)));
                answer.setAns_dateValue(cursor.getString(cursor.getColumnIndex(Answer_Table.ANS_DATE)));
                answer.setLocal_servey_idValue(cursor.getString(cursor.getColumnIndex(Answer_Table.LOCAL_SERVEY_ID)));
                answer.setProgram_topicValue(cursor.getString(cursor.getColumnIndex(Answer_Table.PROGRAM_TOPIC)));
                answer.setProgram_holderValue(cursor.getString(cursor.getColumnIndex(Answer_Table.PROGRAM_HOLDER)));
                answer.setCurrent_datetimeValue(cursor.getString(cursor.getColumnIndex(Answer_Table.CURRENT_DATETIME)));
                answer.setUpload_statusValue(cursor.getString(cursor.getColumnIndex(Answer_Table.UPLOAD_STATUS)));
                answer.setUser_idValue(cursor.getString(cursor.getColumnIndex(Answer_Table.USER_ID)));
                answer.setVillage_idValue(cursor.getString(cursor.getColumnIndex(Answer_Table.VILLAGE_ID)));
                answer.setCategoryValue(cursor.getString(cursor.getColumnIndex(Answer_Table.CATEGORY)));

                return  answer;
            }
            return null;
        }
        catch (Exception e)

        {
            return null;
        }
    }

    public static ArrayList<Answer_Table> getAnswerdataList(Context context)
    {
        ArrayList<Answer_Table> answerTableArrayList = new ArrayList<Answer_Table>();
        SQLiteDatabase db =  AssetDatabaseHelper.getDataHelper(context).getWritableDatabase();
        // Cursor cursor = db.rawQuery("SELECT * FROM " + Message_Table.TABLE_MESSAGE, null);
        Cursor cursor = db.rawQuery("SELECT * FROM "+Answer_Table.ANSWER_TABLE,null);
        try
        {
            cursor.moveToFirst();
            while (cursor.isAfterLast() == false)
            {
                Answer_Table answer = new Answer_Table();

                answer.setAns_idValue(cursor.getString(cursor.getColumnIndex(Answer_Table.ANS_ID)));
                answer.setQuestion_idValue(cursor.getString(cursor.getColumnIndex(Answer_Table.QUESTION_ID)));
                answer.setAnswer_countValue(cursor.getString(cursor.getColumnIndex(Answer_Table.ANSWER_COUNT)));
                answer.setAns_dateValue(cursor.getString(cursor.getColumnIndex(Answer_Table.ANS_DATE)));
                answer.setLocal_servey_idValue(cursor.getString(cursor.getColumnIndex(Answer_Table.LOCAL_SERVEY_ID)));
                answer.setProgram_topicValue(cursor.getString(cursor.getColumnIndex(Answer_Table.PROGRAM_TOPIC)));
                answer.setProgram_holderValue(cursor.getString(cursor.getColumnIndex(Answer_Table.PROGRAM_HOLDER)));
                answer.setCurrent_datetimeValue(cursor.getString(cursor.getColumnIndex(Answer_Table.CURRENT_DATETIME)));
                answer.setUpload_statusValue(cursor.getString(cursor.getColumnIndex(Answer_Table.UPLOAD_STATUS)));
                answer.setUser_idValue(cursor.getString(cursor.getColumnIndex(Answer_Table.USER_ID)));
                answer.setVillage_idValue(cursor.getString(cursor.getColumnIndex(Answer_Table.VILLAGE_ID)));
                answer.setCategoryValue(cursor.getString(cursor.getColumnIndex(Answer_Table.CATEGORY)));

                answerTableArrayList.add(answer);
                cursor.moveToNext();
            }
            return answerTableArrayList;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    public static boolean deleteAnswerById(Context context, String ans_id)
    {
        try {
            SQLiteDatabase db =  AssetDatabaseHelper.getDataHelper(context).getWritableDatabase();

            db.execSQL("DELETE FROM " + Answer_Table.ANSWER_TABLE +
                    " WHERE " + Answer_Table.ANS_ID + " = '" + ans_id + "'");
            //delete all rows in titlebackground table

            Toast.makeText(context, "Answers Deleted Successfully", Toast.LENGTH_SHORT).show();


            db.close();


            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean deleteAnswerData(Context context)
    {
        try {
            SQLiteDatabase db =  AssetDatabaseHelper.getDataHelper(context).getWritableDatabase();

            db.execSQL("DELETE FROM " + Answer_Table.ANSWER_TABLE ); //delete all rows in titlebackground table


            Toast.makeText(context,"Answer data Deleted Successfully",Toast.LENGTH_SHORT).show();


            db.close();


            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
