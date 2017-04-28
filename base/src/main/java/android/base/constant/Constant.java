package android.base.constant;


/**
 * The type Constant.
 */
public class Constant {

    public static String PACKAGE_NAME = "";
    public static String ARABIC_LANG_CODE = "ar";
    /**
     * External storage permission string
     */
    public static final String EXTERNAL_STORAGE_PERMISSION = "android.permission.WRITE_EXTERNAL_STORAGE";

    private static final String ACTION_BROADCAST_NETWORK_CHANGED = ".android.base.util.CONNECTIVITY_CHANGE";
    /**
     * The constant ACTION_BROADCAST_LANGUAGE_CHANGED.
     */
    private static final String ACTION_BROADCAST_LANGUAGE_CHANGED = ".android.base.util.LanguageChanged";
    /**
     * The constant BUILD_VERSION_LOLLIPOP.
     */
    public static final int BUILD_VERSION_LOLLIPOP = 21;
    /**
     * The constant BUILD_VERSION_KITKAT.
     */
    public static final int BUILD_VERSION_KITKAT = 19;
    /**
     * The constant BUILD_VERSION_JELLY_BEAN_MR1.
     */
    public static final int BUILD_VERSION_JELLY_BEAN_MR1 = 17;
    /**
     * The constant BUILD_VERSION_ICE_CREAM_SANDWICH.
     */
    public static final int BUILD_VERSION_ICE_CREAM_SANDWICH = 14;

    private Constant() {
        // private constructor
    }

    public static String getActionBroadcastNetworkChanged() {
        return PACKAGE_NAME + ACTION_BROADCAST_NETWORK_CHANGED;
    }

    public static String getActionBroadcastLanguageChanged() {
        return PACKAGE_NAME + ACTION_BROADCAST_LANGUAGE_CHANGED;
    }
}