package com.example.rohit.insider.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by rohit on 3/7/17.
 */

public class CheckInternet extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        int type[] = {ConnectivityManager.TYPE_WIFI,ConnectivityManager.TYPE_MOBILE};
        if(isNetworkAvailable(context,type)==true)
        {
            return;
        }
        else{
            Toast.makeText(context,"No internet",Toast.LENGTH_LONG).show();
        }
    }

    public static boolean isNetworkAvailable(Context context, int[] type) {

        try{
            ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            for(int typenetwork : type)
            {
                NetworkInfo info = cm.getNetworkInfo(typenetwork);
                if(info!=null && info.getState()== NetworkInfo.State.CONNECTED)
                {
                    return true;
                }
            }
        }
        catch(Exception e){
            return false;
        }
        return false;
    }
}
