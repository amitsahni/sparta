package android.base.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * Common base class of common implementation for an Adapter that can be used
 * for listView, GridView etc..
 * <p class="note">
 * <strong>Note:</strong> If you want to use {@link Context} you should instead
 * use the {@link #getContext()}
 * </p>
 * <ul>
 * <li>
 * <P>
 * {@link #setList(List)} method is used to setmList to this adapter
 * </p>
 * <li>
 * <P>
 * {@link #getList()} method is used to getmList from this adapter.
 * </p>
 * <p>
 * </ul>
 *
 * @param <T> <P>            This class is of generic type. T here describes the getter/setter            class. T should is either {@link String} , {@link Integer} or any            user defined class model.            </p>
 * @author amit.singh
 */
public abstract class BaseAdapter<T> extends android.widget.BaseAdapter {
    private Context mContext;
    protected List<T> mList = new ArrayList<>();
    protected String TAG = BaseAdapter.class.getSimpleName();

    public BaseAdapter(Context context) {
        this.mContext = context;
    }

    public void setList(List<T> items) {
        if (items != null) {
            mList = new ArrayList<>(items);
            notifyDataSetChanged();
        }
    }

    public List<T> getList() {
        return mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public T getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return convertView;
    }

    /**
     * This method is used to get the mContext
     *
     * @return {@link Context}
     */
    public Context getContext() {
        return mContext;
    }

    public void removeItem(int pos) {
        this.mList.remove(pos);
        notifyDataSetChanged();
    }

    public void addItem(int pos, T item) {
        this.mList.add(pos, item);
        notifyDataSetChanged();
    }

    public void addItem(T item) {
        this.mList.add(item);
        notifyDataSetChanged();
    }

}
