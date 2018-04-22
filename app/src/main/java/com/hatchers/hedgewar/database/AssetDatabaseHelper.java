package com.hatchers.hedgewar.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class AssetDatabaseHelper extends SQLiteOpenHelper {
    //private static AssetDatabaseHelper sInstance;


    private String dbName;
    private String db_path;
    private Context context;


    public static AssetDatabaseHelper getDataHelper(Context  context)
  {
        AssetDatabaseHelper dbHelper = new AssetDatabaseHelper(
                context, "hedgewar.db");
        try {
            dbHelper.importIfNotExist();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dbHelper;

    }


    public AssetDatabaseHelper(Context context, String dbName)
    {
        super(context, dbName, null, 1);
        this.dbName = dbName;
        this.context = context;
        db_path = "/data/data/" + context.getPackageName() + "/databases/";
    }

    /**
     * Check if the database already exist to avoid re-copying the file each
     * time you open the application.
     *
     * @return true if it exists, false if it doesn't
     */
    public boolean checkExist() {

        SQLiteDatabase checkDB = null;

        try {
            String myPath = db_path + dbName;
            checkDB = SQLiteDatabase.openDatabase(myPath, null,
                    SQLiteDatabase.OPEN_READWRITE);

        } catch (SQLiteException e) {
            e.printStackTrace();
            // database does't exist yet.

        } catch (Exception ep) {
            ep.printStackTrace();
        }

        if (checkDB != null) {

            checkDB.close();

        }

        return checkDB != null ? true : false;
    }

    /**
     * Creates a empty database on the system and rewrites it with your own
     * database.
     * */
    public void importIfNotExist() throws IOException {

        boolean dbExist = checkExist();

        if (dbExist) {
            // do nothing - database already exist
        } else {

            // By calling this method and empty database will be created into
            // the default system path
            // of your application so we are gonna be able to overwrite that
            // database with our database.
            this.getReadableDatabase();

            try {

                copyDatabase();

            } catch (IOException e) {

                throw new Error("Error copying database");

            }
        }

    }

    private void copyDatabase() throws IOException {
        InputStream is = context.getAssets().open(dbName);

        OutputStream os = new FileOutputStream(db_path + dbName);

        byte[] buffer = new byte[4096];
        int length;
        while ((length = is.read(buffer)) > 0) {
            os.write(buffer, 0, length);
        }
        os.flush();
        os.close();
        is.close();
        this.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}