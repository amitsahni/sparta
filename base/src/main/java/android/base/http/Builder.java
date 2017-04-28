package android.base.http;

import android.app.Activity;
import android.base.dialog.BaseDialog;
import android.base.dialog.BaseProgressDialog;
import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.File;
import java.util.Map;


/**
 * The type Builder.
 */
public class Builder {


    private WebParam mWebParam;

    /**
     * Instantiates a new Builder.
     *
     * @param context the context
     * @param url     the url
     */
    public Builder(@NonNull Activity context, @NonNull String url) {
        mWebParam = new WebParam();
        mWebParam.activityContext = context;
        mWebParam.context = context;
        mWebParam.url = url;
    }

    /**
     * Instantiates a new Builder.
     *
     * @param context the context
     * @param url     the url
     */
    public Builder(@NonNull Context context, @NonNull String url) {
        mWebParam = new WebParam();
        mWebParam.context = context;
        mWebParam.url = url;
    }


    /**
     * Base url builder.
     *
     * @param url the url
     * @return the builder
     */
    public Builder baseUrl(@NonNull String url) {
        mWebParam.baseUrl = url;
        return this;
    }

    /**
     * Http type builder.
     *
     * @param httpType the http type
     * @return the builder
     */
    public Builder httpType(@NonNull WebParam.HttpType httpType) {
        mWebParam.httpType = httpType;
        return this;
    }

    /**
     * Request param builder.
     *
     * @param requestParam the request param
     * @return the builder
     */
    public Builder requestParam(@NonNull Map<String, ?> requestParam) {
        mWebParam.requestParam = requestParam;
        return this;
    }

    /**
     * Request param builder.
     *
     * @param multipartParam the multipart Param
     * @return the builder
     */
    public Builder multipartParam(@NonNull Map<String, ?> multipartParam) {
        mWebParam.multipartParam = multipartParam;
        return this;
    }

    /**
     * Header param builder.
     *
     * @param headerParam the header param
     * @return the builder
     */
    public Builder headerParam(@NonNull Map<String, String> headerParam) {
        mWebParam.headerParam = headerParam;
        return this;
    }

    /**
     * Callback builder.
     *
     * @param callback the callback
     * @return the builder
     */
    public Builder callback(@NonNull WebHandler.OnWebCallback callback) {
        mWebParam.callback = callback;
        return this;
    }

    /**
     * Callback builder.
     *
     * @param callback the callback
     * @param success  the success
     * @param error    the error
     * @return the builder
     */
    public Builder callback(@NonNull WebHandler.OnWebCallback callback,
                            @NonNull Class<?> success, @NonNull Class<?> error) {
        mWebParam.callback = callback;
        mWebParam.model = success;
        mWebParam.error = error;
        return this;
    }

    /**
     * Success model builder.
     *
     * @param success the success
     * @return the builder
     */
    public Builder successModel(@NonNull Class<?> success) {
        mWebParam.model = success;
        return this;
    }

    /**
     * Error model builder.
     *
     * @param error the error
     * @return the builder
     */
    public Builder errorModel(@NonNull Class<?> error) {
        mWebParam.error = error;
        return this;
    }

    /**
     * Task id builder.
     *
     * @param taskId the task id
     * @return the builder
     */
    public Builder taskId(int taskId) {
        mWebParam.taskId = taskId;
        return this;
    }

    /**
     * Progress dialog builder.
     *
     * @param progressDialog the progress dialog
     * @param message        the message
     * @return the builder
     */
    public Builder progressDialog(@NonNull BaseDialog progressDialog, @Nullable String message) {
        mWebParam.progressDialog = progressDialog;
        mWebParam.progressDialogMessage = message;
        return this;
    }

    /**
     * Progress dialog builder.
     *
     * @param progressDialog the progress dialog
     * @return the builder
     */
    public Builder progressDialog(@NonNull BaseDialog progressDialog) {
        mWebParam.progressDialog = progressDialog;
        return this;
    }

    /**
     * Progress dialog builder.
     *
     * @param message the progress dialog
     * @return the builder
     */
    public Builder progressDialogText(@NonNull String message) {
        mWebParam.progressDialogMessage = message;
        return this;
    }

    /**
     * Progress dialog color builder.
     *
     * @param progressDialogColor the progress dialog color
     * @return the builder
     */
    public Builder progressDialogColor(@ColorRes int progressDialogColor) {
        mWebParam.progressDialogColor = progressDialogColor;
        return this;
    }

    /**
     * Progress dialog IndeterminateDrawable.
     *
     * @param indeterminateDrawable the progress dialog IndeterminateDrawable
     * @return the builder
     */
    public Builder progressDialogIndeterminateDrawable(@DrawableRes int indeterminateDrawable) {
        mWebParam.indeterminateDrawable = indeterminateDrawable;
        return this;
    }

    /**
     * Progress dialog text color.
     *
     * @param progressDialogTextColor the progress dialog text color
     * @return the builder
     */
    public Builder progressDialogTextColor(@ColorRes int progressDialogTextColor) {
        mWebParam.progressDialogTextColor = progressDialogTextColor;
        return this;
    }

    /**
     * Show dialog builder.
     *
     * @param showDialog the show dialog
     * @return the builder
     */
    public Builder showDialog(boolean showDialog) {
        mWebParam.showDialog = showDialog;
        return this;
    }

    public WebParam getWebParam() {
        return mWebParam;
    }

    /**
     * Connect t.
     *
     * @param <T> the type parameter
     * @param cls the cls
     * @return the t
     */
    public <T> T connect(Class<T> cls) {
        return new RetrofitManager().createService(cls, mWebParam);
    }
}
