package edu.uci.ics.fabflixmobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class SingleMv extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mv_listview);
        //this should be retrieved from the database and the backend server
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        Log.d("id", id);


//        final ArrayList<Movie> movies = new ArrayList<>();
//
//        movies.add(new Movie(data, "2016","hello"));
//        try{
//            JSONArray jsonArray = new JSONArray(data);
//            for(int i= 0; i< jsonArray.length(); i++){
//                JSONObject jsonObject = jsonArray.getJSONObject(i);
//                String id = jsonObject.getString("id");
//                String title = jsonObject.getString("title");
//                String year = jsonObject.getString("year");
//                String director = jsonObject.getString("director");
//                String rating = jsonObject.getString("rating");
//                String genre = jsonObject.getString("g");
//                String star = jsonObject.getString("allstar");
//
//                Movie m = new Movie(title,year,director);
//                System.out.println(m.toString());
//                movies.add(m);
//            }
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }

//        SingleMovieAdapter adapter = new SingleMovieAdapter(movies, this);
//        ListView listview = findViewById(R.id.list);
//        listview.setAdapter(adapter);

//        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Movie movie = movies.get(position);
//            }
//        });
    }
}