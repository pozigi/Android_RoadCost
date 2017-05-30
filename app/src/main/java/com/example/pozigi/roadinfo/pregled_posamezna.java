package com.example.pozigi.roadinfo;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Lokacija;
import com.example.Pot;
import com.example.Poti;
import com.example.Vozilo;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by pozigi on 14. 03. 2017.
 */

public class pregled_posamezna extends AppCompatActivity {
    ApplicationMy app;
    EditText et;
    EditText et2;
    EditText et3;
    EditText et4,et5,et6,et8;
    TextView tv12;
    Button mapa,shrani;
    Pot tmp;
    String id;
    String XY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (ApplicationMy) getApplication();
        setContentView(R.layout.pregled_posamezna);
        et = (EditText) findViewById(R.id.editText); //zacetek poti
        et2 = (EditText) findViewById(R.id.editText2); //konec poti
        et3 = (EditText) findViewById(R.id.editText3); //oseba ime
        et5 = (EditText) findViewById(R.id.editText5); //oseba priimek
        et6 = (EditText) findViewById(R.id.editText6); //registrska
        et8 = (EditText) findViewById(R.id.editText8); //namen tekst
        et4 = (EditText) findViewById(R.id.editText4); //prevozeno kilometrov
        tv12 = (TextView) findViewById(R.id.textView12); //izpis povracila stroškov
        mapa = (Button) findViewById(R.id.button6); //odpre aktiviti z mapo
        shrani = (Button) findViewById(R.id.button7); //shranimo te ali ponastavlene nastavitve


        shrani.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //pridobimo vse nazej pa jih shranimo
                //testni podatki !
              //  tmp.setIzbrano_vozilo(tmp.getIzbrano_vozilo());
               // tmp.SpremeniPrvoLokacijo(new Lokacija(46.5546500,15.6458810,999));
               // tmp.SpremeniZadnjoLokacijo(new Lokacija(46.5546500,15.6458810,778));
               // app.PrepisiPot(tmp);

                // če je namen vnešen tam spremenimo ka bo v pregledi zelena forba (končano / ni končanoo)
                if(!(et8.getText().toString() == null) && !et8.getText().toString().isEmpty()){
                    tmp.setNamen(et8.getText().toString());
                    tmp.setKoncano(true);
                }else{
                    tmp.setNamen(et8.getText().toString());
                    tmp.setKoncano(false);
                }
                app.save();
               // finish();
                Intent pregled = new Intent(getBaseContext(), pregled.class);
                startActivity(pregled);

            }
        });
        mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(),Mapa.class );
                i.putExtra("id",tmp.GetID());
                startActivity(i);
            }
        });
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                    //ofni mapo ter v jem poišči lokacijo ter jo zapiši
            }
        });
    }
    public class DobiLokacijo extends AsyncTask<Void,Void,String>{
        Lokacija tmp;
        EditText e;
        public DobiLokacijo(Lokacija t,EditText et){
            this.tmp=t;
            this.e=et;
        }
        @Override
        protected void onPreExecute(){
            //v et.je lehko napisemo ka pridobiva lokacijo ali pa ke drugega
        }
        @Override
        protected String doInBackground(Void... params){
            Geocoder geocoder;
            List<Address> naslovi;
            geocoder = new Geocoder(getApplication(),Locale.getDefault());
            try {
                naslovi = geocoder.getFromLocation(tmp.getLang(), tmp.getLatt(),1);
                String naslov = naslovi.get(0).getLocality(); //recimo dobis nazej MB
                return naslov;
            } catch (IOException e) {
                e.printStackTrace();
            }

            return "Vkljuci podatke/wifi/gps";
        }
        @Override
        protected void onPostExecute(String vrni){
            super.onPostExecute(vrni);
            setXY(vrni,e);
        }
    }
    public void setXY(String lok,EditText edit){
       edit.setText(lok);
    }
    public String xyToAdress(Lokacija tmp,EditText edit){
      /*  Geocoder geocoder;
        List<Address> naslovi;
        geocoder = new Geocoder(this, Locale.getDefault());
        try {
            naslovi = geocoder.getFromLocation(tmp.getLang(), tmp.getLatt(),1);
            String naslov = naslovi.get(0).getLocality(); //recimo dobis nazej MB
            return naslov;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Vkljuci podatke/wifi/gps";*/

        new DobiLokacijo(tmp,edit).execute();
       // db.cancel(true);
        return "Procesira ...";
    }

    public void update(Pot t){
        et4.setText(Float.toString(t.getPrevozeno()));
        tv12.setText(Double.toString(t.getStrosek()));
        // db.execute(t.prva_lok1(),t.zadnja_lok1());
        et.setText(xyToAdress(t.prva_lok1(),et)); //string z zacetkom lokacije
        et2.setText(xyToAdress(t.zadnja_lok1(),et2)); //string zadnje lokacije
        et3.setText(t.getIzbrano_vozilo().getVoznik().getIme()); //ime
        et5.setText(t.getIzbrano_vozilo().getVoznik().getPriimek()); //priimek
        et6.setText(t.getIzbrano_vozilo().getRegistrska());

        // et8 - namen izvoza
        et8.setText(t.getNamen());

    }
    void pridobi(String ID){
        tmp=app.getPotId(ID);
        izracunej_prevozeno(tmp);
        izracunaj_strosek(tmp);
        update(tmp);
    }

    @Override
    protected void onResume(){
        super.onResume();
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
           // pridobi(extras.getString(""));
            id = extras.getString("id");

            pridobi(id);
        }else{
            System.out.print("Nič ni v extras");
        }
    }
   /* @Override
    public void onBackPressed(){
        Pot t = app.getPotId(id);
        t.setIzbrano_vozilo();
    }*/

    void izracunej_prevozeno(Pot t){
        float prevozeno=0f;
        Location one = new Location("one");
        Location two = new Location("two");
        for(int i=0;i<t.GetLokacije().size()-1;i++){
           one.setLatitude(t.GetLokacije().get(i).getLatt());
            one.setLongitude(t.GetLokacije().get(i).getLang());

            two.setLatitude(t.GetLokacije().get(i+1).getLatt());
            two.setLongitude(t.GetLokacije().get(i+1).getLang());
           prevozeno += one.distanceTo(two);
        }
        prevozeno = prevozeno / 1000;
        t.setPrevozeno(prevozeno);

    }
   void izracunaj_strosek(Pot t){
        double strosek=0;
        strosek=t.getPrevozeno()*0.37;
        t.setStrosek(strosek);
    }
}
