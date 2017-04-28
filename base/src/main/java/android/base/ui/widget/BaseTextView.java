package android.base.ui.widget;

import android.base.R;
import android.base.constant.Constant;
import android.base.util.ApplicationUtils;
import android.base.util.LetterSpacingUtils;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.text.Html;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;


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
            String typeface = ApplicationUtils.System.getFontName(getContext(), a
                    .getInt(R.styleable.BaseTextView_typefaces, -1), a.getResourceId(R.styleable.BaseTextView_customTypeface, -1));
            if (!TextUtils.isEmpty(typeface)) {
                Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
                        typeface);
                setTypeface(tf);
            }
            float letterSpacing = a.getFloat(R.styleable.BaseTextView_letterSpacing, 0.0f);
            if (letterSpacing != 0.0f) {
                setTextSpacing(letterSpacing);
            }
            boolean textAllCaps = a.getBoolean(R.styleable.BaseTextView_android_textAllCaps, false);
            if (textAllCaps) {
                setText(ApplicationUtils.Validator.upperCase(getText().toString()));
            }
            if (a.getBoolean(R.styleable.BaseTextView_enableHtml, false)) {
                setText(Html.fromHtml(getText().toString()));
            }
            a.recycle();
        }
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
            if (TextUtils.equals(langCode, Constant.ARABIC_LANG_CODE)) {
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
            if (TextUtils.equals(langCode, Constant.ARABIC_LANG_CODE)) {
                gravity = Gravity.RIGHT;
            }
        }
        this.setGravity(gravity);
    }

    /**
     * ***************
     * Spacing between characters of text mView
     * *******************
     * <ahref http://stackoverflow.com/questions/5133548/how-to-change-letter-spacing-in-a-textview></ahref>
     * <ahref http://stackoverflow.com/questions/5133548/how-to-change-letter-spacing-in-a-textview></ahref>
     */

    private float letterSpacing = LetterSpacingUtils.BIGGEST;
    private CharSequence originalText = "";

    /**
     * Sets text spacing.
     *
     * @return the text spacing
     */
    public float setTextSpacing() {
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
