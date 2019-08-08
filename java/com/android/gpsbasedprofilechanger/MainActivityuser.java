package com.android.gpsbasedprofilechanger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivityuser extends AppCompatActivity {
    Button b1,b2,b3,b4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainuser);
        b1=(Button)findViewById(R.id.b1);
        b2=(Button)findViewById(R.id.b3);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent i=new Intent(MainActivityuser.this,Userpage.class);
                startActivity(i);

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent i=new Intent(MainActivityuser.this,Setlloginuserid.class);
                startActivity(i);



            }
        });










    }
}
