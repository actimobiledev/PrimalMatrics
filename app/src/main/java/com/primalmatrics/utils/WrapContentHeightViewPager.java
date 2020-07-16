package com.primalmatrics.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import java.util.Vector;

public class WrapContentHeightViewPager extends ViewPager {
    private Vector<Integer> heights = new Vector<>();

    public WrapContentHeightViewPager(@NonNull Context context) {
        super(context);
    }

    public WrapContentHeightViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        for(int i=0;i<getChildCount();i++) {
            View view = getChildAt(i);
            if (view != null) {
                view.measure(widthMeasureSpec, heightMeasureSpec);
                heights.add(measureHeight(heightMeasureSpec, view));
            }
        }
        setMeasuredDimension(getMeasuredWidth(), measureHeight(heightMeasureSpec, getChildAt(0)));
    }

    public int getHeightAt(int position){
        return heights.get(position);
    }

    private int measureHeight(int measureSpec, View view) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            if (view != null) {
                result = view.getMeasuredHeight();
            }
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }
}