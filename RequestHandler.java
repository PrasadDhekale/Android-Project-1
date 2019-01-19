package com.example.prasad.project;

import android.content.Context;
import android.provider.SyncStateContract;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RequestHandler {

    Context context;
    static RequestHandler requestHandler;
    RequestQueue requestQueue;
    public void setContext(Context context){
        this.context = context;
    }

    public static synchronized RequestHandler getInstance(){
        if(requestHandler == null){
            return new RequestHandler();
        }else{
            return requestHandler;
        }
    }

    public RequestQueue getRequestQueue(){
         if(requestQueue == null){
           requestQueue = Volley.newRequestQueue(context);
         }
         return requestQueue;
    }

    public <T> void addToRequestQueue(Request <T> req){
         getRequestQueue().add(req);
    }


}
