package android.base.ui.widget;

import android.annotation.SuppressLint;
import android.base.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.AppCompatButton;
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
            a = context.obtainStyledAttributes(attrs,
                    attr);
            // android.R.attr.drawableLeft
            resId = a.getResourceId(a.getIndex(android.R.attr.drawableLeft), -1);
            if (resId != -1) {
                setCompoundDrawablesWithIntrinsicBounds(resId, getCompoundPaddingTop(), getCompoundPaddingRight(), getCompoundPaddingBottom());
            }

            // android.R.attr.drawableStart
            resId = a.getResourceId(a.getIndex(android.R.attr.drawableStart), -1);
            if (resId != -1) {
                setCompoundDrawablesRelativeWithIntrinsicBounds(resId, getCompoundPaddingTop(), getCompoundPaddingEnd(), getCompoundPaddingBottom());
            }

            // android.R.attr.drawableRight
            resId = a.getResourceId(a.getIndex(android.R.attr.drawableRight), -1);
            if (resId != -1) {
                setCompoundDrawablesWithIntrinsicBounds(getCompoundPaddingLeft(), getCompoundPaddingTop(), resId, getCompoundPaddingBottom());
            }

            // android.R.attr.drawableEnd
            resId = a.getResourceId(a.getIndex(android.R.attr.drawableEnd), -1);
            if (resId != -1) {
                setCompoundDrawablesRelativeWithIntrinsicBounds(getCompoundPaddingStart(), getCompoundPaddingTop(), resId, getCompoundPaddingBottom());
            }

            // android.R.attr.drawableTop
            resId = a.getResourceId(a.getIndex(android.R.attr.drawableTop), -1);
            if (resId != -1) {
                setCompoundDrawablesRelativeWithIntrinsicBounds(getCompoundPaddingStart(), resId, getCompoundPaddingEnd(), getCompoundPaddingBottom());
            }

            // android.R.attr.drawableBottom
            resId = a.getResourceId(a.getIndex(android.R.attr.drawableBottom), -1);
            if (resId != -1) {
                setCompoundDrawablesRelativeWithIntrinsicBounds(getCompoundPaddingStart(), getCompoundPaddingTop(), getCompoundPaddingEnd(), resId);
            }
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
