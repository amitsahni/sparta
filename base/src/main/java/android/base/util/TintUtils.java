package android.base.util;

import android.base.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.AttrRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.AppCompatEditText;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.SeekBar;


/**
 * The type Tint utils.
 */
public class TintUtils {
    private TintUtils() {

    }

    /**
     * The type Theme resolver.
     */
    public static class ThemeResolver {
        private ThemeResolver() {

        }

        /**
         * Gets color.
         *
         * @param context the context
         * @param attr    the attr
         * @return the color
         */
        public static int getColor(Context context, @AttrRes int attr) {
            return getColor(context, attr, 0);
        }

        /**
         * Gets color.
         *
         * @param context      the context
         * @param attr         the attr
         * @param defaultColor the default color
         * @return the color
         */
        public static int getColor(Context context, @AttrRes int attr, int defaultColor) {
            TypedArray a = context.getTheme().obtainStyledAttributes(new int[]{attr});
            try {
                return a.getColor(0, defaultColor);
            } finally {
                a.recycle();
            }
        }
    }

    /**
     * Sets tint.
     *
     * @param radioButton the radio button
     * @param color       the color
     */
    public static void setTint(RadioButton radioButton, int color) {
        ColorStateList sl = new ColorStateList(new int[][]{
                new int[]{-android.R.attr.state_checked},
                new int[]{android.R.attr.state_checked}
        }, new int[]{
                ThemeResolver.getColor(radioButton.getContext(), R.attr.colorControlNormal),
                color
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            radioButton.setButtonTintList(sl);
        } else {
            Drawable d = DrawableCompat.wrap(ContextCompat.getDrawable(radioButton.getContext(), R.drawable.abc_btn_radio_material));
            DrawableCompat.setTintList(d, sl);
            radioButton.setButtonDrawable(d);
        }
    }

    /**
     * Sets tint.
     *
     * @param seekBar the seek bar
     * @param color   the color
     */
    public static void setTint(SeekBar seekBar, int color) {
        ColorStateList s1 = ColorStateList.valueOf(color);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            seekBar.setThumbTintList(s1);
            seekBar.setProgressTintList(s1);
        } else if (Build.VERSION.SDK_INT > Build.VERSION_CODES.GINGERBREAD_MR1) {
            Drawable progressDrawable = DrawableCompat.wrap(seekBar.getProgressDrawable());
            seekBar.setProgressDrawable(progressDrawable);
            DrawableCompat.setTintList(progressDrawable, s1);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                Drawable thumbDrawable = DrawableCompat.wrap(seekBar.getThumb());
                DrawableCompat.setTintList(thumbDrawable, s1);
                seekBar.setThumb(thumbDrawable);
            }
        } else {
            PorterDuff.Mode mode = PorterDuff.Mode.SRC_IN;
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.GINGERBREAD_MR1) {
                mode = PorterDuff.Mode.MULTIPLY;
            }
            if (seekBar.getIndeterminateDrawable() != null)
                seekBar.getIndeterminateDrawable().setColorFilter(color, mode);
            if (seekBar.getProgressDrawable() != null)
                seekBar.getProgressDrawable().setColorFilter(color, mode);
        }
    }

    /**
     * Sets tint.
     *
     * @param progressBar the progress bar
     * @param color       the color
     */
    public static void setTint(ProgressBar progressBar, int color) {
        setTint(progressBar, color, false);
    }

    /**
     * Sets tint.
     *
     * @param progressBar       the progress bar
     * @param color             the color
     * @param skipIndeterminate the skip indeterminate
     */
    public static void setTint(ProgressBar progressBar, int color, boolean skipIndeterminate) {
        ColorStateList sl = ColorStateList.valueOf(color);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            progressBar.setProgressTintList(sl);
            progressBar.setSecondaryProgressTintList(sl);
            if (!skipIndeterminate)
                progressBar.setIndeterminateTintList(sl);
        } else {
            PorterDuff.Mode mode = PorterDuff.Mode.SRC_IN;
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.GINGERBREAD_MR1) {
                mode = PorterDuff.Mode.MULTIPLY;
            }
            if (!skipIndeterminate && progressBar.getIndeterminateDrawable() != null)
                progressBar.getIndeterminateDrawable().setColorFilter(color, mode);
            if (progressBar.getProgressDrawable() != null)
                progressBar.getProgressDrawable().setColorFilter(color, mode);
        }
    }

    private static ColorStateList createEditTextColorStateList(Context context, int color) {
        int[][] states = new int[3][];
        int[] colors = new int[3];
        int i = 0;
        states[i] = new int[]{-android.R.attr.state_enabled};
        colors[i] = ThemeResolver.getColor(context, R.attr.colorControlNormal);
        i++;
        states[i] = new int[]{-android.R.attr.state_pressed, -android.R.attr.state_focused};
        colors[i] = ThemeResolver.getColor(context, R.attr.colorControlNormal);
        i++;
        states[i] = new int[]{};
        colors[i] = color;
        return new ColorStateList(states, colors);
    }

    /**
     * Sets tint.
     *
     * @param editText the edit text
     * @param color    the color
     */
    public static void setTint(EditText editText, int color) {
        ColorStateList editTextColorStateList = createEditTextColorStateList(editText.getContext(), color);
        if (editText instanceof AppCompatEditText) {
            ((AppCompatEditText) editText).setSupportBackgroundTintList(editTextColorStateList);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            editText.setBackgroundTintList(editTextColorStateList);
        }
    }

    /**
     * Sets tint.
     *
     * @param box   the box
     * @param color the color
     */
    public static void setTint(CheckBox box, int color) {
        ColorStateList sl = new ColorStateList(new int[][]{
                new int[]{-android.R.attr.state_checked},
                new int[]{android.R.attr.state_checked}
        }, new int[]{
                ThemeResolver.getColor(box.getContext(), R.attr.colorControlNormal),
                color
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            box.setButtonTintList(sl);
        } else {
            Drawable drawable = DrawableCompat.wrap(ContextCompat.getDrawable(box.getContext(), R.drawable.abc_btn_check_material));
            DrawableCompat.setTintList(drawable, sl);
            box.setButtonDrawable(drawable);
        }
    }
}
