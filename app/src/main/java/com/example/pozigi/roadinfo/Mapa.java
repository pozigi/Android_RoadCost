package com.example.pozigi.roadinfo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.example.Lokacija;
import com.example.Pot;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Overlay;

import java.util.ArrayList;

/**
 * Created by pozigi on 17. 04. 2017.
 */

public class Mapa extends Activity  {
    ApplicationMy app;
    Pot tmp;
    String id;
    Lokacija prva;
    MapView map;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context ctx = getApplicationContext();
        app = (ApplicationMy) getApplication();
        //important! set your user agent to prevent getting banned from the osm servers
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
        setContentView(R.layout.mapa);

        map = (MapView) findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);



    }
    public void nastaviMapo(){
        //nastovimo default lokacijo na tote kordinate (zoom in)
        IMapController mapController = map.getController();
        mapController.setZoom(17);
        Lokacija lok = tmp.prva_lok1();
        GeoPoint startPoint = new GeoPoint(lok.getLang(),lok.getLatt());
        mapController.setCenter(startPoint);


        GooglePolylineOverlay gp = new GooglePolylineOverlay(Color.RED,8);

        //ide skozi vse pointe pa izrise mapo?
        for(Lokacija l : tmp.GetLokacije()){
            gp.addPoints(new GeoPoint(l.getLang(),l.getLatt()));
        }
       //  gp.addPoints(new GeoPoint(46.25139,15.2568));
      //  gp.addPoints(new GeoPoint(46.25139,15.2568));
       //  gp.addPoints(new GeoPoint(46.25139,15.2578));
        // gp.addPoints(new GeoPoint(46.25300,15.2572));
        // gp.addPoints( new GeoPoint(46.25139,15.2568), new GeoPoint(46.25139,15.2578), new GeoPoint(46.25300,15.2572),new GeoPoint(46.25139,15.2568));
        map.getOverlays().add(gp);
    }
    void pridobi(String ID){
        tmp=app.getPotId(ID);
       prva = tmp.prva_lok1();
        nastaviMapo();
    }
    @Override
    public void onResume(){
        super.onResume();
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            id = extras.getString("id");
                pridobi(id);
        }else{
            System.out.print("Nič ni v extras");
        }
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().save(this, prefs);
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));
    }
}
