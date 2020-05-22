package edu.uci.ics.fabflixmobile;

import java.util.ArrayList;

public class Movie {
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
}