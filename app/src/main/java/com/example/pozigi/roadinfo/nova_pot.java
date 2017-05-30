package com.example.pozigi.roadinfo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.Lokacija;
import com.example.Oseba;
import com.example.Pot;
import com.example.Poti;
import com.example.Vozilo;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.jar.Manifest;

import static com.example.pozigi.roadinfo.nastavitve.PODATKI;

/**
 * Created by pozigi on 13. 03. 2017.
 */

public class nova_pot extends AppCompatActivity{
    ApplicationMy app;
    Button b4;
    Button b5;
    Pot tmp;
    ArrayList<Lokacija> tmpLok;
   // GetLokacija gps;
   // GPSTracker gps;
    double latitude;
    double longitude;
    private BroadcastReceiver broadcast;

    @Override
    protected void onResume(){
        super.onResume();
        if(broadcast==null) {
            broadcast = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    b4.setText("kk");
                    Location m = intent.getParcelableExtra("koordinate");
                    tmp.dodajLok(new Lokacija(m.getLatitude(), m.getLongitude(), m.getTime()));
                }
            };

        }
        registerReceiver(broadcast,new IntentFilter("location_update"));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        app =(ApplicationMy) getApplication();
        setContentView(R.layout.nova_pot);
        b4 = (Button) findViewById(R.id.button4);
        b5 = (Button) findViewById(R.id.button5);
        b5.setEnabled(false);



        b4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                SharedPreferences lastno1 = getSharedPreferences(PODATKI,0);
                boolean v = lastno1.getBoolean("podatki",true);
                if(v) {

                    tmp = new Pot();
                    tmp.setIzbrano_vozilo(app.getVozilo());
                    //app.DodajPot(tmp);

                    Intent i = new Intent(getApplicationContext(), GetLokacija.class);
                    startService(i);


                    //utriple ka laufa al nekej?
                    // pridobimo lokacijo
                    //unesposobimo ponoven klik
                    //nastavimo lokacijo na vpogled?
            /*    gps = new GetLokacija(nova_pot.this);
                if(gps.canGetLocation()){
                    latitude = gps.getLatitude();
                    longitude = gps.getLongitude();

                    tmp = new Pot();
                    tmp.LokacijeDodaj(new Lokacija(latitude,longitude,99));
                    tmp.setIzbrano_vozilo(app.getVozilo());
                    //  tmp.setIzbrano_vozilo(new Vozilo("","kjl",new Oseba("","matej","kok")));
                    // app.DodajLok(new Lokacija(3432,43243,432423));
                    b4.setEnabled(false);
                    b5.setEnabled(true);
                }else{
                    Toast.makeText(getApplicationContext(),"ne radi!",4).show();
                }/*
                tmp = new Pot();
                tmp.setIzbrano_vozilo(app.getVozilo());*/

                b4.setEnabled(false);
                    b5.setEnabled(true);
                }else{
                    Intent i= new Intent(view.getContext(),nastavitve.class);
                    startActivity(i);
                }
            }
        });
        b5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                //stavimo lokacijo
                //vrže nas na novi intent z vsemi podatki
               // b5.setEnabled(false);
                Intent i = new Intent(getApplicationContext(),GetLokacija.class);
                stopService(i);
                if(broadcast!=null) unregisterReceiver(broadcast);
               // if(dataUpdateReceiver!=null) unregisterReceiver(dataUpdateReceiver);
                app.DodajPot(tmp);
               // app.save();
               // Toast.makeText(getApplicationContext(),"ne radi!",4).show();
              /*  Intent i = new Intent(getBaseContext(),pregled_posamezna.class );
                i.putExtra("id",tmp.GetID());
                startActivity(i); */
          /*      gps = new GetLokacija(nova_pot.this);
                if(gps.canGetLocation()){
                    latitude = gps.getLatitude();
                    longitude = gps.getLongitude();
                }else{
                    Toast.makeText(getApplicationContext(),"ne radi!",4).show();
                }
                tmp.LokacijeDodaj(new Lokacija(latitude,longitude,99));
                app.DodajPot(tmp);
                app.save();

*/
                Intent it = new Intent(getBaseContext(),pregled_posamezna.class );
                it.putExtra("id",tmp.GetID());

                //   String im = lv.getSelectedItem().toString();
                //  Log.i("Hello!", "Y u no see me?");
                startActivity(it);



                //vrni na začetnega zej*/

            }
        });


    }



}
