package android.base.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import java.util.Map;


/**
 * <p>
 * This is SharedPreferences SingleTon class used to store small amount of data
 * in private mode.
 * </p>
 *
 * @author user amit.singh
 */
public class SharedPreferenceApp {
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;


    /**
     * Parameterized Constructor is called
     *
     * @param context the context
     */
    public SharedPreferenceApp(@NonNull Context context) {
        pref = context.getSharedPreferences(getClass().getSimpleName(), 0);
        editor = pref.edit();
    }

    /**
     * Default Constructor that make this class Singleton by taking this
     * Constructor as private
     */
    private SharedPreferenceApp() {
    }

    /**
     * <p>
     * This method used to store value of boolean type
     * </p>
     *
     * @param key   Unique Key for a value
     * @param value Value to be stored
     * @return the shared preference app
     */
    public void save(String key, boolean value) {
        editor.putBoolean(key, value).commit();
    }

    /**
     * <p>
     * This method used to store value of string type
     * </p>
     *
     * @param key   Unique Key for a value
     * @param value Value to be stored
     * @return the shared preference app
     */
    public void save(String key, String value) {
        editor.putString(key, value).commit();
    }

    /**
     * <p>
     * This method used to store value of integer type
     * </p>
     *
     * @param key   Unique Key for a value
     * @param value Value to be stored
     * @return the shared preference app
     */
    public void save(String key, int value) {
        editor.putInt(key, value).commit();
    }

    /**
     * <p>
     * This method used to store value of long type
     * </p>
     *
     * @param key   Unique Key for a value
     * @param value Value to be stored
     * @return the shared preference app
     */
    public void save(String key, long value) {
        editor.putLong(key, value).commit();
    }

    /**
     * <p>
     * This method used to store value of float type
     * </p>
     *
     * @param key   Unique Key for a value
     * @param value Value to be stored
     * @return the shared preference app
     */
    public void save(String key, float value) {
        editor.putFloat(key, value).commit();
    }

    /**
     * <p>
     * This will remove the specific value from the preference by passing its
     * key.
     * </p>
     *
     * @param key set key that value needs to be removed.
     * @return the shared preference app
     */
    public void remove(String key) {
        editor.remove(key).commit();
    }

    /**
     * This method will clear the whole preference.
     *
     * @return the shared preference app
     */
    public void clearPref() {
        editor.clear().commit();
    }

    /**
     * <p>
     * Here all values is to get String value from shared preferences.
     * </p>
     *
     * @param key      retrieve by unique key
     * @param defValue give here defaultValue if not found defalutValue is assigned
     * @return string string
     */
    public String getString(String key, String defValue) {
        return pref.getString(key, defValue);
    }

    /**
     * <p>
     * Here all values is to get int value from shared preferences.
     * </p>
     *
     * @param key      retrieve by unique key
     * @param defValue give here defaultValue if not found defalutValue is assigned
     * @return int int
     */
    public int getInt(String key, int defValue) {

        return pref.getInt(key, defValue);
    }

    /**
     * <p>
     * Here all values is to get boolean value from shared preferences.
     * </p>
     *
     * @param key      retrieve by unique key
     * @param defValue give here defaultValue if not found defalutValue is assigned
     * @return boolean boolean
     */
    public boolean getBoolean(String key, boolean defValue) {
        return pref.getBoolean(key, defValue);
    }

    /**
     * <p>
     * Here all values is to get long valued from shared preferences.
     * </p>
     *
     * @param key      retrieve by unique key
     * @param defValue give here defaultValue if not found defalutValue is assigned
     * @return long long
     */
    public long getLong(String key, long defValue) {
        return pref.getLong(key, defValue);
    }

    /**
     * <p>
     * Here all values is to get float value from shared preferences.
     * </p>
     *
     * @param key      retrieve by unique key
     * @param defValue give here defaultValue if not found defalutValue is assigned
     * @return float float
     */
    public float getFloat(String key, float defValue) {
        return pref.getFloat(key, defValue);
    }


//    public void commit() {
//        editor.commit();
//    }

    /**
     * Gets all.
     *
     * @return the all
     */
    public Map<String, ?> getAll() {
        return pref.getAll();
    }

    /**
     * Has preference boolean.
     *
     * @param key the key
     * @return the boolean
     */
    public boolean hasPreference(String key) {
        return pref.contains(key);
    }
}
