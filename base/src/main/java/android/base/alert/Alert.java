package android.base.alert;


import android.app.Activity;
import android.base.R;
import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.widget.TextView;


/**
 * The type Alert.
 */
public class Alert {
    private static volatile Alert sAlert;

    private Alert() {
        // private constructor
    }

    /**
     * Get alert.
     *
     * @return the alert
     */
    public static Alert get() {
        if (sAlert == null) {
            synchronized (Alert.class) {
                if (sAlert == null) {
                    sAlert = new Alert();
                }
            }
        }
        return sAlert;
    }

    /**
     * *******************************************************************************************************************
     *
     * @param context the context
     * @return the toast builder
     */
    public static ToastBuilder with(@NonNull Context context) {
        return new ToastBuilder(context);
    }

    /**
     * With toast builder.
     *
     * @param context the context
     * @param resId   the res id
     * @return the toast builder
     */
    public static ToastBuilder with(@NonNull Context context, @StringRes int resId) {
        return new ToastBuilder(context, resId);
    }

    /**
     * *******************************************************************************************************************
     *
     * @param context    the context
     * @param dialogType the dialog type
     * @return the dialog builder
     */
    public static DialogBuilder with(@NonNull Context context, AlertParam.DialogType dialogType) {
        return new DialogBuilder(context, dialogType, -1);
    }

    /**
     * *******************************************************************************************************************
     *
     * @param context    the context
     * @param dialogType the dialog type
     * @return the dialog builder
     */
    public static DialogBuilder with(@NonNull Context context, AlertParam.DialogType dialogType, @StyleRes int style) {
        return new DialogBuilder(context, dialogType, style);
    }

    /**
     * *******************************************************************************************************************
     * SnackBuilder class for showing Material SnackBuilder on Screen. To use this need to compile Design support lib
     * ************************************************/

    /**
     * @param context the context
     * @param resId   the res id
     * @return the snack builder
     */
    public static SnackBuilder with(Activity context, @StringRes int resId) {
        return new SnackBuilder(context, resId);
    }

    /**
     * With snack builder.
     *
     * @param context the context
     * @param msg     the msg
     * @return the snack builder
     */
    public static SnackBuilder with(Activity context, String msg) {
        return new SnackBuilder(context, msg);
    }

    /**
     * @param context         the context
     * @param resId           the res id
     * @param backgroundColor the color for snackbar
     * @return the snack builder
     */
    public static SnackBuilder with(Activity context, @StringRes int resId, @ColorRes int backgroundColor) {
        return new SnackBuilder(context, resId, backgroundColor);
    }

    /**
     * With snack builder.
     *
     * @param context         the context
     * @param msg             the msg
     * @param backgroundColor the color for snackbar
     * @return the snack builder
     */
    public static SnackBuilder with(Activity context, String msg, @ColorRes int backgroundColor) {
        return new SnackBuilder(context, msg, backgroundColor);
    }

    /***************
     *Helper methods*
     * **************/

    /**
     * Functionality to set type face or font for the text mView
     *
     * @param context  Context
     * @param textView TextView on which the text need to be formatted
     * @param typeface string that must be defined in assets
     */
    public void setTypeface(@NonNull Context context, @NonNull TextView textView, String typeface) {
        Typeface tf = Typeface.createFromAsset(context.getAssets(),
                typeface);
        textView.setTypeface(tf);
    }

}
