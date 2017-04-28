package android.base.application;

import android.app.Activity;
import android.app.Application;
import android.base.activity.ActivityManagerUtil;
import android.base.activity.broadcast.InternetBroadCastReceiver;
import android.base.activity.broadcast.NetworkBroadCastReceiver;
import android.base.constant.Constant;
import android.base.http.WebConstant;
import android.base.interfaces.ConnectivityListener;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDex;
import android.support.v4.content.LocalBroadcastManager;


/**
 * The type Base application.
 */
public class BaseApplication extends Application implements Application.ActivityLifecycleCallbacks {
    private InternetBroadCastReceiver internetBroadCastReceiver;
    private IntentFilter filter = null;

    @Override
    public void onCreate() {
        super.onCreate();
        internetBroadCastReceiver = new InternetBroadCastReceiver();
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        // Nothing used
    }

    @Override
    public void onActivityStarted(Activity activity) {
        // Nothing used
    }

    @Override
    public void onActivityResumed(Activity activity) {
        ActivityManagerUtil.setTopActivity(activity);
        LocalBroadcastManager.getInstance(activity).registerReceiver(internetBroadCastReceiver, getFilter());
    }

    @Override
    public void onActivityPaused(Activity activity) {
        // Nothing used
        LocalBroadcastManager.getInstance(activity).unregisterReceiver(internetBroadCastReceiver);
    }

    @Override
    public void onActivityStopped(Activity activity) {
        // Nothing used
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        // Nothing used
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        // Nothing used
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
        registerActivityLifecycleCallbacks(this);
    }

    public void setPackageName(@NonNull String packageName) {
        Constant.PACKAGE_NAME = packageName;
    }

    public void setEndPointBaseUrl(@NonNull String baseUrl) {
        WebConstant.setBaseUrl(baseUrl);
    }

    public InternetBroadCastReceiver getInternetBroadCastReceiver() {
        return internetBroadCastReceiver;
    }

    public IntentFilter getFilter() {
        if (filter == null) {
            filter = new IntentFilter(Constant.getActionBroadcastNetworkChanged());
        }
        return filter;
    }
}
