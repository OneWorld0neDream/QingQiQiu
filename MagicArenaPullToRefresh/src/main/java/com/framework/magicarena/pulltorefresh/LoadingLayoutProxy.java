package com.framework.magicarena.pulltorefresh;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;

import com.framework.magicarena.pulltorefresh.internal.AbsLoadingLayout;
import com.framework.magicarena.pulltorefresh.internal.FlipLoadingLayout;
import com.framework.magicarena.pulltorefresh.internal.RotateLoadingLayout;

import java.util.HashSet;

/**
 * <p>Proxy class that manipulates the {@linkplain ILoadingLayout} View such as
 * {@linkplain RotateLoadingLayout},{@linkplain FlipLoadingLayout} in batch.</p>
 *
 * <b>Maintenance History</b>:
 * <table>
 * 		<tr>
 * 			<th>Date</th>
 * 			<th>Developer</th>
 * 			<th>Target</th>
 * 			<th>Content</th>
 * 		</tr>
 * 		<tr>
 * 			<td>2012-8-17</td>
 * 			<td>Chris Banes</td>
 * 			<td>All</td>
 *			<td>Created.</td>
 * 		</tr>
 * </table>
 *
 */
public class LoadingLayoutProxy implements ILoadingLayout {
    //*******************************************
    //*	Instance Area 							*
    //*******************************************
    //***************************************
    //*	Fields								*
    //***************************************
    private final HashSet<AbsLoadingLayout> mLoadingLayouts;

    //***************************************
    //*	Constructors						*
    //***************************************
    /**
     * Initialize a new instance for {@link LoadingLayoutProxy}.
     */
    LoadingLayoutProxy() {
        this.mLoadingLayouts = new HashSet<AbsLoadingLayout>();
    }

    //***************************************
    //*	Methods								*
    //***************************************
    //***********************************
    //*	Functional Methods		    	*
    //***********************************
    /**
     * This allows you to add extra LoadingLayout instances to this proxy.
     * This is only necessary if you keep your own instances,
     * and want to have them included in any
     * {@link PullToRefreshBase#createLoadingLayoutProxy(boolean, boolean)
     * createLoadingLayoutProxy(...)} calls.
     *
     * @param layout LoadingLayout to have included.
     */
    public void addLayout(AbsLoadingLayout layout) {
        if (null != layout) {
            mLoadingLayouts.add(layout);
        }
    }

    //***********************************
    //*	Implements Methods				*
    //***********************************
    @Override
    public void setLoadingDrawable(Drawable drawable) {
        for (AbsLoadingLayout layout : this.mLoadingLayouts) {
            layout.setLoadingDrawable(drawable);
        }
    }

    @Override
    public void setPullLabel(CharSequence label) {
        for (AbsLoadingLayout layout : mLoadingLayouts) {
            layout.setPullLabel(label);
        }
    }

    @Override
    public void setReleaseLabel(CharSequence label) {
        for (AbsLoadingLayout layout : mLoadingLayouts) {
            layout.setReleaseLabel(label);
        }
    }

    @Override
    public void setRefreshingLabel(CharSequence refreshingLabel) {
        for (AbsLoadingLayout layout : this.mLoadingLayouts) {
            layout.setRefreshingLabel(refreshingLabel);
        }
    }

    @Override
    public void setLastUpdatedLabel(CharSequence label) {
        for (AbsLoadingLayout layout : this.mLoadingLayouts) {
            layout.setLastUpdatedLabel(label);
        }
    }

    @Override
    public void setTextTypeface(Typeface tf) {
        for (AbsLoadingLayout layout : mLoadingLayouts) {
            layout.setTextTypeface(tf);
        }
    }
}
