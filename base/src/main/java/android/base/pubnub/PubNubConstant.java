package android.base.pubnub;

import android.base.application.BaseApplication;
import android.base.constant.Constant;

/**
 * The type Pub nub constant.
 */
public class PubNubConstant {

    /**
     * The constant PUBLISH_KEY.
     */
    public static final String PUBLISH_KEY = "pub-c-a8c927a2-17cb-401f-8f34-373866d32036";
    /**
     * The constant SUBSCRIBE_KEY.
     */
    public static final String SUBSCRIBE_KEY = "sub-c-7296ab88-35e2-11e5-a605-0619f8945a4f";
    /**
     * The constant SECRET_KEY.
     */
    public static final String SECRET_KEY = "sec-c-OWVmZmJlMWEtZjk4NC00NDJmLWE2MDEtYWFlNDcyMDhiYTM2";
    /**
     * The constant CIPHER_KEY.
     */
    public static final String CIPHER_KEY = "sec-c-OWVmZmJlMWEtZjk4NC00NDJmLWE2MDEtYWFlNDcyMDhiYTM2";
    /**
     * The constant ORIGIN.
     */
    public static final String ORIGIN = "origin";
    /**
     * The Broadcast
     */
    public static String BROADCAST = Constant.PACKAGE_NAME + ".pubnub";
    /**
     * The Broadcast Bundle Keys
     */
    public static final String BUNDLE_MESSAGE = "message";
    public static final String BUNDLE_CHANNEL = "channel";

    private PubNubConstant() {

    }
}
