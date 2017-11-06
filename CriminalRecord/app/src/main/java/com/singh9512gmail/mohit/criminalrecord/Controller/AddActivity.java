package com.singh9512gmail.mohit.criminalrecord.Controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.singh9512gmail.mohit.criminalrecord.Helper.DatabaseHandler;
import com.singh9512gmail.mohit.criminalrecord.Helper.Helper;
import com.singh9512gmail.mohit.criminalrecord.Model.Criminal;
import com.singh9512gmail.mohit.criminalrecord.R;

public class AddActivity extends AppCompatActivity {

    EditText nameET, addressET, dateET;
    Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        nameET = (EditText) findViewById(R.id.name_et);
        addressET = (EditText) findViewById(R.id.address_et);
        addressET.setText(Helper.randomAddress());
        dateET = (EditText) findViewById(R.id.date_et);
        dateET.setText(Helper.getTodayDate());
        add = (Button) findViewById(R.id.add_btn);

        final DatabaseHandler db = new DatabaseHandler(this);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameET.getText().toString();
                String address = addressET.getText().toString();
                String date = dateET.getText().toString();
                if(!name.trim().equals("")&&!address.trim().equals("")&&!date.trim().equals("")){
                    db.insertRecord(new Criminal("null", name, address , date , "31/12/9999"));
                }
                finish();
            }
        });
    }
}
