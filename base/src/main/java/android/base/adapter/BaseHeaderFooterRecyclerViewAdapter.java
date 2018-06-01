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
public abstract class BaseHeaderFooterRecyclerViewAdapter<VH extends RecyclerView.ViewHolder, T> extends BaseRecyclerViewAdapter<VH, T> {
    private static final int TYPE_HEADER = Integer.MIN_VALUE;
    private static final int TYPE_FOOTER = Integer.MIN_VALUE + 1;
    private static final int TYPE_ADAPTEE_OFFSET = 2;
    private List<T> list = new ArrayList<>();
    private Context context;
    private BaseRecyclerViewAdapter.OnItemClickListener itemClickListener;
    private OnItemLongClickListener itemLongClickListener;

    /**
     * Instantiates a new Base header footer recycler mView adapter.
     *
     * @param context the context
     */
    public BaseHeaderFooterRecyclerViewAdapter(Context context) {
        super(context);
    }


    @Override
    public List<T> getList() {
        return list;
    }

    @Override
    public T getItem(int pos) {
        return this.list.get(pos);
    }


    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            return onCreateHeaderViewHolder(parent, viewType);
        } else if (viewType == TYPE_FOOTER) {
            return onCreateFooterViewHolder(parent, viewType);
        }
        return onCreateBasicItemViewHolder(parent, viewType - TYPE_ADAPTEE_OFFSET);
    }

    @Override
    public final void onBindViewHolder(VH holder, int position) {
        if (position == 0 && holder.getItemViewType() == TYPE_HEADER) {
            onBindHeaderView(holder, position);
        } else if (position == getBasicItemCount() && holder.getItemViewType() == TYPE_FOOTER) {
            onBindFooterView(holder, position);
        } else {
            onBindBasicItemView(holder, position - (useHeader() ? 1 : 0));
        }
    }

    @Override
    public int getItemCount() {
        int itemCount = getBasicItemCount();
        if (useHeader()) {
            itemCount += 1;
        }
        if (useFooter()) {
            itemCount += 1;
        }
        return itemCount;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 && useHeader()) {
            return TYPE_HEADER;
        }
        if (position >= getBasicItemCount() && useFooter()) {
            return TYPE_FOOTER;
        }
        int basicItemType = getBasicItemType(position - (useHeader() ? 1 : 0));
        if (basicItemType >= Integer.MAX_VALUE - TYPE_ADAPTEE_OFFSET) {
            new IllegalStateException(
                    "HeaderRecyclerViewAdapter offsets your BasicItemType by " + TYPE_ADAPTEE_OFFSET + ".");
        }
        return basicItemType + TYPE_ADAPTEE_OFFSET;
    }

    /**
     * Use header boolean.
     *
     * @return the boolean
     */
    public abstract boolean useHeader();

    /**
     * On create header mView holder vh.
     *
     * @param parent   the parent
     * @param viewType the mView type
     * @return the vh
     */
    public abstract VH onCreateHeaderViewHolder(ViewGroup parent, int viewType);

    /**
     * On bind header mView.
     *
     * @param holder   the holder
     * @param position the position
     */
    public abstract void onBindHeaderView(VH holder, int position);

    /**
     * Use footer boolean.
     *
     * @return the boolean
     */
    public abstract boolean useFooter();

    /**
     * On create footer mView holder vh.
     *
     * @param parent   the parent
     * @param viewType the mView type
     * @return the vh
     */
    public abstract VH onCreateFooterViewHolder(ViewGroup parent, int viewType);

    /**
     * On bind footer mView.
     *
     * @param holder   the holder
     * @param position the position
     */
    public abstract void onBindFooterView(VH holder, int position);

    /**
     * On create basic item mView holder vh.
     *
     * @param parent   the parent
     * @param viewType the mView type
     * @return the vh
     */
    public abstract VH onCreateBasicItemViewHolder(ViewGroup parent, int viewType);

    /**
     * On bind basic item mView.
     *
     * @param holder   the holder
     * @param position the position
     */
    public abstract void onBindBasicItemView(VH holder, int position);

    /**
     * Gets basic item count.
     *
     * @return the basic item count
     */
    public abstract int getBasicItemCount();

    /**
     * make sure you don't use [Integer.MAX_VALUE-1, Integer.MAX_VALUE] as BasicItemViewType
     *
     * @param position the position
     * @return basic item type
     */
    public abstract int getBasicItemType(int position);
}
