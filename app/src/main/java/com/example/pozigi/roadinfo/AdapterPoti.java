package com.example.pozigi.roadinfo;

/**
 * Created by pozigi on 6. 04. 2017.
 */
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.Poti;
import com.example.Pot;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class AdapterPoti extends RecyclerView.Adapter<AdapterPoti.ViewHolder> {
    List<Pot> all;
    Activity ac;

    public AdapterPoti(Poti all,Activity ac){
        super();
        this.all = all.getPotPot();
        this.ac=ac;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView txt;
        public TextView txt2;
        public TextView zacetek,konec,prevozeno;

        public ViewHolder(View v){
            super(v);
            txt = (TextView) v.findViewById(R.id.textView15); // potni
            txt2 = (TextView) v.findViewById(R.id.textView13); // ime priimek
            zacetek = (TextView) v.findViewById(R.id.textView14);
            konec= (TextView)v.findViewById(R.id.textView10);
            prevozeno = (TextView)v.findViewById(R.id.textView16);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowelement, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }
    private static void startDView(String PotID, Activity ac) {
        //  System.out.println(name+":"+position);
        Intent i = new Intent(ac.getBaseContext(), pregled_posamezna.class);
        i.putExtra("id",  PotID);
        ac.startActivity(i);

    }
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Pot trenutni = all.get(position);

        //ZACETNI - KONCNI DATUM - CAS
        final long datum_zacetek = trenutni.prva_lok1().getDatum();
        final long datum_konec = trenutni.zadnja_lok1().getDatum();
        Date date_z = new Date(datum_zacetek);
        SimpleDateFormat df_z = new SimpleDateFormat("dd/MM/yy HH:mm");

        Date date_k = new Date(datum_konec);
        SimpleDateFormat df_k = new SimpleDateFormat("dd/MM/yy HH:mm");

        holder.zacetek.setText(df_z.format(date_z));
        holder.konec.setText(df_k.format(date_k));
        // PREVOZENO
        holder.prevozeno.setText(Float.toString(trenutni.getPrevozeno()) + "KM");
        // POTNI STROŠKI
        holder.txt.setText(Double.toString(trenutni.getStrosek()));

        //nastavimo ime
        final String name = trenutni.getIzbrano_vozilo().getVoznik().IzpisOsebe();
        holder.txt2.setText(name);


      //  final String regi = trenutni.getIzbrano_vozilo().getRegistrska();
       // holder.txt2.setText(regi);
      //  holder.iv.setImageDrawable(this.  ac.getDrawable(R.drawable.ic_airline_seat_recline_extra_black_24dp));
        if(trenutni.getKoncano()){
            holder.itemView.setBackgroundColor(Color.rgb(139,195,74));
        }else{
            holder.itemView.setBackgroundColor(Color.rgb(225,87,34));
        }
      //  holder.itemView.setBackgroundColor(Color.rgb(225,87,34));
        /*if (position%2==1) {
            //light green 139,195,74
            //deep orange
            holder.itemView.setBackgroundColor(Color.rgb(225,87,34));
           // holder.txt.setTextColor(Color.BLUE);
           // holder.txt2.setTextColor(Color.BLUE);
        }*/
        //holder.txt.setonclick
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AdapterPoti.startDView(trenutni.GetID(),ac);
            }
        });
       /* holder.txtFooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdapterLokacija.startDView(trenutni.getId(),ac);
            }
        });*/

      //  holder.txtFooter.setText("Footer: " + trenutni.getX()+","+trenutni.getY());
    }
    @Override
    public int getItemCount() {
        return all.size();
    }


}
