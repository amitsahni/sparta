package android.base.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * Common base class of common implementation for an Adapter that can be used
 * for listView, GridView etc..
 * <p class="note">
 * <strong>Note:</strong> If you want to use {@link Context} you should instead
 * use the {@link #getContext()} and to use Activity mContext use
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
@SuppressWarnings("rawtypes")
public abstract class BaseArrayAdapter<T> extends ArrayAdapter {
    private List<T> mList = new ArrayList<>();
    private Activity mContext;

    /**
     * Instantiates a new Base array adapter.
     *
     * @param context the mContext
     */
    public BaseArrayAdapter(Context context) {
        super(context, android.R.layout.simple_list_item_1);
    }

    /**
     * Instantiates a new Base array adapter.
     *
     * @param context the mContext
     */
    public BaseArrayAdapter(Activity context) {
        super(context, android.R.layout.simple_list_item_1);
        this.mContext = context;
    }

    /**
     * This method is used to setmList containing ListArray<T>
     *
     * @param items the items
     */
    public void setList(List<T> items) {
        mList = new ArrayList<>(items);
        this.notifyDataSetChanged();
    }

    /**
     * This method is used to get the setListArray
     *
     * @return ListArray<T> list
     */
    public List<T> getList() {
        return mList;
    }

    @Override
    public int getCount() {
        if (mList == null)
            return 0;
        return mList.size();
    }

    @Override
    public T getItem(int position) {
        return mList.get(position);
    }

    /**
     * This method is used to get the mContext
     *
     * @return {@link Context}
     */
    public Context getContext() {
        return mContext;
    }

    /**
     * Gets activity mContext.
     *
     * @return the activity mContext
     */
    @Nullable
    public Activity getActivityContext() {
        return mContext;
    }
}
