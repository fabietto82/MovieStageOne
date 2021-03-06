package com.example.fyahbetz.movie;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.fyahbetz.movie.Adapter.AdapterMovie;
import com.example.fyahbetz.movie.JsonUtil.JsonUtils;
import com.example.fyahbetz.movie.Model.MovieList;
import com.example.fyahbetz.movie.NetworkUtils.NetworkUtils;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<MovieList> ,
        AdapterMovie.MovieListenerHandler{

    private String querySortby="popular";
    private MovieList movieList;
    private RecyclerView recyclerView;
    private AdapterMovie adapterMovie;
    public final static int MOVIE_LOADER=77;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.main_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setHasFixedSize(true);




        // Log.d("problems here  ", "problems here");
        // makeMovieSearch(querySortby);

        //adapterMovie= new AdapterMovie(this,this.movieList.getResults(),this);
        //recyclerView.setAdapter(adapterMovie);


        getSupportLoaderManager().initLoader(MOVIE_LOADER, null,this).forceLoad();

        // adapterMovie=new AdapterMovie(this, movieList.getResults(),this);
        // recyclerView.setAdapter(adapterMovie);


    }

    public void makeMovieSearch (String querySortby){
        URL movieUrlSearch = NetworkUtils.buildUrl(querySortby);

    }

        //TODO USE THIS METHOD TO CHECK IF CONNECTION IS AVAILABLE
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    @Override
    public Loader<MovieList> onCreateLoader(int id, final Bundle args) {
        return new AsyncTaskLoader<MovieList>(this) {

            MovieList movieJason;


            @Override
            public void deliverResult(MovieList movieList) {



                super.deliverResult(movieList);
            }

            @Override
            protected void onStartLoading() {
                super.onStartLoading();
            }

            @Override
            public void forceLoad() {
                super.forceLoad();
            }

            @Override
            public MovieList loadInBackground() {

                URL movieUrl = NetworkUtils.buildUrl(querySortby);
                try {
                    String responseQuery = NetworkUtils.getResponseFromHttpUrl(movieUrl);
                    movieJason= JsonUtils.jsonParsing(responseQuery);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return movieJason;
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<MovieList> loader, MovieList movieList) {


        this.movieList=movieList;
        adapterMovie= new AdapterMovie(this,this.movieList.getResults(),this);
        recyclerView.setAdapter(adapterMovie);

        adapterMovie.notifyDataSetChanged();




    }

    @Override
    public void onLoaderReset(Loader<MovieList> loader) {

    }

    @Override
    public void onClickListener(int position) {

    }






}
