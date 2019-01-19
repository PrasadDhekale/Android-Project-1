package com.example.prasad.project;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterPolice extends AppCompatActivity {

    EditText username;
    EditText password;
    EditText ID;
    Button register;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_police);
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

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        register = (Button) findViewById(R.id.register);
        ID = (EditText) findViewById(R.id.ID);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PostFieldData postFieldData = new PostFieldData();
                postFieldData.setContext(RegisterPolice.this);
                postFieldData.setOperation("Register Police");
                postFieldData.setParameters(username.getText().toString(), password.getText().toString(), ID.getText().toString(), progressBar);
                postFieldData.databaseOperation(new DatabaseResponse() {
                    @Override
                    public void onSuccessListener(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String message = jsonObject.getString("message");
                            Toast.makeText(RegisterPolice.this,message,Toast.LENGTH_LONG).show();
                        }catch(JSONException e){
                            e.printStackTrace();
                        }
                    }
                });

            }
        });

    }

}
