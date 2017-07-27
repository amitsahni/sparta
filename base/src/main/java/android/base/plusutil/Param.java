package android.base.plusutil;

import android.app.Activity;
import android.content.Context;

import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;

/**
 * Created by clickapps on 27/7/17.
 */

public class Param {

    protected Context context;

    protected Activity activity;

    protected GooglePlusListener listener;

    protected Request request = Request.SIGN_IN;

    protected int type;

    public enum Request {
        SIGN_IN,
        PROFILE,
        SIGN_OUT,
        REVOKE_ACCESS
    }

    /**
     * GooglePlusListener with Callbacks methods based on the result of Google Sign in.
     */
    public interface GooglePlusListener {
        void onConnectionFailed(ConnectionResult connectionResult);

        <T> void onResult(T result, int type);
    }
}
