package com.example;

import java.util.ArrayList;

/**
 * Created by pozigi on 27.2.2017.
 */
public class NamenArray {
    private ArrayList<Namen> namen_izvoza;

    public NamenArray(){
       // namen_izvoza.add(tmp);
        namen_izvoza=new ArrayList<>();
    }
    public void  dodajNamen(Namen tmp){
        namen_izvoza.add(tmp);
    }

    @Override
    public String toString() {
        return "NamenArray{" +
                "namen_izvoza=" + namen_izvoza +
                '}';
    }


}
