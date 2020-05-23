package edu.uci.ics.fabflixmobile;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SingleMv extends Activity {
    Movie mv_object;
    String id;
    String all_gens = "";
    String all_stars ="";
    private TextView txstitle;
    private TextView txsYear;
    private TextView txsdirector;
    private TextView txsgenres;
    private TextView txsstars;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_mv_row);
        //this should be retrieved from the database and the backend server
        Intent intent = getIntent();
        mv_object = intent.getParcelableExtra("mv_object");//id -> movie object
        id = mv_object.getId();
        get_all_details(id);

        txstitle = findViewById(R.id.txstitle);
        txsYear = findViewById(R.id.txsYear);
        txsdirector = findViewById(R.id.txsdirector);
        txsstars = findViewById(R.id.txsstars);
        txsgenres = findViewById(R.id.txsgenres);

        fill_page(mv_object);


    }
    private void fill_page(Movie mv){
        Log.d("IN_function", "in fill page function");

        txstitle.setText(mv.getName());
        txsYear.setText(mv.getYear());
        txsdirector.setText(mv.getDirector());

    }
    private void parse_details(String jsonData, String all_gens, String all_stars) throws JSONException {
        JSONArray jsonArray = new JSONArray(jsonData);

        for(int i = 0; i< jsonArray.length()-1;i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            try{
                all_gens += jsonObject.getString("name") + ",";
                Log.d("name",jsonObject.getString("name"));
            }
            catch (Exception e){
                all_stars += jsonObject.getString("star_name") + ",";
            }
        }
        all_gens = all_gens.substring(0,all_gens.length()-1);
        all_stars = all_stars.substring(0,all_stars.length()-1);

        Log.d("all_gens.success", all_gens);
        Log.d("all_stars.success", all_stars);

        //直接添加到textview
        txsgenres.setText(all_gens);
        txsstars.setText(all_stars);

    }

    private void get_all_details(String id){
        final RequestQueue queue = NetworkManager.sharedManager(this).queue;
        String url = "https://10.0.2.2:8443/project3/api/";

        final StringRequest all_star_details = new StringRequest(Request.Method.POST, url + "single-mv", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //TODO should parse the json response to redirect to appropriate functions.
                Log.d("get_all_details.success", response);
                try {
                    parse_details(response, all_gens, all_stars);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("get all stars.error", error.toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                // Post request form data
                final Map<String, String> params = new HashMap<>();
                params.put("id", id);
                return params;
            }
        };

        // !important: queue.add is where the login request is actually sent
        queue.add(all_star_details);

    }
}