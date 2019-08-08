package com.android.gpsbasedprofilechanger;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class Delmailaddress extends AppCompatActivity {
    Cursor c;
    Spinner s1;
    Button b;
    ArrayAdapter ml;
    String t1;
    String mail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dellocation);
        s1=(Spinner)findViewById(R.id.s1);
        b=(Button)findViewById(R.id.b);
        ml= new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item);
        ml.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SQLiteDatabase db = openOrCreateDatabase("zone", Context.MODE_PRIVATE, null);
        try {
            if (db != null) {
                c = db.rawQuery("SELECT * from location", null);
                if (c.moveToFirst()) {
                    do {
                        mail=c.getString(c.getColumnIndexOrThrow("latitude"));
                        ml.add(mail);
                        mail="";
                        s1.setAdapter(ml);

                    } while (c.moveToNext());
                }
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Exception" + e, Toast.LENGTH_SHORT).show();
        }
        db.close();
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                t1=(String)s1.getSelectedItem();
                Toast.makeText(getApplicationContext(),"Item selected is"+t1,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase sb=openOrCreateDatabase("zone",Context.MODE_PRIVATE,null);
                String del="DELETE FROM  location WHERE latitude = '"+t1+"'";
                try{
                    sb.execSQL(del);
                    Toast.makeText(getApplicationContext(),"Location Details deleted",Toast.LENGTH_SHORT).show();
                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(),"Exception in deleting",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
