package android.base.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;


/**
 * The type Base pager adapter.
 */
public class BasePagerAdapter extends PagerAdapter {

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

}
