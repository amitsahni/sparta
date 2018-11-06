package android.base.ui.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.bottomappbar.BottomAppBar;
import android.util.AttributeSet;

public class BaseBottomAppBar extends BottomAppBar {
    public BaseBottomAppBar(Context context) {
        super(context);
    }

    public BaseBottomAppBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseBottomAppBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
