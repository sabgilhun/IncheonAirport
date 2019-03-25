package ado.sabgil.incheonariport.view.custom;

import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.security.InvalidParameterException;
import java.util.List;

import ado.sabgil.incheonariport.R;
import androidx.annotation.NonNull;

public class LineChart extends View {

    private final static int CHART_DEFAULT_MAX_VALUE = 100;
    private final static int CHART_DEFAULT_MIN_VALUE = 0;
    private final static int CHART_DEFAULT_Y_LABEL_NUMBER = 6;
    private final static int ANIMATION_DEFAULT_DURATION = 1000;

    // Attributes
    private int backgroundColor;
    private int valuesColor;

    private float valuesStrokeWidth;
    private float backgroundStrokeWidth;

    private float maxValue;
    private float minValue;

    private float labelSize;
    private int yLabelNum;

    // Internal values
    private Paint backgroundPaint;
    private Paint valuesPaint;

    private float startX;
    private float startY;
    private float endX;
    private float endY;

    private String[] yLabels;
    private float[] yLabelsPosition;
    private String[] xLabels;

    // Animator
    private int lineAnimationValue = 0;
    private int alphaAnimationValue = 0;

    // Chart values
    private List<Integer> chartValues;
    private float[] xLabelsPosition;
    private float[] valuesYPosition;

    public LineChart(Context context) {
        super(context);
        initView(null);
    }

