package android.base.ui.widget;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;

/**
 * Created by clickapps on 27/11/15.
 */
public class BaseNestedScrollView extends NestedScrollView {
    /**
     * Instantiates a new Base nested scroll mView.
     *
     * @param context the context
     */
    public BaseNestedScrollView(Context context) {
        super(context);
    }

    /**
     * Instantiates a new Base nested scroll mView.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public BaseNestedScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Instantiates a new Base nested scroll mView.
     *
     * @param context      the context
     * @param attrs        the attrs
     * @param defStyleAttr the def style attr
     */
    public BaseNestedScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
