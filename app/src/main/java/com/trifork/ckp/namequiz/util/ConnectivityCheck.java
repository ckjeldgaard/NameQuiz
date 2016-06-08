package com.trifork.ckp.namequiz.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectivityCheck {

    private final Context context;

    public ConnectivityCheck(Context context) {
        this.context = context;
    }

    public boolean isOnline() {
        final ConnectivityManager cm = (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return (netInfo != null && netInfo.isConnectedOrConnecting());
    }
}
