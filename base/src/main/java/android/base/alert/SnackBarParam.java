package android.base.alert;

import android.app.Activity;
import android.base.R;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by amit on 24/1/17.
 */

public class SnackBarParam {
    protected static final int red = R.color.red;
    protected static final int green = R.color.green;
    protected static final int blue = R.color.blue;
    protected static final int orange = R.color.orange;
    /**
     * The Message.
     */
    protected String message = "", actionMessage = "", typeface;
    /**
     * The Context.
     */
    protected Context context;

    protected Activity activityContext;
    /**
     * The Message res id.
     */
    protected int messageResId = -1, actionMessageResId = -1, actionColorResId = -1,
            snackBarDuration = Snackbar.LENGTH_LONG, alertTaskId, actionMessageMaxLine = -1,
            textColor = -1;

    /**
     * The Action background res id.
     */
    protected int actionBackgroundResId = blue;

    /**
     * The Snack bar mView.
     */
    protected View snackBarView;

    /**
     * The On snack bar action listener.
     */
    protected OnSnackBarActionListener onSnackBarActionListener;

}
