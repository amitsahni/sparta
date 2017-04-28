package android.base.alert;

import android.base.dialog.OnDialogProcess;
import android.base.util.ApplicationUtils;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.widget.TextView;


/**
 * The type Dialog builder.
 */
public class DialogBuilder {

    private AlertParam mAlertParam;

    /**
     * Instantiates a new Dialog builder.
     *
     * @param context the context
     */
    public DialogBuilder(@NonNull Context context) {
        mAlertParam = new AlertParam();
        mAlertParam.context = context;
        mAlertParam.style = -1;
    }

    /**
     * Instantiates a new Dialog builder.
     *
     * @param context    the context
     * @param dialogType the dialog type
     */
    public DialogBuilder(@NonNull Context context, @NonNull AlertParam.DialogType dialogType, @StyleRes int style) {
        mAlertParam = new AlertParam();
        mAlertParam.context = context;
        mAlertParam.dialogType = dialogType;
        mAlertParam.style = style;
    }

    /**
     * Message dialog builder.
     *
     * @param message the message
     * @return the dialog builder
     */
/*Set Message for AlertDialog*/
    public DialogBuilder message(String message) {
        mAlertParam.message = message;
        return this;
    }

    /**
     * Message dialog builder.
     *
     * @param resId the res id
     * @return the dialog builder
     */
/*Set Message resId for AlertDialog*/
    public DialogBuilder message(@StringRes int resId) {
        mAlertParam.messageResId = resId;
        return this;
    }

    /**
     * Title dialog builder.
     *
     * @param title the title
     * @return the dialog builder
     */
/*Set Title for AlertDialog*/
    public DialogBuilder title(String title) {
        mAlertParam.title = title;
        return this;
    }

    /**
     * Title dialog builder.
     *
     * @param resId the res id
     * @return the dialog builder
     */
/*Set Title resId for AlertDialog*/
    public DialogBuilder title(@StringRes int resId) {
        mAlertParam.titleResId = resId;
        return this;
    }

    /**
     * Dialog type dialog builder.
     *
     * @param dialogType the dialog type
     * @return the dialog builder
     */
/*Set DialogType for AlertDialog*/
    public DialogBuilder dialogType(@NonNull AlertParam.DialogType dialogType) {
        mAlertParam.dialogType = dialogType;
        return this;
    }

    /**
     * Icon dialog builder.
     *
     * @param icon the icon
     * @return the dialog builder
     */
/*Set icon for AlertDialog*/
    public DialogBuilder icon(@DrawableRes int icon) {
        mAlertParam.icon = icon;
        return this;
    }

    /**
     * Icon dialog builder.
     *
     * @param drawable the drawable
     * @return the dialog builder
     */
/*Set Drawable for AlertDialog*/
    public DialogBuilder icon(Drawable drawable) {
        mAlertParam.drawable = drawable;
        return this;
    }

    /**
     * Positive button dialog builder.
     *
     * @param positiveButton the positive button
     * @return the dialog builder
     */
/*Set PositiveButton Message for AlertDialog*/
    public DialogBuilder positiveButton(String positiveButton) {
        mAlertParam.positiveButton = positiveButton;
        return this;
    }

    /**
     * Negative button dialog builder.
     *
     * @param negativeButton the negative button
     * @return the dialog builder
     */
/*Set NegativeButton Message for AlertDialog*/
    public DialogBuilder negativeButton(String negativeButton) {
        mAlertParam.negativeButton = negativeButton;
        return this;
    }

    /**
     * Positive button dialog builder.
     *
     * @param resId the res id
     * @return the dialog builder
     */
/*Set PositiveButton Message resId for AlertDialog*/
    public DialogBuilder positiveButton(@StringRes int resId) {
        mAlertParam.positiveButtonResId = resId;
        return this;
    }

    /**
     * Negative button dialog builder.
     *
     * @param resId the res id
     * @return the dialog builder
     */
/*Set NegativeButton Message resId for AlertDialog*/
    public DialogBuilder negativeButton(@StringRes int resId) {
        mAlertParam.negativeButtonResId = resId;
        return this;
    }

