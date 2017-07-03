package com.example.rohit.insider.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rohit.insider.DetailActivity;
import com.example.rohit.insider.MainActivity;
import com.example.rohit.insider.R;
import com.example.rohit.insider.model.SongModel;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by rohit on 3/7/17.
 */

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.Holder>{

    private List<SongModel> songs;
    Context context;

    public SongAdapter(MainActivity context, ArrayList<SongModel> songs)
    {
        this.context = context;
        this.songs = songs;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item,parent,false);
        return new Holder(view);
    }

    private int lastPosition = -1;

    @Override
    public void onBindViewHolder(Holder holder, final int position) {

        int dur,cnt;
        double prc;
        String str_dur="",str_price="",str_cnt="";
        try {

            dur = songs.get(position).getTrackTimeMillis() / 1000;
            str_dur = ConvertSecondToHHMMString(dur);

            prc = songs.get(position).getTrackPrice();
            str_price = String.valueOf(prc);

            cnt = songs.get(position).getTrackCount();
            str_cnt = String.valueOf(cnt);
        }
        catch(Exception e){}
        holder.a_name.setText(songs.get(position).getArtistName()+" ");
        holder.t_name.setText(songs.get(position).getTrackName());
        holder.genre_name.setText(songs.get(position).getPrimaryGenreName());
        holder.duration.setText(str_dur.toString());
        holder.price.setText("\u0024"+str_price.toString());

        Picasso.with(context)
                .load(songs.get(position).getArtworkUrl60())
                .placeholder(R.drawable.artist_pic)
                .error(R.drawable.artist_pic)
                .into(holder.artwork_img);

        final String finalStr_price = str_price;
        final String finalStr_cnt = str_cnt;
        final String finalStr_dur = str_dur;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(context, DetailActivity.class);
                in.putExtra("img_url",songs.get(position).getArtworkUrl100());
                in.putExtra("artistName",songs.get(position).getArtistName());
                in.putExtra("collectionName",songs.get(position).getCollectionName());
                in.putExtra("trackName",songs.get(position).getTrackName());
                in.putExtra("trackCount", finalStr_cnt);
                in.putExtra("trackPrice", finalStr_price);
                in.putExtra("trackTime", finalStr_dur);
                in.putExtra("releaseDate",songs.get(position).getReleaseDate());
                in.putExtra("track_url",songs.get(position).getTrackViewUrl());
                context.startActivity(in);
            }
        });

        Animation animation = AnimationUtils.loadAnimation(context, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        holder.itemView.startAnimation(animation);
        lastPosition = position;
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        TextView a_name,t_name,genre_name,duration,price;
        ImageView artwork_img;

        public Holder(View itemView) {
            super(itemView);

            a_name = (TextView)itemView.findViewById(R.id.artist_name);
            t_name = (TextView)itemView.findViewById(R.id.track_name);
            genre_name = (TextView)itemView.findViewById(R.id.genre_name);
            duration = (TextView)itemView.findViewById(R.id.duration);
            price = (TextView)itemView.findViewById(R.id.price);
            artwork_img = (ImageView)itemView.findViewById(R.id.artwork_img);
        }
    }

    private String ConvertSecondToHHMMString(int secondtTime)
    {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        SimpleDateFormat df = new SimpleDateFormat("mm:ss");
        df.setTimeZone(tz);
        String time = df.format(new Date(secondtTime*1000L));
        return time;

    }
}
