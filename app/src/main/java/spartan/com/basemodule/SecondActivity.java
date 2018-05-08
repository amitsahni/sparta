package spartan.com.basemodule;

import android.base.ui.custom.FloatingSpinner;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.transition.AutoTransition;
import android.transition.ChangeScroll;
import android.transition.Transition;
import android.transition.TransitionSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.github.florent37.viewanimator.AnimationListener;
import com.github.florent37.viewanimator.ViewAnimator;

public class SecondActivity extends AppCompatActivity {
    TextView view;
    float textSize = 0f;
    boolean isAnimationCompleted = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        FloatingSpinner spinner = (FloatingSpinner) findViewById(R.id.floatingSpinner);
        view = findViewById(R.id.text2);
        ViewAnimator
                .animate(spinner)
                .duration(5000)
                .alpha(0.5f, 0.9f)
                .fadeIn()
                .thenAnimate(spinner)
                .tada()
                .rotation(180, 360);
//        .start();
        textSize = getIntent().getFloatExtra("textSize", 0f);
        TransitionSet transitionSet = new TransitionSet();
        transitionSet.addTransition(new AutoTransition());
        ChangeScroll textResize = new ChangeScroll();
        textResize.addTarget(view);
        //textResize.textSize(textSize, view.getTextSize());
        transitionSet.setDuration(800);
        transitionSet.addTransition(textResize);
        getWindow().setSharedElementEnterTransition(transitionSet);
        getWindow().getSharedElementEnterTransition().addListener(new TransitionAdapter() {
            @Override
            public void onTransitionStart(Transition transition) {
                super.onTransitionStart(transition);
                ViewAnimator
                        .animate(view)
                        .custom(new AnimationListener.Update() {
                            @Override
                            public void update(View view, float value) {
                                if (view instanceof TextView) {
                                    ((TextView) view).setTextSize(TypedValue.COMPLEX_UNIT_PX, value);
                                }
                            }
                        }, textSize, view.getTextSize())
                        .duration(transition.getDuration())
                        .start();
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                super.onTransitionEnd(transition);
                getWindow().getSharedElementEnterTransition().removeListener(this);
            }
        });
    }

    private class Adapter extends ArrayAdapter<String> {

        public Adapter(@NonNull Context context, @LayoutRes int resource) {
            super(context, resource);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            return super.getView(position, convertView, parent);
        }

        @Override
        public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            return super.getDropDownView(position, convertView, parent);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ViewAnimator
                .animate(view)
                .custom(new AnimationListener.Update() {
                    @Override
                    public void update(View view, float value) {
                        if (view instanceof TextView) {
                            ((TextView) view).setTextSize(TypedValue.COMPLEX_UNIT_PX, value);
                        }
                    }
                }, view.getTextSize(), textSize)
                .duration(800)
                .textColor(Color.RED, Color.BLACK, Color.BLUE)
                .start();

    }
}