    /**
     * Negative button color dialog builder.
     *
     * @param resId the res id
     * @return the dialog builder
     */
    public DialogBuilder negativeButtonColor(@ColorRes int resId) {
        mAlertParam.negativeBtnColor = resId;
        return this;
    }

    /**
     * Positive button color dialog builder.
     *
     * @param resId the res id
     * @return the dialog builder
     */
    public DialogBuilder positiveButtonColor(@ColorRes int resId) {
        mAlertParam.positiveBtnColor = resId;
        return this;
    }

    /**
     * Set typeface of text snack builder.
     *
     * @param typeface string name of assets typeface
     * @return the snack builder
     */
    @SuppressWarnings("Typeface must be defined in assets")
    public DialogBuilder typeface(String typeface) {
        mAlertParam.typeface = typeface;
        return this;
    }

    /**
     * Listener dialog builder.
     *
     * @param l the l
     * @return the dialog builder
     */
/*Set Listener for AlertDialog*/
    public DialogBuilder listener(OnDialogProcess l) {
        mAlertParam.listener = l;
        return this;
    }

    /**
     * Bundle dialog builder.
     *
     * @param bundle the bundle
     * @return the dialog builder
     */
/*Set data bundle for AlertDialog*/
    public DialogBuilder bundle(Bundle bundle) {
        mAlertParam.bundle = bundle;
        return this;
    }

    /**
     * Task id dialog builder.
     *
     * @param alertTaskId the alert task id
     * @return the dialog builder
     */
/* Set alertTask Id for Uniqueness */
    public DialogBuilder taskId(int alertTaskId) {
        mAlertParam.alertTaskId = alertTaskId;
        return this;
    }

    /**
     * Cancelable dialog builder.
     *
     * @param isCancel the is cancel
     * @return the dialog builder
     */
    public DialogBuilder cancelable(boolean isCancel) {
        mAlertParam.isCancelable = isCancel;
        return this;
    }

    /**
     * List dialog builder.
     *
     * @param list the list
     * @return the dialog builder
     */
    public DialogBuilder list(String[] list) {
        mAlertParam.list = list;
        return this;
    }

