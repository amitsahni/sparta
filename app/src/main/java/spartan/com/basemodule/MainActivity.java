package spartan.com.basemodule;

import android.app.ActivityOptions;
import android.base.ui.custom.FloatingSpinner;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.github.florent37.viewanimator.ViewAnimator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingSpinner spinner = (FloatingSpinner) findViewById(R.id.floatingSpinner);
        View view = findViewById(android.R.id.text1);
        ViewAnimator
                .animate(view)
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
                        MainActivity.this);

                startActivity(i, transitionActivityOptions.toBundle());
            }
        });
        Slide slide = new Slide();
        slide.addTarget(R.id.fab);
        getWindow().setExitTransition(slide);
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
