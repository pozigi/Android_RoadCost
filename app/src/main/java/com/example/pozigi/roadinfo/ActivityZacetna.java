package com.example.pozigi.roadinfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by pozigi on 13. 03. 2017.
 */

public class ActivityZacetna extends AppCompatActivity {
    ApplicationMy app;
    Button b1;
    Button b2;
    Button b3;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        app = (ApplicationMy) getApplication();
        setContentView(R.layout.activity_application_my);
        b1 = (Button) findViewById(R.id.button);
        b2 = (Button) findViewById(R.id.button2);
        b3 = (Button) findViewById(R.id.button3);

        b1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent i = new Intent(view.getContext(), nova_pot.class);
                startActivity(i);
            }
        });
        b2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
               Intent i = new Intent(view.getContext(), pregled.class);
                startActivity(i);
            }
        });
        b3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent i = new Intent(view.getContext(), nastavitve.class);
                startActivity(i);
            }
        });
    }
   /* public void onClickOpen(View view){
        Intent i = new Intent(view.getContext(), nova_pot.class);
        startActivity(i);
    }*/

}
