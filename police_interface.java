package com.example.prasad.project;

import android.content.Intent;
import android.os.Bundle;
import android.os.PatternMatcher;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class police_interface extends AppCompatActivity {
    Spinner vehicle_type_spinner;
    EditText plate_number;
    EditText drop_location;
    EditText fine;
    String documents;
    String police_id;
    Button submit,logout;
    CheckBox liscense,vehicle_papers;
    Spinner city_spinner;
    Spinner state_spinner;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_police_interface);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        police_id = getIntent().getStringExtra("police_id").toString();

        drop_location = (EditText) findViewById(R.id.drop);
        PostFieldData postFieldData = new PostFieldData();
        postFieldData.setContext(police_interface.this);
        postFieldData.setOperation("fetch location");
        postFieldData.setParameters(police_id);
        postFieldData.databaseOperation(new DatabaseResponse() {
            @Override
            public void onSuccessListener(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    drop_location.setText(jsonObject.getString("message"));
                }catch(JSONException e){
                    e.printStackTrace();
                }
            }
        });

        ArrayList<String> list = new ArrayList<String>();
        list.add("Bike");
        list.add("Car");

        vehicle_type_spinner = (Spinner) findViewById(R.id.myspinner);

        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(police_interface.this, R.layout.spinner_item, list);
        arrayAdapter1.setDropDownViewResource(R.layout.spinner_item);
        vehicle_type_spinner.setAdapter(arrayAdapter1);


        city_spinner = (Spinner) findViewById(R.id.city);
        final ArrayList<String> city = new ArrayList<String>();
        city.add("Choose City");
        city.add("PUNE");
        city.add("MUMBAI");
        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(police_interface.this, R.layout.spinner_item, city);
        arrayAdapter2.setDropDownViewResource(R.layout.spinner_item);
        city_spinner.setAdapter(arrayAdapter2);

        state_spinner = (Spinner) findViewById(R.id.state);
        ArrayList<String> state = new ArrayList<String>();
        state.add("Choose State");
        state.add("MAHARASHTRA");
        ArrayAdapter<String> arrayAdapter3 = new ArrayAdapter<String>(police_interface.this, R.layout.spinner_item, state);
        arrayAdapter3.setDropDownViewResource(R.layout.spinner_item);
        state_spinner.setAdapter(arrayAdapter3);


        final String vehicle_type = vehicle_type_spinner.getSelectedItem().toString();
        fine = (EditText)findViewById(R.id.fine);
        fine.setEnabled(false);
        if(vehicle_type.equals("Bike")){
            fine.setText("800 Rs");
        }
        else{
            fine.setText("1200 Rs");
        }

        plate_number = (EditText) findViewById(R.id.plate_number);
        liscense = (CheckBox) findViewById(R.id.liscense);
        vehicle_papers = (CheckBox) findViewById(R.id.papers);
        submit = (Button) findViewById(R.id.submit);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 if(plate_number.getText().toString().equals("")){
                     Toast.makeText(police_interface.this,"Enter Registration Number" , Toast.LENGTH_SHORT).show();
                 }
                 else if((liscense.isChecked() == false) && (vehicle_papers.isChecked() == false)){
                     Toast.makeText(police_interface.this,"Documents not specified",Toast.LENGTH_SHORT).show();
                }
                 else{
                     PostFieldData postFieldData = new PostFieldData();
                     postFieldData.setContext(police_interface.this);
                     postFieldData.setOperation("insert vehicle");

                     String Documents = "";

                     if(liscense.isChecked()){
                         Documents = "liscense ";
                     }
                     if (vehicle_papers.isChecked()){
                         Documents = Documents + " vehicle papers";
                     }

                     postFieldData.setParameters(plate_number.getText().toString(), drop_location.getText().toString(), city_spinner.getSelectedItem().toString(), state_spinner.getSelectedItem().toString(), vehicle_type_spinner.getSelectedItem().toString(), fine.getText().toString(), Documents, progressBar);

                     postFieldData.databaseOperation(new DatabaseResponse() {
                         @Override
                         public void onSuccessListener(String response) {
                             Toast.makeText(police_interface.this,"Data Inserted Successfully",Toast.LENGTH_SHORT).show();
                             plate_number.setText("");
                             vehicle_type_spinner.setSelection(0);
                             city_spinner.setSelection(0);
                             state_spinner.setSelection(0);
                             liscense.setChecked(false);
                             vehicle_papers.setChecked(false);
                         }
                     });
                 }
            }
        });

        logout = (Button) findViewById(R.id.logout_button);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                police_interface.this.finish();
            }
        });

    }

}
