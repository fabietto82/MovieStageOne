package com.example.fyahbetz.movie.Model;

import java.util.ArrayList;

/**
 * Created by fyahbetz on 03/03/18.
 */

public class MovieList {

    private int page;
    private ArrayList<Movie> results;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public ArrayList<Movie> getResults() {
        return results;
    }

    public void setResults(ArrayList<Movie> results) {
        this.results = results;
    }
}
