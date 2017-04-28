package android.base.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.base.activity.BaseAppCompatActivity;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * The type Fragment manager.
 */
public class FragmentManager {
    private static volatile FragmentManager sFragmentManager;

    private FragmentManager() {
        // private constructor
    }

    /**
     * Get fragment manager.
     *
     * @return the fragment manager
     */
    public static FragmentManager get() {
        if (sFragmentManager == null) {
            synchronized (FragmentManager.class) {
                if (sFragmentManager == null) {
                    sFragmentManager = new FragmentManager();
                }
            }
        }
        return sFragmentManager;
    }

    /**
     * This method is used to get the top fragmnet on the stack
     *
     * @param activity the activity
     * @return {@link Fragment}
     */
    public Fragment getTopFragment(@NonNull Activity activity) {
        if (activity == null)
            return null;
        android.app.FragmentManager fm = activity.getFragmentManager();
        Fragment fragment = null;
        for (int entry = 0; entry < fm.getBackStackEntryCount(); entry++) {
            fragment = fm.findFragmentByTag(fm.getBackStackEntryAt(entry)
                    .getName());
        }
        return fragment;
    }

    /**
     * This method is used to get List of backstack fragments
     *
     * @param activity the activity
     * @return {@link List}
     */
    public List<String> getStackList(@NonNull Activity activity) {
        List<String> stackList = new ArrayList<>();
        stackList.clear();
        if (activity == null)
            return stackList;
        android.app.FragmentManager fm = activity.getFragmentManager();
        for (int entry = 0; entry < fm.getBackStackEntryCount(); entry++) {
            stackList.add(fm.getBackStackEntryAt(entry).getName());
        }
        return stackList;
    }

    /**
     * This method is used to get the fragment
     *
     * @param activity the activity
     * @param id       set UniqueId
     * @return {@link Fragment}
     */
    public Fragment getFragment(@NonNull Activity activity, int id) {
        if (activity == null)
            return null;
        return activity.getFragmentManager().findFragmentById(id);
    }

    /**
     * This method is used to get the fragment
     *
     * @param activity the activity
     * @param tag      set UniqueTag
     * @return {@link Fragment}
     */
    public Fragment getFragment(@NonNull Activity activity, String tag) {
        if (activity == null)
            return null;
        return activity.getFragmentManager().findFragmentByTag(tag);
    }

    /**
     * With builder.
     *
     * @param context   the context
     * @param replaceId the replace id
     * @return the builder
     */
    public static Builder with(@NonNull FragmentActivity context, @IdRes int replaceId) {
        return new Builder(context, replaceId);
    }
}
