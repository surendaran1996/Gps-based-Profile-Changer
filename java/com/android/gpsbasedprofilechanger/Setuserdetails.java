package com.android.gpsbasedprofilechanger;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Setuserdetails extends AppCompatActivity {

    EditText e1,e2,e3,e4,e5;
    String user;
    String empty="";
    String name,mail,phone,address;
    int age,fg=0;
    Button b1;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setuserdetails);
        e1=(EditText)findViewById(R.id.e1);
        e2=(EditText)findViewById(R.id.e2);
        e3=(EditText)findViewById(R.id.e3);
        e4=(EditText)findViewById(R.id.e4);
        e5=(EditText)findViewById(R.id.e5);
        b1=(Button)findViewById(R.id.b1);
        user=getIntent().getExtras().getString("user");
        Toast.makeText(this,"User name"+user,Toast.LENGTH_SHORT).show();
        final SQLiteDatabase db=openOrCreateDatabase("gps", Context.MODE_PRIVATE,null);
        String q1="CREATE TABLE userdetails(user TEXT PRIMARY KEY,name TEXT,age INTEGER,mail TEXT,phone TEXT,address TEXT);";
        try{

            db.execSQL(q1);
        }
        catch (Exception e){
            Toast.makeText(this,"Exception "+e,Toast.LENGTH_SHORT).show();
        }
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=e1.getText().toString();
                age=Integer.parseInt(e2.getText().toString());
                mail=e3.getText().toString();
                phone=e4.getText().toString();
                address=e5.getText().toString();
                if(name.equals(empty))
                    fg=1;
                if(address.equals(empty))
                    fg=1;
                if(mail.equals(empty))
                    fg=1;
                if(phone.equals(empty))
                    fg=1;
                if(age==0)
                    fg=1;
                if(fg==1){
                    Toast.makeText(getApplicationContext(),"Field can't be empty",Toast.LENGTH_SHORT).show();
                    startActivity(getIntent());
                }
                else{
                    String insert="INSERT INTO userdetails(user,name,age,mail,phone,address) VALUES('"+user+"','"+name+"','"+age+"','"+mail+"','"+phone+"','"+address+"')";
                    try{
                       db.execSQL(insert);
                        Toast.makeText(getApplicationContext(), "Data Inserted", Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(Setuserdetails.this,MainActivityuser.class);
                        startActivity(i);

                    }
                    catch (Exception e){
                        Toast.makeText(getApplicationContext(),"Exception in inserting "+e,Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

}
