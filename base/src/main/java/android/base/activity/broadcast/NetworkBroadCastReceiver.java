package android.base.activity.broadcast;

import android.base.constant.Constant;
import android.base.interfaces.ConnectivityListener;
import android.base.util.ApplicationUtils;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

/**
 * Created by amit on 15/2/17.
 */

public class NetworkBroadCastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        LocalBroadcastManager.getInstance(context)
                .sendBroadcast(new Intent(Constant.getActionBroadcastNetworkChanged()));
    }
}
