package com.example.mahadi.mysqllitedemo;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListViewActivity extends AppCompatActivity {

    private ListView listView;
    MyDBHelper myDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        listView = (ListView) findViewById(R.id.list_view_id);

        myDBHelper = new MyDBHelper(this);
        loadData();
    }

    public void loadData() {
        ArrayList<String> list = new ArrayList<>();

        Cursor resSet = myDBHelper.fatchAllData();

        if (resSet.getCount() == 0) {
            Toast.makeText(this, "No Data Availble !", Toast.LENGTH_LONG).show();
        } else {

            while (resSet.moveToNext()) {

                list.add(resSet.getString(0) + "\t" + resSet.getString(1)+ "\t" + resSet.getString(2));
            }
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.listVId, list);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String selectValue = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getApplicationContext(), "Clicked Item"+selectValue, Toast.LENGTH_LONG).show();
            }
        });
    }
}
