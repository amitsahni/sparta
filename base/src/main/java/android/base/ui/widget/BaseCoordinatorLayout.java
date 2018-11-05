package android.base.ui.widget;

import android.content.Context;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import android.util.AttributeSet;

/**
 * Created by clickapps on 27/11/15.
 */
public class BaseCoordinatorLayout extends CoordinatorLayout {
    /**
     * Instantiates a new Base coordinator layout.
     *
     * @param context the context
     */
    public BaseCoordinatorLayout(Context context) {
        super(context);
    }

    /**
     * Instantiates a new Base coordinator layout.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public BaseCoordinatorLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Instantiates a new Base coordinator layout.
     *
     * @param context      the context
     * @param attrs        the attrs
     * @param defStyleAttr the def style attr
     */
    public BaseCoordinatorLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
