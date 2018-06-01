package android.base.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by clickapps on 2/12/15.
 *
 * @param <VH> the type parameter
 * @param <T>  the type parameter
 */
public abstract class BaseRecyclerViewAdapter<VH extends RecyclerView.ViewHolder, T> extends RecyclerView.Adapter<VH> {

    private OnItemClickListener<T> mItemClickListener;
    private OnItemLongClickListener<T> mItemLongClickListener;
    private Context mContext;

    /**
     * Instantiates a new Base recycler mView adapter.
     *
     * @param context the mContext
     */
    public BaseRecyclerViewAdapter(Context context) {
        this.mContext = context;
    }

    /**
     * The interface On item click listener.
     *
     * @param <T> the type parameter
     */
    public interface OnItemClickListener<T> {
        /**
         * On item click.
         *
         * @param view     the mView
         * @param position the position
         * @param model    the model
         */
        void onItemClick(View view, int position, T model);
    }

    /**
     * The interface On item long click listener.
     *
     * @param <T> the type parameter
     */
    public interface OnItemLongClickListener<T> {
        /**
         * On item long click boolean.
         *
         * @param view     the mView
         * @param position the position
         * @param model    the model
         * @return the boolean
         */
        boolean onItemLongClick(View view, int position, T model);
    }

    private List<T> list = new ArrayList<>();

    /**
     * Gets list.
     *
     * @return the list
     */
    public List<T> getList() {
        return list;
    }

    /**
     * Sets list.
     *
     * @param list the list
     */
    public void setList(@NonNull List<T> list) {
        this.list = new ArrayList<>(list);
        notifyDataSetChanged();
    }

    /**
     * Sets on item click listener.
     *
     * @param l the l
     */
    public void setOnItemClickListener(@NonNull OnItemClickListener l) {
        mItemClickListener = l;
    }

    /**
     * Sets on item long click listener.
     *
     * @param l the l
     */
    public void setOnItemLongClickListener(@NonNull OnItemLongClickListener l) {
        mItemLongClickListener = l;
    }

    /**
     * Gets item click listener.
     *
     * @return the item click listener
     */
    public
    @Nullable
    OnItemClickListener<T> getItemClickListener() {
        return mItemClickListener;
    }

    /**
     * Gets item long click listener.
     *
     * @return the item long click listener
     */
    public
    @Nullable
    OnItemLongClickListener<T> getItemLongClickListener() {
        return mItemLongClickListener;
    }

    /**
     * Gets mContext.
     *
     * @return the mContext
     */
    public Context getContext() {
        return mContext;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    /**
     * Gets item.
     *
     * @param pos the pos
     * @return the item
     */
    public T getItem(int pos) {
        return this.list.get(pos);
    }

    /**
     * Remove item.
     *
     * @param pos the pos
     */
    public void removeItem(int pos) {
        this.list.remove(pos);
        notifyItemRemoved(pos);
        notifyDataSetChanged();
    }

    /**
     * Add item.
     *
     * @param pos  the pos
     * @param item the item
     */
    public void addItem(int pos, T item) {
        this.list.add(pos, item);
        notifyItemInserted(pos);
    }

    /**
     * Add item.
     *
     * @param item the item
     */
    public void addItem(T item) {
        this.list.add(item);
        notifyItemInserted(list.size() - 1);
    }
}
