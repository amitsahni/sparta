package android.base.alert;

import android.base.util.ApplicationUtils;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * The type Toast builder.
 */
public class ToastBuilder {

    private ToastParam toastParam;
    private android.widget.Toast mToast;

    /**
     * Instantiates a new Toast builder.
     *
     * @param context the context
     */
    public ToastBuilder(@NonNull Context context) {
        toastParam = new ToastParam();
        toastParam.context = context;
    }

    /**
     * Instantiates a new Toast builder.
     *
     * @param context the context
     * @param resId   the res id
     */
    public ToastBuilder(@NonNull Context context, @StringRes int resId) {
        toastParam = new ToastParam();
        toastParam.context = context;
        toastParam.messageResId = resId;
    }

    /**
     * Message mToast builder.
     *
     * @param message the message
     * @return the mToast builder
     */
    public ToastBuilder message(@NonNull String message) {
        toastParam.message = message;
        return this;
    }

    /**
     * Message mToast builder.
     *
     * @param resId the res id
     * @return the mToast builder
     */
    public ToastBuilder message(int resId) {
        toastParam.messageResId = resId;
        return this;
    }

    /**
     * Duration mToast builder.
     *
     * @param duration the duration
     * @return the mToast builder
     */
    public ToastBuilder duration(int duration) {
        toastParam.duration = duration;
        return this;
    }

    public ToastBuilder typeface(@NonNull String typeface) {
        toastParam.typeface = typeface;
        return this;
    }

    /**
     * Show.
     */
    public void show() {
        if (null != mToast) {
            mToast.cancel();
        }
        if (toastParam.messageResId != -1) {
            mToast = android.widget.Toast.makeText(toastParam.context.getApplicationContext(), toastParam.messageResId, toastParam.duration);
        } else {
            mToast = android.widget.Toast.makeText(toastParam.context.getApplicationContext(), toastParam.message, toastParam.duration);
        }

        try {
            //find text mView
            TextView textView = (TextView) ((LinearLayout) mToast.getView()).getChildAt(0);
            //check typeface
            if (!TextUtils.isEmpty(toastParam.typeface)) {
                Alert.get().setTypeface(toastParam.context, textView, toastParam.typeface);
            }
        } catch (Exception e) {
            ApplicationUtils.Log.e(e.getMessage() + e);
        }
        mToast.show();
    }
}
