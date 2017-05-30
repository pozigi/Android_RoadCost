package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.UUID;
/**
 * Created by pozigi on 27.2.2017.
 */
public class Pot {
    public  String ID;
    private float prevozeno=0f;
    private double strosek = 0;
    private ArrayList<Lokacija> Lok_na_poti;
    private Hashtable<String,Vozilo> Izbrano_vozilo;
    private String namen=null;
    private boolean koncano=false;
  //  private Vozilo izbrano_vozilo;
    // private String Izbrano_vozilo;

    private NamenArray postorjeno;


    public Pot(){
        ID= UUID.randomUUID().toString().replaceAll("-", "");
        Lok_na_poti = new ArrayList<>();
       Izbrano_vozilo = new Hashtable<>();
        //izbrano_vozilo = new Vozilo();
        postorjeno = new NamenArray();

    }

    public Pot(String ID, ArrayList<Lokacija> lok_na_poti, Vozilo izbrano_vozilo, NamenArray postorjeno) {
        this.ID = UUID.randomUUID().toString().replaceAll("-", "");
        Lok_na_poti = lok_na_poti;
        Izbrano_vozilo = new Hashtable<>();
        this.Izbrano_vozilo.put(izbrano_vozilo.getId(),izbrano_vozilo);
       // this.izbrano_vozilo=izbrano_vozilo;
        //this.Izbrano_vozilo = izbrano_vozilo;
        this.postorjeno = postorjeno;
    }

    public void LokacijeDodaj(Lokacija tmp){
        Lok_na_poti.add(tmp);
    }

    @Override
    public String toString() {
        return "Pot{ \n" +
                "ID='" + ID + '\'' +
                ", \n Lok_na_poti=" + Lok_na_poti +
                ", \n  Izbrano_vozilo=" + Izbrano_vozilo +
                ", \n postorjeno=" + postorjeno +
                '}';
    }
    public  String GetID(){
        return ID;
    }

   /* public static  Pot ScenarijA(){
        //  Poti a = new Poti();
        Oseba os= new Oseba("","Janko","Kersnik");
        Vozilo voz= new Vozilo("","MB-54255",os.getId());
        NamenArray n = new NamenArray();
        n.dodajNamen(new Namen("Pregled"));
        ArrayList<Lokacija> lok= new ArrayList<>();
        lok.add(new Lokacija(46.482298,16.165805,321321));
        lok.add(new Lokacija(56.482298,36.165805,761321));
        lok.add(new Lokacija(66.482298,66.165805,76321));
        lok.add(new Lokacija(76.482298,76.165805,76321));
        Pot a = new Pot("ID",lok,voz.getId(),n);
        // a.dodaj(new Pot("ID",lok,voz,n));
        return a;
    }*/

    public String prva_lok(){
        Lokacija tmp =Lok_na_poti.get(0);
        return tmp.toString();
    }
    public String zadnja_lok(){
        Lokacija tmp = Lok_na_poti.get(Lok_na_poti.size()-1);
        return tmp.toString();
    }
    public Lokacija prva_lok1(){
        Lokacija tmp =Lok_na_poti.get(0);
        return tmp;
    }
    public Lokacija zadnja_lok1(){
        Lokacija tmp = Lok_na_poti.get(Lok_na_poti.size()-1);
        return tmp;
    }
    public void SpremeniPrvoLokacijo(Lokacija l){ Lok_na_poti.set(0,l); } //prvo lokacijo spremenimo v dobljeno lokacijo
    public void SpremeniZadnjoLokacijo(Lokacija l) {Lok_na_poti.set(Lok_na_poti.size()-1,l); } //spremenimo zadnjo lokacijo
    public void dodajLok(Lokacija l){
        Lok_na_poti.add(l);
    }
    public void setIzbrano_vozilo(Vozilo voz){
        Izbrano_vozilo.put(voz.getId(),voz);
       // this.izbrano_vozilo = voz;
    }
    public Vozilo getIzbrano_vozilo(){
             return this.Izbrano_vozilo.get(this.Izbrano_vozilo.keySet().toArray()[0]);
         }
    public static String ime_osebo(){
        return "janko";
    }
    public static String priimek_osebo(){
        return "kkk";
    }
    public String string_ime(){
       return this.Izbrano_vozilo.toString();
    }
    public ArrayList<Lokacija> GetLokacije(){
        return this.Lok_na_poti;
    }
    public void setPrevozeno(float prev){
        prevozeno = prev;
    }
    public float getPrevozeno(){
        return  prevozeno;
    }
    public void setStrosek(double strosek){
        this.strosek=strosek;
    }
    public double getStrosek(){
        return strosek;
    }
    public boolean getKoncano(){
        return  koncano;
    }
    public void setKoncano(boolean koncano){
        this.koncano=koncano;
    }
    public String getNamen(){
        return namen;
    }
    public void setNamen(String namen){
        this.namen=namen;
    }



}
