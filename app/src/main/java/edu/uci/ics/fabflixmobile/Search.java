package edu.uci.ics.fabflixmobile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Search extends ActionBarActivity {
    RadioGroup radioGroup;
    RadioButton radioButton;
    private EditText et_search;
    private Button search;
    private String url;
    public ArrayList<Movie>mv_store;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

//        url = "https://10.0.2.2:8443/project3/api/";
        // upon creation, inflate and initialize the layout
        url = "https://ec2-3-136-86-44.us-east-2.compute.amazonaws.com:8443/project1/api/";
        setContentView(R.layout.search);
        et_search = findViewById(R.id.etSearch);
        search = findViewById(R.id.btnSearch);
        radioGroup = findViewById(R.id.radio_group);
        mv_store = new ArrayList<Movie>();

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
                String index = (String) radioButton.getText();
                do_search(index);
            }
        });
        et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
                    int radioId = radioGroup.getCheckedRadioButtonId();
                    radioButton = findViewById(radioId);
                    String index = (String) radioButton.getText();
                    Toast.makeText(getApplicationContext(),"Enter is pressed",Toast.LENGTH_SHORT).show();
                    //隐藏软键盘
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);

                    do_search(index);
                }
                return false;
            }
        });
    }


    protected void do_search(String index) {

        final RequestQueue queue = NetworkManager.sharedManager(this).queue;

        final StringRequest searchRequest = new StringRequest(Request.Method.POST, url + "autoComp", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //TODO should parse the json response to redirect to appropriate functions.
                Log.d("response", response);
                //initialize the activity(page)/destination
                Intent ListViewPage = new Intent(Search.this, ListViewActivity.class);
                //without starting the activity/page, nothing would happen
                ListViewPage.putExtra("info",response);

                try {
                    showResponse(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                ListViewPage.putExtra("info",mv_store.toString());

                startActivity(ListViewPage);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("search.error", error.toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                // Post request form data
                final Map<String, String> params = new HashMap<>();
                params.put("index", index);
                params.put("query", et_search.getText().toString());

                Log.d("para",params.toString());

                return params;
            }
        };
        queue.add(searchRequest);

    }

    private void showResponse(String jsonData) throws JSONException {
        Log.d("show response ", jsonData);
        JSONArray jsonArray = new JSONArray(jsonData);
        for(int i = 0; i< jsonArray.length();i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String id = jsonObject.getString("id");
            String title = jsonObject.getString("title");
            String year = jsonObject.getString("year");
            String director = jsonObject.getString("director");
            Movie mv = new Movie(id, title, year, director);
            mv.all_gens = jsonObject.getString("all_gens");
            mv.all_stars = jsonObject.getString("all_stars");
            mv_store.add(mv);
            Log.d("AAA1 response ", mv.toString());
        }


    }

    public void checkButton(View view) {
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        Log.d("check",radioButton.getText().toString());

        Toast.makeText(this,"select button "+ radioButton.getText(),Toast.LENGTH_SHORT).show();
    }
}