    public LineChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(attrs);
    }

    public LineChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(attrs);
    }

    private void initView(AttributeSet attrs) {
        readAttributes(attrs);
        initPaints();
    }

    private void readAttributes(AttributeSet attrs) {
        Resources resources = getContext().getResources();

        // setting default values
        backgroundStrokeWidth = resources.getDimension(R.dimen.default_background_stroke_width);
        backgroundColor = resources.getColor(R.color.colorBackground, getContext().getTheme());

        labelSize = resources.getDimension(R.dimen.default_text_label_size);
        yLabelNum = CHART_DEFAULT_Y_LABEL_NUMBER;

        valuesStrokeWidth = resources.getDimension(R.dimen.default_line_stroke_width);
        valuesColor = resources.getColor(R.color.colorPrimary, getContext().getTheme());

        maxValue = CHART_DEFAULT_MAX_VALUE;
        minValue = CHART_DEFAULT_MIN_VALUE;

        if (attrs != null) {
            TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.LineChart);
            backgroundStrokeWidth = array
                    .getDimensionPixelSize(R.styleable.LineChart_backgroundStrokeWidth, (int) backgroundStrokeWidth);
            backgroundColor = array.getColor(R.styleable.LineChart_backgroundColor, backgroundColor);

            labelSize = array.getDimensionPixelSize(R.styleable.LineChart_labelSize, (int) labelSize);
            yLabelNum = array.getInt(R.styleable.LineChart_yLabelNum, yLabelNum);

            valuesStrokeWidth = array
                    .getDimensionPixelSize(R.styleable.LineChart_valuesStrokeWidth, (int) valuesStrokeWidth);
            valuesColor = array.getColor(R.styleable.LineChart_valuesColor, valuesColor);

            maxValue = array.getInt(R.styleable.LineChart_maxValue, (int) maxValue);
            minValue = array.getInt(R.styleable.LineChart_minValue, (int) minValue);

            array.recycle();
        }
    }

    private void initPaints() {
        backgroundPaint = getPaint();
        backgroundPaint.setColor(backgroundColor);
        backgroundPaint.setStrokeWidth(backgroundStrokeWidth);
        backgroundPaint.setTextSize(labelSize);
        backgroundPaint.setTextAlign(Paint.Align.CENTER);

        valuesPaint = getPaint();
        valuesPaint.setColor(valuesColor);
        valuesPaint.setStrokeWidth(valuesStrokeWidth);
    }

    private Paint getPaint() {
        if (!isInEditMode()) {
            return new Paint(Paint.ANTI_ALIAS_FLAG);
        } else {
            return new Paint();
        }
    }

    private void initInternalValues(int width, int height) {
        final float margin = backgroundPaint.getTextSize() * 3.5f;

        startX = margin;
        startY = margin;
        endX = width - margin;
        endY = height - margin;

        int yLabelInterval = (int) ((maxValue - minValue) / (yLabelNum - 1));
        yLabels = new String[yLabelNum];
        yLabelsPosition = new float[yLabelNum];

        final float interval = (endY - startY) / (yLabelNum - 1);
        for (int i = 0; i < yLabelNum; i++) {
            yLabels[i] = Integer.toString(yLabelInterval * i);
            yLabelsPosition[i] = endY - (interval * i);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        initInternalValues(getWidth(), getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        renderBackground(canvas);
        renderChart(canvas);
    }

    private void renderBackground(Canvas canvas) {
        canvas.drawRect(startX, endY, endX + (endX - startX) * 0.05f, endY + 4, backgroundPaint);

        for (int i = 1; i < yLabelNum; i++) {
            canvas.drawRect(startX, yLabelsPosition[i], endX, yLabelsPosition[i] + 2, backgroundPaint);
        }

        for (int i = 0; i < yLabelNum; i++) {
            canvas.drawText(yLabels[i], startX / 2, yLabelsPosition[i], backgroundPaint);
        }
    }

    private void renderChart(Canvas canvas) {
        if (chartValues != null) {
            if (xLabels.length != 0) {
                for (int i = 0; i < xLabels.length; i++) {
                    canvas.drawText(xLabels[i], xLabelsPosition[i], endY + startY / 2, backgroundPaint);
                }
            }

            final int index = (lineAnimationValue / 100) + 1;
            for (int i = 0; i < index; i++) {
                if (i == index - 1) {
                    final float diff_x = (xLabelsPosition[i + 1] - xLabelsPosition[i]) / 100;
                    final float diff_y = (valuesYPosition[i + 1] - valuesYPosition[i]) / 100;
                    final int count = lineAnimationValue - i * 100;
                    canvas.drawLine(xLabelsPosition[i], valuesYPosition[i],
                            xLabelsPosition[i] + diff_x * count,
                            valuesYPosition[i] + diff_y * count,
                            valuesPaint);
                } else {
                    canvas.drawLine(xLabelsPosition[i], valuesYPosition[i],
                            xLabelsPosition[i + 1], valuesYPosition[i + 1],
                            valuesPaint);
                }
            }

            for (int i = 0; i < chartValues.size(); i++) {
                valuesPaint.setColor(0xFFFFFF);
                valuesPaint.setAlpha(alphaAnimationValue);
                canvas.drawCircle(xLabelsPosition[i], valuesYPosition[i], valuesStrokeWidth * 1.8f, valuesPaint);
                valuesPaint.setColor(valuesColor);
                valuesPaint.setAlpha(alphaAnimationValue);
                canvas.drawCircle(xLabelsPosition[i], valuesYPosition[i], valuesStrokeWidth, valuesPaint);
            }
        }
    }

    private void play() {
        ValueAnimator animator;

        PropertyValuesHolder propertyRadius = PropertyValuesHolder.ofInt("line",
                0, (chartValues.size() - 1) * 100 - 1);
        PropertyValuesHolder propertyAlpha = PropertyValuesHolder.ofInt("alpha",
                0, 255);

        animator = new ValueAnimator();
        animator.setValues(propertyRadius, propertyAlpha);
        animator.setDuration(ANIMATION_DEFAULT_DURATION);
        animator.addUpdateListener(animation -> {
            lineAnimationValue = (int) animation.getAnimatedValue("line");
            alphaAnimationValue = (int) animation.getAnimatedValue("alpha");
            invalidate();
        });

        animator.start();
    }

    public void setData(@NonNull List<Integer> values, @NonNull String[] xLabels) {
        if (values.size() != xLabels.length) {
            throw new InvalidParameterException();
        }

        chartValues = values;
        final int size = chartValues.size();
        this.xLabels = xLabels;
        xLabelsPosition = new float[size];
        valuesYPosition = new float[size];

        final float interval = (endX - startX) / (size - 1);
        final float atomicValue = (endY - startY) / (maxValue - minValue);
        for (int i = 0; i < size; i++) {
            xLabelsPosition[i] = startX + (interval * i);
            valuesYPosition[i] = endY - atomicValue * chartValues.get(i);
        }

        play();
    }

    public void setData(@NonNull List<Integer> values) {
        String[] xLabels = new String[values.size()];

        for (int i = 0; i < values.size(); i++) {
            xLabels[i] = i + "";
        }
        setData(values, xLabels);
    }
}
