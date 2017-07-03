package com.example.rohit.insider.api;

import com.example.rohit.insider.model.ArtistModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by rohit on 3/7/17.
 */

  public interface ApiInterface {

        @GET("search")
        Call<ArtistModel> getSongs(@Query("term") String search);
  }
