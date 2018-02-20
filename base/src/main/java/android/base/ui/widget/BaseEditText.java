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
import android.support.v7.widget.AppCompatEditText;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.widget.Checkable;

import java.util.Locale;


/**
 * This class is used as Widget instead of EditText.This class has also a custom
 * attribute which is used in xml file.
 * <p>
 * This attribute is customtypeface support string value pass name of typeface
 * of using in asses folder here. It will automatically set on EditText text.
 * </P>
 *
 * @author amit.singh
 */
public class BaseEditText extends AppCompatEditText implements Checkable {

    /**
     * Instantiates a new Base edit text.
     *
     * @param context the context
     */
    public BaseEditText(Context context) {
        super(context);
        // TODO Auto-generated constructor stub

    }

    /**
     * Instantiates a new Base edit text.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public BaseEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        setTypeface(Typeface.DEFAULT);
        if (InputType.TYPE_TEXT_VARIATION_PASSWORD == getInputType()) {
            setTransformationMethod(new PasswordTransformationMethod());
        } else if (InputType.TYPE_CLASS_PHONE == getInputType()) {
            addTextChangedListener(new PhoneNumberFormattingTextWatcher());
        }
        if (attrs != null) {

            TypedArray a = context.obtainStyledAttributes(attrs,
                    R.styleable.BaseTextView, 0, 0);
            String typeface = getFontName(getContext(), a.getResourceId(R.styleable.BaseTextView_customTypeface, -1));
            if (!TextUtils.isEmpty(typeface))
                setTypeface(Typeface.createFromAsset(context.getAssets(),
                        typeface));
            boolean textAllCaps = a.getBoolean(R.styleable.BaseTextView_android_textAllCaps, false);
            if (textAllCaps) {
                setText(getText().toString().toUpperCase(Locale.getDefault()));
            }
            int resId = a.getResourceId(R.styleable.BaseTextView_android_tint, -1);
            if (resId != -1) {
                ColorStateList tint = ContextCompat.getColorStateList(getContext(), resId);
                if (Build.VERSION.SDK_INT >= 21) {
                    // Call some material design APIs here
                    setCompoundDrawableTintList(tint);
                } else {
                    // Implement this feature without material design
                    Drawable[] drawable = getCompoundDrawablesRelative();
                    Drawable start = drawable[0];
                    Drawable top = drawable[1];
                    Drawable end = drawable[2];
                    Drawable bottom = drawable[3];
                    if (start != null) {
                        start = DrawableCompat.wrap(start);
                        DrawableCompat.setTintList(start, tint);
                    }
                    if (top != null) {
                        top = DrawableCompat.wrap(top);
                        DrawableCompat.setTintList(top, tint);
                    }
                    if (end != null) {
                        end = DrawableCompat.wrap(end);
                        DrawableCompat.setTintList(end, tint);
                    }
                    if (bottom != null) {
                        bottom = DrawableCompat.wrap(bottom);
                        DrawableCompat.setTintList(bottom, tint);
                    }
                    setCompoundDrawablesRelativeWithIntrinsicBounds(start, top, end, bottom);
                }
            }
            a.recycle();

            int attrss[] = {
                    android.R.attr.checked
            };
            a = context.obtainStyledAttributes(attrs,
                    attrss, 0, 0);
            boolean isChecked = a.getBoolean(0, false);
            setChecked(isChecked);
            a.recycle();
        }
    }

    private String getFontName(Context context, @StringRes int resId) {
        if (resId != -1)
            return context.getString(resId);
        return "";
    }

    /**
     * This method is used to clear editText value
     */
    public void clear() {
        getText().clear();
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

    private static final int[] CheckedStateSet = {android.R.attr.state_checked};

    private boolean mChecked = false;

    @Override
    public boolean isChecked() {
        return mChecked;
    }

    @Override
    public void setChecked(boolean b) {
        if (b != mChecked) {
            mChecked = b;
        }
        refreshDrawableState();
    }

    @Override
    public void toggle() {
        setChecked(!isChecked());
    }

    @Override
    public int[] onCreateDrawableState(int extraSpace) {
        final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
        if (isChecked()) {
            mergeDrawableStates(drawableState, CheckedStateSet);
        }
        return drawableState;
    }

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        invalidate();
    }
}
