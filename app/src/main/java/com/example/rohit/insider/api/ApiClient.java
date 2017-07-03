package com.example.rohit.insider.api;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.rohit.insider.model.ArtistModel;
import com.example.rohit.insider.model.SongModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rohit on 3/7/17.
 */

public class ApiClient extends AsyncTask<String,Void,ArrayList<SongModel>> {

    final static String BASE_URL = "https://itunes.apple.com/";
    String query;
    Context ctx;
    ProgressDialog progress;

    public ApiClient(Context ctx,String search) {
        query = search;
        this.ctx = ctx;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progress = new ProgressDialog(ctx);
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
    }

    @Override
    protected void onPostExecute(ArrayList<SongModel> songInners) {
        super.onPostExecute(songInners);
        progress.dismiss();
    }

    @Override
    protected ArrayList<SongModel> doInBackground(String... params) {
        ArrayList<SongModel> response;
        ArtistModel results;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface rfItunes = retrofit.create(ApiInterface.class);

        Call<ArtistModel> query = rfItunes.getSongs(this.query);

        try {
            results = query.execute().body();

            Log.d("result",results.toString());
            if (results.getResults().size()>0)
                response = results.getResults();
            else
                response = new ArrayList<>();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            response =  new ArrayList<>();
        }
        return response;
    }
}
