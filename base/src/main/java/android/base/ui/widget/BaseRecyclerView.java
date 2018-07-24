package android.base.ui.widget;

import android.base.interfaces.OnLoadMoreListener;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by clickapps on 2/12/15.
 */
public class BaseRecyclerView extends RecyclerView {
    private View emptyView;
    private OnLoadMoreListener loadMoreListener;

    /**
     * Instantiates a new Base recycler mView.
     *
     * @param context the context
     */
    public BaseRecyclerView(Context context) {
        super(context);
    }

    /**
     * Instantiates a new Base recycler mView.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public BaseRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Instantiates a new Base recycler mView.
     *
     * @param context  the context
     * @param attrs    the attrs
     * @param defStyle the def style
     */
    public BaseRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    final private AdapterDataObserver observer = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            checkIfEmpty();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            checkIfEmpty();
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            checkIfEmpty();
        }
    };

    private void checkIfEmpty() {
        if (emptyView != null && getAdapter() != null) {
            final boolean emptyViewVisible = getAdapter().getItemCount() == 0;
            emptyView.setVisibility(emptyViewVisible ? VISIBLE : GONE);
            setVisibility(emptyViewVisible ? GONE : VISIBLE);
        }
    }

    @Override
    public void setAdapter(Adapter adapter) {
        final Adapter oldAdapter = getAdapter();
        if (oldAdapter != null) {
            oldAdapter.unregisterAdapterDataObserver(observer);
        }
        super.setAdapter(adapter);
        if (adapter != null) {
            adapter.registerAdapterDataObserver(observer);
        }
        checkIfEmpty();
    }

    /**
     * Sets empty mView.
     *
     * @param emptyView the empty mView
     */
    public void setEmptyView(@NonNull View emptyView) {
        this.emptyView = emptyView;
        checkIfEmpty();
    }

    private boolean loading = false;
    /**
     * The Past visibles items.
     */
    int pastVisiblesItems, /**
     * The Visible item count.
     */
    visibleItemCount, /**
     * The Total item count.
     */
    totalItemCount;

    /**
     * Sets on load more listener.
     *
     * @param onLoadMoreListener the on load more listener
     */
    public void setOnLoadMoreListener(final OnLoadMoreListener onLoadMoreListener) {
        loadMoreListener = onLoadMoreListener;
        this.addOnScrollListener(scrollListener);
    }

    /**
     * Notify the loading more operation has finished
     */
    public void onLoadMoreComplete() {
        loading = false;
    }

    private OnScrollListener scrollListener = new OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if (dy > 0 && getLayoutManager() instanceof LinearLayoutManager && loadMoreListener != null) //check for scroll down
            {
                visibleItemCount = getLayoutManager().getChildCount();
                totalItemCount = getLayoutManager().getItemCount();
                pastVisiblesItems = ((LinearLayoutManager) getLayoutManager()).findFirstVisibleItemPosition();
                if (!loading) {
                    if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                        loading = true;
                        loadMoreListener.onLoadMore();
                    }
                }
            }
        }
    };
}
