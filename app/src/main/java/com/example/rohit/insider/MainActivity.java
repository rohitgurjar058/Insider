package com.example.rohit.insider;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.example.rohit.insider.adapter.SongAdapter;
import com.example.rohit.insider.api.ApiClient;
import com.example.rohit.insider.api.ApiInterface;
import com.example.rohit.insider.model.ArtistModel;
import com.example.rohit.insider.model.SongModel;
import com.example.rohit.insider.util.CheckInternet;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements MaterialSearchBar.OnSearchActionListener{

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private SongAdapter adapter;
    private List<SongModel> songInner;
    ArtistModel song;
    private ApiInterface apiInterface;
    ArrayList<SongModel> results;
    MaterialSearchBar searchBar;
    TextView txt,txt_internet;
    CoordinatorLayout coordinatorLayout;
    Snackbar snackbar;
    private BroadcastReceiver broadcast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coordinatorLayout = (CoordinatorLayout)findViewById(R.id.cordinator_layout);
        txt = (TextView)findViewById(R.id.textView);
        txt_internet = (TextView)findViewById(R.id.textView1);
        recyclerView = (RecyclerView)findViewById(R.id.recycle);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        searchBar = (MaterialSearchBar) findViewById(R.id.searchBar);
        searchBar.setHint("Search by Artist");
        searchBar.enableSearch();
        searchBar.setOnSearchActionListener(this);

        checkInternetConnectivity();
    }

    private void checkInternetConnectivity() {

        IntentFilter intentfilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        broadcast = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int[] type = {ConnectivityManager.TYPE_WIFI,ConnectivityManager.TYPE_MOBILE};

                if(CheckInternet.isNetworkAvailable(context,type)==true){
                    return;
                }
                else
                {
                    Toast.makeText(MainActivity.this,"No Internet Connection",Toast.LENGTH_LONG).show();
                }
            }
        };

        registerReceiver(broadcast,intentfilter);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        unregisterReceiver(broadcast);
    }

    @Override
    public void onSearchStateChanged(boolean enabled) {

    }

    @Override
    public void onSearchConfirmed(CharSequence text) {

        txt.setVisibility(View.GONE);
        txt_internet.setVisibility(View.GONE);
        searchBar.disableSearch();
        ApiClient rfh = new ApiClient(MainActivity.this,text.toString());
        try {
            results = rfh.execute().get();
        }
        catch (Exception e) {
        }

        adapter = new SongAdapter(this,results);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onButtonClicked(int buttonCode) {

    }
}
