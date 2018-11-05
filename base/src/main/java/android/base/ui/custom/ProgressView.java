package android.base.ui.custom;

import android.base.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ProgressBar;

/**
 * Created by amit on 18/2/18.
 */

public class ProgressView extends ProgressBar {
    private float strokeSize;
    private final Paint mPaint = new Paint();
    private final RectF mBounds = new RectF();
    private int mode;
    private float startAngle = 0f;
    private float endAngle = 0f;
    private float radius = 100f;
    private int backgroundColor = Color.GRAY;
    private int secondaryProgressColor = Color.RED;
    private int progressColor = Color.BLUE;

    private final int LINE = 1;
    private final int ARC = 2;
    private final int SEMI = 3;
    private final int CIRCLE = 4;

    public ProgressView(Context context) {
        this(context, null);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public ProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);


    }

    public ProgressView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        final TypedArray a = context.obtainStyledAttributes(
                attrs, R.styleable.ProgressView, defStyleAttr, defStyleRes);

        if (a.hasValue(R.styleable.ProgressView_mode)) {
            mode = a.getInteger(R.styleable.ProgressView_mode, -1);
        }

        if (a.hasValue(R.styleable.ProgressView_startAngle)) {
            startAngle = a.getFloat(R.styleable.ProgressView_startAngle, 0.0f);
        }

        if (a.hasValue(R.styleable.ProgressView_endAngle)) {
            endAngle = a.getFloat(R.styleable.ProgressView_endAngle, 0.0f);
        }

        float STROKE_SIZE = 4;
        if (a.hasValue(R.styleable.ProgressView_strokeSize)) {
            STROKE_SIZE = a.getFloat(R.styleable.ProgressView_strokeSize, STROKE_SIZE);
        }

        if (a.hasValue(R.styleable.ProgressView_radius)) {
            radius = a.getFloat(R.styleable.ProgressView_radius, 200);
        }

        if (a.hasValue(R.styleable.ProgressView_backgroundColor)) {
            backgroundColor = a.getColor(R.styleable.ProgressView_backgroundColor, 0);
        }

        if (a.hasValue(R.styleable.ProgressView_progressColor)) {
            progressColor = a.getColor(R.styleable.ProgressView_progressColor, 0);
        }

        if (a.hasValue(R.styleable.ProgressView_secondaryColor)) {
            secondaryProgressColor = a.getColor(R.styleable.ProgressView_secondaryColor, 0);
        }

        final float density = getResources().getDisplayMetrics().density;
        strokeSize = STROKE_SIZE * density;
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(strokeSize);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        a.recycle();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        // Extra padding to avoid cuttings on arc.
        float xpad = strokeSize / 2f;
        mBounds.top = getPaddingTop() + xpad;
        mBounds.bottom = h - getPaddingBottom() - xpad;
        mBounds.left = getPaddingStart() + xpad;
        mBounds.right = w - getPaddingEnd() - xpad;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        final float scale = getMax() > 0 ? getProgress() / (float) getMax() : 0;
        final float scaleSecondary = getMax() > 0 ? getSecondaryProgress() / (float) getMax() : 0;
        switch (mode) {
            case LINE:
                // Draw the line
                mPaint.setColor(backgroundColor);
                canvas.drawLine(mBounds.left, mBounds.top, mBounds.right, mBounds.top, mPaint);

                float stopX = mBounds.width() * scaleSecondary + mBounds.left;
                mPaint.setColor(secondaryProgressColor);
                canvas.drawLine(mBounds.left, mBounds.top, stopX, mBounds.top, mPaint);

                stopX = mBounds.width() * scale + mBounds.left;
                mPaint.setColor(progressColor);
                canvas.drawLine(mBounds.left, mBounds.top, stopX, mBounds.top, mPaint);
                break;
            case ARC:
                // Draw the arc
                if (startAngle == 0f) {
                    startAngle = 135f;
                }
                if (endAngle == 0f) {
                    endAngle = 270f;
                }
                mPaint.setColor(backgroundColor);
                canvas.drawArc(mBounds, startAngle, endAngle, false, mPaint);

                mPaint.setColor(secondaryProgressColor);
                canvas.drawArc(mBounds, startAngle, endAngle * scaleSecondary, false, mPaint);

                mPaint.setColor(progressColor);
                canvas.drawArc(mBounds, startAngle, endAngle * scale, false, mPaint);
                break;
            case SEMI:
                // Draw semi-circle
                if (startAngle == 0f) {
                    startAngle = 180f;
                }
                if (endAngle == 0f) {
                    endAngle = 180f;
                }
                mPaint.setColor(backgroundColor);
                canvas.drawArc(mBounds, startAngle, endAngle, false, mPaint);

                mPaint.setColor(secondaryProgressColor);
                canvas.drawArc(mBounds, startAngle, endAngle * scaleSecondary, false, mPaint);

                mPaint.setColor(progressColor);
                canvas.drawArc(mBounds, startAngle, endAngle * scale, false, mPaint);
                break;
            case CIRCLE:
                // Draw Circle
                mPaint.setStyle(Paint.Style.FILL);
                mPaint.setColor(backgroundColor);
                canvas.drawCircle(getWidth() / 2, getHeight() / 2, radius, mPaint);

                mPaint.setColor(secondaryProgressColor);
                canvas.drawCircle(getWidth() / 2, getHeight() / 2, radius * scaleSecondary, mPaint);

                mPaint.setColor(progressColor);
                canvas.drawCircle(getWidth() / 2, getHeight() / 2, radius * scale, mPaint);
                break;
            default:
                super.onDraw(canvas);
                break;
        }
    }
}
