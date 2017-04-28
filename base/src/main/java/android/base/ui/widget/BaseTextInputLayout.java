package android.base.ui.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;

/**
 * Created by clickapps on 15/10/15.
 * {@link < bug link ahref https://code.google.com/p/android/issues/detail?id=175228/>}
 */
public class BaseTextInputLayout extends TextInputLayout {

    /**
     * Instantiates a new Base text input layout.
     *
     * @param context the context
     */
    public BaseTextInputLayout(Context context) {
        super(context);
    }

    /**
     * Instantiates a new Base text input layout.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public BaseTextInputLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void addView(View child, int index, android.view.ViewGroup.LayoutParams params) {
        if (child instanceof EditText) {
            // TextInputLayout updates mCollapsingTextHelper bounds on onLayout. but Edit text is not layouted.
            child.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @SuppressLint("WrongCall")
                @Override
                public void onGlobalLayout() {
                    onLayout(false, getLeft(), getTop(), getRight(), getBottom());
                }
            });
        }
        super.addView(child, index, params);
    }
}
