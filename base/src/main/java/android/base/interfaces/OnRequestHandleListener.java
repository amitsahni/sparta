package android.base.interfaces;

import android.os.Bundle;
import android.view.View;


/**
 * The interface On request handle listener.
 */
public interface OnRequestHandleListener {
    /**
     * call back for the list item click
     *
     * @param <T>    object of generic type
     * @param view   List row/item mView/any other mView
     * @param bundle bundle containing data to transfer via callback
     * @param id     integer value to identify task to be performed
     * @param t      the t
     */
    <T> void onRequest(View view, Bundle bundle, int id, T t);
}

