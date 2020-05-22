package edu.uci.ics.fabflixmobile;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class SingleMovieAdapter extends ArrayAdapter<Movie> {
    private ArrayList<Movie> movies;

    public SingleMovieAdapter(ArrayList<Movie> movies, Context context) {
        super(context, R.layout.row, movies);
        this.movies = movies;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.single_mv_row, parent, false);

        Movie movie = movies.get(position);

        TextView txTitle = view.findViewById(R.id.txSingleStar);

        txTitle.setText(movie.getName()); // need to cast the year to a string to set the label

        return view;
    }
}