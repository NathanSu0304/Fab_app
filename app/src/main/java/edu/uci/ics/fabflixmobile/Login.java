package edu.uci.ics.fabflixmobile;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Login extends ActionBarActivity {

    private EditText username;
    private EditText password;
    private TextView message;
    private Button loginButton;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // upon creation, inflate and initialize the layout
        setContentView(R.layout.login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        message = findViewById(R.id.message);
        loginButton = findViewById(R.id.login);
        /**
         * In Android, localhost is the address of the device or the emulator.
         * To connect to your machine, you need to use the below IP address
         * **/
//        url = "https://10.0.2.2:8443/project3/api/";
        url = "https://ec2-18-222-98-131.us-east-2.compute.amazonaws.com:8443/project1/api/";

        //assign a listener to call a function to handle the user request when clicking a button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    public String[] parseResponse(String response){
        String[] res = new String[2];
        response = response.substring(1, response.length()-1);
        String[]temp = new String[2];
        temp[0] = (response.split(",")[0]).split(":")[1];
        temp[0] = temp[0].substring(1, temp[0].length()-1);
        res[0] = temp[0];
        Log.d("here",res[0]);

        temp[1] = (response.split(",")[1]).split(":")[1];

        temp[1] = temp[1].substring(1, temp[1].length()-1);
        res[1] = temp[1];
        Log.d("here",res[1]);
        return res;
    }

    public void login() {

        message.setText("Trying to login");
        // Use the same network queue across our application
        final RequestQueue queue = NetworkManager.sharedManager(this).queue;
        //request type is POST


        final StringRequest loginRequest = new StringRequest(Request.Method.POST, url + "login", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //TODO should parse the json response to redirect to appropriate functions.
                Log.d("login.success", response);
                //initialize the activity(page)/destination

                String[]parse;
                parse = parseResponse(response);

                Log.d("parseResponse", parse.toString());

                if(parse[0].equals("success")){
                    Intent searchPage = new Intent(Login.this, Search.class);
                    //without starting the activity/page, nothing would happen
                    startActivity(searchPage);
                }
                else{
                    message.setText(parse[1]);
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("login.error", error.toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                // Post request form data
                final Map<String, String> params = new HashMap<>();
                params.put("username", username.getText().toString());
                params.put("password", password.getText().toString());
//                Log.d("para", params.toString());

                return params;
            }
        };

        // !important: queue.add is where the login request is actually sent
        queue.add(loginRequest);

    }
}