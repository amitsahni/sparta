package spartan.com.basemodule;

import android.animation.Animator;
import android.transition.ChangeBounds;
import android.transition.TransitionValues;
import android.transition.Visibility;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by clickapps on 10/5/18.
 */

public class Test extends Visibility {
    @Override
    public void captureStartValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    @Override
    public void captureEndValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    private void captureValues(TransitionValues transitionValues) {
        if (!(transitionValues.view instanceof TextView)) {
            return;
        }
        TextView textView = (TextView) transitionValues.view;
        transitionValues.values.put("test", textView.getText());
    }

    @Override
    public Animator createAnimator(ViewGroup sceneRoot, TransitionValues startValues, TransitionValues endValues) {
        return super.createAnimator(sceneRoot, startValues, endValues);
    }
}
