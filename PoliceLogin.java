package com.example.prasad.project;

import android.content.Intent;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PoliceLogin extends AppCompatActivity {

    EditText police_id;
    EditText password;
    EditText register;
    Button userButton;
    Button login;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_police_login);

        userButton = (Button) findViewById(R.id.userButton);
        userButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PoliceLogin.this,MainActivity.class);
                i.setFlags(i.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(i);
            }
        });

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        register = (EditText) findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PoliceLogin.this,RegisterPolice.class);
                startActivity(i);
            }
        });

        police_id = (EditText) findViewById(R.id.police_id);
        password = (EditText) findViewById(R.id.password);

        login = (Button) findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                System.out.println("Login button clicked");
                PostFieldData postFieldData = new PostFieldData();
                postFieldData.setContext(PoliceLogin.this);
                postFieldData.setOperation("Login");
                postFieldData.setParameters(police_id.getText().toString(), password.getText().toString(), progressBar);
                postFieldData.databaseOperation(new DatabaseResponse() {
                    @Override
                    public void onSuccessListener(String response) {
                        Intent i = new Intent(PoliceLogin.this, police_interface.class);
                        String message = "";
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            message = jsonObject.getString("message");
                        }catch(JSONException e){
                            e.printStackTrace();
                        }
                            if(message == "Invalid Data"){
                                Toast.makeText(PoliceLogin.this,"Invalid",Toast.LENGTH_LONG).show();
                                police_id.setText("");
                                password.setText("");
                            }else {
                                i.putExtra("police_id", police_id.getText().toString());
                                startActivity(i);
                            }
                    }


                });
            }
        });

    }

}
