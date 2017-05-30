package com.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by pozigi on 27.2.2017.
 */
public class Poti {
  //  private ArrayList<Pot> posamezna_pot;
    public Hashtable<String,Pot> posamezna_pot;
    public Poti(){
        posamezna_pot= new Hashtable<>();

    }
    public void dodaj(Pot p){
        posamezna_pot.put(p.GetID(),p);
    }

    @Override
    public String toString() {
        return "Poti{" +
                " \n posamezna_pot=" + posamezna_pot +
                '}';
    }
    public  void Scenarij(){

        //Pot a = new Pot();

        Oseba os= new Oseba("","Žiga","Potočnik");
        Vozilo voz= new Vozilo("","MB-43255",os);
        NamenArray n = new NamenArray();
        n.dodajNamen(new Namen("Soljenje"));
        n.dodajNamen(new Namen("Pluzenje"));
        ArrayList<Lokacija> lok= new ArrayList<>();
        lok.add(new Lokacija(46.482298,16.165805,321321));
        lok.add(new Lokacija(56.482298,36.165805,761321));
        Pot a = new Pot("ID",lok,voz,n);
        dodaj(a);


    }
    public void ScenarijA(){
        //  Poti a = new Poti();
        Oseba os= new Oseba("","Janko","Kersnik");
        Vozilo voz= new Vozilo("","MB-54255",os);
        NamenArray n = new NamenArray();
        n.dodajNamen(new Namen("Pregled"));
        ArrayList<Lokacija> lok= new ArrayList<>();
        lok.add(new Lokacija(46.482298,16.165805,321321));
        lok.add(new Lokacija(56.482298,36.165805,761321));
        lok.add(new Lokacija(66.482298,66.165805,76321));
        lok.add(new Lokacija(76.482298,76.165805,76321));
        Pot a = new Pot("ID",lok,voz,n);
        // a.dodaj(new Pot("ID",lok,voz,n));
        dodaj(a);
    }

    public List<String> getPotIDs(){
        return Collections.list(posamezna_pot.keys());
    }
    public List<Pot> getPotPot(){ return Collections.list(posamezna_pot.elements());}
    public Pot getPotId(String id){
        return posamezna_pot.get(id);
    }
   // public Pot getPotInt(int i){return posamezna_pot.get();}
    public int PotiListSize(){return posamezna_pot.size(); }
    public void Prepisi(Pot tmp){
        posamezna_pot.put(tmp.GetID(),tmp); //prepise pot ce ta ze obstaja s totin keyom
    }
    public Pot dobiZadnjo(){
        return posamezna_pot.get(posamezna_pot.size()-1);
    }
}
