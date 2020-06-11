package com.alimaddi.segmentedbutton;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

/**
 * This view is used as dividers between Button Actors for the SegmentedButtonGroup LinearLayout
 * that handles the dividers
 *
 * Each view has a model SegmentedButton. The width & height is adjusted as the
 * divider width & height between the two buttons.
 */
class Divider extends View
{
//    private static int color = 0;
    // Button to mimics size of
    private SegmentedButton button = null;

    // Divider width
    private int customWidth = 0;
    // Divider padding
    private int padding = 0;

    public Divider(Context context)
    {
        super(context);
//        setBackground(ContextCompat.getDrawable(context, R.drawable.back_test));
//        setBackgroundColor(Color.argb(255, 0, color+=80, 0));
    }

    public Divider(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
    }

    public Divider(Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    public void setButton(SegmentedButton button)
    {
        this.button = button;
    }

    public void setWidth(int width)
    {
        customWidth = width;
    }
    public void setVerticalPadding(int padding)
    {
        this.padding = padding;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        // Desired size is the suggested minimum size
        // Resolve size based on the measure spec and go from there
        // resolveSize
        // View.onMeasure uses getDefaultSize which is similar to resolveSize except in the case of
        // MeasureSpec.AT_MOST, the maximum value will be returned. In other words, View will expand to fill the
        // available area while resolveSize will only use the desired size.

        // Width and height to set the view to
        int width;
        int height;

        // the button is a Button Actor in the right hand side of the divider
        if (button != null)
        {
            // Calculate the width & height based on the desired width & height and the measure specs
            width = resolveSize(customWidth, widthMeasureSpec);
            height = resolveSize(button.getMeasuredHeight() - padding, heightMeasureSpec);
        }
        else
        {
            // Fallback option to calculate the size based on suggested minimum width & height
            width = resolveSize(getSuggestedMinimumWidth(), widthMeasureSpec);
            height = resolveSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        }

        setMeasuredDimension(width, height);
    }
}
