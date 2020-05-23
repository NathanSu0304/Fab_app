package edu.uci.ics.fabflixmobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
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

public class ListViewActivity extends Activity {
    private Button next, previous;
    private ListView listView;
    private Pagination pagination;
    private int lastPage, currentPage = 0;
    private MovieListViewAdapter adapter;
    private HashMap<String,String> map = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);
        //this should be retrieved from the database and the backend server
        Intent intent = getIntent();
        String data = intent.getStringExtra("info");
        Log.d("info", data);

        final ArrayList<Movie> movies = new ArrayList<>();
        parseData(data, movies);
        listView = findViewById(R.id.list);
        next = findViewById(R.id.btnNext);
        previous = findViewById(R.id.btnPre);

        Log.d("movies",movies.toString());

        pagination = new Pagination(20,movies);
        lastPage = pagination.getLastPage();

        updateData();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("click", "11");
                Movie movie = movies.get(position + 20* currentPage);
                String message = String.format("Clicked on position: %d, id: %s,name: %s,Year: %s", position, movie.getId(), movie.getName(), movie.getYear());
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

                Intent singleMvPage = new Intent(ListViewActivity.this, SingleMv.class);
                singleMvPage.putExtra("mv_object", movie);
                startActivity(singleMvPage);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPage += 1;
                updateData();
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPage -= 1;
                updateData();
            }
        });



    }
    protected String parseStr(String str){
        str = str.substring(1, str.length()-1);
        System.out.println(str);
        return str;
    }
    protected String parseStar(String str){
        str = str.substring(1, str.length()-1);
        String res = "";
        for(String s: str.split(",")){
            res += s.split(":")[1] + ",";
        }
        res = res.substring(0, res.length()-1);
        Log.d("par", res);
        return res;
    }

    protected void parseData(String data, ArrayList<Movie> movies){
        try{
            JSONArray jsonArray = new JSONArray(data);
            for(int i= 0; i< jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String title = jsonObject.getString("title");
                String year = jsonObject.getString("year");
                String director = jsonObject.getString("director");
                String gens = parseStr(jsonObject.getString("all_gens"));

                String stars = parseStar(jsonObject.getString("all_stars"));

                Movie m = new Movie(id, title,year,director);
                m.all_gens = gens;
                m.all_stars = stars;
                movies.add(m);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    protected void updateData(){
        ArrayList<Movie>temp;
        temp = pagination.generateData(currentPage);

        adapter = new MovieListViewAdapter(this, R.layout.row,temp);
        listView.setAdapter(adapter);

        updateButton();
    }

    protected void updateButton(){
        if(currentPage == 0 && currentPage != lastPage){
            next.setEnabled(true);
            previous.setEnabled(false);
        }
        else if(currentPage == lastPage && lastPage != 0){
            next.setEnabled(false);
            previous.setEnabled(true);
        }
        else if(currentPage == lastPage){
            next.setEnabled(false);
            previous.setEnabled(false);
        }
        else{
            next.setEnabled(true);
            previous.setEnabled(true);
        }
    }
}