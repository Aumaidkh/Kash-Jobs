package com.aumaid.jobskash.HelperClasses;

import android.content.Context;
import android.net.ConnectivityManager;

public class ConnectionChecker {

    public boolean isConnected(Context context){

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();

    }
}
