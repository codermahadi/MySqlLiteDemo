package com.example.mahadi.mysqllitedemo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    MyDBHelper myDBHelper;

    private EditText name, age, gender;
    private Button insert, fatchData;

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
        fatchData = (Button) findViewById(R.id.showData);

        insert.setOnClickListener(this);
        fatchData.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        String empName = name.getText().toString();
        String empAge = age.getText().toString();
        String empGender = gender.getText().toString();

        if (view.getId() == R.id.insert) {


            long res = myDBHelper.addData(empName, empAge, empGender);

            if (res > 0) {
                Toast.makeText(getBaseContext(), " Insert Success " + res, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getBaseContext(), " data not inserted ! " + res, Toast.LENGTH_LONG).show();

            }
        } else if (view.getId() == R.id.showData) {

            Cursor resSet = myDBHelper.fatchAllData();

            if (resSet.getCount() == 0) {

                showData("Error !", "Result Not found!");
                return;
            } else {
                    StringBuffer stringBuffer = new StringBuffer();

                    while (resSet.moveToNext()){
                        stringBuffer.append("ID : "+ resSet.getString(0)+"\n");
                        stringBuffer.append("Name : "+ resSet.getString(1)+"\n");
                        stringBuffer.append("Age : "+ resSet.getString(2)+"\n");
                        stringBuffer.append("Gender : "+ resSet.getString(3)+"\n");
                    }

                    showData("Result Set : ", stringBuffer.toString());
            }

        }

    }

    private void showData(String title, String resultSet) {

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(title);
        dialog.setMessage(resultSet);
        dialog.setCancelable(true);
        dialog.show();
    }
}
