package android.base.image;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.util.Map;

import id.zelory.compressor.Compressor;


/**
 * The type Image util.
 */
public class ImageUtil {
    private static volatile ImageUtil sImageUtil;

    private ImageUtil() {

    }

    /**
     * Get image util.
     *
     * @return the image util
     */
    public static ImageUtil get() {
        if (sImageUtil == null) {
            synchronized (ImageUtil.class) {
                if (sImageUtil == null) {
                    sImageUtil = new ImageUtil();
                }
            }
        }
        return sImageUtil;
    }

    public void enableLog(boolean isEnabled){
        GlideUtil.LOG_ENABLED = isEnabled;
    }
    /**
     * With builder.
     *
     * @param context the context
     * @param url     the url
     * @return the builder
     */
    @Deprecated
    public static Builder with(@NonNull Context context, @NonNull String url) {
        return new Builder(context, url, null);
    }

    /**
     * With builder.
     *
     * @param context the context
     * @param url     the url
     * @return the builder
     */
    @Deprecated
    public static Builder with(@NonNull Activity context, @NonNull String url) {
        return new Builder(context, url, null);
    }

    /**
     * With builder.
     *
     * @param context the context
     * @param url     the url
     * @return the builder
     */
    public static Builder with(@NonNull Context context, @NonNull String url, @NonNull ImageView imageView) {
        return new Builder(context, url, imageView);
    }

    /**
     * With builder.
     *
     * @param context the context
     * @param url     the url
     * @return the builder
     */
    public static Builder with(@NonNull Activity context, @NonNull String url, @NonNull ImageView imageView) {
        return new Builder(context, url, imageView);
    }
}
