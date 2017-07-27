package android.base.plusutil;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Created by clickapps on 27/7/17.
 */

public class Builder {

    private Param param;

    /**
     * Instantiates a new Builder.
     *
     * @param context the context
     */
    public Builder(@NonNull Context context) {
        param = new Param();
        param.context = context;
    }

    /**
     * Instantiates a new Builder.
     *
     * @param context the context
     */
    public Builder(@NonNull Context context, @NonNull Param.GooglePlusListener listener) {
        param = new Param();
        param.context = context;
        param.listener = listener;
    }

    /**
     * Instantiates a new Builder.
     *
     * @param context the context
     */
    public Builder(@NonNull Activity context) {
        param = new Param();
        param.context = context;
        param.activity = context;
    }

    /**
     * Instantiates a new Builder.
     *
     * @param context the context
     */
    public Builder(@NonNull Activity context, @NonNull Param.GooglePlusListener listener) {
        param = new Param();
        param.context = context;
        param.activity = context;
        param.listener = listener;
    }

    public Builder callback(@NonNull Param.GooglePlusListener listener) {
        param.listener = listener;
        return this;
    }

    public void connect() {
        new GooglePlus().connect(param);
    }

    public void build() {
        new GooglePlus().handleEvent(param);
    }
}
