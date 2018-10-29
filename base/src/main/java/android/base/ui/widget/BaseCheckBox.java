package android.base.ui.widget;

import android.annotation.SuppressLint;
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

    @SuppressLint("ResourceType")
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
            TypedArray defaultAttr = getContext().obtainStyledAttributes(attrs,
                    attr);
            // android.R.attr.drawableLeft
            int resId = defaultAttr.getResourceId(0, -1);
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

            resId = a.getResourceId(R.styleable.BaseTextView_android_tint, -1);
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
                    start = DrawableCompat.wrap(start);
                    DrawableCompat.setTintList(start.mutate(), tint);

                    top = DrawableCompat.wrap(top);
                    DrawableCompat.setTintList(top.mutate(), tint);

                    end = DrawableCompat.wrap(end);
                    DrawableCompat.setTintList(end.mutate(), tint);

                    bottom = DrawableCompat.wrap(bottom);
                    DrawableCompat.setTintList(bottom.mutate(), tint);
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
}
