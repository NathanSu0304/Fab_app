package edu.uci.ics.fabflixmobile;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class MovieListViewAdapter extends ArrayAdapter<Movie> {
    private ArrayList<Movie> movies;

    public MovieListViewAdapter(@NonNull Context context, int resource, @NonNull List<Movie> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String title = getItem(position).getName();
        String year = getItem(position).getYear();
        String director = getItem(position).getDirector();
        String all_gens = getItem(position).all_gens;
        String all_stars = getItem(position).all_stars;
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.row, parent, false);


        TextView txTitle = view.findViewById(R.id.txTitle);
        TextView txYear = view.findViewById(R.id.txYear);
        TextView txDirector = view.findViewById(R.id.txDirector);
        TextView txGens = view.findViewById(R.id.txGens);
        TextView txStars = view.findViewById(R.id.txStars);

        txTitle.setText(title);
        txYear.setText(year);
        txDirector.setText(director);
        txGens.setText(all_gens);
        txStars.setText(all_stars);

        return view;
    }
}