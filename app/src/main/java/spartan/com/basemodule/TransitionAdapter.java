package spartan.com.basemodule;

import android.transition.Transition;
import android.util.Log;

/**
 * Created by clickapps on 4/5/18.
 */

public class TransitionAdapter implements Transition.TransitionListener {
    private static final String TAG = "TransitionAdapter";

    @Override
    public void onTransitionStart(Transition transition) {
        Log.i(TAG, "onTransitionStart");
    }

    @Override
    public void onTransitionEnd(Transition transition) {
        Log.i(TAG, "onTransitionEnd");
    }

    @Override
    public void onTransitionCancel(Transition transition) {
        Log.i(TAG, "onTransitionCancel");
    }

    @Override
    public void onTransitionPause(Transition transition) {
        Log.i(TAG, "onTransitionPause");
    }

    @Override
    public void onTransitionResume(Transition transition) {
        Log.i(TAG, "onTransitionResume");
    }
}
