package android.base.util.categories;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

/**
 * Created by amit on 14/12/16.
 */

public class PermissionUtils {

    public static void requestLocationPermission(@NonNull Activity activity, int requestCode) {
        String[] permissions = new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION};
        if(!verifyPermissions(activity,permissions)) {
            ActivityCompat.requestPermissions(activity, permissions, requestCode);
        }
    }

    private static String[] requestLocationPermission() {
        String[] permissions;
        permissions = new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION};
        return permissions;
    }

    public static void requestAllPermission(@NonNull Activity activity, @NonNull String[] permissions, int requestCode) {
        ActivityCompat.requestPermissions(activity, permissions, requestCode);
    }

    /**
     * Check that all given permissions have been granted by verifying that each entry in the
     * given array is of the value {@link PackageManager#PERMISSION_GRANTED}.
     *
     * @see Activity#onRequestPermissionsResult(int, String[], int[])
     */
    public static boolean verifyPermissions(@NonNull Activity activity, String[] permissions) {
        // Verify that each required permission has been granted, otherwise return false.
        for (String permission : permissions) {
            if (ActivityCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }
}
