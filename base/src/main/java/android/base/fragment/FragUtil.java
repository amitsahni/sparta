package android.base.fragment;

import android.app.*;
import android.app.FragmentManager;
import android.base.util.ApplicationUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * The type Frag util.
 */
public class FragUtil {

    private FragUtil() {
    }

    /**
     * This method is used to popFragment from stack
     *
     * @param activity  the activity
     * @param replaceId the replace id
     * @Waring This methods runs on UI Thread
     */
    public static void popFragment(Activity activity, int replaceId) {
        if (activity == null)
            return;
        if (activity.getFragmentManager().getBackStackEntryCount() > 0) {
            FragmentTransaction fragTrans = activity
                    .getFragmentManager().beginTransaction();
            fragTrans.remove(activity.getFragmentManager()
                    .findFragmentById(replaceId));
            fragTrans.commit();
            activity.getFragmentManager().popBackStackImmediate();
        }
    }

    /**
     * This method is used to popFragment from stack
     *
     * @param activity the activity
     * @param tag      the tag
     * @Waring This methods runs on UI Thread
     */
    public static void popFragment(Activity activity, String tag) {
        if (activity == null)
            return;
        if (activity.getFragmentManager().getBackStackEntryCount() > 0) {
            FragmentTransaction fragTrans = activity
                    .getFragmentManager().beginTransaction();
            fragTrans.remove(activity.getFragmentManager()
                    .findFragmentByTag(tag));
            fragTrans.commit();
            activity.getFragmentManager().popBackStackImmediate();
        }
    }

    /**
     * This method is used to clear all the fragments from stack
     *
     * @param activity the activity
     */
    public static void clearBackStack(Activity activity) {
        try {
            if (activity == null)
                return;
            FragmentManager fm = activity.getFragmentManager();
            for (int i = 0; i < fm.getBackStackEntryCount(); i++) {
                String fragTag = fm.getBackStackEntryAt(i).getName();
                Fragment fragment = fm.findFragmentByTag(fragTag);
                FragmentTransaction fragTrans = activity
                        .getFragmentManager().beginTransaction();
                fragTrans.remove(fragment);
                fragTrans.commit();
                fm.popBackStack();
            }

        } catch (Exception e) {
            ApplicationUtils.Log.e(e.getMessage());
        }
    }

    /**
     * This method is used to remove all the stack in async
     *
     * @param activity the activity
     */
    public static void clearAllStack(Activity activity) {
        if (activity == null)
            return;
        FragmentManager fm = activity.getFragmentManager();
        fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    /**
     * This method is used to remove all the stack in sync
     *
     * @param activity the activity
     * @Waring This methods runs on UI Thread
     */
    public static void clearAllStackImmediate(Activity activity) {
        if (activity == null)
            return;
        FragmentManager fm = activity.getFragmentManager();
        fm.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    /**
     * This method is used to get the top fragmnet on the stack
     *
     * @param activity the activity
     * @return {@link Fragment}
     */
    public static Fragment getTopFragment(Activity activity) {
        if (activity == null)
            return null;
        FragmentManager fm = activity.getFragmentManager();
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
    public static List<String> getStackList(Activity activity) {
        List<String> stackList = new ArrayList<>();
        stackList.clear();
        if (activity == null)
            return stackList;
        FragmentManager fm = activity.getFragmentManager();
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
    public static Fragment getFragment(Activity activity, int id) {
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
    public static Fragment getFragment(Activity activity, String tag) {
        if (activity == null)
            return null;
        return activity.getFragmentManager().findFragmentByTag(tag);
    }

    /**
     * Functionality to refresh or restart a fragment
     *
     * @param activity   the activity
     * @param fragmentId resource id for fragment
     */
    public static void restartFragment(Activity activity, int fragmentId) {
        try {
            Fragment fragment = getFragment(activity, fragmentId);
            if (fragment != null) {
                FragmentTransaction fragTransaction = activity.getFragmentManager().beginTransaction();
                fragTransaction.detach(fragment);
                fragTransaction.attach(fragment);
                fragTransaction.commit();
            }
        } catch (Exception e) {
            ApplicationUtils.Log.e(e.getMessage());
        }
    }
}
