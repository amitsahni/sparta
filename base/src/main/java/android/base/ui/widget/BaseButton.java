package android.base.ui.widget;

import android.annotation.SuppressLint;
import android.base.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.appcompat.widget.AppCompatButton;
import android.text.TextUtils;
import android.util.AttributeSet;

import java.util.Locale;


/**
 * This class is used as widget. This is used instead of using Button. This
 * class has also a custom attribute which is used in xml file.
 * <p>
 * This attribute is customtypeface support string value pass name of typeface
 * of using in asses folder here. It will automatically set on button text.
 * </P>
 *
 * @author amit.singh
 */
public class BaseButton extends AppCompatButton {

    /**
     * Instantiates a new Base button.
     *
     * @param context the context
     */
    public BaseButton(Context context) {
        super(context);
        // TODO Auto-generated constructor stub

    }

    /**
     * Instantiates a new Base button.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    @SuppressLint("ResourceType")
    public BaseButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs,
                    R.styleable.BaseTextView, 0, 0);
            String typeface = getFontName(getContext(), a.getResourceId(R.styleable.BaseTextView_customTypeface, -1));
            int resId = a.getResourceId(R.styleable.BaseTextView_android_tint, -1);
            if (resId != -1) {
                setBackgroundDrawableTint(resId);
            }
            if (!TextUtils.isEmpty(typeface)) {
                setTypeface(Typeface.createFromAsset(context.getAssets(),
                        typeface));
            }
            boolean textAllCaps = a.getBoolean(R.styleable.BaseTextView_android_textAllCaps, false);
            if (textAllCaps) {
                setText(getText().toString().toUpperCase(Locale.getDefault()));
            }
            int attr[] = {android.R.attr.drawableLeft, android.R.attr.drawableStart,
                    android.R.attr.drawableRight, android.R.attr.drawableEnd,
                    android.R.attr.drawableTop, android.R.attr.drawableBottom};
            TypedArray defaultAttr = getContext().obtainStyledAttributes(attrs,
                    attr);
            // android.R.attr.drawableLeft
            resId = defaultAttr.getResourceId(0, -1);
            if (resId != -1) {
                Drawable[] drawables = getCompoundDrawables();
                setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(getContext(), resId), drawables[1], drawables[2], drawables[3]);
            }

            // android.R.attr.drawableStart
            resId = defaultAttr.getResourceId(1, -1);
            if (resId != -1) {
                Drawable[] drawables = getCompoundDrawablesRelative();
                setCompoundDrawablesRelativeWithIntrinsicBounds(ContextCompat.getDrawable(getContext(), resId), drawables[1], drawables[2], drawables[3]);
            }

            // android.R.attr.drawableRight
            resId = defaultAttr.getResourceId(2, -1);
            if (resId != -1) {
                Drawable[] drawables = getCompoundDrawables();
                setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1], ContextCompat.getDrawable(getContext(), resId), drawables[3]);
            }

            // android.R.attr.drawableEnd
            resId = defaultAttr.getResourceId(3, -1);
            if (resId != -1) {
                Drawable[] drawables = getCompoundDrawablesRelative();
                setCompoundDrawablesRelativeWithIntrinsicBounds(drawables[0], drawables[1], ContextCompat.getDrawable(getContext(), resId), drawables[3]);
            }

            // android.R.attr.drawableTop
            resId = defaultAttr.getResourceId(4, -1);
            if (resId != -1) {
                Drawable[] drawables = getCompoundDrawablesRelative();
                setCompoundDrawablesRelativeWithIntrinsicBounds(drawables[0], ContextCompat.getDrawable(getContext(), resId), drawables[2], drawables[3]);
            }

            // android.R.attr.drawableBottom
            resId = defaultAttr.getResourceId(5, -1);
            if (resId != -1) {
                Drawable[] drawables = getCompoundDrawablesRelative();
                setCompoundDrawablesRelativeWithIntrinsicBounds(drawables[0], drawables[1], drawables[2], ContextCompat.getDrawable(getContext(), resId));
            }
            defaultAttr.recycle();

            a.recycle();
        }
    }

    private String getFontName(Context context, @StringRes int resId) {
        if (resId != -1)
            return context.getString(resId);
        return "";
    }

    /**
     * Sets background drawable tint.
     *
     * @param resId the res id
     */
    public void setBackgroundDrawableTint(@ColorRes int resId) {
        setSupportBackgroundTintList(ContextCompat.getColorStateList(getContext(), resId));
    }

    /**
     * Gets tint drawable.
     *
     * @param drawable the drawable
     * @param resId    the res id
     * @return the tint drawable
     */
    public Drawable getTintDrawable(@DrawableRes int drawable, int resId) {
        Drawable d = ContextCompat.getDrawable(getContext(), drawable);
        DrawableCompat.wrap(d);
        DrawableCompat.setTintList(d, ContextCompat.getColorStateList(getContext(), resId));
        return d;
    }

    /**
     * This method is used to setTextSize. In this float value is changed to
     * scaledDenisty and then set to the editText
     *
     * @param px float size of editText
     */
    public void setEditTextSize(float px) {
        float scaledDensity = getResources().getDisplayMetrics().scaledDensity;
        int sp = (int) (px / scaledDensity);
        setTextSize(sp);
    }

}
