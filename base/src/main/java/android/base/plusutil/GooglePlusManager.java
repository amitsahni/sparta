package android.base.plusutil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;

/**
 * Created by clickapps on 27/7/17.
 */

public class GooglePlusManager {

    private static volatile GooglePlusManager sGooglePlusManager;

    private GooglePlusManager() {

    }

    /**
     * Get pub nub manager.
     *
     * @return the pub nub manager
     */
    public static GooglePlusManager get() {
        if (sGooglePlusManager == null) {
            synchronized (GooglePlusManager.class) {
                if (sGooglePlusManager == null) {
                    sGooglePlusManager = new GooglePlusManager();
                }
            }
        }
        return sGooglePlusManager;
    }

    public GoogleSignInResult OnActivityResult(@NonNull Intent data) {
        return Auth.GoogleSignInApi.getSignInResultFromIntent(data);
    }

    /**
     * With builder.
     *
     * @param context the context
     * @return the builder
     */
    public static Builder with(@NonNull Context context) {
        return new Builder(context);
    }

    /**
     * With builder.
     *
     * @param context the context
     * @return the builder
     */
    public static Builder with(@NonNull Activity context) {
        return new Builder(context);
    }
}
