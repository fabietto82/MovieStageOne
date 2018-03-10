package com.example.fyahbetz.movie.NetworkUtils;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by fyahbetz on 03/03/18.
 */

public class NetworkUtils {

    private static final String BASEURL = "http://api.themoviedb.org/3/movie";
    // put your apikey number down here..... API_KEY="6363635273....."
    private static final String API_KEY="c311b943d7935a85908ca4ab78555beb";
    private static final String PARAM_API_QUERY="api_key";
    //private static final String TOTAL_URL="http://api.themoviedb.org/3/movie/popular?api_key=";

    private static final String BASE_URL="ttp://api.themoviedb.org/3/movie/";
    private static final String API_KEY_FULL ="api_key=";


    public static URL buildUrl(String sortby){

        Uri builtUri = Uri.parse(BASEURL).buildUpon()
                .appendPath(sortby)
                .appendQueryParameter(PARAM_API_QUERY ,API_KEY)
                .build();

        //Uri builtUri = Uri.parse(BASE_URL +  sortby + "?" + API_KEY_FULL ).buildUpon().build();


        URL url = null;
        try {
            url=new URL(builtUri.toString());
            Log.v(url.toString() , "check url");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;


    }
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }





}