    /**
     * Show.
     */
    public void show() {
        // alert dialog functionality
        AlertDialog.Builder mAlert;
        if (mAlertParam.style != -1) {
            mAlert = new AlertDialog.Builder(mAlertParam.context, mAlertParam.style);
        } else {
            mAlert = new AlertDialog.Builder(mAlertParam.context);
        }
        // set the message
        if (mAlertParam.messageResId != -1) {
            mAlert.setMessage(mAlertParam.messageResId);
        } else if (!TextUtils.isEmpty(mAlertParam.message)) {
            mAlert.setMessage(mAlertParam.message);
        }
        // set title
        if (mAlertParam.titleResId != -1) {
            mAlert.setTitle(mAlertParam.titleResId);
        } else if (!TextUtils.isEmpty(mAlertParam.title)) {
            mAlert.setTitle(mAlertParam.title);
        } else {
            mAlert.setTitle("");
        }
        mAlert.setCancelable(mAlertParam.isCancelable);
        // set icon
        if (mAlertParam.icon != -1) {
            mAlert.setIcon(mAlertParam.icon);
        } else if (mAlertParam.drawable != null) {
            mAlert.setIcon(mAlertParam.drawable);
        } else {
            mAlert.setIcon(0);
        }
        // set Positive button functionality
        if (mAlertParam.dialogType == AlertParam.DialogType.DOUBLE_OPTION ||
                mAlertParam.dialogType == AlertParam.DialogType.SINGLE_OPTION) {
            if (mAlertParam.positiveButtonResId != -1) {
                mAlert.setPositiveButton(mAlertParam.positiveButtonResId,
                        new OnDialogClick(mAlertParam, 1));
            } else if (!TextUtils.isEmpty(mAlertParam.positiveButton)) {
                mAlert.setPositiveButton(mAlertParam.positiveButton,
                        new OnDialogClick(mAlertParam, 1));
            } else {
                mAlert.setPositiveButton("",
                        new OnDialogClick(mAlertParam, 1));
            }

        }
        // set Negative button functionality
        if (mAlertParam.dialogType == AlertParam.DialogType.DOUBLE_OPTION) {
            if (mAlertParam.negativeButtonResId != -1) {
                mAlert.setNegativeButton(mAlertParam.negativeButtonResId,
                        new OnDialogClick(mAlertParam, 2));
            } else if (!TextUtils.isEmpty(mAlertParam.negativeButton)) {
                mAlert.setNegativeButton(mAlertParam.negativeButton,
                        new OnDialogClick(mAlertParam, 2));
            } else {
                mAlert.setNegativeButton("",
                        new OnDialogClick(mAlertParam, 2));
            }
        }
        // List
        if (mAlertParam.dialogType == AlertParam.DialogType.DIALOG_LIST) {
            mAlert.setItems(mAlertParam.list, new OnDialogClick(mAlertParam, 3));
        }

        //create dialog
        final AlertDialog dialog = mAlert.create();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                if (mAlertParam.positiveBtnColor != -1) {
                    dialog.getButton(DialogInterface.BUTTON_POSITIVE)
                            .setTextColor(ContextCompat.getColorStateList(mAlertParam.context, mAlertParam.positiveBtnColor));
                }
                if (mAlertParam.negativeBtnColor != -1) {
                    dialog.getButton(DialogInterface.BUTTON_NEGATIVE)
                            .setTextColor(ContextCompat.getColorStateList(mAlertParam.context, mAlertParam.negativeBtnColor));
                }
            }
        });

        //display dialog
        dialog.show();

        try {
            //find message text mView
            TextView messageTextView = (TextView) dialog.findViewById(android.R.id.message);
            TextView button1TextView = (TextView) dialog.findViewById(android.R.id.button1);
            TextView button2TextView = (TextView) dialog.findViewById(android.R.id.button2);
            TextView button3TextView = (TextView) dialog.findViewById(android.R.id.button3);
            //check typeface
            String typeface;
            if (!TextUtils.isEmpty(mAlertParam.typeface)) {
                typeface = mAlertParam.typeface;
                //set message typeface
                Alert.get().setTypeface(mAlertParam.context, messageTextView, typeface);
                //set button1 typeface
                Alert.get().setTypeface(mAlertParam.context, button1TextView, typeface);
                //set button2 typeface
                Alert.get().setTypeface(mAlertParam.context, button2TextView, typeface);
                //set button3 typeface
                Alert.get().setTypeface(mAlertParam.context, button3TextView, typeface);
            }
        } catch (Exception e) {
            ApplicationUtils.Log.e(e.getMessage() + e);
        }


    }

    /* Handle Alert Dialog Button Click */
    private class OnDialogClick implements DialogInterface.OnClickListener {
        private AlertParam alertParam;
        private int buttonType;

        /**
         * Instantiates a new On dialog click.
         *
         * @param alertParam the alert param
         * @param buttonType the button type
         */
        public OnDialogClick(AlertParam alertParam, int buttonType) {
            this.alertParam = alertParam;
            this.buttonType = buttonType;
        }

        @Override
        public void onClick(DialogInterface dialog, int which) {
            if (alertParam.listener == null)
                return;
            if (buttonType == 3) {
                alertParam.bundle = new Bundle();
                alertParam.bundle.putString(AlertParam.ITEM, alertParam.list[which]);
                alertParam.bundle.putInt(AlertParam.POS, which);
            }
            alertParam.listener.onDialog(
                    alertParam.alertTaskId, alertParam.bundle == null ? new Bundle() : alertParam.bundle,
                    dialog, buttonType);
        }
    }


}

