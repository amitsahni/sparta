package android.base.alert;

import android.app.Activity;
import android.base.R;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;


/**
 * The type Snack builder.
 */
public class SnackBuilder {

    private SnackBarParam snackBarParam;
    private Snackbar mSnackbar;

    /**
     * Instantiates a new Snack builder.
     *
     * @param context the context
     * @param resId   the res id
     */
    public SnackBuilder(@NonNull Activity context, @StringRes int resId) {
        snackBarParam = new SnackBarParam();
        snackBarParam.activityContext = context;
        snackBarParam.context = context;
        snackBarParam.messageResId = resId;
    }

    /**
     * Instantiates a new Snack builder.
     *
     * @param context the context
     * @param msg     the msg
     */
    public SnackBuilder(@NonNull Activity context, @NonNull String msg) {
        snackBarParam = new SnackBarParam();
        snackBarParam.activityContext = context;
        snackBarParam.context = context;
        snackBarParam.message = msg;
    }

    /**
     * Instantiates a new Snack builder.
     *
     * @param context         the context
     * @param resId           the res id
     * @param backgroundColor background color of mSnackbar
     */
    public SnackBuilder(@NonNull Activity context, @StringRes int resId, @ColorRes int backgroundColor) {
        snackBarParam = new SnackBarParam();
        snackBarParam.activityContext = context;
        snackBarParam.context = context;
        snackBarParam.messageResId = resId;
        snackBarParam.actionBackgroundResId = backgroundColor;
    }

    /**
     * Instantiates a new Snack builder.
     *
     * @param context         the context
     * @param msg             the msg
     * @param backgroundColor background color of mSnackbar
     */
    public SnackBuilder(@NonNull Activity context, String msg, @ColorRes int backgroundColor) {
        snackBarParam = new SnackBarParam();
        snackBarParam.activityContext = context;
        snackBarParam.context = context;
        snackBarParam.message = msg;
        snackBarParam.actionBackgroundResId = backgroundColor;
    }

    /**
     * Action message snack builder.
     *
     * @param actionMessage the action message
     * @return the snack builder
     */
/*Set ActionMessage for SnackBuilder*/
    public SnackBuilder actionMessage(String actionMessage) {
        snackBarParam.actionMessage = actionMessage;
        return this;
    }

    /**
     * Action message snack builder.
     *
     * @param resId the res id
     * @return the snack builder
     */
/*Set ActionMessage resId for SnackBuilder*/
    public SnackBuilder actionMessage(@StringRes int resId) {
        snackBarParam.actionMessageResId = resId;
        return this;
    }

    /**
     * View snack builder.
     *
     * @param view the mView
     * @return the snack builder
     */
/*Set View for SnackBuilder*/
    public SnackBuilder view(View view) {
        snackBarParam.snackBarView = view;
        snackBarParam.snackBarView.setFocusable(false);
        return this;
    }

    /**
     * Listener snack builder.
     *
     * @param l the l
     * @return the snack builder
     */
/*Set Listener for SnackBuilder*/
    public SnackBuilder listener(OnSnackBarActionListener l) {
        snackBarParam.onSnackBarActionListener = l;
        return this;
    }

    /**
     * Action color snack builder.
     *
     * @param resId the res id
     * @return the snack builder
     */
/*Set color for SnackBuilder*/
    public SnackBuilder actionColor(@ColorRes int resId) {
        snackBarParam.actionColorResId = resId;
        return this;
    }

    /**
     * Text color snack builder.
     *
     * @param resId the res id
     * @return the snack builder
     */
/*Set color for SnackBuilder*/
    public SnackBuilder textColor(@ColorRes int resId) {
        snackBarParam.textColor = resId;
        return this;
    }

    /**
     * Duration snack builder.
     *
     * @param duration the duration
     * @return the snack builder
     */
    public SnackBuilder duration(int duration) {
        snackBarParam.snackBarDuration = duration;
        return this;
    }

    /**
     * Background color snack builder.
     *
     * @param color the color
     * @return the snack builder
     */
    public SnackBuilder backgroundColor(@ColorRes int color) {
        snackBarParam.actionBackgroundResId = color;
        return this;
    }

    /**
     * Background color blue snack builder during information
     *
     * @return the snack builder
     */
    public SnackBuilder info() {
        snackBarParam.actionBackgroundResId = SnackBarParam.blue;
        return this;
    }

