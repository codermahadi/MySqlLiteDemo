package com.example.mahadi.mysqllitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Mahadi on 3/15/2018.
 */

public class MyDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "Emp.db";
    private static final String TABLE_NAME = "emp_details";
    private static final String EMP_ID = "_id";
    private static final String EMP_NAME = "name";
    private static final String EMP_AGE = "age";
    private static final String GENDER = "gender";
    private static final int DB_VERSION = 4;
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + EMP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + EMP_NAME + " VARCHAR(255), " + EMP_AGE + " INTEGER, " + GENDER + " VARCHAR(25));";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    private static final String FATCH_ALL = "SELECT * FROM " + TABLE_NAME;

    private Context context;

    public MyDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        try {
            sqLiteDatabase.execSQL(CREATE_TABLE);
            Toast.makeText(context, " OnCreate is Called ", Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            Toast.makeText(context, " Exception " + e, Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {


        try {
            sqLiteDatabase.execSQL(DROP_TABLE);
            onCreate(sqLiteDatabase);
            Toast.makeText(context, " onUpgrade is Called ", Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            Toast.makeText(context, " Exception " + e, Toast.LENGTH_LONG).show();

        }

    }

//    Data Insert Method Here

    public long addData(String name, String age, String gender){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(EMP_NAME, name);
        contentValues.put(EMP_AGE, age);
        contentValues.put(GENDER, gender);

        long rowId  = db.insert(TABLE_NAME,null, contentValues);
        return rowId;

    }

    public Cursor fatchAllData(){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(FATCH_ALL,null);

        return cursor;
    }

    public boolean updateDate(String id, String name, String age, String gender){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(EMP_NAME, name);
        contentValues.put(EMP_AGE, age);
        contentValues.put(GENDER, gender);
        contentValues.put(EMP_ID, id);

        db.update(TABLE_NAME,contentValues,EMP_ID +" = ?", new String[]{id});


        return true;
    }


}
