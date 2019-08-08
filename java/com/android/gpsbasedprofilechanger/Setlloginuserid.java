package com.android.gpsbasedprofilechanger;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Setlloginuserid extends AppCompatActivity {


    Button b;
    Button b1,b2;
    EditText e1,e2;
    String user,pass,dbpass,dbuser;
    int fg=0;
    int flg=0;
    String empty="";
    Cursor c;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userloginpage);
        e1=(EditText)findViewById(R.id.e1);
        e2=(EditText)findViewById(R.id.e2);
        b1=(Button)findViewById(R.id.b1);
        b2=(Button)findViewById(R.id.b2);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"welcome",Toast.LENGTH_SHORT).show();

                flg=0;
                fg=0;
                user=e1.getText().toString();
                pass=e2.getText().toString();
                if(user.equals(empty))
                    fg=1;
                if(pass.equals(empty))
                    fg=1;
                if(fg==1){
                    Toast.makeText(getApplicationContext(),"Field cant be empty",Toast.LENGTH_SHORT).show();
                    startActivity(getIntent());
                }
                else{
                    SQLiteDatabase db=openOrCreateDatabase("gps", Context.MODE_PRIVATE,null);


                    try{

                        if(db!=null){

                            c=db.rawQuery("SELECT * from users",null);
                            if(c.moveToFirst()){
                                do{

                                    dbuser=c.getString(c.getColumnIndexOrThrow("user"));
                                    dbpass=c.getString(c.getColumnIndexOrThrow("password"));
                                    if(dbuser.equals(user)){
                                        if(dbpass.equals(pass)){
                                            Toast.makeText(getApplicationContext(),"Username success "+user,Toast.LENGTH_SHORT).show();
                                            flg=1;
                                           Intent i=new Intent(Setlloginuserid.this,MainActivity.class);
                                           // i.putExtra("user",user);
                                            startActivity(i);
                                        }

                                    }
                                    else
                                    {
                                        Toast.makeText(getApplicationContext(),"Invalid Password"+user,Toast.LENGTH_SHORT).show();

                                    }

                                }while(c.moveToNext());

                            }
                        }
                    }
                    catch (Exception e){
                        Toast.makeText(getApplicationContext(),"Exception in extracting "+e,Toast.LENGTH_SHORT).show();
                    }
                }
            }


        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // Intent i=new Intent(Setlloginuserid.this, MainActivityuser.class);
              //  startActivity(i);
            }
        });
    }

    }


