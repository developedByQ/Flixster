package com.example.flixster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Movie {

    int movieID;
    String backDropPath;
    String imagepath;
    String title;
    String description;
    double rating;

    public Movie(){}

    public Movie(JSONObject jsonObject) throws JSONException {
        backDropPath = jsonObject.getString("backdrop_path");
        imagepath = jsonObject.getString("poster_path");
        title = jsonObject.getString("title");
        description = jsonObject.getString("overview");
        rating = jsonObject.getDouble("vote_average");
        movieID = jsonObject.getInt("id");

    }

    public static List<Movie> convertJSONtoMovies(JSONArray JSONmovies) throws JSONException {
        List<Movie> movieList = new ArrayList<>();
        for (int i=0; i < JSONmovies.length(); i++) {
            movieList.add(new Movie(JSONmovies.getJSONObject(i)));
        }
        return movieList;
    }

    public String getBackDropPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", backDropPath);
    }

    public String getImagepath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", imagepath);
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public double getRating() {
        return rating;
    }

    public int getMovieID() {
        return movieID;
    }


}
