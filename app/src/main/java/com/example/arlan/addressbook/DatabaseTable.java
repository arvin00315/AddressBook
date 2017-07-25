package com.example.arlan.addressbook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by arlan on 7/11/2017.
 */

public class DatabaseTable {

    DatabaseOpenHelper mDatabaseOpenHelper;


    public DatabaseTable(Context context) {
        mDatabaseOpenHelper = new DatabaseOpenHelper(context);
        Log.i("arvinsTag", "DatabaseTable Constructor was called");
    }


    static class DatabaseOpenHelper extends SQLiteOpenHelper {

        //Database Configuration
        private static final String DATABASE_NAME = "arvinsdatabase";
        private static final String TABLE_NAME = "ARVINSTABLE";
        private static final int DATABASE_VERSION = 7;

        //Column
        private static final String UID = "_id";
        private static final String NAME = "Name";
        private static final String MOBILE = "Mobile";
        private static final String EMAIL = "Email";

        //Query
        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " VARCHAR(255), " + MOBILE + " INTEGER, " + EMAIL + " VARCHAR(255));";
        private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;


        private Context context;

        public DatabaseOpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
            Log.i("arvinsTag", "DatabaseOpenHelper Constructor was called");
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {

            try {
                sqLiteDatabase.execSQL(CREATE_TABLE);
            } catch (SQLException e) {
                e.printStackTrace();
                Log.e("arvinsTag", "" + e);
            }

            Log.i("arvinsTag", "onCreate was called");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

            try {
                sqLiteDatabase.execSQL(DROP_TABLE);
                Log.i("arvinsTag", "onUpgrade was called");

                onCreate(sqLiteDatabase);
            } catch (SQLException e) {
                e.printStackTrace();
                Log.e("arvinsTag", "" + e);
            }


        }
    }


    public ArrayList<Data> getAllData() {
        ArrayList<Data> arrayData = new ArrayList<Data>();
        SQLiteDatabase sqLiteDatabase = mDatabaseOpenHelper.getWritableDatabase();

        String[] columns = {DatabaseOpenHelper.UID, DatabaseOpenHelper.NAME, DatabaseOpenHelper.MOBILE, DatabaseOpenHelper.EMAIL};
        Cursor cursor = sqLiteDatabase.query(DatabaseOpenHelper.TABLE_NAME, columns, null, null, null, null, null);

        //will do this while the database can still move to next data
        //append every data in String buffer
        while (cursor.moveToNext()) {
            String name = cursor.getString(1);
            int mobile = cursor.getInt(2);
            String email = cursor.getString(3);
            arrayData.add(new Data(name, mobile, email));
        }
        return arrayData;
    }


    public String searchSpecificData(String name) {

        SQLiteDatabase sqLiteDatabase = mDatabaseOpenHelper.getWritableDatabase();

        String[] columns = {DatabaseOpenHelper.NAME, DatabaseOpenHelper.MOBILE, DatabaseOpenHelper.EMAIL};
        Cursor cursor = sqLiteDatabase.query(DatabaseOpenHelper.TABLE_NAME, columns, DatabaseOpenHelper.NAME + " = '" + name + "'", null, null, null, null);
        StringBuffer buffer = new StringBuffer();

        //will do this while the database can still move to next data
        while (cursor.moveToNext()) {
            int index1 = cursor.getColumnIndex(DatabaseOpenHelper.NAME);
            int index2 = cursor.getColumnIndex(DatabaseOpenHelper.MOBILE);
            int index3 = cursor.getColumnIndex(DatabaseOpenHelper.EMAIL);

            String contactName = cursor.getString(index1);
            int mobile = cursor.getInt(index2);
            String email = cursor.getString(index3);
            buffer.append(contactName + " " + mobile + " " + email + "\n");
        }

        return buffer.toString();


    }


    public long insertData(String name, int mobile, String email) {

        SQLiteDatabase sqLiteDatabase = mDatabaseOpenHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseOpenHelper.NAME, name);
        contentValues.put(DatabaseOpenHelper.MOBILE, mobile);
        contentValues.put(DatabaseOpenHelper.EMAIL, email);
        long id = sqLiteDatabase.insert(DatabaseOpenHelper.TABLE_NAME, null, contentValues);
        return id;
    }


}
