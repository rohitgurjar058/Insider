package com.example.rohit.insider.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by rohit on 3/7/17.
 */

public class ArtistModel {

    @SerializedName("results")
    private ArrayList<SongModel> results = new ArrayList<SongModel>();

    public ArrayList<SongModel> getResults() {
        return results;
    }
}
