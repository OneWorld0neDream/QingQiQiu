package com.framework.magicarena.pulltorefresh;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by 31098 on 9/21/2016.
 */

public class FullDisplayRecyclerView extends RecyclerView {

    public FullDisplayRecyclerView(Context context) {
        super(context);
    }

    public FullDisplayRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FullDisplayRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //        int width = MeasureSpec.getSize(widthMeasureSpec);
        //        int height = MeasureSpec.getSize(heightMeasureSpec);
        //        this.setMeasuredDimension(width, height);
        //        int widthSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 1, MeasureSpec.AT_MOST);
        int heightSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 1, MeasureSpec.AT_MOST);
        super.setMeasuredDimension(widthMeasureSpec, heightSpec);
    }
}
