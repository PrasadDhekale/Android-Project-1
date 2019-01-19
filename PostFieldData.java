package com.example.prasad.project;

        import android.app.ActionBar;
        import android.content.Context;
        import android.content.Intent;
        import android.os.AsyncTask;
        import android.util.Log;
        import android.view.Gravity;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.widget.AbsListView;
        import android.widget.Button;
        import android.widget.PopupWindow;
        import android.widget.ProgressBar;
        import android.widget.RelativeLayout;
        import android.widget.Toast;

        import com.android.volley.AuthFailureError;
        import com.android.volley.Request;
        import com.android.volley.RequestQueue;
        import com.android.volley.Response;
        import com.android.volley.VolleyError;
        import com.android.volley.toolbox.StringRequest;
        import com.android.volley.toolbox.Volley;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;


        import java.util.HashMap;
        import java.util.Iterator;
        import java.util.List;
        import java.util.Map;

public class PostFieldData {

    Context context;

    String operation = "";
    String plate_number = "";
    String drop_location;
    String vehicle_type;
    String fine;
    String documents;
    String username = "";
    String password = "";
    String ID = "";
    String city;
    String state;
    PopupWindow popupWindow;
    Button closePopupBtn;
    ProgressBar progressBar;



    public void setContext(Context context){
        this.context = context;
    }

    public void setOperation(String operation){
        this.operation = operation;
    }

    public void setParameters(String ID){
        this.ID = ID;
    }

    public void setParameters(String ID, String password, ProgressBar progressBar){
        this.ID = ID;
        this.password = password;
        this.progressBar = progressBar;
    }

    public void setParameters(String param1 , String param2 , String param3, ProgressBar progressBar){
       if(operation.equals("fetch")) {                               // first setOperation
           this.plate_number = param1;
           this.city = param2;
           this.state = param3;
       }
       else if(operation.equals("Register Police")){
           this.username = param1;
           this.password = param2;
           this.ID = param3;
       }
       this.progressBar = progressBar;
    }

    public void setParameters(String plate_number , String drop_location, String city, String state, String vehicle_type, String fine, String documents, ProgressBar progressBar){
           this.plate_number = plate_number;
           this.drop_location = drop_location;
           this.city = city;
           this.state = state;
           this.vehicle_type = vehicle_type;
           this.fine = fine;
           this.documents = documents;
           this.progressBar = progressBar;
    }


    protected void databaseOperation( final DatabaseResponse responseListener ){

        final String myresponse[] = {null};
        String server_url ="";

        if(this.operation.equals("Register Police")){
            server_url = DBConstants.RegisterPolice_url;
        }
        if(this.operation.equals("Login")){
            server_url = DBConstants.police_login_service_url;
        }
        if(this.operation.equals("fetch")) {
            server_url = DBConstants.fetch_vehicle_server_url;
        }
        if(this.operation.equals("insert vehicle")){
            server_url = DBConstants.insertVehicle_url;
        }

        if (this.operation.equals("fetch location")){
            server_url = DBConstants.drop_address_fetch_url;
        }
        StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                           if(response != null){
                                   responseListener.onSuccessListener(response);
                           }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context,"Error in Connection", Toast.LENGTH_LONG).show();
                    }

                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> Params = new HashMap<>();
                if(operation.equals("Register Police")){
                    Params.put("username", username);
                    Params.put("password",password);
                    Params.put("id",ID);
                }
                else if(operation.equals("Login")){
                    Params.put("ID", ID);
                    Params.put("password", password);
                }
                else if(operation.equals("insert vehicle")){

                    Params.put("plate_number", plate_number);
                    Params.put("drop_location", drop_location);
                    Params.put("city", city);
                    Params.put("state", state);
                    Params.put("vehicle_type", vehicle_type);
                    Params.put("fine", fine);
                    Params.put("documents", documents);
                }
                else if(operation.equals("fetch location")){
                    Params.put("id", ID);
                }
                else{
                    Params.put("plate_number", plate_number);
                    Params.put("city",city);
                    Params.put("state",state);
                }
                return Params;
            }

        };
        RequestHandler requestHandler = RequestHandler.getInstance();
        requestHandler.setContext(context);
        requestHandler.addToRequestQueue(stringRequest);

    }


}