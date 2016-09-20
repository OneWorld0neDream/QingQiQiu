package com.qf.lenovo.qingqiqiu.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.framework.magicarena.pulltorefresh.PullToRefreshBase;

/**
 * Created by Administrator on 2016/9/20 0020.
 */
public class TravelNoteAdapter extends PullToRefreshBase<RecyclerView> {

    public TravelNoteAdapter(Context context) {
        super(context);
    }

    public TravelNoteAdapter(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TravelNoteAdapter(Context context, Mode mode) {
        super(context, mode);
    }

    public TravelNoteAdapter(Context context, Mode mode, AnimationStyle animStyle) {
        super(context, mode, animStyle);
    }

    @Override
    public Orientation getPullToRefreshScrollDirection() {
        return null;
    }

    @Override
    protected RecyclerView createRefreshableView(Context context, AttributeSet attrs) {
        return null;
    }

    @Override
    protected boolean isReadyForPullEnd() {
        return false;
    }

    @Override
    protected boolean isReadyForPullStart() {
        return false;
    }
}
