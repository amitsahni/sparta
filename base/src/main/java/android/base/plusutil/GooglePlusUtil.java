package android.base.plusutil;

import android.base.activity.BaseAppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;


import java.util.List;

/**
 * Created by sidharath on 5/3/17.
 */
public class GooglePlusUtil implements GoogleApiClient.OnConnectionFailedListener{

    public enum GOOGLE_SIGNIN_REQUESTS {REQUEST_EMAIL,  REQUEST_PROFILE}
    private GoogleApiClient mGoogleApiClient;
    private GooglePlusListener mListener;
    private int mRequestCode;
    private static GooglePlusUtil mInstance;
    public static final int GOOGLE_SIGN_REQUEST_CODE = 1001;
    /**
     * GooglePlusListener with Callbacks methods based on the result of Google Sign in.
     */
    public interface GooglePlusListener{
        void onConnectionFailed(ConnectionResult connectionResult);
        void onGoogleSignInResult(GoogleSignInResult result);
        void onGoogleSignInFailed(GoogleSignInResult result);
    }

    /**
     * Private Constructor to restrict creating new instance outside this class.
     */
    private GooglePlusUtil(){}

    /**
     * Creates a new instance
     */
    static {
        mInstance = new GooglePlusUtil();
    }

    /**
     * Check if instance is null then create new instance else return the instance
     * @return
     */
    public static final GooglePlusUtil getInsance()
    {
        if(mInstance == null)

        {
            mInstance = new GooglePlusUtil();
        }
        return  mInstance;
    }
    /**
     * Used to configure google sign in.
     * @param signInRequestList
     */
    public void configureGoogleSignIn(BaseAppCompatActivity context, List<GOOGLE_SIGNIN_REQUESTS> signInRequestList, GooglePlusListener listener)
    {
         mListener = listener;
        GoogleSignInOptions.Builder builder= new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN);
                for(GOOGLE_SIGNIN_REQUESTS signin_requests : signInRequestList)
                {
                    if(signin_requests.name() == GOOGLE_SIGNIN_REQUESTS.REQUEST_EMAIL.name())
                    {
                        builder.requestEmail();
                    }else if(signin_requests.name() == GOOGLE_SIGNIN_REQUESTS.REQUEST_PROFILE.name())
                    {
                        builder.requestProfile();
                    }
                }
        GoogleSignInOptions gso = builder.build();
        createGoogleApiClient(context, gso);

    }

    /**
     * Creates Google API client with Auth.GOOGLE_SIGN_IN_API
     * @param context
     *
     * @param gso
     */
    private void createGoogleApiClient(BaseAppCompatActivity context, GoogleSignInOptions gso)
    {
        mGoogleApiClient = new GoogleApiClient.Builder(context)
                .enableAutoManage(context /* FragmentActivity */, this/* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    /**
     *  Used to call Sign in Intent using FragmentActivity Context
     * @param context
     * @param requestCode
     */
    public void signIn(Context context, int requestCode) {
        mRequestCode = requestCode;
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        ((BaseAppCompatActivity)context).startActivityForResult(signInIntent, requestCode);
    }

    /**
     * Returns the OnConnection Failed method of GooglePlusListener
     * @param connectionResult
     */
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        mListener.onConnectionFailed(connectionResult);
    }

    /**
     * R
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == mRequestCode) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                mListener.onGoogleSignInResult(result);

            }else{
                mListener.onGoogleSignInFailed(result);
            }
        }
    }
}
