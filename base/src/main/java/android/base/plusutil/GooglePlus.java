package android.base.plusutil;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;

/**
 * Created by clickapps on 27/7/17.
 */

public class GooglePlus {
    private static volatile GoogleApiClient googleApiClient;

    public void connect(final Param param) {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .build();
        if (googleApiClient == null) {
            synchronized (GooglePlus.class) {
                if (googleApiClient == null) {
                    googleApiClient = new GoogleApiClient.Builder(param.context)
                            .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                                @Override
                                public void onConnected(@Nullable Bundle bundle) {
                                    bundle = bundle == null ? new Bundle() : bundle;
                                    if (param.listener != null)
                                        param.listener.onResult(bundle, param.type);
                                }

                                @Override
                                public void onConnectionSuspended(int i) {
                                    Bundle bundle = new Bundle();
                                    bundle.putInt("onConnectionSuspended", i);
                                    if (param.listener != null)
                                        param.listener.onResult(bundle, param.type);
                                }
                            })
                            .addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
                                @Override
                                public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                                    if (param.listener != null)
                                        param.listener.onConnectionFailed(connectionResult);
                                }
                            })
                            .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                            .build();
                }
            }
        }

    }

    public void handleEvent(final Param param) {
        switch (param.request) {
            case SIGN_IN:
                OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(googleApiClient);
                if (opr.isDone()) {
                    // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
                    // and the GoogleSignInResult will be available instantly.
                    GoogleSignInResult result = opr.get();
                    if (param.listener != null)
                        param.listener.onResult(result, param.type);
                } else {
                    // If the user has not previously signed in on this device or the sign-in has expired,
                    // this asynchronous branch will attempt to sign in the user silently.  Cross-device
                    // single sign-on will occur in this branch.
                    opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                        @Override
                        public void onResult(GoogleSignInResult googleSignInResult) {
                            if (param.listener != null)
                                param.listener.onResult(googleSignInResult, param.type);
                        }
                    });
                }
//                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
//                param.activity.startActivityForResult(signInIntent, GooglePlusConstant.REQUEST_CODE);
                break;
            case SIGN_OUT:
                Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(
                        new ResultCallback<Status>() {
                            @Override
                            public void onResult(Status status) {
                                if (param.listener != null)
                                    param.listener.onResult(status, param.type);
                            }
                        });
                break;
            case PROFILE:
                break;
            case REVOKE_ACCESS:
                Auth.GoogleSignInApi.revokeAccess(googleApiClient).setResultCallback(
                        new ResultCallback<Status>() {
                            @Override
                            public void onResult(Status status) {
                                if (param.listener != null)
                                    param.listener.onResult(status, param.type);
                            }
                        });
                break;
        }
    }

}
