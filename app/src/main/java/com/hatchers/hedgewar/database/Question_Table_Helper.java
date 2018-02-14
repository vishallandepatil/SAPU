package com.hatchers.hedgewar.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Ashwin on 16-Dec-17.
 */

public class Question_Table_Helper
{
    public static boolean insertQuestion(Context context, Question_Table questionTable)
    {
        try {
            SQLiteDatabase db = AssetDatabaseHelper.getDataHelper(context).getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(Question_Table.QUESTION_ID, Integer.parseInt(questionTable.getQUESTION_ID_VALUE()));
            values.put(Question_Table.SR_NO, Integer.parseInt(questionTable.getSR_NO_VALUE()));
            values.put(Question_Table.QUESTION_MARATHI, questionTable.getQUESTION_MARATHI_VALUE());
            values.put(Question_Table.QUESTION_ENGLISH, questionTable.getQUESTION_ENGLISH_VALUE());
            values.put(Question_Table.TYPE, questionTable.getTYPE_VALUE());
            values.put(Question_Table.CATEGORY_MARATHI, questionTable.getCATEGORY_MARATHI_VALUE());
            values.put(Question_Table.CATEGORY_ENGLISH, questionTable.getCATEGORY_ENGLISH_VALUE());
            values.put(Question_Table.UPLOAD_STATUS, questionTable.getUPLOAD_STATUS_VALUE());

            if (db.insert(Question_Table.QUESTION_TABLE, null, values) > 0)
            {
               // Toast.makeText(context,"Birth data inserted",Toast.LENGTH_LONG).show();
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

    public static boolean updateQuestion(Context context, Question_Table questionTable)
    {
        try {
            SQLiteDatabase db =  AssetDatabaseHelper.getDataHelper(context).getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(Question_Table.QUESTION_ID,Integer.parseInt( questionTable.getQUESTION_ID_VALUE()));
            values.put(Question_Table.SR_NO, Integer.parseInt(questionTable.getSR_NO_VALUE()));
            values.put(Question_Table.QUESTION_MARATHI, questionTable.getQUESTION_MARATHI_VALUE());
            values.put(Question_Table.QUESTION_ENGLISH, questionTable.getQUESTION_ENGLISH_VALUE());
            values.put(Question_Table.TYPE, questionTable.getTYPE_VALUE());
            values.put(Question_Table.CATEGORY_MARATHI, questionTable.getCATEGORY_MARATHI_VALUE());
            values.put(Question_Table.CATEGORY_ENGLISH, questionTable.getCATEGORY_ENGLISH_VALUE());
            values.put(Question_Table.UPLOAD_STATUS, questionTable.getUPLOAD_STATUS_VALUE());

            // upadating Row
            if(db.update(Question_Table.QUESTION_TABLE, values, Question_Table.QUESTION_ID+"="+questionTable.getQUESTION_ID_VALUE(), null)>0)
            {
                //Toast.makeText(context,"Birth data updated",Toast.LENGTH_LONG).show();
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

    public static Question_Table getQuestionsData(Context context,String category)
    {
        SQLiteDatabase db =  AssetDatabaseHelper.getDataHelper(context).getWritableDatabase();
        // Cursor cursor = db.rawQuery("SELECT * FROM " + Message_Table.TABLE_MESSAGE, null);
        Cursor cursor = db.rawQuery("SELECT * FROM "+Question_Table.QUESTION_TABLE+" WHERE "+Question_Table.TYPE +"='"+category+"'",null);
        try
        {
            Question_Table questionTable = new Question_Table();
            cursor.moveToFirst();
            while (cursor.isAfterLast() == false)
            {
                questionTable.setQUESTION_ID_VALUE(cursor.getString(cursor.getColumnIndex(Question_Table.QUESTION_ID)));
                questionTable.setSR_NO_VALUE(cursor.getString(cursor.getColumnIndex(Question_Table.SR_NO)));
                questionTable.setQUESTION_MARATHI_VALUE(cursor.getString(cursor.getColumnIndex(Question_Table.QUESTION_MARATHI)));
                questionTable.setQUESTION_ENGLISH_VALUE(cursor.getString(cursor.getColumnIndex(Question_Table.QUESTION_ENGLISH)));
                questionTable.setTYPE_VALUE(cursor.getString(cursor.getColumnIndex(Question_Table.TYPE)));
                questionTable.setCATEGORY_MARATHI_VALUE(cursor.getString(cursor.getColumnIndex(Question_Table.CATEGORY_MARATHI)));
                questionTable.setCATEGORY_ENGLISH_VALUE(cursor.getString(cursor.getColumnIndex(Question_Table.CATEGORY_ENGLISH)));
                questionTable.setUPLOAD_STATUS_VALUE(cursor.getString(cursor.getColumnIndex(Question_Table.UPLOAD_STATUS)));

                return  questionTable;
            }
            return null;
        }
        catch (Exception e)

        {
            return null;
        }
    }

    public static ArrayList<Question_Table> getQuestionList(Context context,String category)
    {
        ArrayList<Question_Table> question_tableArrayList = new ArrayList<Question_Table>();
        if(category.equalsIgnoreCase(Question_Table.CATEGORY_EVENT))
        {
            Question_Table questionTable1 = new Question_Table("-1","","कार्यक्रमाचे नाव निवडा","","","","","");
            question_tableArrayList.add(questionTable1);
        }
        SQLiteDatabase db =  AssetDatabaseHelper.getDataHelper(context).getWritableDatabase();
        // Cursor cursor = db.rawQuery("SELECT * FROM " + Message_Table.TABLE_MESSAGE, null);
        Cursor cursor = db.rawQuery("SELECT * FROM "+Question_Table.QUESTION_TABLE +" WHERE "+Question_Table.CATEGORY_ENGLISH +" = '"+category+"'",null);
        try
        {
            cursor.moveToFirst();
            while (cursor.isAfterLast() == false)
            {
                Question_Table questionTable = new Question_Table();
                questionTable.setQUESTION_ID_VALUE(cursor.getString(cursor.getColumnIndex(Question_Table.QUESTION_ID)));
                questionTable.setSR_NO_VALUE(cursor.getString(cursor.getColumnIndex(Question_Table.SR_NO)));
                questionTable.setQUESTION_MARATHI_VALUE(cursor.getString(cursor.getColumnIndex(Question_Table.QUESTION_MARATHI)));
                questionTable.setQUESTION_ENGLISH_VALUE(cursor.getString(cursor.getColumnIndex(Question_Table.QUESTION_ENGLISH)));
                questionTable.setTYPE_VALUE(cursor.getString(cursor.getColumnIndex(Question_Table.TYPE)));
                questionTable.setCATEGORY_MARATHI_VALUE(cursor.getString(cursor.getColumnIndex(Question_Table.CATEGORY_MARATHI)));
                questionTable.setCATEGORY_ENGLISH_VALUE(cursor.getString(cursor.getColumnIndex(Question_Table.CATEGORY_ENGLISH)));
                questionTable.setUPLOAD_STATUS_VALUE(cursor.getString(cursor.getColumnIndex(Question_Table.UPLOAD_STATUS)));
                question_tableArrayList.add(questionTable);
                cursor.moveToNext();
            }
            return question_tableArrayList;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    public static boolean deleteQuestionById(Context context, String question_id)
    {
        try {
            SQLiteDatabase db =  AssetDatabaseHelper.getDataHelper(context).getWritableDatabase();

            db.execSQL("DELETE FROM " + Question_Table.QUESTION_TABLE +
                    " WHERE " + Question_Table.QUESTION_ID + " = '" + question_id + "'");
            //delete all rows in titlebackground table

         //   Toast.makeText(context, "Birth Deleted Successfully", Toast.LENGTH_SHORT).show();


            db.close();


            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean deleteQuestionData(Context context)
    {
        try {
            SQLiteDatabase db =  AssetDatabaseHelper.getDataHelper(context).getWritableDatabase();

            db.execSQL("DELETE FROM " + Question_Table.QUESTION_TABLE ); //delete all rows in titlebackground table


           // Toast.makeText(context,"Birth data Deleted Successfully",Toast.LENGTH_SHORT).show();


            db.close();


            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
