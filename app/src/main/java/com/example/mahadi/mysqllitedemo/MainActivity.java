package com.example.mahadi.mysqllitedemo;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    MyDBHelper myDBHelper;

    private EditText name,age,gender;
    private Button insert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDBHelper = new MyDBHelper(this);

        SQLiteDatabase database = myDBHelper.getWritableDatabase();


        name = (EditText) findViewById(R.id.name);
        age = (EditText) findViewById(R.id.age);
        gender = (EditText) findViewById(R.id.gdr);

        insert = (Button) findViewById(R.id.insert);


    }

    @Override
    public void onClick(View view) {

        String empName = name.getText().toString();
        String empAge = age.getText().toString();
        String empGender = gender.getText().toString();

    }
}
