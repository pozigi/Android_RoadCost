package com.example;

//import com.sun.xml.internal.ws.commons.xmlutil.Converter;



/**
 * Created by pozigi on 27.2.2017.
 */
public class Lokacija {

    private double lang,latt;
    private long Datum;

    public Lokacija(double lang, double latt, long datum) {
        this.lang = lang;
        this.latt = latt;
        Datum = datum;
    }

    public double getLang() {
        return lang;
    }

    public void setLang(double lang) {
        this.lang = lang;
    }

    public double getLatt() {
        return latt;
    }

    public void setLatt(double latt) {
        this.latt = latt;
    }

    public long getDatum() {
        return Datum;
    }

    public void setDatum(long datum) {
        Datum = datum;
    }

    @Override
    public String toString() {
        return " \n Lokacija{" +
                " \n lang=" + lang +
                ", \n latt=" + latt +
                ", \n Datum=" + Datum +
                '}';
    }


}
