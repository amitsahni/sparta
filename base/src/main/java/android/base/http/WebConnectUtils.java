package android.base.http;

import android.base.R;
import android.base.dialog.BaseDialog;
import android.base.ui.custom.ProgressViewApplication;
import android.base.dialog.BaseProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;


/**
 * The type Web connect utils.
 */
public class WebConnectUtils {
//    private static final int DEFAULT_MESSAGE = R.string.loading;
    private static final String DEFAULT_MESSAGE = "";

    /**
     * Resolve progress dialog base progress dialog.
     *
     * @param webParam the web param
     * @return the base progress dialog
     */
    public static BaseDialog resolveProgressDialog(WebParam webParam) {
        if (webParam.progressDialog == null) {
            ProgressViewApplication progressDialog = new ProgressViewApplication(webParam.context);
            progressDialog.getTextView().setText(TextUtils.isEmpty(webParam.progressDialogMessage)
                    ? DEFAULT_MESSAGE : webParam.progressDialogMessage);
            if (webParam.indeterminateDrawable != -1)
                progressDialog.getProgressBar().
                        setIndeterminateDrawable(ContextCompat.getDrawable(webParam.context, webParam.indeterminateDrawable));
            if (webParam.progressDialogColor != -1) {
                progressDialog.getProgressBar().setIndeterminateTint(webParam.progressDialogColor);
            }
            if (webParam.progressDialogTextColor != -1) {
                progressDialog.getTextView().
                        setTextColor(ContextCompat.getColorStateList(webParam.context, webParam.progressDialogTextColor));
            }
            webParam.progressDialog = progressDialog.getProgressDialog();
            webParam.progressDialog.setCanceledOnTouchOutside(false);
            webParam.progressDialog.setCancelable(true);
        } else {
            webParam.progressDialog.setCanceledOnTouchOutside(false);
            webParam.progressDialog.setCancelable(true);
        }

        return webParam.progressDialog;
    }
}
