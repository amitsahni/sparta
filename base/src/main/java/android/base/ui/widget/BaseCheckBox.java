package android.base.ui.widget;

import android.base.R;
import android.base.util.ApplicationUtils;
import android.base.util.LetterSpacingUtils;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatCheckBox;
import android.text.TextUtils;
import android.util.AttributeSet;

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
            a.recycle();
        }
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
