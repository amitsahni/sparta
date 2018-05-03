package android.base.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by clickapps on 9/2/16.
 */
public abstract class BaseFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<FragmentPagerModel> mList = new ArrayList<>();
    private Context mContext;
    private int mCount;
    private Map<Integer, Fragment> mCurrentFragmentList = new LinkedHashMap<>();

    /**
     * Instantiates a new Base fragment pager adapter.
     *
     * @param fm      the fm
     * @param context the mContext
     */
    public BaseFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.mContext = context;
        mCount = mList.size();
    }

    /**
     * Sets mList.
     *
     * @param items the items
     */
    public void setList(@NonNull List<FragmentPagerModel> items) {
        mList = new ArrayList<>(items);
        mCount = mList.size();
        this.notifyDataSetChanged();
    }

    /**
     * Gets mList.
     *
     * @return ListArray<FragmentPagerModel> mList
     */
    public List<FragmentPagerModel> getList() {
        return mList;
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
    public Fragment getItem(int position) {
        return mList.get(position).getFragment();
    }

    @Override
    public int getCount() {
        return mCount;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mList.get(position).getName();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        mCurrentFragmentList.put(position, fragment);
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        mCurrentFragmentList.remove(position);
        super.destroyItem(container, position, object);
    }

    public Fragment getFragment(int position) {
        if (mCurrentFragmentList.isEmpty())
            return null;
        return mCurrentFragmentList.get(position);
    }
}
