package android.base.alert;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by amit on 24/1/17.
 */

public class ToastParam {

    /**
     * The Message.
     */
    protected String message = "";
    /**
     * The Context.
     */
    protected Context context;
    /**
     * The Message res id.
     */
    protected int messageResId = -1;

    /*Typeface for the specific alert*/
    protected String typeface;

    /**
     * The Duration.
     */
    protected int duration = Toast.LENGTH_SHORT;
}
