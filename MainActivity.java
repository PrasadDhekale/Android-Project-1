package com.example.prasad.project;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.sun.jna.platform.win32.WinUser;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button user_button;
    Button police_button;
    EditText city;
    EditText state;
    EditText plate_number;
    Button searchButton;
    ProgressBar pbHeaderProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        user_button = (Button) findViewById(R.id.userButton);
        police_button = (Button) findViewById(R.id.policeButton);

        police_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                Intent i = new Intent(MainActivity.this,PoliceLogin.class);
                i.setFlags(i.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(i);
            }

        });


        plate_number = (EditText) findViewById(R.id.plate_number);
        city = (EditText) findViewById(R.id.city);
        state = (EditText) findViewById(R.id.state);

        searchButton = (Button) findViewById(R.id.search);
        pbHeaderProgress = (ProgressBar) findViewById(R.id.progressBar);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pbHeaderProgress.setVisibility(View.VISIBLE);
                plate_number.setInputType(InputType.TYPE_NULL);
                state.setInputType(InputType.TYPE_NULL);
                city.setInputType(InputType.TYPE_NULL);
                police_button.setClickable(false);

                PostFieldData postData = new PostFieldData();
                postData.setOperation("fetch");
                postData.setParameters(plate_number.getText().toString(),city.getText().toString(),state.getText().toString(),pbHeaderProgress);
                postData.setContext(MainActivity.this);
                postData.databaseOperation(new DatabaseResponse(){
                    @Override
                    public void onSuccessListener(String response){
                        Toast.makeText(MainActivity.this,"success",Toast.LENGTH_SHORT).show();
                        pbHeaderProgress.setVisibility(View.GONE);
                        plate_number.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
                        state.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
                        city.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
                        police_button.setClickable(false);


                            Intent i = new Intent(MainActivity.this, ResponseDisplay.class);
                            i.putExtra("response", response);
                            startActivity(i);
                    }

                });

            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public  Context getMyContext(){
        return MainActivity.this;
    }
}
