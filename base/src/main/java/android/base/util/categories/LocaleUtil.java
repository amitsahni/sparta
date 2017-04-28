package android.base.util.categories;

import android.app.Activity;
import android.base.R;
import android.base.constant.Constant;
import android.base.util.ApplicationUtils;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.LayoutDirection;
import android.view.View;

import org.apache.commons.lang3.LocaleUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;


/**
 * The type Locale utils.
 */
public class LocaleUtil extends LocaleUtils {

    /**
     * Gets locale application.
     *
     * @param context the context
     * @return the locale application
     */
    public static String getLocaleApplication(Context context) {
        return context.getResources().getConfiguration().locale.toString();
    }

    /**
     * Gets locale current app.
     *
     * @param context the context
     * @return the locale current app
     */
    public static Locale getLocaleCurrentApp(Context context) {
        return context.getResources().getConfiguration().locale;
    }

    /**
     * Gets phone number.
     *
     * @param context the context
     * @return the phone number
     */
    public static String getPhoneNumber(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String countryCodeValue = tm.getSimCountryIso();
        if (!ValidatorUtil.isEmptyOrNull(countryCodeValue)) {
            countryCodeValue = countryCodeValue.toUpperCase();
            String[] rl = context.getResources().getStringArray(R.array.CountryCodes);
            for (int i = 0; i < rl.length; i++) {
                String[] g = rl[i].split(",");
                if (g[1].trim().equals(countryCodeValue.trim())) {
                    countryCodeValue = g[0];
                    countryCodeValue = "+" + countryCodeValue;
                    break;
                }
            }
        }

        return countryCodeValue;
    }

    /**
     * Update locale.
     *
     * @param context      the context
     * @param languageCode the language code
     */
    public static void updateLocale(@Nullable Context context, @NonNull String languageCode) {
        updateLocale(context, languageCode, false);
    }

    /**
     * Update locale.
     *
     * @param context         the context
     * @param languageCode    the language code
     * @param enableBroadCast the enable broad cast
     */
    public static void updateLocale(@Nullable Context context,
                                    @NonNull String languageCode, boolean enableBroadCast) {
        if (context != null
                && !ValidatorUtil.isEmptyOrNull(languageCode)) {
            Locale locale = new Locale(languageCode);
            Resources res = context.getResources();
            Locale.setDefault(locale);
            Resources resources = context.getResources();
            Configuration config = resources.getConfiguration();
            config.setLocale(locale);
            config.setLayoutDirection(locale);
            res.updateConfiguration(config, resources.getDisplayMetrics());
            if (enableBroadCast)
                LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(Constant.getActionBroadcastLanguageChanged()));
        }
    }

    public static void changeForceView(@NonNull Activity activity, @NonNull Locale locale) {
        if (ApplicationUtils.Validator.isMatches(locale.getLanguage(), "ar")) {
            activity.getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        } else {
            activity.getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        }
    }

    public static String getLocaleDevice() {
        return new Locale(getLocaleAndroidOS("persist.sys.language"),
                getLocaleAndroidOS("persist.sys.country")).getDefault().toString().split("_", 2)[0];
    }

    /**
     * We can also use Locale.getDefault().toString();
     * <ahref http://stackoverflow.com/questions/23348954/android-get-device-locale></ahref>
     * <ahref http://stackoverflow.com/questions/4212320/get-the-current-language-in-device</ahref>
     */
    private static String getLocaleAndroidOS(String name) {
        try {
            Class<?> systemProperties = Class.forName("android.os.SystemProperties");
            try {
                Method get = systemProperties.getMethod("get", String.class, String.class);
                if (get == null) {
                    return "Failure!?";
                }
                try {
                    return (String) get.invoke(systemProperties, name, "");
                } catch (IllegalAccessException e) {
                    return "IllegalAccessException";
                } catch (IllegalArgumentException e) {
                    return "IllegalArgumentException";
                } catch (InvocationTargetException e) {
                    return "InvocationTargetException";
                }
            } catch (NoSuchMethodException e) {
                return "SystemProperties.get(String key, String def) method is not found";
            }
        } catch (ClassNotFoundException e) {
            return "SystemProperties class is not found";
        }

    }
}
