package com.example.rohit.insider.model;

import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by rohit on 3/7/17.
 */

public class SongModel {

    @SerializedName("artistName")
    private String artistName;

    @SerializedName("collectionName")
    private String collectionName;

    @SerializedName("trackName")
    private String trackName;

    @SerializedName("trackViewUrl")
    private String trackViewUrl;

    @SerializedName("artworkUrl30")
    private String artworkUrl30;

    @SerializedName("artworkUrl60")
    private String artworkUrl60;

    @SerializedName("artworkUrl100")
    private String artworkUrl100;

    @SerializedName("trackPrice")
    private Double trackPrice;

    @SerializedName("releaseDate")
    private String releaseDate;

    @SerializedName("trackCount")
    private Integer trackCount;

    public String getPrimaryGenreName() {
        return primaryGenreName;
    }

    @SerializedName("trackTimeMillis")
    private Integer trackTimeMillis;

    @SerializedName("primaryGenreName")
    private String primaryGenreName;

    public String getArtistName() {
        return artistName;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public String getTrackName() {
        return trackName;
    }

    public String getTrackViewUrl() {
        return trackViewUrl;
    }

    public String getArtworkUrl30() {
        return artworkUrl30;
    }

    public String getArtworkUrl60() {
        return artworkUrl60;
    }

    public String getArtworkUrl100() {
        return artworkUrl100;
    }

    public Double getTrackPrice() {
        return trackPrice;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public Integer getTrackCount() {
        return trackCount;
    }

    public Integer getTrackTimeMillis() {
        return trackTimeMillis;
    }

}
