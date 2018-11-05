package android.base.ui.widget;

import android.content.Context;
import androidx.appcompat.widget.AppCompatSpinner;
import android.util.AttributeSet;

/**
 * Created by clickapps on 3/12/15.
 */
public class BaseSpinner extends AppCompatSpinner {

    /**
     * Instantiates a new Base spinner.
     *
     * @param context the context
     */
    public BaseSpinner(Context context) {
        super(context);
    }

    /**
     * Instantiates a new Base spinner.
     *
     * @param context the context
     * @param mode    the mode
     */
    public BaseSpinner(Context context, int mode) {
        super(context, mode);
    }

    /**
     * Instantiates a new Base spinner.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public BaseSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}
