package com.aumaid.jobskash.HelperClasses;

import android.content.Context;
import android.net.ConnectivityManager;

public class InternetChecker {

    public boolean isConnected(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getActiveNetworkInfo()!=null && connectivityManager.getActiveNetworkInfo().isConnected()){
            return true;
        }
        return false;
    }

}
