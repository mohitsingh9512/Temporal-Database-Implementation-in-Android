package com.singh9512gmail.mohit.criminalrecord.Controller;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.InputType;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.singh9512gmail.mohit.criminalrecord.Helper.DatabaseHandler;
import com.singh9512gmail.mohit.criminalrecord.Model.Criminal;
import com.singh9512gmail.mohit.criminalrecord.R;
import com.singh9512gmail.mohit.criminalrecord.View.SearchAdapter;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    EditText name ;
    ListView listView;
    SearchAdapter adapter;
    Button search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        name = (EditText)findViewById(R.id.name_tv_search);
        listView = (ListView) findViewById(R.id.list_search);
        adapter = new SearchAdapter(getApplicationContext() , R.layout.model_search);
        listView.setAdapter(adapter);
        search = (Button)findViewById(R.id.search_btn);
        final DatabaseHandler db = new DatabaseHandler(this);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText().toString().trim().equals("")){
                    adapter.clear();
                    List<Criminal> listArray = db.getAllCriminals();
                    for (Criminal criminal : listArray){
                        adapter.add(criminal);
                    }
                }else{
                    adapter.clear();
                    List<Criminal> listArray = db.getCriminal(name.getText().toString().trim());
                    for (Criminal criminal : listArray){
                        adapter.add(criminal);
                    }
                }
                adapter.notifyDataSetChanged();

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Criminal criminal =  (Criminal) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(SearchActivity.this, CriminalActivity.class);
                intent.putExtra("Criminal" , criminal);
                startActivity(intent);
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


    @Override
    protected void onResume() {
        super.onResume();
        adapter.clear();
        adapter.notifyDataSetChanged();
    }
}
