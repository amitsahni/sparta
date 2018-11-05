package android.base.ui.widget;

import android.content.Context;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by clickapps on 27/11/15.
 */
public class BaseDrawerLayout extends DrawerLayout {
    /**
     * Instantiates a new Base drawer layout.
     *
     * @param context the context
     */
    public BaseDrawerLayout(Context context) {
        super(context);
        setup();
    }

    /**
     * Instantiates a new Base drawer layout.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public BaseDrawerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup();
    }

    /**
     * Instantiates a new Base drawer layout.
     *
     * @param context  the context
     * @param attrs    the attrs
     * @param defStyle the def style
     */
    public BaseDrawerLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setup();
    }

    /**
     * Sets .
     */
    public void setup() {
        this.addDrawerListener(new DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }

    /**
     * Toggle.
     */
    public void toggle() {
        if (isDrawerOpen(GravityCompat.START)) {
            closeDrawer();
        } else {
            openDrawer();
        }
    }

    /**
     * Toggle.
     *
     * @param view the mView
     */
    public void toggle(View view) {
        if (isDrawerOpen(view)) {
            this.closeDrawer(view);
        } else {
            openDrawer(view);
        }
    }

    /**
     * Open drawer.
     */
    public void openDrawer() {
        openDrawer(GravityCompat.START);
    }

    /**
     * Close drawer.
     */
    public void closeDrawer() {
        openDrawer(GravityCompat.START);
    }

}
