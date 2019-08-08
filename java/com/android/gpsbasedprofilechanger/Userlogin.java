package com.android.gpsbasedprofilechanger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Userlogin extends AppCompatActivity {

    String user;
    Button b1,b2,b3,b7,b8;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlogin);

        b3=(Button)findViewById(R.id.b3);

        user=getIntent().getExtras().getString("user");



        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               //Intent i=new Intent(Userlogin.this,bmicalculation.class);
               // i.putExtra("user",user);
               // startActivity(i);
            }
        });



    }
}
