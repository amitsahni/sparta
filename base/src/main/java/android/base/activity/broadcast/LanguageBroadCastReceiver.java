package android.base.activity.broadcast;

import android.app.Activity;
import android.base.activity.BaseAppCompatActivity;
import android.base.interfaces.OnRequestHandleListener;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

/**
 * Created by clickapps on 24/2/16.
 * <p class="note">Language change Broadcast is included in this {@link LanguageBroadCastReceiver}.
 * Whenever language changes this broadcast hits and result will pass on to listener {@link OnRequestHandleListener} if having
 * and also to {@link BaseAppCompatActivity}
 * </p>
 */
public class LanguageBroadCastReceiver extends BroadcastReceiver {
    private Activity mActivity;

    /**
     * Instantiates a new Language broad cast receiver.
     *
     * @param activity the activity
     */
    public LanguageBroadCastReceiver(@NonNull Activity activity) {
        this.mActivity = activity;
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        if (mActivity instanceof BaseAppCompatActivity) {
            BaseAppCompatActivity activity = (BaseAppCompatActivity) mActivity;
            activity.recreate();
        }
    }

}
