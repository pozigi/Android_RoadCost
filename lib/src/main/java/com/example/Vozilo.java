package com.example;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.UUID;

/**
 * Created by pozigi on 27.2.2017.
 */
public class Vozilo {
    private String Id;
    private String Registrska;
    private boolean Lastno=true;
    //private Hashtable<String,Oseba> voznik1 = new Hashtable<>();
    private Oseba voznik;

    public Vozilo(){
    voznik = new Oseba();
    }
    public Vozilo(String Id,String registrska, Oseba voznik) {
        this.Id=UUID.randomUUID().toString().replaceAll("-", "");
        Registrska = registrska;
       // voznik1 = new Hashtable<>();
        //voznik1.put(voznik.getId(),voznik);
        this.voznik = voznik;
    }
    public Vozilo(String Id,String registrska, Oseba voznik,Boolean lastno) {
        this.Id=UUID.randomUUID().toString().replaceAll("-", "");
        Registrska = registrska;
        // voznik1 = new Hashtable<>();
        //voznik1.put(voznik.getId(),voznik);
        this.voznik = voznik;
        Lastno=lastno;
    }
    public Vozilo(Vozilo voz){
        this.Id=UUID.randomUUID().toString().replaceAll("-", "");
        Registrska = voz.Registrska;
        voznik = voz.voznik;
    }
    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getRegistrska() {
        return Registrska;
    }

    public void setRegistrska(String registrska) {
        Registrska = registrska;
    }

    public Oseba getVoznik() {
       // return voznik.get(id);
        return voznik;
    }

    public void setVoznik(String id,Oseba tmp) {
       // this.voznik = voznik;
     //  voznik.put(id,tmp);
    }


    @Override
    public String toString() {
        return "Vozilo{" +
                "Registrska='" + Registrska + '\'' +
                ", voznik=" + voznik +
                '}';
    }
}
