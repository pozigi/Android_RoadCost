package com.example.pozigi.roadinfo;

import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
//import android.support.design.widget.Snackbar;

import com.example.Pot;
import com.example.Poti;

import java.util.List;

/**
 * Created by pozigi on 13. 03. 2017.
 */

public class pregled extends AppCompatActivity {
    ApplicationMy app;
   // ListView lv;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceBundle){
        super.onCreate(savedInstanceBundle);
        app = (ApplicationMy) getApplication();
        setContentView(R.layout.pregled);
      //  lv = (ListView) findViewById(R.id.listview);
        mRecyclerView =(RecyclerView) findViewById(R.id.myrecycleview);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        // specify an adapter (see also next example)
        app = (ApplicationMy) getApplication();
        mAdapter = new AdapterPoti(app.getAll(), this);
        mRecyclerView.setAdapter(mAdapter);

        setDeleteOnSwipe(mRecyclerView);
    }

    public void setDeleteOnSwipe(final RecyclerView mRecyclerView){
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT){
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target){
                return false;
            }
            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                               // app.removeLocationByPosition(viewHolder.getAdapterPosition());
                                app.removeLocation(viewHolder.getAdapterPosition());
                                app.save();
                                mAdapter.notifyDataSetChanged();
                               // mRecyclerView.getAdapter().notifyDataSetChanged();
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                mRecyclerView.getAdapter().notifyDataSetChanged();
                                //mAdapter.notifyDataSetChanged();
                               // mAdapter.notifyDataSetChanged();
                                break;
                        }
                        // mRecyclerView.getAdapter().notifyDataSetChanged();
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(pregled.this);
                builder.setTitle("Delete location");
                builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener)
                ;
                builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                         mRecyclerView.getAdapter().notifyDataSetChanged();
                       // mAdapter.notifyDataSetChanged();
                    }
                });
             /*   builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        mRecyclerView.getAdapter().notifyDataSetChanged();
                    }
                });*/
                builder.show();

            }

        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    protected void onResume(){
        super.onResume();
       // SetListView();
        app.sortPodatke();
        mAdapter = new AdapterPoti(app.getAll(), this);
        int i=0;
        mAdapter.notifyDataSetChanged();
    }
    @Override
    public void onBackPressed(){
        Intent zacetna = new Intent(getBaseContext(), ActivityZacetna.class);
        startActivity(zacetna);
    }
   /* public void SetListView(){
       ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,app.getListLocationID());
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
               Intent i = new Intent(getBaseContext(),pregled_posamezna.class );
                String tmp = lv.getItemAtPosition(position).toString();
                i.putExtra("id", lv.getItemAtPosition(position).toString());

             //   String im = lv.getSelectedItem().toString();
              //  Log.i("Hello!", "Y u no see me?");
                startActivity(i);
            }

        });

    }*/
}
