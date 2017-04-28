package android.base.activity.broadcast;

import android.base.interfaces.ConnectivityListener;
import android.base.util.ApplicationUtils;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by amit on 20/2/17.
 */

public class InternetBroadCastReceiver extends BroadcastReceiver {
    public ConnectivityListener connectivityListener;

    public void addCallback(ConnectivityListener listener) {
        this.connectivityListener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        boolean isConnected = ApplicationUtils.System.isInternetConnected(context);
        if (connectivityListener != null) {
            connectivityListener.onConnectivityChange(isConnected);
        }
    }
}
