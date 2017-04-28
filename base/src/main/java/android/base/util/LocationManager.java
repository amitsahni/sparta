package android.base.util;

import android.Manifest;
import android.app.Activity;
import android.base.util.categories.PermissionUtils;
import android.content.Context;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;

/**
 * Created by amit on 13/12/16.
 */

public class LocationManager implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    // Location updates intervals in sec
    private static int UPDATE_INTERVAL = 1 * 10 * 1000; // 10 sec
    private static int FATEST_INTERVAL = 5 * 1000; // 5 sec
    private static int DISPLACEMENT = 10; // 10 meters
    public static final int REQUEST_LOCATION = 1002;
    public static final int LOCATION_REQUEST_CODE = 2123;
    private LocationRequest locationRequest;
    private GoogleApiClient googleApiClient;
    private Context context;
    private LocationCallback callback;
    private boolean isPopUpAlreadyShown = false;

    public LocationManager(@NonNull Activity context, @NonNull LocationCallback callback) {
        this.context = context;
        this.callback = callback;
        buildGoogleApiClient(context);
    }

    /**
     * Builds a GoogleApiClient. Uses the addApi() method to request the LocationServices API.
     */
    private synchronized void buildGoogleApiClient(Context context) {
        if (googleApiClient == null) {
            googleApiClient = new GoogleApiClient.Builder(context)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
            googleApiClient.connect();
        }
    }

    public void locationConnect(@NonNull Context context) {
        locationRequest = locationChecker(googleApiClient, context);
        if (callback != null) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            try {
                LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, callback, Looper.myLooper());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void disconnect() {
        if (googleApiClient != null && googleApiClient.isConnected()) {
            googleApiClient.disconnect();
        }
    }

    public void onCancelLocationPopUp() {
        isPopUpAlreadyShown = false;
    }


    /**
     * Prompt user to enable GPS and Location Services
     *
     * @param mGoogleApiClient
     */
    private LocationRequest locationChecker(GoogleApiClient mGoogleApiClient, @NonNull final Context context) {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(UPDATE_INTERVAL);
        locationRequest.setFastestInterval(FATEST_INTERVAL);
//        locationRequest.setSmallestDisplacement(DISPLACEMENT);
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);
        PendingResult<LocationSettingsResult> result =
                LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient, builder.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult result) {
                final Status status = result.getStatus();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        // All location settings are satisfied. The client can initialize location
                        // requests here.
                        isPopUpAlreadyShown = false;
                        if (context instanceof Activity) {
                            PermissionUtils.requestLocationPermission((Activity) context, REQUEST_LOCATION);
                        }
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        // Location settings are not satisfied. But could be fixed by showing the user
                        // a dialog.
                        try {
                            // Show the dialog by calling startResolutionForResult(),
                            // and check the result in onActivityResult().
                            if (!isPopUpAlreadyShown
                                    && context instanceof Activity) {
                                status.startResolutionForResult(
                                        (Activity) context, LOCATION_REQUEST_CODE);
                            }
                            isPopUpAlreadyShown = true;
                        } catch (IntentSender.SendIntentException e) {
                            // Ignore the error.
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        // Location settings are not satisfied. However, we have no way to fix the
                        // settings so we won't show the dialog.
                        break;
                }
            }
        });
        return locationRequest;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locationConnect(context);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }
}
