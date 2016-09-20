/*******************************************************************************
 * Copyright 2011, 2012 Chris Banes.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.framework.magicarena.pulltorefresh;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListAdapter;

import com.framework.magicarena.pulltorefresh.internal.EmptyViewMethodAccessor;
import com.framework.magicarena.pulltorefresh.internal.IndicatorLayout;

/**
 * <p>PullToRefresh View implementation for {@linkplain AdapterView} .</p>
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
public abstract class PullToRefreshAdapterViewBase<T extends AbsListView> extends PullToRefreshBase<T> implements
        OnScrollListener {
    //*******************************************
    //*	Static Area 							*
    //*******************************************
    //***************************************
    //*	Fields								*
    //***************************************
    private static FrameLayout.LayoutParams convertEmptyViewLayoutParams(ViewGroup.LayoutParams lp) {
        FrameLayout.LayoutParams newLp = null;

        if (lp != null) {
            newLp = new FrameLayout.LayoutParams(lp);

            if (lp instanceof LinearLayout.LayoutParams) {
                newLp.gravity = ((LinearLayout.LayoutParams) lp).gravity;
            } else {
                newLp.gravity = Gravity.CENTER;
            }
        }

        return newLp;
    }

    //*******************************************
    //*	Instance Area 							*
    //*******************************************
    //***************************************
    //*	Fields								*
    //***************************************
    private boolean mLastItemVisible;
    private OnScrollListener mOnScrollListener;
    private OnLastItemVisibleListener mOnLastItemVisibleListener;
    private View mEmptyView;

    private IndicatorLayout mIndicatorIvTop;
    private IndicatorLayout mIndicatorIvBottom;

    private boolean mShowIndicator;
    private boolean mScrollEmptyView = true;

    //***************************************
    //*	Constructors						*
    //***************************************

    /**
     * Initialize a new instance for {@link PullToRefreshAdapterViewBase}.
     *
     * @param context a {@link Context} of the application package implementing this class
     */
    public PullToRefreshAdapterViewBase(Context context) {
        super(context);
        mRefreshableView.setOnScrollListener(this);
    }

    /**
     * Initialize a new instance for {@link PullToRefreshAdapterViewBase}.
     *
     * @param context a {@link Context} of the application package implementing this class
     * @param attrs attribute set this instance's initialized with
     */
    public PullToRefreshAdapterViewBase(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mRefreshableView.setOnScrollListener(this);
    }

    /**
     * Initialize a new instance for {@link PullToRefreshAdapterViewBase}.
     *
     * @param context a {@link Context} of the application package implementing this class
     * @param mode the refresh mode in {@link PullToRefreshBase.Mode} that can be performed
     */
    public PullToRefreshAdapterViewBase(Context context, Mode mode) {
        super(context, mode);
        this.mRefreshableView.setOnScrollListener(this);
    }

    /**
     * Initialize a new instance for {@link PullToRefreshAdapterViewBase}.
     *
     * @param context a {@link Context} of the application package implementing this class
     * @param mode the refresh mode in {@link PullToRefreshBase.Mode} that can be performed
     * @param animStyle {@linkplain AnimationStyle} set this instance's initialized with
     */
    public PullToRefreshAdapterViewBase(Context context, Mode mode, AnimationStyle animStyle) {
        super(context, mode, animStyle);
        this.mRefreshableView.setOnScrollListener(this);
    }

    //***************************************
    //*	Methods								*
    //***************************************
    //***********************************
    //*	Functional Methods		    	*
    //***********************************

    /**
     * Gets whether an indicator graphic should be displayed when the View is in
     * a state where a Pull-to-Refresh can happen. An example of this state is
     * when the Adapter View is scrolled to the top and the mode is set to
     * {@link Mode#PULL_FROM_START}. The default value is <var>true</var> if
     * {@link PullToRefreshBase#isPullToRefreshOverScrollEnabled()
     * isPullToRefreshOverScrollEnabled()} returns false.
     *
     * @return true if the indicators will be shown
     */
    public boolean getShowIndicator() {
        return this.mShowIndicator;
    }

    /**
     * Sets whether an indicator graphic should be displayed when the View is in
     * a state where a Pull-to-Refresh can happen. An example of this state is
     * when the Adapter View is scrolled to the top and the mode is set to
     * {@link Mode#PULL_FROM_START}
     *
     * @param showIndicator true if the indicators should be shown
     */
    public void setShowIndicator(boolean showIndicator) {
        this.mShowIndicator = showIndicator;

        if (this.getShowIndicatorInternal()) {
            // If we're set to Show Indicator, add/update them
            this.addIndicatorViews();
        } else {
            // If not, then remove then
            this.removeIndicatorViews();
        }
    }

    /**
     * Pass-through method for {@link PullToRefreshBase#getRefreshableView} for
     * {@link AdapterView#setAdapter} for convenience.
     *
     * @param adapter {@linkplain Adapter} to set
     */
    public void setAdapter(ListAdapter adapter) {
        ((AdapterView<ListAdapter>) this.mRefreshableView).setAdapter(adapter);
    }

    /**
     * Pass-through method for {@link PullToRefreshBase#getRefreshableView} for
     * {@link AdapterView#setOnItemClickListener} for convenience.
     *
     * @param listener {@linkplain OnItemClickListener} to use
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        mRefreshableView.setOnItemClickListener(listener);
    }

    /**
     * Called by {@linkplain PullToRefreshAdapterViewBase#onScrollStateChanged}
     * when last item of {@linkplain AdapterView}'s visible.
     *
     * @param listener {@linkplain OnLastItemVisibleListener} to use
     */
    public final void setOnLastItemVisibleListener(OnLastItemVisibleListener listener) {
        this.mOnLastItemVisibleListener = listener;
    }

    /**
     * Called by {@linkplain PullToRefreshAdapterViewBase#onScrollStateChanged} and
     * {@linkplain PullToRefreshAdapterViewBase#onScroll} when {@linkplain AdapterView} is being pulled.
     *
     * @param listener {@linkplain OnLastItemVisibleListener} to use
     */
    public final void setOnScrollListener(OnScrollListener listener) {
        this.mOnScrollListener = listener;
    }

    /**
     * Sets the Empty View to be used by the {@linkplain AdapterView}.
     * We need it handle it ourselves so that we can Pull-to-Refresh when the Empty View is shown.
     *
     * <p>Please note, you do <strong>not</strong> usually need to call this method
     * yourself. Calling {@linkplain AdapterView#setEmptyView} will automatically call
     * this method and set everything up. This includes when the Android
     * Framework automatically sets the Empty View based on it's ID.</p>
     *
     * @param newEmptyView Empty View to be used
     */
    public final void setEmptyView(View newEmptyView) {
        FrameLayout refreshableViewWrapper = this.getRefreshableViewWrapper();

        if (newEmptyView != null) {
            // New view needs to be clickable so that Android recognizes it as a target for Touch Events
            newEmptyView.setClickable(true);

            ViewParent newEmptyViewParent = newEmptyView.getParent();
            if (newEmptyViewParent != null && newEmptyViewParent instanceof ViewGroup) {
                ((ViewGroup) newEmptyViewParent).removeView(newEmptyView);
            }

            // We need to convert any LayoutParams so that it works in our FrameLayout
            FrameLayout.LayoutParams lp = PullToRefreshAdapterViewBase.convertEmptyViewLayoutParams(newEmptyView.getLayoutParams());
            if (lp != null) {
                refreshableViewWrapper.addView(newEmptyView, lp);
            } else {
                refreshableViewWrapper.addView(newEmptyView);
            }
        }

        if (this.mRefreshableView instanceof EmptyViewMethodAccessor) {
            ((EmptyViewMethodAccessor) this.mRefreshableView).setEmptyViewInternal(newEmptyView);
        } else {
            this.mRefreshableView.setEmptyView(newEmptyView);
        }

        this.mEmptyView = newEmptyView;
    }

    /**
     * Set whether to use empty view when {@linkplain AdapterView}'s {@linkplain Adapter} is empty.
     *
     * @param doScroll true if using empty view
     */
    public final void setScrollEmptyView(boolean doScroll) {
        this.mScrollEmptyView = doScroll;
    }

    /**
     * Add indicator views to {@linkplain PullToRefreshAdapterViewBase}.
     */
    private void addIndicatorViews() {
        Mode mode = this.getMode();
        FrameLayout refreshableViewWrapper = this.getRefreshableViewWrapper();

        if (mode.showHeaderLoadingLayout() && this.mIndicatorIvTop == null) {
            // If the mode can pull down, and we don't have one set already
            this.mIndicatorIvTop = new IndicatorLayout(this.getContext(), Mode.PULL_FROM_START);

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.rightMargin = this.getResources().getDimensionPixelSize(R.dimen.indicator_right_padding);
            params.gravity = Gravity.TOP | Gravity.RIGHT;

            refreshableViewWrapper.addView(this.mIndicatorIvTop, params);
        } else if (!mode.showHeaderLoadingLayout() && this.mIndicatorIvTop != null) {
            // If we can't pull down, but have a View then remove it
            refreshableViewWrapper.removeView(this.mIndicatorIvTop);

            this.mIndicatorIvTop = null;
        }

        if (mode.showFooterLoadingLayout() && this.mIndicatorIvBottom == null) {
            // If the mode can pull down, and we don't have one set already
            this.mIndicatorIvBottom = new IndicatorLayout(getContext(), Mode.PULL_FROM_END);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.rightMargin = getResources().getDimensionPixelSize(R.dimen.indicator_right_padding);
            params.gravity = Gravity.BOTTOM | Gravity.RIGHT;
            refreshableViewWrapper.addView(this.mIndicatorIvBottom, params);
        } else if (!mode.showFooterLoadingLayout() && this.mIndicatorIvBottom != null) {
            // If we can't pull down, but have a View then remove it
            refreshableViewWrapper.removeView(this.mIndicatorIvBottom);
            this.mIndicatorIvBottom = null;
        }
    }

    //***********************************
    //*	Implements Methods				*
    //***********************************

    //***********************************
    //*	Overrides Methods				*
    //***********************************
    @Override
    public final void onScroll(final AbsListView view, final int firstVisibleItem, final int visibleItemCount,
                               final int totalItemCount) {
        if (DEBUG) {
            Log.d(LOG_TAG, "First Visible: " + firstVisibleItem + ". Visible Count: " + visibleItemCount
                    + ". Total Items:" + totalItemCount);
        }

        //Set whether the Last Item is Visible. lastVisibleItemIndex is a zero-based index, so we minus one totalItemCount to check
        if (this.mOnLastItemVisibleListener != null) {
            this.mLastItemVisible = (totalItemCount > 0) && (firstVisibleItem + visibleItemCount >= totalItemCount - 1);
        }

        // If we're showing the indicator, check positions...
        if (this.getShowIndicatorInternal()) {
            this.updateIndicatorViewsVisibility();
        }

        // Finally call OnScrollListener if we have one
        if (this.mOnScrollListener != null) {
            this.mOnScrollListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
        }
    }

    @Override
    public final void onScrollStateChanged(final AbsListView view, final int state) {
        // Check that the scrolling has stopped, and that the last item is visible.
        if (state == OnScrollListener.SCROLL_STATE_IDLE && this.mOnLastItemVisibleListener != null && this.mLastItemVisible) {
            this.mOnLastItemVisibleListener.onLastItemVisible();
        }

        if (this.mOnScrollListener != null) {
            this.mOnScrollListener.onScrollStateChanged(view, state);
        }
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (this.mEmptyView != null && !this.mScrollEmptyView) {
            this.mEmptyView.scrollTo(-l, -t);
        }
    }

    @Override
    protected void onPullToRefresh() {
        super.onPullToRefresh();

        if (this.getShowIndicatorInternal()) {
            switch (this.getCurrentMode()) {
                case PULL_FROM_END:
                    this.mIndicatorIvBottom.pullToRefresh();
                    break;
                case PULL_FROM_START:
                    this.mIndicatorIvTop.pullToRefresh();
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    protected void onReleaseToRefresh() {
        super.onReleaseToRefresh();

        if (getShowIndicatorInternal()) {
            switch (getCurrentMode()) {
                case PULL_FROM_END:
                    this.mIndicatorIvBottom.releaseToRefresh();
                    break;
                case PULL_FROM_START:
                    this.mIndicatorIvTop.releaseToRefresh();
                    break;
                default:
                    // NO-OP
                    break;
            }
        }
    }

    @Override
    protected void onRefreshing(boolean doScroll) {
        super.onRefreshing(doScroll);

        if (getShowIndicatorInternal()) {
            updateIndicatorViewsVisibility();
        }
    }

    @Override
    protected void onReset() {
        super.onReset();

        if (getShowIndicatorInternal()) {
            updateIndicatorViewsVisibility();
        }
    }

    @Override
    protected void handleStyledAttributes(TypedArray a) {
        // Set Show Indicator to the XML value, or default value
        this.mShowIndicator = a.getBoolean(R.styleable.PullToRefresh_ptrShowIndicator, !this.isPullToRefreshOverScrollEnabled());
    }

    @Override
    protected boolean isReadyForPullStart() {
        return this.isFirstItemVisible();
    }

    @Override
    protected boolean isReadyForPullEnd() {
        return this.isLastItemVisible();
    }

    @Override
    protected void updateUIForMode() {
        super.updateUIForMode();

        // Check Indicator Views consistent with new Mode
        if (this.getShowIndicatorInternal()) {
            this.addIndicatorViews();
        } else {
            this.removeIndicatorViews();
        }
    }

    /**
     * Indicate whether to show the indicator or not.
     *
     * @return  true if show the indicator
     */
    private boolean getShowIndicatorInternal() {
        return this.mShowIndicator && this.isPullToRefreshEnabled();
    }

    private boolean isFirstItemVisible() {
        final Adapter adapter = mRefreshableView.getAdapter();

        if (null == adapter || adapter.isEmpty()) {
            if (DEBUG) {
                Log.d(LOG_TAG, "isFirstItemVisible. Empty View.");
            }
            return true;

        } else {

            /**
             * This check should really just be:
             * mRefreshableView.getFirstVisiblePosition() == 0, but PtRListView
             * internally use a HeaderView which messes the positions up. For
             * now we'll just add one to account for it and rely on the inner
             * condition which checks getTop().
             */
            if (mRefreshableView.getFirstVisiblePosition() <= 1) {
                final View firstVisibleChild = mRefreshableView.getChildAt(0);
                if (firstVisibleChild != null) {
                    return firstVisibleChild.getTop() >= mRefreshableView.getTop();
                }
            }
        }

        return false;
    }

    private boolean isLastItemVisible() {
        final Adapter adapter = mRefreshableView.getAdapter();

        if (null == adapter || adapter.isEmpty()) {
            if (DEBUG) {
                Log.d(LOG_TAG, "isLastItemVisible. Empty View.");
            }
            return true;
        } else {
            final int lastItemPosition = mRefreshableView.getCount() - 1;
            final int lastVisiblePosition = mRefreshableView.getLastVisiblePosition();

            if (DEBUG) {
                Log.d(LOG_TAG, "isLastItemVisible. Last Item Position: " + lastItemPosition + " Last Visible Pos: "
                        + lastVisiblePosition);
            }

            /**
             * This check should really just be: lastVisiblePosition ==
             * lastItemPosition, but PtRListView internally uses a FooterView
             * which messes the positions up. For me we'll just subtract one to
             * account for it and rely on the inner condition which checks
             * getBottom().
             */
            if (lastVisiblePosition >= lastItemPosition - 1) {
                final int childIndex = lastVisiblePosition - mRefreshableView.getFirstVisiblePosition();
                final View lastVisibleChild = mRefreshableView.getChildAt(childIndex);
                if (lastVisibleChild != null) {
                    return lastVisibleChild.getBottom() <= mRefreshableView.getBottom();
                }
            }
        }

        return false;
    }

    private void removeIndicatorViews() {
        if (null != mIndicatorIvTop) {
            getRefreshableViewWrapper().removeView(mIndicatorIvTop);
            mIndicatorIvTop = null;
        }

        if (null != mIndicatorIvBottom) {
            getRefreshableViewWrapper().removeView(mIndicatorIvBottom);
            mIndicatorIvBottom = null;
        }
    }

    private void updateIndicatorViewsVisibility() {
        if (null != mIndicatorIvTop) {
            if (!isRefreshing() && isReadyForPullStart()) {
                if (!mIndicatorIvTop.isVisible()) {
                    mIndicatorIvTop.show();
                }
            } else {
                if (mIndicatorIvTop.isVisible()) {
                    mIndicatorIvTop.hide();
                }
            }
        }

        if (null != mIndicatorIvBottom) {
            if (!isRefreshing() && isReadyForPullEnd()) {
                if (!mIndicatorIvBottom.isVisible()) {
                    mIndicatorIvBottom.show();
                }
            } else {
                if (mIndicatorIvBottom.isVisible()) {
                    mIndicatorIvBottom.hide();
                }
            }
        }
    }
}
