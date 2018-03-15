package com.example.mahadi.mysqllitedemo;

import android.content.Context;
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
    private static final int  DB_VERSION = 1;
    private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+"("+EMP_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+EMP_NAME+" VARCHAR(255), "+EMP_AGE+" INTEGER);";

    private Context context;

    public MyDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        try {
             sqLiteDatabase.execSQL(CREATE_TABLE);
            Toast.makeText(context," OnCreate is Called " ,Toast.LENGTH_LONG).show();

        }catch (Exception e){
            Toast.makeText(context," Exception "+e ,Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
