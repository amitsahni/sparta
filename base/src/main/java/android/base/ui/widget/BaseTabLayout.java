package android.base.ui.widget;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.util.AttributeSet;

/**
 * Created by clickapps on 28/12/15.
 */
public class BaseTabLayout extends TabLayout {

    /**
     * Instantiates a new Base tab layout.
     *
     * @param context the context
     */
    public BaseTabLayout(Context context) {
        super(context);
    }

    /**
     * Instantiates a new Base tab layout.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public BaseTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Instantiates a new Base tab layout.
     *
     * @param context      the context
     * @param attrs        the attrs
     * @param defStyleAttr the def style attr
     */
    public BaseTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
