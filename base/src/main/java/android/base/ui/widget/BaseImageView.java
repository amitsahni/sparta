package android.base.ui.widget;

import android.base.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.AppCompatImageView;
import android.text.TextUtils;
import android.util.AttributeSet;

/**
 * This class is used as widget instead to using FrameLayout with ProgressBar
 * and ImageView. This is used like a ImageView. This is used when images are
 * fetched from server and show on ImageView. Till the images are loader from
 * server progress bar is visible on image mView. After that Images are loaded
 * and set on imageView. This is also done with the help of Universal Image
 * Loader.
 *
 * @author amit.singh
 */
public class BaseImageView extends AppCompatImageView {

    /**
     * Instantiates a new Base image mView.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public BaseImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    /**
     * Instantiates a new Base image mView.
     *
     * @param context the context
     */
    public BaseImageView(Context context) {
        super(context);
    }

    /**
     * Instantiates a new Base image mView.
     *
     * @param context  the context
     * @param attrs    the attrs
     * @param defStyle the def style
     */
    public BaseImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs,
                    R.styleable.BaseTextView, 0, 0);
            int resId = a.getResourceId(R.styleable.BaseTextView_android_tint, -1);
            if (resId != -1) {
                setImageTint(resId);
            }
            a.recycle();
        }
    }

    private void setImageTint(@ColorRes int resId) {
        super.setColorFilter(ContextCompat.getColorStateList(getContext(), resId).getColorForState(getDrawableState(), 0));
    }

    public void setImageTintColor(@ColorRes int resId, @DrawableRes int drawableRes) {
        Drawable d = ContextCompat.getDrawable(getContext(), drawableRes);
        DrawableCompat.wrap(d);
        DrawableCompat.setTintList(d, ContextCompat.getColorStateList(getContext(), resId));
        setImageDrawable(d);
    }

}
