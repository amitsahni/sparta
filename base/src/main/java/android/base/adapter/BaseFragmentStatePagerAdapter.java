package android.base.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by clickapps on 9/2/16.
 */
public abstract class BaseFragmentStatePagerAdapter extends FragmentStatePagerAdapter {
    private List<FragmentPagerModel> mList = new ArrayList<>();
    private Context mContext;
    private int mCount;
    private Map<Integer, Fragment> mCurrentFragmentList = new LinkedHashMap<>();

    public BaseFragmentStatePagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.mContext = context;
        mCount = mList.size();
    }

    public void setList(@NonNull List<FragmentPagerModel> items) {
        mList = new ArrayList<>(items);
        mCount = mList.size();
        this.notifyDataSetChanged();
    }

    public List<FragmentPagerModel> getList() {
        return mList;
    }

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

    @Nullable
    public Fragment getFragment(int position) {
        if (mCurrentFragmentList.isEmpty())
            return null;
        return mCurrentFragmentList.get(position);
    }
}
