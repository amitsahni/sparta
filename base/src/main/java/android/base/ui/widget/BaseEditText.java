package android.base.ui.widget;


import android.base.R;
import android.base.util.ApplicationUtils;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatEditText;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;


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
public class BaseEditText extends AppCompatEditText {

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
            String typeface = ApplicationUtils.System.getFontName(getContext(), a.getResourceId(R.styleable.BaseTextView_customTypeface, -1));
            if (!TextUtils.isEmpty(typeface))
                setTypeface(Typeface.createFromAsset(context.getAssets(),
                        typeface));
            boolean textAllCaps = a.getBoolean(R.styleable.BaseTextView_android_textAllCaps, false);
            if (textAllCaps) {
                setText(ApplicationUtils.Validator.upperCase(getText().toString()));
            }
            a.recycle();
        }
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
}
