package android.base.alert;

import android.view.View;

/**
 * Created by amit on 24/1/17.
 */

public interface OnSnackBarActionListener {
    /**
     * On snack bar action clicked.
     *
     * @param uniqueId the unique id
     * @param view     the mView
     */
    void onSnackBarActionClicked(int uniqueId, View view);
}
