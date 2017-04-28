package android.base.dialog;

import android.os.Bundle;

/**
 * This interface is used as a call back for the dialogs
 */
public interface OnDialogProcess {
    /**
     * The constant POSITIVE.
     */
    int POSITIVE = 1;
    /**
     * The constant NEGATIVE.
     */
    int NEGATIVE = 2;
    /**
     * The constant NEUTRAL.
     */
    int NEUTRAL = 3;
    /**
     * The constant VIEW.
     */
    int VIEW = 4;


    /**
     * callback for the mView that is clicked/selected to perform other tasks
     *
     * @param dialogId      to identify dialog
     * @param bundle        data that can required by activity/fragment
     * @param object        Dialog class object
     * @param selectionType type LEFT, RIGHT, MIDDLE,VIEW
     */
    void onDialog(int dialogId, Bundle bundle, Object object, int selectionType);

}
