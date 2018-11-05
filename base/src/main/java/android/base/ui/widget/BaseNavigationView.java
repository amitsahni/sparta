package android.base.ui.widget;

import android.content.Context;
import com.google.android.material.navigation.NavigationView;
import android.util.AttributeSet;

/**
 * Created by clickapps on 27/11/15.
 */
public class BaseNavigationView extends NavigationView {
    /**
     * Instantiates a new Base navigation mView.
     *
     * @param context the context
     */
    public BaseNavigationView(Context context) {
        super(context);
    }

    /**
     * Instantiates a new Base navigation mView.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public BaseNavigationView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Instantiates a new Base navigation mView.
     *
     * @param context      the context
     * @param attrs        the attrs
     * @param defStyleAttr the def style attr
     */
    public BaseNavigationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
