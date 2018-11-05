package android.base.ui.widget;

import android.content.Context;
import androidx.appcompat.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.widget.Checkable;

/**
 * This is CustomCheckBox class used to show image. This class also used to
 * change the state of the image which looks similar to checkbox.
 *
 * @author amit.singh
 */
public class BaseCheckableImageView extends AppCompatImageView implements Checkable {

    /**
     * Instantiates a new Base checkable image mView.
     *
     * @param context the context
     */
    public BaseCheckableImageView(Context context) {
        super(context);
    }

    /**
     * Instantiates a new Base checkable image mView.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public BaseCheckableImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    private static final int[] CheckedStateSet = {android.R.attr.state_checked};

    private boolean mChecked = false;

    @Override
    public boolean isChecked() {
        return mChecked;
    }

    @Override
    public void setChecked(boolean b) {
        if (b != mChecked) {
            mChecked = b;
        }
        refreshDrawableState();
    }

    @Override
    public void toggle() {
        setChecked(!isChecked());
    }

    @Override
    public int[] onCreateDrawableState(int extraSpace) {
        final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
        if (isChecked()) {
            mergeDrawableStates(drawableState, CheckedStateSet);
        }
        return drawableState;
    }

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        invalidate();
    }

}
