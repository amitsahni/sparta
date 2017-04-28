package android.base.ui.custom;

import android.base.R;
import android.base.constant.Constant;
import android.base.ui.widget.BaseImageView;
import android.base.ui.widget.BaseProgressBar;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;


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
public class BaseImageViewLoading extends FrameLayout {
    private BaseProgressBar mProgressBar;
    private BaseImageView mImageView, mGradientImageView;
    private float minHeight, minWidth;

    /**
     * Instantiates a new Base image mView loading.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public BaseImageViewLoading(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
        if (attrs != null) {
            if (Build.VERSION.SDK_INT >= Constant.BUILD_VERSION_LOLLIPOP) {
                int attr[] = {android.R.attr.transitionName};
                TypedArray ta = getContext().obtainStyledAttributes(attrs,
                        attr);
                getImageView().setTransitionName(ta.getString(0));
                ta.recycle();
            }
            TypedArray ta = context.obtainStyledAttributes(attrs,
                    R.styleable.BaseImageViewLoading, 0, 0);
            if (ta.getResourceId(R.styleable.BaseImageViewLoading_android_src, -1) != -1)
                mImageView.setImageResource(ta.getResourceId(R.styleable.BaseImageViewLoading_android_src, -1));
            mGradientImageView.setImageResource(ta.getResourceId(R.styleable.BaseImageViewLoading_gradientImage, R.drawable.shape_gradient_base));
            if (ta.getDrawable(R.styleable.BaseImageViewLoading_indeterminateDrawable) != null)
                mProgressBar.setIndeterminateDrawable(ta.getDrawable(R.styleable.BaseImageViewLoading_indeterminateDrawable));
            ta.recycle();
        }
    }

    /**
     * Instantiates a new Base image mView loading.
     *
     * @param context the context
     */
    public BaseImageViewLoading(Context context) {
        super(context);
        initView();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mImageView.setLayoutParams(getLayoutParams());
        mGradientImageView.setLayoutParams(getLayoutParams());
//        mProgressBar.setLayoutParams(getLayoutParams());
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void initView() {
        LayoutInflater inflator = LayoutInflater.from(getContext());
        View v = inflator.inflate(R.layout.imageview_loading_base, null);
        mProgressBar = (BaseProgressBar) v
                .findViewById(R.id.imageview_loading_progressViewApp);
        mImageView = (BaseImageView) v.findViewById(R.id.imageview_loading_baseImageView);
        mGradientImageView = (BaseImageView) v.findViewById(R.id.imageview_loading_baseImageView_gradient);
        addView(v);
    }

    /**
     * Gets image mView.
     *
     * @return the image mView
     */
    public BaseImageView getImageView() {
        return mImageView;
    }

    /**
     * Gets progress bar.
     *
     * @return the progress bar
     */
    public BaseProgressBar getProgressBar() {
        return mProgressBar;
    }

    /**
     * Gets gradient image mView.
     *
     * @return the gradient image mView
     */
    public BaseImageView getGradientImageView() {
        return mGradientImageView;
    }


}
