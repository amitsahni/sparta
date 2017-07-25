package android.base.adapter;

import android.content.Context;
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
public abstract class BaseFooterRecyclerViewAdapter<VH extends RecyclerView.ViewHolder, T> extends BaseRecyclerViewAdapter<VH, T> {
    private static final int TYPE_FOOTER = Integer.MIN_VALUE;
    private static final int TYPE_ADAPTEE_OFFSET = 1;
    private int basicItemCount = 0;

    /**
     * Instantiates a new Base header footer recycler mView adapter.
     *
     * @param context the context
     */
    public BaseFooterRecyclerViewAdapter(Context context) {
        super(context);
    }

    @Deprecated
    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOTER) {
            return onCreateFooterViewHolder(parent, viewType);
        } else {
            return onCreateBasicItemViewHolder(parent, viewType - TYPE_ADAPTEE_OFFSET);
        }
    }
    @Deprecated
    @Override
    public final void onBindViewHolder(VH holder, int position) {
        if (position == basicItemCount && holder.getItemViewType() == TYPE_FOOTER) {
            onBindFooterView(holder, position);
        } else {
            onBindBasicItemView(holder, position);
        }
    }

    @Override
    public int getItemCount() {
        basicItemCount = getList().size();
        int itemCount = basicItemCount;
        if (useFooter()) {
            itemCount += 1;
        }
        return itemCount;
    }

    @Override
    public int getItemViewType(int position) {
        if (position >= basicItemCount && useFooter()) {
            return TYPE_FOOTER;
        }
        return position;
    }


    /**
     * Use footer boolean.
     *
     * @return the boolean
     */
    public boolean useFooter() {
        return false;
    }

    /**
     * On create footer mView holder vh.
     *
     * @param parent   the parent
     * @param viewType the mView type
     * @return the vh
     */
    public VH onCreateFooterViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    /**
     * On bind footer mView.
     *
     * @param holder   the holder
     * @param position the position
     */
    public void onBindFooterView(VH holder, int position) {
    }

    /**
     * On create basic item mView holder vh.
     *
     * @param parent   the parent
     * @param viewType the mView type
     * @return the vh
     */
    public VH onCreateBasicItemViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    /**
     * On bind basic item mView.
     *
     * @param holder   the holder
     * @param position the position
     */
    public void onBindBasicItemView(VH holder, int position) {
    }


}
