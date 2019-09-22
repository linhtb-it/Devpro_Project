package com.example.watchnow_project.Adapter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.example.watchnow_project.R;

public class InternetConnection {
    public static boolean ConnectionForInternet(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo == null){
            Toast.makeText(context, R.string.no_internet,Toast.LENGTH_LONG).show();
            return false;
        }else if(!networkInfo.isConnected()){
            Toast.makeText(context,R.string.no_internet,Toast.LENGTH_LONG).show();
            return false;
        }else if(!networkInfo.isAvailable()){
            Toast.makeText(context, R.string.no_internet,Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}

