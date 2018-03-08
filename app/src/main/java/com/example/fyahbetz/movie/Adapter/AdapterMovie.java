package com.example.fyahbetz.movie.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.fyahbetz.movie.Model.Movie;
import com.example.fyahbetz.movie.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by fyahbetz on 08/03/18.
 */

public class AdapterMovie extends RecyclerView.Adapter<AdapterMovie.Holder> {

    private final static String TMDB_BASE_URL = "https://api.themoviedb.org/3/movie/";
    private final Context context;
    private ArrayList<Movie> movies;
    private final MovieListenerHandler movieListenerHandler;

    public AdapterMovie(Context context, ArrayList<Movie> movies, MovieListenerHandler movieListenerHandler) {
        this.context = context;
        this.movies = movies;
        this.movieListenerHandler = movieListenerHandler;
    }

    public interface MovieListenerHandler{
        void onClickListener(int position);
    }



    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_list, parent , false );

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        holder.bindPicasso(position);
        String path = TMDB_BASE_URL.concat(movies.get(position).getPoster_path());
        Log.d("home", path);

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class Holder extends RecyclerView.ViewHolder implements RecyclerView.OnClickListener {

        final ImageView imageView;


        public Holder(View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.poster_image);
            imageView.setOnClickListener(this);
        }

        public void bindPicasso(int position){
           // Picasso.with(context).load(movies.get(position).getPoster_path()).into(imageView);
            Picasso.with(context).load(TMDB_BASE_URL.concat(movies.get(position).getPoster_path()))
                    .resize(20,30)
                    .into(imageView);
            String path = TMDB_BASE_URL.concat(movies.get(position).getPoster_path());
            Log.d("home", path);



        }

        @Override
        public void onClick(View view) {
            int clickPos = getAdapterPosition();
            movieListenerHandler.onClickListener(clickPos);

        }
    }


}
