package android.base.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;


/**
 * The type Activity param.
 */
public class ActivityParam {

    /**
     * The Context.
     */
    protected Activity context;
    /**
     * The Uri.
     */
    protected Class<?> uri;
    /**
     * The Enter.
     */
    protected int enter = 0;
    /**
     * The Exit.
     */
    protected int exit = 0;
    /**
     * The Request code.
     */
    protected int requestCode = 0;
    /**
     * The Flag.
     */
    protected int flag = 0;
    /**
     * The Activity type.
     */
    protected ActivityType activityType = ActivityType.START;
    /**
     * The Bundle.
     */
    protected Bundle bundle;
    /**
     * The Activity options compat.
     */
    protected ActivityOptionsCompat activityOptionsCompat;

    /**
     * The Enable animation.
     */
    protected boolean enableAnimation = false;
    /**
     * The Is back stack.
     */
    protected boolean isBackStack = false;

    /**
     * The enum Activity type.
     */
    public enum ActivityType {
        /**
         * Start activity type.
         */
        START, /**
         * Start result activity type.
         */
        START_RESULT, /**
         * Finish activity type.
         */
        FINISH, /**
         * Start finish activity type.
         */
        START_FINISH, /**
         * Start result finish activity type.
         */
        START_RESULT_FINISH
    }
}
