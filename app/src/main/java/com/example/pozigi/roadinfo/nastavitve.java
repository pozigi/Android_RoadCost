package com.example.pozigi.roadinfo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.Oseba;
import com.example.Pot;
import com.example.Vozilo;

/**
 * Created by pozigi on 13. 03. 2017.
 */

public class nastavitve extends AppCompatActivity {
    ApplicationMy app;
    EditText et;
    EditText et1;
    EditText et2;
    Button potrdi,slo,tujina,lastno,podjetje;
    Vozilo voz;
    Oseba os;
    public static final String IME = "";
    public static final String PRIIMEK="";
    public static final String REGISTERSKA="";
    public static final String DRZAVA="";
    public static final String VOZILO_LASTNIK="";
    public static final String PODATKI="";
    @Override
    protected void onCreate(Bundle savedIstanceState){
        super.onCreate(savedIstanceState);
        setContentView(R.layout.nastavitve);
        app= (ApplicationMy) getApplication();
        et =(EditText) findViewById(R.id.editText7); //registerska
        et1 =(EditText) findViewById(R.id.editText10); //ime
        et2 =(EditText) findViewById(R.id.editText11); //priimek
        potrdi = (Button) findViewById(R.id.button13); //shrani
        slo = (Button)  findViewById(R.id.button9);
        tujina =( Button) findViewById(R.id.button10);
        lastno= (Button) findViewById(R.id.button11);
        podjetje = (Button)findViewById(R.id.button12);

        slo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slo.setBackgroundColor(Color.BLUE);
                tujina.setBackgroundColor(Color.LTGRAY);
                SharedPreferences drzava = getSharedPreferences(DRZAVA,0);
                SharedPreferences.Editor edit = drzava.edit();
                edit.putString("drzava","SLO");
                edit.commit();
            }
        });
        tujina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slo.setBackgroundColor(Color.LTGRAY);
                tujina.setBackgroundColor(Color.BLUE);
                SharedPreferences drzava = getSharedPreferences(DRZAVA,0);
                SharedPreferences.Editor edit = drzava.edit();
                edit.putString("drzava","TUJINA");
                edit.commit();
            }
        });
        lastno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastno.setBackgroundColor(Color.BLUE);
                podjetje.setBackgroundColor(Color.LTGRAY);
                SharedPreferences vozilo = getSharedPreferences(VOZILO_LASTNIK,0);
                SharedPreferences.Editor edit = vozilo.edit();
                edit.putString("vozilo","LASTNO");
                edit.commit();
            }
        });
        podjetje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastno.setBackgroundColor(Color.LTGRAY);
                podjetje.setBackgroundColor(Color.BLUE);
                SharedPreferences vozilo = getSharedPreferences(VOZILO_LASTNIK,0);
                SharedPreferences.Editor edit = vozilo.edit();
                edit.putString("vozilo","PODJETJE");
                edit.commit();
            }
        });

        potrdi.setOnClickListener(new View.OnClickListener() {
        @Override
                public void onClick(View view){
            SharedPreferences ime = getSharedPreferences(IME,0);
            SharedPreferences.Editor edit = ime.edit();
            edit.putString("ime",et1.getText().toString());
            edit.commit();

            SharedPreferences priimek = getSharedPreferences(PRIIMEK,0);
            SharedPreferences.Editor edit1 = priimek.edit();
            edit.putString("priimek",et2.getText().toString());
            edit.commit();

            SharedPreferences registerska = getSharedPreferences(REGISTERSKA,0);
            SharedPreferences.Editor edit2 = registerska.edit();
            edit.putString("registerska",et.getText().toString());
            edit.commit();

            SharedPreferences lastno1 = getSharedPreferences(VOZILO_LASTNIK,0);
            String v = lastno1.getString("vozilo",VOZILO_LASTNIK);

            SharedPreferences drzava = getSharedPreferences(DRZAVA,0);
            String d = drzava.getString("drzava",DRZAVA);

            if(et.getText().toString().equals("") || et1.getText().toString().equals("") || et2.getText().toString().equals("") ||
                    v.equals("") || d.equals("") ){
                //prikažemo ka še nesmo vsega dodali!!
                Toast.makeText(getApplicationContext(),"Vsi podatki niso vneseni!",5).show();
                SharedPreferences podatki = getSharedPreferences(PODATKI,0);
                SharedPreferences.Editor e = podatki.edit();
                e.putBoolean("podatki",false);
                e.commit();
            }else {
                boolean svoj_avto=false;
                SharedPreferences lastno12 = getSharedPreferences(VOZILO_LASTNIK,0);
                String ve = lastno12.getString("vozilo",VOZILO_LASTNIK);
                if(ve.equals("LASTNO")){
                    svoj_avto=true;
                   // lastno.setBackgroundColor(Color.BLUE);
                } else if(ve.equals("PODJETJE")){
                   // podjetje.setBackgroundColor(Color.BLUE);
                    svoj_avto=false;
                }

                os = new Oseba("", et1.getText().toString(), et2.getText().toString());
                voz = new Vozilo("", et.getText().toString(), os,svoj_avto);
                app.VoziloPrenos(voz);
                SharedPreferences podatki = getSharedPreferences(PODATKI,0);
                SharedPreferences.Editor e = podatki.edit();
                e.putBoolean("podatki",true);
                e.commit();
                Intent i = new Intent(view.getContext(), ActivityZacetna.class);
                startActivity(i);
            }
        }

        });

    }
    @Override
    protected void onResume(){
        super.onResume();
        update();
    }
    public void update(){
       // et.setText(app.getVozilo().getRegistrska());
       // et1.setText(app.getVozilo().getVoznik().getIme());
       // et2.setText(app.getVozilo().getVoznik().getPriimek());

        SharedPreferences ime = getSharedPreferences(IME,0);
        et1.setText(ime.getString("ime",IME));
        SharedPreferences priimek = getSharedPreferences(PRIIMEK,0);
        et2.setText(ime.getString("priimek",PRIIMEK));
        SharedPreferences registerska = getSharedPreferences(REGISTERSKA,0);
        et.setText(ime.getString("registerska",REGISTERSKA));

        SharedPreferences drzava = getSharedPreferences(DRZAVA,0);
        String d = drzava.getString("drzava",DRZAVA);
        if(d.equals("SLO")){
            slo.setBackgroundColor(Color.BLUE);
        } else if(d.equals("TUJINA")){
            tujina.setBackgroundColor(Color.BLUE);
        }

        SharedPreferences lastno1 = getSharedPreferences(VOZILO_LASTNIK,0);
        String v = lastno1.getString("vozilo",VOZILO_LASTNIK);
        if(v.equals("LASTNO")){
            lastno.setBackgroundColor(Color.BLUE);
        } else if(v.equals("PODJETJE")){
            podjetje.setBackgroundColor(Color.BLUE);
        }

    }




    }

