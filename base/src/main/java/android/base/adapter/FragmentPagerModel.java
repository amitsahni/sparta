package android.base.adapter;


import android.support.v4.app.Fragment;

/**
 * Created by amit on 28/12/16.
 */

public final class FragmentPagerModel {

    private String name;
    private Fragment fragment;

    public FragmentPagerModel(String name, Fragment fragment) {
        this.name = name;
        this.fragment = fragment;
    }

    public String getName() {
        return name;
    }

    public Fragment getFragment() {
        return fragment;
    }
}
