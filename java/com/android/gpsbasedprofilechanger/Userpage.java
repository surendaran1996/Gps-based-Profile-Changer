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

public class Userpage extends AppCompatActivity {
    Button b1,b2;
    EditText e1,e2;
    String user,pass,dbpass,dbuser;

    int flg=0;
    String empty="";
    Cursor c;
    int fg=0;
    int flag=0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userpage);
        e1=(EditText)findViewById(R.id.e1);
        e2=(EditText)findViewById(R.id.e2);
        b1=(Button)findViewById(R.id.b1);
        b2=(Button)findViewById(R.id.b2);

        final SQLiteDatabase db=openOrCreateDatabase("gps", Context.MODE_PRIVATE,null);
        final String q1="CREATE TABLE users(user TEXT PRIMARY KEY,password TEXT);";
        try{
            db.execSQL(q1);

        }catch (Exception e)
        {
            Toast.makeText(this,"Exception "+e,Toast.LENGTH_SHORT).show();
        }


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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


                    check();
                    if (flag == 0) {
                        String insert = "INSERT INTO users(user,password) VALUES('" + user + "','" + pass + "')";
                        try {

                            db.execSQL(insert);

                            Toast.makeText(getApplicationContext(), "Data Inserted", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(Userpage.this, Setuserdetails.class);
                            i.putExtra("user", user);
                            startActivity(i);
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Exception in inserting" + e, Toast.LENGTH_SHORT).show();
                        }

                    }

                }
                }


        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user=e1.getText().toString();

              Intent i=new Intent(Userpage.this,Setuserdetails.class);
                i.putExtra("user",user);
                startActivity(i);
            }
        });
    }
    public void check(){
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
                                Toast.makeText(this,"Username "+user,Toast.LENGTH_SHORT).show();
                                flg=1;
                            }

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
