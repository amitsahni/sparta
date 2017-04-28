package android.base.util.categories;

import android.base.preferences.SharedPreferenceApp;
import android.content.Context;

/**
 * Created by cesarferreira on 9/26/14.
 */
public class PrefUtil {

    private static volatile SharedPreferenceApp sharedPreferenceApp;


    /**
     * Instantiates a new PrefUtil.
     */
    protected PrefUtil() {
       // Constructor
    }

    /**
     * Get pub nub manager.
     *
     * @return the pub nub manager
     */
    private static SharedPreferenceApp get(Context context) {
        if (sharedPreferenceApp == null) {
            synchronized (SharedPreferenceApp.class) {
                if (sharedPreferenceApp == null) {
                    sharedPreferenceApp = new SharedPreferenceApp(context);
                }
            }
        }
        return sharedPreferenceApp;
    }

    /**
     * With prefs.
     *
     * @param context the context
     * @return the prefs
     */
    public static SharedPreferenceApp with(Context context) {
        return get(context);
    }
}

