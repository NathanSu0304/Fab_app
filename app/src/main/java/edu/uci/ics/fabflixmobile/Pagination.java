package edu.uci.ics.fabflixmobile;

import android.util.Log;

import java.util.ArrayList;

class Pagination {
    private final int itemsPerPage;
    private final int lastPageItems;
    private int lastPage;
    private final ArrayList<Movie>movieData;


    public Pagination(int itemsPerPage, ArrayList<Movie> movieData) {

        this.itemsPerPage = itemsPerPage;
        this.movieData = movieData;
        int total_mv = movieData.size();
        lastPage = total_mv / itemsPerPage;
        if(lastPage == 0){
            lastPageItems = movieData.size();
        }
        else{
            lastPageItems = total_mv % lastPage;
        }

        Log.d("movies111",itemsPerPage + "");
        Log.d("movies111",total_mv + "");
        Log.d("movies111",lastPage + "");
        Log.d("movies111",lastPageItems + "");

    }

    public ArrayList<Movie>generateData(int current){
        ArrayList<Movie>newPage = new ArrayList<Movie>();
        int start = current * itemsPerPage;
        if(current == lastPage){
            for(int i = 0; i< lastPageItems;i++)
                newPage.add(movieData.get(i+start));
        }
        else{
            for(int i = 0; i<itemsPerPage;i++)
                newPage.add(movieData.get(i+start));
        }
        return  newPage;
    }
    public int getLastPage() {return lastPage;}


}
