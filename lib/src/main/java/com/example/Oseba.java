package com.example;

import java.util.UUID;

/**
 * Created by pozigi on 27.2.2017.
 */
public class Oseba {
    private String Id;
    private String Ime;
    private String Priimek;

    public Oseba(){

    }
    public Oseba(String Id,String ime, String priimek) {
        Id= UUID.randomUUID().toString().replaceAll("-", "");
        Ime = ime;
        Priimek = priimek;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getIme() {
        return Ime;
    }

    public void setIme(String ime) {
        Ime = ime;
    }

    public String getPriimek() {
        return Priimek;
    }

    public void setPriimek(String priimek) {
        Priimek = priimek;
    }

    @Override
    public String toString() {
        return "Oseba{" +
                "Ime='" + Ime + '\'' +
                ", Priimek='" + Priimek + '\'' +
                '}';
    }
    public String IzpisOsebe(){
        return Ime + " " + Priimek;
    }
}
