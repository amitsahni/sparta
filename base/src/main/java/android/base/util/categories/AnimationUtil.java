package android.base.util.categories;

import android.support.annotation.AnimRes;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationUtils;


/**
 * The type Animation util.
 */
public class AnimationUtil {

    final static long ANIM_DURATION_MILLIS = 400L;

    /**
     * Instantiates a new Animation util.
     */
    protected AnimationUtil() {
    }

    /**
     * Make a View Blink for a desired duration
     *
     * @param view     mView that will be animated
     * @param duration for how long in ms will it blink
     * @param offset   start offset of the animation
     * @return returns the same mView with animation properties
     */
    public static View blink(View view, int duration, int offset) {
        android.view.animation.Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(duration);
        anim.setStartOffset(offset);
        anim.setRepeatMode(android.view.animation.Animation.REVERSE);
        anim.setRepeatCount(android.view.animation.Animation.INFINITE);
        view.startAnimation(anim);
        return view;
    }

    /**
     * Animate.
     *
     * @param view the mView
     * @param anim the anim
     */
    public static void animate(View view, @AnimRes int anim) {
        android.view.animation.Animation animation =
                AnimationUtils.loadAnimation(view.getContext(), anim);
        view.startAnimation(animation);
    }


    public static void show(View view) {
        animateAlpha(view, 1, null);
    }

    public static void hide(View view) {
        animateAlpha(view, 0, null);
    }

    public static void rotate(View view, int value, @Nullable Runnable endAction) {
        ViewPropertyAnimatorCompat animator = ViewCompat.animate(view).rotation(value);

        if (endAction != null) {
            animator.withEndAction(endAction);
        }

        animator.setDuration(ANIM_DURATION_MILLIS).start();
    }

    public static void showViews(View... views) {
        for (View view : views) {
            show(view);
        }
    }

    public static void hideViews(View... views) {
        for (View view : views) {
            hide(view);
        }
    }

    public static void scaleXViews(float value, View... views) {
        for (View view : views) {
            scaleX(value, view);
        }
    }

    public static void scaleX(float value, View view) {
        ViewCompat.animate(view).scaleX(value).setDuration(ANIM_DURATION_MILLIS).start();
    }

    public static void moveX(View view, float value) {
        ViewCompat.animate(view).translationX(value).setDuration(ANIM_DURATION_MILLIS).start();
    }

    public static void moveX(View view, float value, Runnable endAction) {
        ViewCompat.animate(view).translationX(value).withEndAction(endAction).setDuration(ANIM_DURATION_MILLIS).start();
    }

    private static void animateAlpha(final View view, final float alpha, @Nullable final Runnable endAction) {
        if (alpha == 1) {
            view.setVisibility(View.VISIBLE);
            ViewCompat.animate(view).alpha(0);
        }
        ViewCompat.animate(view).alpha(alpha).setDuration(ANIM_DURATION_MILLIS).withEndAction(new Runnable() {
            @Override
            public void run() {
                if (alpha == 0) {
                    view.setVisibility(View.INVISIBLE);
                }

                if (endAction != null) {
                    endAction.run();
                }
            }
        }).start();
    }
}
