package com.android.gpsbasedprofilechanger;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.Ringtone;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static android.content.ContentValues.TAG;

public class MainActivity extends Activity implements LocationListener {
    private AudioManager myAudioManager;
    Ringtone ringTone;
    Uri uriRingtone;
    EditText e1,e2,r;int md;
    Button b1,b2,b3;
    String s1,s2,s3,s4;
    int temp=0,temp1=0;
    double rad;
    float [] dist = new float[1];
    double lat=0.0,lon=0.0,latitude=0.0,longitude=0.0,la=0.0,lo=0,fg=0,fg1=0,radius=0;
    protected LocationManager lm;
    float result;
    String resultloc;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);



        e1=(EditText)findViewById(R.id.e1);
        r=(EditText)findViewById(R.id.r);
        e2=(EditText)findViewById(R.id.e2);
        b1=(Button)findViewById(R.id.button);
        b2=(Button)findViewById(R.id.button1);

        b3=(Button)findViewById(R.id.button2);






        SQLiteDatabase myDb = openOrCreateDatabase("zone", Context.MODE_PRIVATE, null);
        String query = "CREATE TABLE location(id INTEGER PRIMARY KEY AUTOINCREMENT,latitude REAL,longitude REAL,radius REAL,geo TEXT);";
        try {
            myDb.execSQL(query);
        } catch (Exception e) {
            //Toast.makeText(getApplicationContext(), "Exception" + e, Toast.LENGTH_SHORT).show();
        }
        myDb.close();
        call();

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent i=new Intent(MainActivity.this,Delmailaddress.class);
               startActivity(i);

               // SQLiteDatabase myDb = openOrCreateDatabase("zone", Context.MODE_PRIVATE, null);

              //  final String delete_Data = "DELETE from location";


               // try {
               //     myDb.execSQL(delete_Data );
                  //  Toast.makeText(getApplicationContext(), "location clear sucessful", Toast.LENGTH_LONG).show();

               // } catch (Exception e) {
                   // System.out.println(e);
                   // Toast.makeText(getApplicationContext(),"Exception"+e,Toast.LENGTH_SHORT).show();

               // }
              //  myDb.close();


            }
        });



        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(i);
            }
        });








        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase myDb = openOrCreateDatabase("zone", Context.MODE_PRIVATE, null);

                //final String Insert_Data = "INSERT INTO data(sympt1,sympt2,sympt3,sympt4,disease) VALUES(e1.getText().toString(),e2.getText().toString(),e3.getText().toString(),e4.getText().toString(),e5.getText().toString())";

                lat=Double.parseDouble(e1.getText().toString());
                lon=Double.parseDouble(e2.getText().toString());
                rad=Double.parseDouble(r.getText().toString());


                if(resultloc!=null  )
                {

                    final String Insert_Data = "INSERT INTO location (latitude,longitude,radius,geo) VALUES('"+lat+"','"+lon+"','"+rad+"','"+resultloc+"')";
                    try {
                        myDb.execSQL(Insert_Data);

                        Toast.makeText(getApplicationContext(),"Location Details Enterd Succesful",Toast.LENGTH_SHORT).show();




                    } catch (Exception e) {
                        System.out.println(e);
                        Toast.makeText(getApplicationContext(),"Exception"+e,Toast.LENGTH_SHORT).show();

                    }
                    myDb.close();
                }
              //  final String Insert_Data = "INSERT INTO location (latitude,longitude,radius,geo) VALUES('"+lat+"','"+lon+"','"+rad+"','"+resultloc+"')";



            }
        });
    }






    @Override
    public void onLocationChanged(Location location) {
        latitude=location.getLatitude();
        longitude=location.getLongitude();
       // Toast.makeText(getApplicationContext(),"hello",Toast.LENGTH_SHORT).show();

        //String.valueOf(latitude);
        e1.setText(String.valueOf(latitude));
        e2.setText(String.valueOf(longitude));

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        try {
            List<Address> addressList = geocoder.getFromLocation(
                    lat, lon, 1);
            // Toast.makeText(this," Fetchingg.....",Toast.LENGTH_LONG).show();

            //Toast.makeText(this," List size "+addressList+"  list "+addressList.size(),Toast.LENGTH_LONG).show();
            if (addressList != null && addressList.size() > 0) {
                Address address = addressList.get(0);
                StringBuilder sb = new StringBuilder();
                //  Toast.makeText(this," Fetchingaxaxxeedfefefaxg....."+address.getMaxAddressLineIndex(),Toast.LENGTH_LONG).show();
                for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
                    sb.append(address.getAddressLine(i)).append("\n");
                    //          Toast.makeText(this," Fetchingaxaxxaxg.....",Toast.LENGTH_LONG).show();
                }


                sb.append( address.getAddressLine(0)).append("\n");
                sb.append( address.getFeatureName()).append("\n");
                sb.append(address.getLocality()).append("\n");
                sb.append(address.getSubAdminArea()).append("\n");
                sb.append(address.getAdminArea()).append("\n");
                sb.append(address.getPostalCode()).append("\n");
                sb.append(address.getCountryName());
                resultloc = sb.toString();

                call();

            }
        } catch (IOException e) {
            Log.e(TAG, "Unable connect to Geocoder", e);
        }





        Toast.makeText(getApplicationContext(),"Latitude"+" "+latitude+" "+"longitude"+" "+longitude,Toast.LENGTH_SHORT).show();
        SQLiteDatabase myDb = openOrCreateDatabase("zone", Context.MODE_PRIVATE, null);
        try {
            if (myDb != null) {
                Cursor c = myDb.rawQuery("SELECT * from location", null);
                if (c.moveToFirst()) {
                    do {

                        s1 = c.getString(c.getColumnIndexOrThrow("id"));

                        //   Lst.add("ID:"+s1);
                        s1 = "";

                        la = c.getDouble(c.getColumnIndex("latitude"));
                        // la = Double.parseDouble(s2);
                        // Lst.add("Latitude:"+s2);
                        s2 = "";

                        lo = c.getDouble(

                                c.getColumnIndexOrThrow("longitude"));
                        radius= c.getDouble(

                                c.getColumnIndexOrThrow("radius"));

                        // Lst.add("Longitude:"+ s3);
                        //lo = Double.parseDouble(s2);

                        s3 = "";
                     /*   if ((latitude - la) < 0.4) {
                            fg = 1;
                        }
                        if ((latitude + la) > 0.4) {
                            fg = 1;

                        }
                        if ((longitude + lo) > 0.4) {
                            fg1 = 1;

                        }
                        if ((longitude - lo) > 0.4           ) {
                            fg1 = 1;

                        }*/
                        Location.distanceBetween(latitude,longitude,la,lo,dist);
                        Toast.makeText(this,"Distance"+dist[0],Toast.LENGTH_LONG).show();
                        if(dist[0]<radius)
                        {
                            fg=1;
                            fg1=1;
                        }
                        else
                        {
                            fg=0;fg=0;
                        }

                        if (fg == 1 && fg1 == 1) {
                            Toast.makeText(this, "Place is near", Toast.LENGTH_SHORT).show();
                            md=myAudioManager.getRingerMode();
                            if(md==AudioManager.RINGER_MODE_SILENT)
                            {
                                temp=1;
                            }
                            if(md==AudioManager.RINGER_MODE_VIBRATE)
                            {
                                temp=1;
                            }
                            if(temp==1)
                            {
                                Toast.makeText(this,"Not in ring mode",Toast.LENGTH_SHORT).show();
                            }

                            else {
                                Toast.makeText(this," Ring mode",Toast.LENGTH_SHORT).show();
                                myAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                                temp1=1;
                            }
                        }
                        else{
                            Toast.makeText(this, "Place is FAR", Toast.LENGTH_SHORT).show();


                            myAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);



                            // if(temp1==1)
                           // {
                                myAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                           // }
                        }
                        //list1.setAdapter(adapter);
                        // Toast.makeText(getApplicationContext(),"List"+Lst,Toast.LENGTH_SHORT).show();
                        fg = 0;
                        fg1 = 0;
                        la = 0;
                        lo = 0;
                        temp=0;
                        temp1=0;
                        md=0;
                        radius=0;
                    } while (c.moveToNext());//Move the cursor to the next row.
                } else {
                    Toast.makeText(getApplicationContext(), "No data found", Toast.LENGTH_LONG).show();
                }

            }
        }
        catch (Exception e)
        {
            Toast.makeText(this,"Exception"+e,Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
    public void call()
    {

        try {
            lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            lm.requestLocationUpdates(lm.GPS_PROVIDER, 0, 0, (LocationListener) this);
        }
        catch (SecurityException e)
        {

        }
    }
}
