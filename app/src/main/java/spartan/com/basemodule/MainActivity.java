package spartan.com.basemodule;

import android.app.ActivityOptions;
import android.base.ui.custom.FloatingSpinner;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.github.florent37.viewanimator.ViewAnimator;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import br.com.simplepass.loading_button_lib.interfaces.OnAnimationEndListener;

public class MainActivity extends AppCompatActivity {
    View v;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getApplication() instanceof AppApplication) {
            ((AppApplication) getApplication()).runJobService();
        }
        FloatingSpinner spinner = (FloatingSpinner) findViewById(R.id.floatingSpinner);
        v = findViewById(R.id.text1);
        ViewAnimator
                .animate(spinner)
                .duration(5000)
                .alpha(0.5f, 0.9f)
                .fadeIn()
                .thenAnimate(spinner)
                .tada()
                .rotation(180, 360)
                .start();
        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,
                        SecondActivity.class);
                ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(
                        MainActivity.this, v, v.getTransitionName());
//                ActivityOptions transitionActivityOptions = ActivityOptions.makeBasic();

                startActivity(i, transitionActivityOptions.toBundle());
            }
        });
        Slide slide = new Slide();
        slide.addTarget(R.id.fab);
        getWindow().setExitTransition(slide);
        final CircularProgressButton pb = findViewById(R.id.btn_id);
        pb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pb.startAnimation();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pb.stopAnimation();
                        pb.revertAnimation(new OnAnimationEndListener() {
                            @Override
                            public void onAnimationEnd() {
                                pb.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.btn_shape));
                            }
                        });
                    }
                }, 1000);

            }
        });
        pb.setSpinningBarColor(Color.BLUE);

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
    protected void onDestroy() {
        super.onDestroy();
        Log.d(getLocalClassName(), "onDestroy");
    }
}
