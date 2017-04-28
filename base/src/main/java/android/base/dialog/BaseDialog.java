package android.base.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;


/**
 * This class used for the base class of all dialogs in the application.
 *
 * @author clickapps
 */
public class BaseDialog extends Dialog implements View.OnClickListener {

    /**
     * Instantiates a new Base dialog.
     *
     * @param context the context
     */
    public BaseDialog(Context context) {
        super(context);
        hideTitle();
    }

    /**
     * Instantiates a new Base dialog.
     *
     * @param context the context
     * @param theme   the theme
     */
    public BaseDialog(Context context, int theme) {
        super(context, theme);
        hideTitle();
    }

    @Override
    public void onClick(View v) {

    }

    /**
     * hide the dialog title
     */
    private void hideTitle() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

}
