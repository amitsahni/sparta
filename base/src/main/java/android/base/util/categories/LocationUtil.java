package android.base.util.categories;

import android.base.util.ApplicationUtils;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import java.io.IOException;
import java.util.List;


/**
 * The type Location util.
 */
public class LocationUtil {

    /**
     * Gets the location by Coordinates
     *
     * @param context   the context
     * @param latitude  the latitude
     * @param longitude the longitude
     * @return Location model
     */
    public static LocationModel getLocationByCoordinates(Context context, Double latitude, Double longitude) {
        try {
            Geocoder geocoder;
            List<Address> addresses;
            geocoder = new Geocoder(context);
            addresses = geocoder.getFromLocation(latitude, longitude, 1);

            LocationModel locationModel = new LocationModel();
            locationModel.latitude = latitude;
            locationModel.longitude = longitude;
            try {
                locationModel.address = addresses.get(0).getAddressLine(0);
            } catch (Exception ex) {
                ApplicationUtils.Log.e("empty address");
            }
            try {
                locationModel.city = addresses.get(0).getAddressLine(1);
            } catch (Exception ex) {
                ApplicationUtils.Log.e("empty city");
            }
            try {
                locationModel.country = addresses.get(0).getAddressLine(2);
            } catch (Exception ex) {
                ApplicationUtils.Log.e("empty country");
            }

            return locationModel;
        } catch (IOException e) {
            ApplicationUtils.Log.e("empty location");
            return new LocationModel();
        }
    }

}
