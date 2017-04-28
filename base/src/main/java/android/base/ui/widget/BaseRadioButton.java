package android.base.ui.widget;

import android.base.R;
import android.base.util.ApplicationUtils;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatRadioButton;
import android.text.TextUtils;
import android.util.AttributeSet;


/**
 * The type Base radio button.
 */
public class BaseRadioButton extends AppCompatRadioButton {

    /**
     * Instantiates a new Base radio button.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public BaseRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        if (attrs != null) {

            TypedArray a = context.obtainStyledAttributes(attrs,
                    R.styleable.BaseTextView, 0, 0);
            String typeface = ApplicationUtils.System.getFontName(getContext(), a
                    .getInt(R.styleable.BaseTextView_typefaces, -1), a.getResourceId(R.styleable.BaseTextView_customTypeface, -1));
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

}
