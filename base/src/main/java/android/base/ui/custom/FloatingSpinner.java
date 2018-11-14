package android.base.ui.custom;

import android.base.R;
import android.base.ui.widget.BaseSpinner;
import android.base.ui.widget.BaseTextView;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.ArrayRes;
import android.support.annotation.AttrRes;
import android.support.annotation.ColorRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.SpinnerAdapter;

import java.util.List;

/**
 * Created by clickapps on 8/9/17.
 */

public class FloatingSpinner extends FrameLayout implements AdapterView.OnItemSelectedListener {
    private BaseTextView textView;
    private BaseSpinner spinner;
    int labelColor;
    private AdapterView.OnItemSelectedListener l;
    private int lastPosition = -1;

    public FloatingSpinner(@NonNull Context context) {
        super(context);
        initUI(null);
    }

    public FloatingSpinner(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initUI(attrs);
    }

    public FloatingSpinner(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initUI(attrs);
    }

    public FloatingSpinner(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initUI(attrs);
    }

    private void initUI(@Nullable AttributeSet attrs) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.ui_floating_spinner, null);
        textView = (BaseTextView) view.findViewById(android.R.id.text1);
        spinner = (BaseSpinner) view.findViewById(R.id.spinner);
        if (attrs != null) {
            //check attributes you need, for example all paddings
            TypedArray a = getContext().obtainStyledAttributes(attrs,
                    R.styleable.FloatingSpinner, 0, 0);
            String labelText = a.getString(R.styleable.FloatingSpinner_labelText);
            if (labelText != null) {
                setLabelText(labelText);
            }
            labelColor = a.getColor(R.styleable.FloatingSpinner_labelTextColor,
                    ContextCompat.getColor(getContext(), R.color.black));
            textView.setTextColor(labelColor);
            final CharSequence[] entries = a.getTextArray(R.styleable.FloatingSpinner_android_entries);
            if (entries != null) {
                setItemsArray(entries);
                setSelection(0);
            }
            a.recycle();
        }
        addView(view);
    }

    /**
     * Sets the text the label is to display.
     *
     * @param labelTextId The string resource identifier which refers to
     *                    the string value which is to be displayed on
     *                    the label.
     * @attr ref R.styleable#FloatingSpinner_labelText
     */
    public void setLabelText(@StringRes int labelTextId) {
        textView.setText(getResources().getString(labelTextId));
    }

    /**
     * Sets the text the label is to display.
     *
     * @param labelText string value which is to be displayed on
     *                  the label.
     * @attr ref R.styleable#FloatingSpinner_labelText
     */
    public void setLabelText(@NonNull String labelText) {
        textView.setText(labelText);
    }

    /**
     * @return the text shown on the floating label
     */
    public CharSequence getLabelText() {
        return textView.getText();
    }

    /**
     * Sets the color to use for the label text and the divider line
     * underneath.
     *
     * @param colorRes The color resource identifier which refers to the
     *                 color that is to be displayed on the widget.
     * @attr ref R.styleable#FloatingSpinner_labelTextColor
     * @see #getColor()
     */
    public void setColor(@ColorRes int colorRes) {
        labelColor = ContextCompat.getColor(getContext(), colorRes);
        textView.setTextColor(labelColor);
    }

    /**
     * @return the color used as the label and divider line
     */
    public int getColor() {
        return labelColor;
    }

    /**
     * Sets the array of items to be used in the Spinner.
     *
     * @param arrayResId The identifier of the array to use as the data
     *                   source (e.g. {@code R.array.myArray})
     * @attr ref R.styleable#LabelledSpinner_spinnerEntries
     * @see #setItemsArray(int, int, int)
     * @see #setItemsArray(CharSequence[])
     * @see #setItemsArray(CharSequence[], int, int)
     * @see #setItemsArray(List)
     * @see #setItemsArray(List, int, int)
     */
    public void setItemsArray(@ArrayRes int arrayResId) {
        setItemsArray(arrayResId, android.R.layout.simple_spinner_item,
                android.R.layout.simple_spinner_dropdown_item);
    }

    /**
     * Sets the array of items to be used in the Spinner.
     *
     * @param arrayResId      The identifier of the array to use as the data
     *                        source (e.g. {@code R.array.myArray})
     * @param spinnerItemRes  The identifier of the layout used to create
     *                        views (e.g. {@code R.layout.my_item})
     * @param dropdownViewRes The layout resource to create the drop down
     *                        views (e.g. {@code R.layout.my_dropdown})
     * @attr ref R.styleable#LabelledSpinner_spinnerEntries
     * @see #setItemsArray(int)
     * @see #setItemsArray(CharSequence[])
     * @see #setItemsArray(CharSequence[], int, int)
     * @see #setItemsArray(List)
     * @see #setItemsArray(List, int, int)
     */
    public void setItemsArray(@ArrayRes int arrayResId, @LayoutRes int spinnerItemRes,
                              @LayoutRes int dropdownViewRes) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getContext(),
                arrayResId,
                spinnerItemRes);
        adapter.setDropDownViewResource(dropdownViewRes);
        spinner.setAdapter(adapter);
    }

    /**
     * Sets the array of items to be used in the Spinner.
     *
     * @param itemsArray The array used as the data source
     * @attr ref R.styleable#LabelledSpinner_spinnerEntries
     * @see #setItemsArray(int)
     * @see #setItemsArray(int, int, int)
     * @see #setItemsArray(CharSequence[], int, int)
     * @see #setItemsArray(List)
     * @see #setItemsArray(List, int, int)
     */
    public void setItemsArray(CharSequence[] itemsArray) {
        setItemsArray(itemsArray, android.R.layout.simple_spinner_item,
                android.R.layout.simple_spinner_dropdown_item);
    }

    /**
     * Sets the array of items to be used in the Spinner.
     *
     * @param itemsArray      The array used as the data source
     * @param spinnerItemRes  The identifier of the layout used to create
     *                        views (e.g. {@code R.layout.my_item})
     * @param dropdownViewRes The layout resource to create the drop down
     *                        views (e.g. {@code R.layout.my_dropdown})
     * @attr ref R.styleable#LabelledSpinner_spinnerEntries
     * @see #setItemsArray(int)
     * @see #setItemsArray(int, int, int)
     * @see #setItemsArray(CharSequence[])
     * @see #setItemsArray(List)
     * @see #setItemsArray(List, int, int)
     */
    public void setItemsArray(CharSequence[] itemsArray, @LayoutRes int spinnerItemRes,
                              @LayoutRes int dropdownViewRes) {
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(
                getContext(),
                spinnerItemRes,
                itemsArray);
        adapter.setDropDownViewResource(dropdownViewRes);
        spinner.setAdapter(adapter);
    }

    /**
     * Sets the array of items to be used in the Spinner.
     *
     * @param list The List used as the data source
     * @attr ref R.styleable#LabelledSpinner_spinnerEntries
     * @see #setItemsArray(int)
     * @see #setItemsArray(int, int, int)
     * @see #setItemsArray(CharSequence[])
     * @see #setItemsArray(CharSequence[], int, int)
     * @see #setItemsArray(List, int, int)
     */
    public void setItemsArray(List<?> list) {
        setItemsArray(list, android.R.layout.simple_spinner_item,
                android.R.layout.simple_spinner_dropdown_item);
    }

    /**
     * Sets the array of items to be used in the Spinner.
     *
     * @param list            The list to be used as the data source.
     * @param spinnerItemRes  The identifier of the layout used to create
     *                        views (e.g. {@code R.layout.my_item})
     * @param dropdownViewRes The layout resource to create the drop down
     *                        views (e.g. {@code R.layout.my_dropdown})
     * @attr ref R.styleable#LabelledSpinner_spinnerEntries
     * @see #setItemsArray(int)
     * @see #setItemsArray(int, int, int)
     * @see #setItemsArray(CharSequence[])
     * @see #setItemsArray(CharSequence[], int, int)
     * @see #setItemsArray(List)
     */
    public void setItemsArray(List<?> list, @LayoutRes int spinnerItemRes,
                              @LayoutRes int dropdownViewRes) {
        ArrayAdapter<?> adapter = new ArrayAdapter<>(
                getContext(),
                spinnerItemRes,
                list);
        adapter.setDropDownViewResource(dropdownViewRes);
        spinner.setAdapter(adapter);
    }

    /**
     * Sets the Adapter used to provide the data for the Spinner.
     * This would be similar to setting an Adapter for a normal Spinner
     * component.
     *
     * @param adapter The Adapter which would provide data for the Spinner
     */
    public void setCustomAdapter(SpinnerAdapter adapter) {
        spinner.setAdapter(adapter);
    }

    /**
     * Sets the currently selected item.
     *
     * @param position Index (starting at 0) of the data item to be selected.
     */
    public void setSelection(int position) {
        lastPosition = Math.max(-1, position);
        spinner.setSelection(position);
    }

    /**
     * Sets the currently selected item.
     *
     * @param position Index (starting at 0) of the data item to be selected.
     * @param animate  Whether or not the transition should be animated
     */
    public void setSelection(int position, boolean animate) {
        lastPosition = Math.max(-1, position);
        spinner.setSelection(position, animate);
    }

    public void setOnItemSelectedListener(AdapterView.OnItemSelectedListener onItemSelectedListener) {
        this.l = onItemSelectedListener;
        spinner.setOnItemSelectedListener(this);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (position != lastPosition) {
            lastPosition = position;
            if (l != null) {
                l.onItemSelected(parent, view, position, id);
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        if (-1 != lastPosition) {
            lastPosition = -1;
            if (l != null) {
                l.onNothingSelected(parent);
            }
        }
    }
}
