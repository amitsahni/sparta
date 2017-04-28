package android.base.activity;

import android.app.Activity;
import android.base.R;
import android.base.util.ApplicationUtils;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.util.Log;


/**
 * The type Builder.
 */
public class Builder {

    private ActivityParam activityParam;

    /**
     * Instantiates a new Builder.
     *
     * @param context the context
     */
    public Builder(@NonNull Activity context) {
        activityParam = new ActivityParam();
        activityParam.context = context;
        defaultAnim();
    }

    /**
     * Instantiates a new Builder.
     *
     * @param context      the context
     * @param activityType the activity type
     */
    public Builder(@NonNull Activity context, ActivityParam.ActivityType activityType) {
        activityParam = new ActivityParam();
        activityParam.context = context;
        activityParam.activityType = activityType;
        defaultAnim();
    }

    /**
     * Bundle builder.
     *
     * @param bundle the bundle
     * @return the builder
     */
    public Builder bundle(Bundle bundle) {
        activityParam.bundle = bundle;
        return this;
    }

    /**
     * Klass builder.
     *
     * @param uri the uri
     * @return the builder
     */
    public Builder klass(Class<?> uri) {
        activityParam.uri = uri;
        return this;
    }

    /**
     * Enable animation builder.
     *
     * @param enableAnimation the enable animation
     * @return the builder
     */
    public Builder enableAnimation(boolean enableAnimation) {
        activityParam.enableAnimation = enableAnimation;
        return this;
    }

    /**
     * Activity type builder.
     *
     * @param activityType the activity type
     * @return the builder
     */
    public Builder activityType(@NonNull ActivityParam.ActivityType activityType) {
        activityParam.activityType = activityType;
        return this;
    }

    /**
     * Request code builder.
     *
     * @param requestCode the request code
     * @return the builder
     */
    public Builder requestCode(int requestCode) {
        activityParam.requestCode = requestCode;
        return this;
    }

    /**
     * if application OS is greater than Lollipop then its @TransitionRes
     * otherwise its @AnimRes
     *
     * @param enter the enter
     * @param exit  the exit
     * @return the builder
     */
    public Builder animation(int enter, int exit) {
        activityParam.enter = enter;
        activityParam.exit = exit;
        return this;
    }


    /**
     * Flag builder.
     *
     * @param flag the flag
     * @return the builder
     */
    public Builder flag(int flag) {
        activityParam.flag = flag;
        return this;
    }

    /**
     * Activity compact option builder.
     *
     * @param activityOptionsCompat the activity options compat
     * @return the builder
     */
    public Builder activityCompactOption(ActivityOptionsCompat activityOptionsCompat) {
        activityParam.activityOptionsCompat = activityOptionsCompat;
        return this;
    }

    private Builder defaultAnim() {
        activityParam.enter = R.anim.slide_in_left;
        activityParam.exit = R.anim.slide_out_left;
        return this;
    }

    /**
     * Build.
     */
    public void build() {
        if (activityParam.context == null) {
            Log.e(getClass().getSimpleName(), "Context is null");
            return;
        }
        ActivityManagerUtil.performTask(activityParam);
    }
}
