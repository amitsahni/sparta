package android.base.ui.widget;

import android.content.Context;
import androidx.appcompat.widget.Toolbar;
import android.util.AttributeSet;

/**
 * Created by Sahni on 24-11-2015.
 */
public class BaseToolbar extends Toolbar {
    /**
     * Instantiates a new Base toolbar.
     *
     * @param context the context
     */
    public BaseToolbar(Context context) {
        super(context);
    }

    /**
     * Instantiates a new Base toolbar.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public BaseToolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Instantiates a new Base toolbar.
     *
     * @param context      the context
     * @param attrs        the attrs
     * @param defStyleAttr the def style attr
     */
    public BaseToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
