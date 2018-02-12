package spartan.com.basemodule;

import android.base.ui.custom.FloatingSpinner;
import android.base.util.categories.AnimationUtil;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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
//        Adapter adapter = new Adapter(this, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setCustomAdapter(adapter);
        View view = findViewById(android.R.id.text1);
//        AnimationUtil.hide(view);
        ViewAnimator
                .animate(view)
                .duration(5000)
                .alpha(0.5f,0.9f)
                .fadeIn()
                .thenAnimate(spinner)
                .tada()
                .rotation(180, 360)
                .start();
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
