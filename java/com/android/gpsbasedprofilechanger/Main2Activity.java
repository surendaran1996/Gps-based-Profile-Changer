package com.android.gpsbasedprofilechanger;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    private ListView list1;
    private ArrayList<String> Lst;
    Cursor c;
    String s1,s2,s3,s4;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        // setContentView(R.layout.activity_main6);
        SQLiteDatabase myDb = openOrCreateDatabase("zone", Context.MODE_PRIVATE, null);
        try {
            if (myDb != null) {
                c = myDb.rawQuery("SELECT * from location", null);

            }
        }
        catch (Exception e)
        {
            Toast.makeText(this,"No records found",Toast.LENGTH_SHORT).show();
        }
        try {


            list1 = (ListView) findViewById(R.id.LstViewStus);

            Toast.makeText(getApplicationContext(), "Record found", Toast.LENGTH_LONG).show();
        } catch (Exception ex1) {

        }
        try {

            Data();
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), "No records found", Toast.LENGTH_LONG).show();
        }
    }


    public void Data() {
        Lst = new ArrayList<String>();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, Lst);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SQLiteDatabase myDb = openOrCreateDatabase("zone", Context.MODE_PRIVATE, null);
        if (myDb != null) {
            Cursor c = myDb.rawQuery("SELECT * from location", null);
            if (c.moveToFirst()) {
                do {

                    s1= c.getString(c.getColumnIndexOrThrow("id"));

                    Lst.add("ID:"+s1);
                    s1 = "";

                    s2= c.getString(c.getColumnIndex("latitude"));

                    Lst.add("Latitude:"+s2);
                    s2 = "";

                    s3= c.getString(c.getColumnIndexOrThrow("longitude"));

                    Lst.add("Longitude:"+ s3);
                    s3= "";
                    s4= c.getString(c.getColumnIndexOrThrow("geo"));

                    Lst.add("Location Info:"+ s4);
                    s4= "";

                    list1.setAdapter(adapter);
                    Toast.makeText(getApplicationContext(),"List"+Lst,Toast.LENGTH_SHORT).show();

                } while (c.moveToNext());//Move the cursor to the next row.
            } else {
                Toast.makeText(getApplicationContext(), "No data found", Toast.LENGTH_LONG).show();
            }


        }
    }
}
