package com.example.prasad.project;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.example.prasad.project.VehicleItem;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ResponseDisplay extends AppCompatActivity implements VehicleList.OnListFragmentInteractionListener, VehicleItemDisplay.OnFragmentInteractionListener{
    EditText plate_number;
    EditText city;
    EditText state;
    VehicleList vehicleList;
    VehicleItemDisplay vehicleItemDisplay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_response_display);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        vehicleList = new VehicleList();
        Bundle bundle = new Bundle();
        String s = getIntent().getStringExtra("response");

        if(s != null) {
            bundle.putString("response", s);
            vehicleList.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, vehicleList).commit();

        }
        else
            Toast.makeText(ResponseDisplay.this,"String is null", Toast.LENGTH_SHORT);

    }

    @Override
    public void onListFragmentInteraction(VehicleItem item) {
        if(item == null){
            Toast.makeText(ResponseDisplay.this,"It is null" , Toast.LENGTH_SHORT).show();
        }else {

            vehicleItemDisplay = new VehicleItemDisplay();
            vehicleItemDisplay.setArguments(item);
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.conainer_layout, vehicleItemDisplay);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
    @Override
    public void onFragmentInteraction(){

    }
}
