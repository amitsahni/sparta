package android.base.ui.widget;

import android.base.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


/**
 * Created by clickapps on 2/7/15.
 */
public class BaseListView extends ListView {
    // private TextView mLabLoadMore;
    private BaseProgressBar mProgressBarLoadMore;
    private boolean mIsLoadingMore = false;
    private View mView;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    private BaseTextView baseTextView;

    /**
     * Instantiates a new Base list mView.
     *
     * @param context the context
     */
    public BaseListView(Context context) {
        super(context);
        init();
    }

    /**
     * Instantiates a new Base list mView.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public BaseListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     * Instantiates a new Base list mView.
     *
     * @param context      the context
     * @param attrs        the attrs
     * @param defStyleAttr the def style attr
     */
    public BaseListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mView = LayoutInflater.from(getContext()).inflate(R.layout.loadmore_footer_progress_base, null);
        mProgressBarLoadMore = (BaseProgressBar) mView.findViewById(R.id.loadmore_footer_progressViewApplication);
        setFooterDividersEnabled(false);
    }

    /**
     * Sets empty mView.
     *
     * @param resId the res id
     */
    public void setEmptyView(int resId) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_empty_base, null);
        baseTextView = (BaseTextView) view.findViewById(R.id.view_empty_baseTextView);
        baseTextView.setText(resId);
        ((ViewGroup) getParent()).addView(view,
                getLayoutParams());
        setEmptyView(view);
    }

    /**
     * Sets empty mView.
     *
     * @param message the message
     */
    public void setEmptyView(String message) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_empty_base, null);
        baseTextView = (BaseTextView) view.findViewById(R.id.view_empty_baseTextView);
        baseTextView.setText(message);
        ((ViewGroup) getParent()).addView(view,
                getLayoutParams());
        setEmptyView(view);
    }

    /**
     * Sets empty text.
     *
     * @param resId the res id
     */
    public void setEmptyText(int resId) {
        if (baseTextView == null) return;
        baseTextView.setText(resId);
    }

    /**
     * Sets empty text.
     *
     * @param message the message
     */
    public void setEmptyText(String message) {
        if (baseTextView == null) return;
        baseTextView.setText(message);
    }

    /**
     * Add footer mView.
     */
    public void addFooterView() {
        super.addFooterView(mView);
    }

    @Override
    public void addFooterView(View v) {
        super.addFooterView(v, null, false);
    }


}
