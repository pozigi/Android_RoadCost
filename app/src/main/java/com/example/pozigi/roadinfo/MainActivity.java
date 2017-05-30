package com.example.pozigi.roadinfo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.Oseba;
import com.example.Pot;

public class MainActivity extends AppCompatActivity {
    ApplicationMy app;
    TextView tx;
    EditText et;
    EditText et1;
    Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        app= (ApplicationMy) getApplication();
        Update();
      //  Prikazi_osebo();

    }
    private View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          //  Prikazi_osebo1();
        }
    };


    /*public void Prikazi_osebo(){
        et.setText(app.tmp.ime_osebo());
        et1.setText(app.tmp.priimek_osebo());
    }
    public void Prikazi_osebo1(){
        et.setText(app.tmp.string_ime());
        et1.setText(app.tmp.string_ime());
    }*/

    public void Update(){
        tx = (TextView) findViewById(R.id.textView);
        et = (EditText) findViewById(R.id.et);
        et1 = (EditText) findViewById(R.id.et1);
        bt = (Button) findViewById(R.id.bt);
        bt.setOnClickListener(click);

    }
}
