package com.example.pozigi.roadinfo;

import android.app.Application;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.Lokacija;
import com.example.Pot;
import com.example.Vozilo;
import com.example.Pot;
import com.example.Poti;

import org.osmdroid.util.constants.UtilConstants;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;



public class ApplicationMy  extends Application {
    Poti tmp = new Poti();
    Vozilo prenos = new Vozilo();
    private static final String DATA_MAP = "roadinfo";
    private static final String FILE_NAME = "roadinfo2.json";

    @Override
    public void onCreate() {
        super.onCreate();
       // tmp.Scenarij();
      //  save();
       // tmp.Scenarij();
        //tmp.ScenarijA();
        if(!load()){
            tmp.ScenarijA();
            save();
        }
    }
    public List<String> getListLocationID(){
        return tmp.getPotIDs();
    }
    public Pot getPotId(String id){
        return tmp.getPotId(id);
    }
    public void DodajPot(Pot p){
        tmp.dodaj(p);
    }
    public void VoziloPrenos(Vozilo voz){
        prenos = voz;
    }
    public Vozilo getVozilo(){
        return prenos;
    }
    public boolean save() {
        File file = new File(this.getExternalFilesDir(DATA_MAP), ""
                + FILE_NAME);

        return ApplicationJson.save(tmp,file);
    }
    public boolean load(){
        File file = new File(this.getExternalFilesDir(DATA_MAP), ""
                + FILE_NAME);
        Poti tmp1 = ApplicationJson.load(file);
        if (tmp1!=null) tmp = tmp1;
        else return false;
        return true;
    }
    public Poti getAll(){
        return tmp;
    }
    public void PrepisiPot(Pot tmp){
        this.tmp.Prepisi(tmp);
    }
    public void removeLocation(int adapterPosition){
        tmp.posamezna_pot.remove(this.tmp.posamezna_pot.keySet().toArray()[adapterPosition]);
    }
    public void setLastLocation(Location loc){
        Pot k = tmp.dobiZadnjo();
        k.dodajLok(new Lokacija(loc.getLongitude(),loc.getLatitude(),999));
    }
    public void sortPodatke(){
        if(tmp==null) return;
        Collections.sort(tmp.getPotPot(), new Comparator<Pot>() {
            @Override
            public int compare(Pot o1, Pot o2) {
                if(o1.getPrevozeno() == o2.getPrevozeno()) return 0;
                if(o1.getPrevozeno() > o2.getPrevozeno()) return 1;
                return -1;
            }
        });
    }
}
