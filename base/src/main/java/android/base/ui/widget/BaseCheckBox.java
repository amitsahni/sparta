package android.base.ui.widget;

import android.base.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.annotation.StringRes;
import android.support.v7.widget.AppCompatCheckBox;
import android.text.TextUtils;
import android.util.AttributeSet;

import java.util.Locale;

/**
 * Created by Sahni on 24-11-2015.
 */
public class BaseCheckBox extends AppCompatCheckBox {
    /**
     * Instantiates a new Base check box.
     *
     * @param context the context
     */
    public BaseCheckBox(Context context) {
        super(context);
        setAttributes(null);
    }

    /**
     * Instantiates a new Base check box.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public BaseCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        setAttributes(attrs);
    }

    /**
     * Instantiates a new Base check box.
     *
     * @param context      the context
     * @param attrs        the attrs
     * @param defStyleAttr the def style attr
     */
    public BaseCheckBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setAttributes(attrs);
    }

    private void setAttributes(AttributeSet attrs) {
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


            int attr[] = {android.R.attr.drawableLeft, android.R.attr.drawableStart,
                    android.R.attr.drawableRight, android.R.attr.drawableEnd,
                    android.R.attr.drawableTop, android.R.attr.drawableBottom};
            a = getContext().obtainStyledAttributes(attrs,
                    attr);
            // android.R.attr.drawableLeft
            int resId = a.getResourceId(a.getIndex(android.R.attr.drawableLeft), -1);
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
}
