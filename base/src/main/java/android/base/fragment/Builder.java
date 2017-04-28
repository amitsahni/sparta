package android.base.fragment;

import android.app.Fragment;
import android.base.R;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.util.Log;


/**
 * The type Builder.
 */
public class Builder {
    private FragParam mFragParam;

    /**
     * Instantiates a new Builder.
     *
     * @param context   the context
     * @param replaceId the replace id
     */
    public Builder(@NonNull FragmentActivity context, @IdRes int replaceId) {
        mFragParam = new FragParam();
        mFragParam.context = context;
        mFragParam.replaceId = replaceId;
    }

    /**
     * Fragment builder.
     *
     * @param fragment the fragment
     * @return the builder
     */
    public Builder fragment(@NonNull Fragment fragment) {
        mFragParam.fragment = fragment;
        return this;
    }

    /**
     * Tag builder.
     *
     * @param tag the tag
     * @return the builder
     */
    public Builder tag(@NonNull String tag) {
        mFragParam.tag = tag;
        return this;
    }

    /**
     * Enable animation builder.
     *
     * @param enableAnimation the enable animation
     * @return the builder
     */
    public Builder enableAnimation(boolean enableAnimation) {
        mFragParam.enableAnimation = enableAnimation;
        return this;
    }

    /**
     * Type builder.
     *
     * @param fragType the frag type
     * @return the builder
     */
    public Builder type(@NonNull FragParam.FragType fragType) {
        mFragParam.fragType = fragType;
        return this;
    }

    /**
     * Back stack builder.
     *
     * @param isBackStack the is back stack
     * @return the builder
     */
    public Builder backStack(boolean isBackStack) {
        mFragParam.isBackStack = isBackStack;
        return this;
    }

    /**
     * Animation builder.
     *
     * @param enter    the enter
     * @param exit     the exit
     * @param popEnter the pop enter
     * @param popExit  the pop exit
     * @return the builder
     */
    public Builder animation(int enter, int exit, int popEnter, int popExit) {
        mFragParam.enter = enter;
        mFragParam.exit = exit;
        mFragParam.popEnter = popEnter;
        mFragParam.popExit = popExit;
        return this;
    }

    private Builder defaultAnim() {
        mFragParam.enter = R.anim.slide_in_right;
        mFragParam.exit = R.anim.slide_out_right;
        mFragParam.popEnter = R.anim.slide_in_left;
        mFragParam.popExit = R.anim.slide_out_left;
        return this;
    }

    /**
     * Build.
     */
    public void build() {
        if (mFragParam.context == null) {
            Log.e(getClass().getSimpleName(), "Context is null");
            return;
        }
        FragmentManagerUtil.performTask(mFragParam);
    }
}
