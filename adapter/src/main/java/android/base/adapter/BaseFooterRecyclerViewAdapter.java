package android.base.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * https://gist.github.com/sebnapi/fde648c17616d9d3bcde
 * <p>
 * If you extend this Adapter you are able to add a Header, a Footer or both
 * by a similar ViewHolder pattern as in RecyclerView.
 * <p>
 * If you want to omit changes to your class hierarchy you can try the Plug-and-Play
 * approach HeaderRecyclerViewAdapterV1.
 * <p>
 * Don't override (Be careful while overriding)
 * - onCreateViewHolder
 * - onBindViewHolder
 * - getItemCount
 * - getItemViewType
 * <p>
 * You need to override the abstract methods introduced by this class. This class
 * is not using generics as RecyclerView.Adapter make yourself sure to cast right.
 * <p>
 * TOTALLY UNTESTED - USE WITH CARE - HAVE FUN :)
 *
 * @param <VH> the type parameter
 * @param <T>  the type parameter
 */
public abstract class BaseFooterRecyclerViewAdapter<VH extends RecyclerView.ViewHolder, FH extends RecyclerView.ViewHolder, T> extends BaseRecyclerViewAdapter<RecyclerView.ViewHolder, T> {
    private static final int TYPE_FOOTER = Integer.MIN_VALUE;
    private static final int TYPE_ADAPTEE_OFFSET = 1;
    private int basicItemCount = 0;
    private boolean hideFooter = false;
    private boolean useFooter = true;
    private List<T> list = new ArrayList<>();

    /**
     * Instantiates a new Base header footer recycler mView adapter.
     *
     * @param context the context
     */
    public BaseFooterRecyclerViewAdapter(Context context) {
        super(context);
    }

    @Override
    public void setList(@Nullable List<T> list) {
        if (list != null) {
            this.list = new ArrayList<>(list);
            hideFooter = false;
            notifyDataSetChanged();
        }
    }

    @Override
    public List<T> getList() {
        return list;
    }

    @Override
    public T getItem(int pos) {
        return this.list.get(pos);
    }

    /**
     * Sets hide footer.
     */
    public void setHideFooter() {
        hideFooter = true;
        notifyDataSetChanged();
    }

    /**
     * Is hide footer boolean.
     *
     * @return the boolean
     */
    public boolean isHideFooter() {
        return hideFooter;
    }

    @Deprecated
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOTER) {
            return onCreateFooterViewHolder(parent, viewType);
        } else {
            return onCreateBasicItemViewHolder(parent, viewType - TYPE_ADAPTEE_OFFSET);
        }
    }

    @Deprecated
    @Override
    public final void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == basicItemCount && holder.getItemViewType() == TYPE_FOOTER) {
            onBindFooterView((FH) holder, position);
        } else {
            onBindBasicItemView((VH) holder, position);
        }
    }

    @Deprecated
    @Override
    public int getItemCount() {
        basicItemCount = getList().size();
        int itemCount = basicItemCount;
        if (useFooter) {
            itemCount += 1;
        }
        return itemCount;
    }

    @Override
    public int getItemViewType(int position) {
        if (position >= basicItemCount && useFooter) {
            return TYPE_FOOTER;
        }
        return position;
    }

    public boolean useFooter() {
        useFooter = true;
        return useFooter;
    }

    public boolean useFooter(boolean isFooter) {
        useFooter = isFooter;
        return useFooter;
    }

    public FH onCreateFooterViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    public void onBindFooterView(FH holder, int position) {
    }

    public VH onCreateBasicItemViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    public void onBindBasicItemView(VH holder, int position) {
    }

    public int getBasicItemCount() {
        return getList().size();
    }

}
