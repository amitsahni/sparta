package android.base.ui.widget;

import android.base.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import android.util.AttributeSet;
import android.widget.ProgressBar;

/**
 * Created by clickapps on 3/7/15.
 */
public class BaseProgressBar extends ProgressBar {
    /**
     * Instantiates a new Base progress bar.
     *
     * @param context the context
     */
    public BaseProgressBar(Context context) {
        super(context);
    }

    /**
     * Instantiates a new Base progress bar.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public BaseProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    private void init(@NonNull AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs,
                    R.styleable.BaseTextView, 0, 0);
            int resId = a.getResourceId(R.styleable.BaseTextView_android_tint, -1);
            setIndeterminateTint(resId);
            a.recycle();
        }
    }

    /**
     * Sets indeterminate tint.
     *
     * @param resId the res id
     */
    public void setIndeterminateTint(int resId) {
        Drawable d = getIndeterminateDrawable();
        if (d != null && resId != -1) {
            DrawableCompat.setTintList(d, ContextCompat.getColorStateList(getContext(), resId));
        }
    }
}
