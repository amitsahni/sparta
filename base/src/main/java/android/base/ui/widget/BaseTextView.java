package android.base.ui.widget;

import android.base.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.AppCompatTextView;
import android.text.Html;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;

import java.util.Locale;


/**
 * This class is used as Widget instead of TextView.This class has also a custom
 * attribute which is used in xml file.
 * <p>
 * This attribute is customtypeface support string value pass name of typeface
 * of using in asses folder here. It will automatically set on TextView text.
 * </P>
 *
 * @author amit.singh
 */
public class BaseTextView extends AppCompatTextView {

    /**
     * Instantiates a new Base text mView.
     *
     * @param context the context
     */
    public BaseTextView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    /**
     * Instantiates a new Base text mView.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public BaseTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        setAttributes(attrs);
    }

    /**
     * Sets attributes.
     *
     * @param attrs the attrs
     */
    void setAttributes(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs,
                    R.styleable.BaseTextView);
            String typeface = getFontName(getContext(), a.getResourceId(R.styleable.BaseTextView_customTypeface, -1));
            if (!TextUtils.isEmpty(typeface)) {
                Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
                        typeface);
                setTypeface(tf);
            }
            boolean textAllCaps = a.getBoolean(R.styleable.BaseTextView_android_textAllCaps, false);
            if (textAllCaps) {
                setText(getText().toString().toUpperCase(Locale.getDefault()));
            }
            if (a.getBoolean(R.styleable.BaseTextView_enableHtml, false)) {
                setText(Html.fromHtml(getText().toString()));
            }
            int resId = a.getResourceId(R.styleable.BaseTextView_android_tint, -1);
            if (resId != -1) {
                ColorStateList tint = ContextCompat.getColorStateList(getContext(), resId);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    // Call some material design APIs here
                    setCompoundDrawableTintList(tint);
                } else {
                    // Implement this feature without material design
                    Drawable[] drawable = getCompoundDrawablesRelative();
                    Drawable start = drawable[0];
                    Drawable top = drawable[1];
                    Drawable end = drawable[2];
                    Drawable bottom = drawable[3];
                    if (start != null)
                        DrawableCompat.setTintList(start, tint);
                    if (top != null)
                        DrawableCompat.setTintList(top, tint);
                    if (end != null)
                        DrawableCompat.setTintList(end, tint);
                    if (bottom != null)
                        DrawableCompat.setTintList(bottom, tint);
                    setCompoundDrawablesRelativeWithIntrinsicBounds(start, top, end, bottom);
                }
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
     * This method is used to setTextSize. In this float value is changed to
     * scaledDenisty and then set to the editText
     *
     * @param px float size of editText
     */
    public void setTextViewSize(float px) {
        float scaledDensity = getResources().getDisplayMetrics().scaledDensity;
        int sp = (int) (px / scaledDensity);
        setTextSize(sp);
    }

    /**
     * Gets forced gravity.
     *
     * @param langCode the lang code
     * @return the forced gravity
     */
    public int getForcedGravity(String langCode) {
        int gravity = Gravity.LEFT;
        if (!TextUtils.isEmpty(langCode)) {
            if (TextUtils.equals(langCode, "ar")) {
                gravity = Gravity.RIGHT;
            }
        }
        return gravity;
    }

    /**
     * Sets forced gravity.
     *
     * @param langCode the lang code
     */
    public void setForcedGravity(String langCode) {
        int gravity = Gravity.LEFT;
        if (!TextUtils.isEmpty(langCode)) {
            if (TextUtils.equals(langCode, "ar")) {
                gravity = Gravity.RIGHT;
            }
        }
        this.setGravity(gravity);
    }
}
