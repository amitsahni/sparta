package spartan.com.basemodule;

import android.app.SharedElementCallback;
import android.base.transition.TextResize;
import android.base.ui.custom.FloatingSpinner;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.transition.Transition;
import android.support.transition.TransitionManager;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.github.florent37.viewanimator.ViewAnimator;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        FloatingSpinner spinner = (FloatingSpinner) findViewById(R.id.floatingSpinner);
        View view = findViewById(R.id.text2);
        ViewAnimator
                .animate(spinner)
                .duration(5000)
                .alpha(0.5f, 0.9f)
                .fadeIn()
                .thenAnimate(spinner)
                .tada()
                .rotation(180, 360);
        //.start();
        TextResize textResize = new TextResize();
        textResize.addTarget(view);
//        textResize.setSlideEdge(Gravity.LEFT);
        textResize.setDuration(1500);
//        TransitionManager.beginDelayedTransition((ViewGroup) findViewById(R.id.root),
//                textResize);
        getWindow().setEnterTransition(textResize);
        getWindow().setExitTransition(textResize);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setEnter() {
        setEnterSharedElementCallback(new SharedElementCallback() {

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
}
