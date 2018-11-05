package android.base.ui.widget;

import android.content.Context;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import android.util.AttributeSet;

/**
 * Created by clickapps on 27/11/15.
 */
public class BaseCollapsingToolbarLayout extends CollapsingToolbarLayout {
    /**
     * Instantiates a new Base collapsing toolbar layout.
     *
     * @param context the context
     */
    public BaseCollapsingToolbarLayout(Context context) {
        super(context);
    }

    /**
     * Instantiates a new Base collapsing toolbar layout.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public BaseCollapsingToolbarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Instantiates a new Base collapsing toolbar layout.
     *
     * @param context      the context
     * @param attrs        the attrs
     * @param defStyleAttr the def style attr
     */
    public BaseCollapsingToolbarLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
