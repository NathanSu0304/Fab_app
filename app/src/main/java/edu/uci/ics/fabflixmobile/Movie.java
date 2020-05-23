package edu.uci.ics.fabflixmobile;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Movie implements Parcelable {
    private String id;
    private String title;
    private String year;
    private String director;
    public String all_gens;
    public String all_stars;


    public Movie(String id, String title, String year, String director) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.director = director;
    }

    protected Movie(Parcel in) {
        id = in.readString();
        title = in.readString();
        year = in.readString();
        director = in.readString();
        all_gens = in.readString();
        all_stars = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", director='" + director + '\'' +
                ", all_gens='" + all_gens + '\'' +
                ", all_stars='" + all_stars + '\'' +
                '}';
    }
    public String getId(){return id;}

    public String getName() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getDirector() {
        return director;
    }

    public void setId(String id){this.id = id;}

    public void setDirector(String director) {
        this.director = director;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(year);
        dest.writeString(director);
        dest.writeString(all_gens);
        dest.writeString(all_stars);
    }
}