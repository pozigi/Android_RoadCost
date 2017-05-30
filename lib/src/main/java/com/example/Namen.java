package com.example;

/**
 * Created by pozigi on 27.2.2017.
 */
public class Namen {
    private String ime;

    public Namen(String ime) {
        this.ime = ime;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }


    @Override
    public String toString() {
        return "Namen{" +
                "ime='" + ime + '\'' +
                '}';
    }
}
