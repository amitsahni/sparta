package android.base.ui.widget;

import android.base.R;
import android.base.util.ApplicationUtils;
import android.base.util.LetterSpacingUtils;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.util.AttributeSet;


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
    public BaseButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs,
                    R.styleable.BaseTextView, 0, 0);
            String typeface = ApplicationUtils.System.getFontName(getContext(), a
                    .getInt(R.styleable.BaseTextView_typefaces, -1), a.getResourceId(R.styleable.BaseTextView_customTypeface, -1));
            int resId = a.getResourceId(R.styleable.BaseTextView_android_tint, -1);
            if (resId != -1) {
                setBackgroundDrawableTint(resId);
            }
            if (!TextUtils.isEmpty(typeface)) {
                setTypeface(Typeface.createFromAsset(context.getAssets(),
                        typeface));
            }
            float letterSpacing = a.getFloat(R.styleable.BaseTextView_letterSpacing, 0.0f);
            if (letterSpacing != 0.0f) {
                setTextSpacing(letterSpacing);
//                setLetterSpacing(letterSpacing);
            }
            boolean textAllCaps = a.getBoolean(R.styleable.BaseTextView_android_textAllCaps, false);
            if (textAllCaps) {
                setText(ApplicationUtils.Validator.upperCase(getText().toString()));
            }

            a.recycle();
        }
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

    /**
     * ***************
     * Spacing between characters of button mView
     * *******************
     * <ahref http://stackoverflow.com/questions/5133548/how-to-change-letter-spacing-in-a-textview></ahref>
     * <ahref http://stackoverflow.com/questions/5133548/how-to-change-letter-spacing-in-a-textview></ahref>
     */

    private float letterSpacing = LetterSpacingUtils.BIGGEST;
    private CharSequence originalText = "";

    /**
     * Gets text spacing.
     *
     * @return the text spacing
     */
    public float getTextSpacing() {
        return letterSpacing;
    }

    /**
     * Sets text spacing.
     *
     * @param letterSpacing the letter spacing
     */
    public void setTextSpacing(float letterSpacing) {
        this.letterSpacing = letterSpacing;
        originalText = getText();
        applyLetterSpacing();
    }

    private void applyLetterSpacing() {
        if (this == null || this.originalText == null) return;
        super.setText(LetterSpacingUtils.applyLetterSpacing(originalText.toString(), letterSpacing), BufferType.SPANNABLE);
    }


}