    /**
     * Background color green snack builder during confirmation
     *
     * @return the snack builder
     */
    public SnackBuilder confirm() {
        snackBarParam.actionBackgroundResId = SnackBarParam.green;
        return this;
    }

    /**
     * Background color orange snack builder during warning
     *
     * @return the snack builder
     */
    public SnackBuilder warning() {
        snackBarParam.actionBackgroundResId = SnackBarParam.orange;
        return this;
    }

    /**
     * Background color red snack builder during warning
     *
     * @return the snack builder
     */
    public SnackBuilder alert() {
        snackBarParam.actionBackgroundResId = SnackBarParam.red;
        return this;
    }

    /**
     * Unique id snack builder.
     *
     * @param uniqueId the unique id
     * @return the snack builder
     */
    public SnackBuilder uniqueId(int uniqueId) {
        snackBarParam.alertTaskId = uniqueId;
        return this;
    }

    /**
     * Action message max lines snack builder.
     *
     * @param maxLines the max lines
     * @return the snack builder
     */
    public SnackBuilder actionMessageMaxLines(int maxLines) {
        snackBarParam.actionMessageMaxLine = maxLines;
        return this;
    }

    /**
     * Set typeface of text snack builder.
     *
     * @param typeface string name of assets typeface
     * @return the snack builder
     */
    @SuppressWarnings("Typeface must be defined in assets")
    public SnackBuilder typeface(@NonNull String typeface) {
        snackBarParam.typeface = typeface;
        return this;
    }

    /**
     * Show.
     */
    public void show() {
        if (mSnackbar != null)
            mSnackbar.dismiss();
        if (snackBarParam.activityContext == null) {
            return;
        }
        if (snackBarParam.snackBarView == null) {
            snackBarParam.snackBarView = snackBarParam.activityContext.findViewById(android.R.id.content);
        }
        mSnackbar = Snackbar.make(snackBarParam.snackBarView, "", snackBarParam.snackBarDuration);
        // Checked for Message
        if (snackBarParam.messageResId != -1) {
            mSnackbar.setText(snackBarParam.messageResId);
        } else if (!TextUtils.isEmpty(snackBarParam.message)) {
            mSnackbar.setText(snackBarParam.message);
        } else {
            mSnackbar.setText("");
        }
        // checked for ActionMessage
        if (snackBarParam.actionMessageResId != -1) {
            mSnackbar.setAction(snackBarParam.actionMessageResId, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (snackBarParam.onSnackBarActionListener == null) {
                        return;
                    }
                    snackBarParam.onSnackBarActionListener.onSnackBarActionClicked(snackBarParam.alertTaskId, v);
                }
            });
        } else if (!TextUtils.isEmpty(snackBarParam.actionMessage)) {
            mSnackbar.setAction(snackBarParam.actionMessage, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (snackBarParam.onSnackBarActionListener == null) {
                        return;
                    }
                    snackBarParam.onSnackBarActionListener.onSnackBarActionClicked(snackBarParam.alertTaskId, v);
                }
            });
        } else {
            mSnackbar.setAction("", null);
        }
        // checked for actionMessagecolor
        if (snackBarParam.actionColorResId != -1) {
            mSnackbar.setActionTextColor(ContextCompat.getColor(snackBarParam.activityContext, snackBarParam.actionColorResId));
        }
        TextView tv = (TextView) mSnackbar.getView().findViewById(R.id.snackbar_text);
        TextView tva = (TextView) mSnackbar.getView().findViewById(R.id.snackbar_action);
        if (snackBarParam.actionMessageMaxLine != -1)
            tv.setMaxLines(snackBarParam.actionMessageMaxLine);
        if (snackBarParam.textColor != -1) {
            tv.setTextColor(ContextCompat.getColorStateList(snackBarParam.activityContext, snackBarParam.textColor));
        }
        if (snackBarParam.actionBackgroundResId != -1)
            mSnackbar.getView().setBackgroundColor(ContextCompat.getColor(snackBarParam.activityContext, snackBarParam.actionBackgroundResId));
        //check typeface
        String typeface;
        if (!TextUtils.isEmpty(snackBarParam.typeface)) {
            typeface = snackBarParam.typeface;
            //set message typeface
            Alert.get().setTypeface(snackBarParam.context, tv, typeface);
            //set action typeface
            Alert.get().setTypeface(snackBarParam.context, tva, typeface);
        }

        mSnackbar.show();
    }


}