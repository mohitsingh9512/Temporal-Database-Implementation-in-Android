package com.singh9512gmail.mohit.criminalrecord.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.singh9512gmail.mohit.criminalrecord.Helper.DatabaseHandler;
import com.singh9512gmail.mohit.criminalrecord.Helper.Helper;
import com.singh9512gmail.mohit.criminalrecord.Helper.TemporalDatabaseHandler;
import com.singh9512gmail.mohit.criminalrecord.Model.Criminal;
import com.singh9512gmail.mohit.criminalrecord.R;
import com.singh9512gmail.mohit.criminalrecord.View.SearchAdapter;

import java.util.List;

public class CriminalActivity extends AppCompatActivity {

    EditText name ;
    ListView listView;
    SearchAdapter adapter;
    Button change;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criminal);

        final Criminal criminal = (Criminal) getIntent().getSerializableExtra("Criminal");
        name = (EditText)findViewById(R.id.name_tv_search);
        listView = (ListView) findViewById(R.id.list_criminal);
        adapter = new SearchAdapter(getApplicationContext() , R.layout.model_search);
        listView.setAdapter(adapter);
        change = (Button)findViewById(R.id.search_btn);
        final TemporalDatabaseHandler dbTemp = new TemporalDatabaseHandler(this);
        final DatabaseHandler db = new DatabaseHandler(this);

        adapter.clear();
        if(!criminal.get_ref_id().equals("null")){
            TemporalDatabaseHandler.allCriminalNames.clear();
            TemporalDatabaseHandler.allCriminalNames.add(criminal);
            dbTemp.getCriminalAllNames(criminal);
            List<Criminal> listArray = TemporalDatabaseHandler.allCriminalNames;
            for (Criminal cr : listArray){
                adapter.add(cr);
            }
        }else{
            adapter.add(criminal);
        }
        adapter.notifyDataSetChanged();

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameStr = name.getText().toString();
                //Get index of new entry in temporal database
                long index = dbTemp.insertRecord(new Criminal(criminal.get_ref_id(), criminal.get_name() , criminal.get_address() , criminal.get_valid_from(),  Helper.getTodayDate()));
                //Update entry in current database with new values and ref of temporal"
                Criminal newCriminal = new Criminal(criminal.get_id(),index+"" , nameStr ,criminal.get_address() ,  Helper.getTodayDate() , "31/12/9999");
                db.updateCriminal(newCriminal);
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.add) {
          startActivity(new Intent(this,AddActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
