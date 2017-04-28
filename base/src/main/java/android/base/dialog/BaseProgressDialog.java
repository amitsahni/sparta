package android.base.dialog;

import android.app.ProgressDialog;
import android.content.Context;


/**
 * The type Base progress dialog.
 */
public class BaseProgressDialog extends ProgressDialog {
    /**
     * Instantiates a new Base progress dialog.
     *
     * @param context the context
     */
    public BaseProgressDialog(Context context) {
        super(context);
    }

    /**
     * Instantiates a new Base progress dialog.
     *
     * @param context the context
     * @param theme   the theme
     */
    public BaseProgressDialog(Context context, int theme) {
        super(context, theme);
    }
}
