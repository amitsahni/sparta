package android.base.ui.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import androidx.fragment.app.Fragment;

import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import com.booking.rtlviewpager.RtlViewPager;


/**
 * Created by clickapps on 30/6/15.
 * <p>
 * http://stackoverflow.com/questions/18462391/android-viewpager-scrolling-issue-with-only-one-item-when-using-getpagewidth-fro
 * <p>
 * http://stackoverflow.com/questions/32033111/set-viewpager-inside-scrollview-without-specifying-its-height
 */
public class BaseViewPager extends RtlViewPager {
    private Activity mActivity;
    private boolean isPagingEnabled = true;
    private boolean isHeightChanges = false;
    private int childId = 0;

    /*Vriable is used whenever children are required to align center horizontal
    * Added on 24th Sept, 2015 - Manmeet*/
    private boolean isChildCenterHorizontalAligned = false;

    /**
     * Instantiates a new Base mView pager.
     *
     * @param context the context
     */
    public BaseViewPager(Context context) {
        super(context);
    }

    /**
     * Instantiates a new Base mView pager.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public BaseViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Init.
     *
     * @param attrs the attrs
     */
    public void init(AttributeSet attrs) {

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (childId > 0) {
            View scroll = findViewById(childId);
            if (scroll != null) {
                Rect rect = new Rect();
                scroll.getHitRect(rect);
                if (rect.contains((int) event.getX(), (int) event.getY())) {
                    return false;
                }
            }
        } else if (isPagingEnabled) {
            return super.onInterceptTouchEvent(event);
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return isPagingEnabled && super.onTouchEvent(event);
    }

    /**
     * Sets paging enabled.
     *
     * @param pagingEnabled the paging enabled
     */
    public void setPagingEnabled(boolean pagingEnabled) {
        isPagingEnabled = pagingEnabled;
    }

    /**
     * Sets child id.
     *
     * @param childId the child id
     */
    public void setChildId(int childId) {
        this.childId = childId;
    }

    /**
     * Sets height change.
     *
     * @param heightChange the height change
     */
    public void setHeightChange(boolean heightChange) {
        isHeightChanges = heightChange;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // super has to be called in the beginning so the child views can be
        // initialized.
        if (isHeightChanges) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);

            if (getChildCount() <= 0)
                return;

            // Check if the selected layout_height mode is set to wrap_content
            // (represented by the AT_MOST constraint).
            boolean wrapHeight = MeasureSpec.getMode(heightMeasureSpec)
                    == MeasureSpec.AT_MOST;

            int width = getMeasuredWidth();

            View firstChild = getChildAt(0);

            // Initially set the height to that of the first child - the
            // PagerTitleStrip (since we always know that it won't be 0).
            int height = firstChild.getMeasuredHeight();

            if (wrapHeight) {

                // Keep the current measured width.
                widthMeasureSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);

            }

            int fragmentHeight = 0;
            if (getAdapter().instantiateItem(this, getCurrentItem()) instanceof Fragment) {
                fragmentHeight = measureFragment(((Fragment) getAdapter().instantiateItem(this, getCurrentItem())).getView());

                // Just add the height of the fragment:
                heightMeasureSpec = MeasureSpec.makeMeasureSpec(height + fragmentHeight,
                        MeasureSpec.EXACTLY);
            }
        }
        // super has to be called again so the new specs are treated as
        // exact measurements.
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * Measure fragment int.
     *
     * @param view the mView
     * @return the int
     */
    public int measureFragment(View view) {
        if (view == null)
            return 0;

        view.measure(0, 0);
        return view.getMeasuredHeight();
    }

    @Override
    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        //logic added to align center children of viewpager
        //on 24th Sept, 2015 - Manmeet
        if (isChildCenterHorizontalAligned()) {
            int childCount = getChildCount();
            if (childCount > 0 && mActivity != null) {
                DisplayMetrics dimensions = getContext().getResources().getDisplayMetrics();
                if (dimensions != null && dimensions.widthPixels > 0) {
//                    canvas.save();
//                    canvas.translate((dimensions.widthPixels / 2) - (child.getWidth() * childCount) / 2, 0);  // change any distance value you want

                    this.setPadding(//left
                            (dimensions.widthPixels / 2) - (child.getWidth() * childCount) / 2,
                            //top
                            0,
                            //right
                            0,
                            //bottom
                            0);

                    boolean r = super.drawChild(canvas, child, drawingTime);
//                    canvas.restore();
                    invalidate();
                    return r;
                }
            }
        }
        return super.drawChild(canvas, child, drawingTime);
    }

    /**
     * Is child center horizontal aligned boolean.
     *
     * @return the boolean
     */
    public boolean isChildCenterHorizontalAligned() {
        return isChildCenterHorizontalAligned;
    }

    /**
     * Sets is child center horizontal aligned.
     *
     * @param isChildCenterHorizontalAligned the is child center horizontal aligned
     * @param activity                       the activity
     */
    public void setIsChildCenterHorizontalAligned(boolean isChildCenterHorizontalAligned, Activity activity) {
        this.mActivity = activity;
        this.isChildCenterHorizontalAligned = isChildCenterHorizontalAligned;
    }
}
