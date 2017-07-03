package com.example.rohit.insider;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView img_art;
    TextView artist_name,collection_name,track_name,track_count,track_price,track_time,release_date;
    String img_url,artistName,CollectionName,trackName,releaseDate,trackCount,trackTime,trackPrice,track_url;
    Button detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        img_art = (ImageView)findViewById(R.id.art_img);
        artist_name = (TextView)findViewById(R.id.artist_name);
        collection_name = (TextView)findViewById(R.id.collection_name);
        track_count = (TextView)findViewById(R.id.track_count);
        track_price = (TextView)findViewById(R.id.track_price);
        track_time = (TextView)findViewById(R.id.track_time);
        release_date = (TextView)findViewById(R.id.release_date);
        track_name = (TextView)findViewById(R.id.track_name);
        detail = (Button)findViewById(R.id.detail);
        detail.setOnClickListener(this);

        img_url = getIntent().getStringExtra("img_url");
        artistName = getIntent().getStringExtra("artistName");
        CollectionName = getIntent().getStringExtra("collectionName");
        trackName = getIntent().getStringExtra("trackName");
        trackPrice = getIntent().getStringExtra("trackPrice");
        trackCount = getIntent().getStringExtra("trackCount");
        trackTime = getIntent().getStringExtra("trackTime");
        releaseDate = getIntent().getStringExtra("releaseDate");
        track_url = getIntent().getStringExtra("track_url");

        Picasso.with(this)
                .load(img_url)
                .fit()
                .placeholder(R.drawable.artist_pic)
                .error(R.drawable.artist_pic)
                .into(img_art);

        artist_name.setText("Artist Name   :   "+artistName);
        collection_name.setText("Collection Name   :   "+CollectionName);
        track_name.setText("Track Name   :   "+trackName);
        track_price.setText("Track Price   :   "+"\u0024"+trackPrice);
        track_count.setText("Track Count  :   "+trackCount);
        track_time.setText("Track Time   :   "+trackTime);
        release_date.setText("Release Date   :   "+releaseDate);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.detail : Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(track_url));
                startActivity(intent);
                break;
        }
    }
}
