package com.example.fyahbetz.movie.JsonUtil;

import com.example.fyahbetz.movie.Model.Movie;
import com.example.fyahbetz.movie.Model.MovieList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by fyahbetz on 04/03/18.
 */

public class JsonUtils {


    private static final String MOVIE_PAGE="page";
    private static final String MOVIE_RESULTS="results";

    private static final String MOVIE_ID="id";
    private static final String MOVIE_VOTE_AVERAGE="vote_average";
    private static final String MOVIE_TITLE="title";
    private static final String MOVIE_POSTER_PATH="poster_path";
    private static final String MOVIE_ORIGINAL_LANGUAGE="original_language";
    private static final String MOVIE_ORIGINAL_TITLE="original_title";
    private static final String MOVIE_BACKDROP_PATH="backdrop_path";
    private static final String MOVIE_OVERVIEW="overview";
    private static final String MOVIE_RELEASE_DATE="release_date";


    /*this static method take as input the string (json) returned by getResponseFromHttpUrl in NetworkUtils class , his static method
    * parse the json and set the Movie and the MovieList object */


    public static MovieList jsonParsing(String json){

        try {
            MovieList movieList = new MovieList();
            JSONObject rootJson = new JSONObject(json);
            movieList.setPage(rootJson.optInt(MOVIE_PAGE));
            JSONArray jsonArray = rootJson.getJSONArray(MOVIE_RESULTS);
            ArrayList<Movie> results = new ArrayList<Movie>();
            for (int i=0; i<jsonArray.length(); i++){
                Movie movie = new Movie();
                movie.setId(jsonArray.getJSONObject(i).optInt(MOVIE_ID));
                movie.setVote_average(jsonArray.getJSONObject(i).optString(MOVIE_VOTE_AVERAGE));
                movie.setTitle(jsonArray.getJSONObject(i).optString(MOVIE_TITLE));
                movie.setPoster_path(jsonArray.getJSONObject(i).optString(MOVIE_POSTER_PATH));
                movie.setOriginal_language(jsonArray.getJSONObject(i).optString(MOVIE_ORIGINAL_LANGUAGE));
                movie.setOriginal_title(jsonArray.getJSONObject(i).optString(MOVIE_ORIGINAL_TITLE));
                movie.setBackdrop_path(jsonArray.getJSONObject(i).optString(MOVIE_BACKDROP_PATH));
                movie.setOverview(jsonArray.getJSONObject(i).optString(MOVIE_OVERVIEW));
                movie.setRelease_date(jsonArray.getJSONObject(i).optString(MOVIE_RELEASE_DATE));
                results.add(movie);
            }
            movieList.setResults(results);
            return movieList;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

}
