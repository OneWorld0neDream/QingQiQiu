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

import android.annotation.TargetApi;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ScrollView;

import com.framework.magicarena.pulltorefresh.PullToRefreshBase.Mode;
import com.framework.magicarena.pulltorefresh.PullToRefreshBase.State;

@TargetApi(9)
/**
 * <p>Helper class for {@linkplain View} Overscroll that encapsulates all of the necessary function.</p>
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
public final class OverscrollHelper {
    //*******************************************
    //*	Static Area 							*
    //*******************************************
    //***************************************
    //*	Fields								*
    //***************************************
    private static final String LOG_TAG = "OverscrollHelper";
    private static final float DEFAULT_OVERSCROLL_SCALE = 1f;

    //***************************************
    //*	Methods								*
    //***************************************

    /**
     * This should only be used on AdapterView's such as ListView as it just
     * calls through to overScrollBy() with the scrollRange = 0.
     * AdapterView's do not have a scroll range (i.e. getScrollY() doesn't work).
     *
     * @param view PullToRefreshView that is calling this
     * @param deltaX change in X in pixels, passed through from from {@linkplain View#overScrollBy} call
     * @param scrollX current X scroll value in pixels before applying deltaY,passed through from {@linkplain View#overScrollBy} call
     * @param deltaY change in Y in pixels, passed through from from {@linkplain View#overScrollBy} call
     * @param scrollY current Y scroll value in pixels before applying deltaY,passed through from {@linkplain View#overScrollBy} call
     * @param isTouchEvent - true if this scroll operation is the result of a touch event, passed through from from {@linkplain View#overScrollBy} call
     */
    public static void overScrollBy(final PullToRefreshBase<?> view, final int deltaX, final int scrollX,
                                    final int deltaY, final int scrollY, final boolean isTouchEvent) {
        OverscrollHelper.overScrollBy(view, deltaX, scrollX, deltaY, scrollY, 0, isTouchEvent);
    }

    /**
     * This version of the call is used for Views that need to specify a Scroll Range but scroll back to it's edge correctly.
     *
     * @param view PullToRefreshView that is calling this.
     * @param deltaX change in X in pixels, passed through from {@linkplain View#overScrollBy} call
     * @param scrollX current X scroll value in pixels before applying deltaY,passed through from {@linkplain View#overScrollBy} call
     * @param deltaY change in Y in pixels, passed through from from overScrollBy call
     * @param scrollY current Y scroll value in pixels before applying deltaY,passed through from {@linkplain View#overScrollBy} call
     * @param scrollRange scroll Range of the View, specifically needed for {@linkplain ScrollView}
     * @param isTouchEvent true if this scroll operation is the result of a touch event, passed through from {@linkplain View#overScrollBy} call
     */
    public static void overScrollBy(final PullToRefreshBase<?> view, final int deltaX, final int scrollX,
                                    final int deltaY, final int scrollY, final int scrollRange, final boolean isTouchEvent) {
        OverscrollHelper.overScrollBy(view, deltaX, scrollX, deltaY, scrollY, scrollRange, 0, DEFAULT_OVERSCROLL_SCALE, isTouchEvent);
    }

    /**
     * This is the advanced version of the call.
     *
     * @param view PullToRefreshView that is calling this.
     * @param deltaX change in X in pixels, passed through from from {@linkplain View#overScrollBy} call
     * @param scrollX current X scroll value in pixels before applying deltaY,passed through from {@linkplain View#overScrollBy} call
     * @param deltaY change in Y in pixels, passed through from from {@linkplain View#overScrollBy} call
     * @param scrollY current Y scroll value in pixels before applying deltaY,passed through from {@linkplain View#overScrollBy} call
     * @param scrollRange scroll range of the View, specifically needed for {@linkplain ScrollView}
     * @param fuzzyThreshold threshold for which the values how fuzzy we should treat the other values.
     *                       Needed for {@linkplain WebView} as it doesn't always scroll back to it's edge. 0 = no fuzziness.
     * @param scaleFactor scale Factor for overscroll amount
     * @param isTouchEvent true if this scroll operation is the result of a touch event, passed through from from {@linkplain View#overScrollBy} call
     */
    public static void overScrollBy(final PullToRefreshBase<?> view, final int deltaX, final int scrollX,
                                    final int deltaY, final int scrollY, final int scrollRange, final int fuzzyThreshold,
                                    final float scaleFactor, final boolean isTouchEvent) {
        final int deltaValue, currentScrollValue, scrollValue;

        switch (view.getPullToRefreshScrollDirection()) {
            case HORIZONTAL:
                deltaValue = deltaX;
                scrollValue = scrollX;
                currentScrollValue = view.getScrollX();
                break;
            case VERTICAL:
            default:
                deltaValue = deltaY;
                scrollValue = scrollY;
                currentScrollValue = view.getScrollY();
                break;
        }

        // Check that OverScroll is enabled and that we're not currently refreshing.
        if (view.isPullToRefreshOverScrollEnabled() && !view.isRefreshing()) {
            final Mode mode = view.getMode();

            // Check that Pull-to-Refresh is enabled, and the event isn't from touch
            if (mode.permitsPullToRefresh() && !isTouchEvent && deltaValue != 0) {
                final int newScrollValue = (deltaValue + scrollValue);

                if (PullToRefreshBase.DEBUG) {
                    Log.d(LOG_TAG, "OverScroll. DeltaX: " + deltaX + ", ScrollX: " + scrollX + ", DeltaY: " + deltaY
                            + ", ScrollY: " + scrollY + ", NewY: " + newScrollValue + ", ScrollRange: " + scrollRange
                            + ", CurrentScroll: " + currentScrollValue);
                }

                if (newScrollValue < (0 - fuzzyThreshold)) {
                    // Check the mode supports the overscroll direction, and then move scroll
                    if (mode.showHeaderLoadingLayout()) {
                        // If we're currently at zero, we're about to start overscrolling, so change the state
                        if (currentScrollValue == 0) {
                            view.setState(State.OVERSCROLLING);
                        }

                        view.setHeaderScroll((int) (scaleFactor * (currentScrollValue + newScrollValue)));
                    }
                } else if (newScrollValue > (scrollRange + fuzzyThreshold)) {
                    // Check the mode supports the overscroll direction, and then move scroll
                    if (mode.showFooterLoadingLayout()) {
                        // If we're currently at zero, we're about to start overscrolling, so change the state
                        if (currentScrollValue == 0) {
                            view.setState(State.OVERSCROLLING);
                        }

                        view.setHeaderScroll((int) (scaleFactor * (currentScrollValue + newScrollValue - scrollRange)));
                    }
                } else if (Math.abs(newScrollValue) <= fuzzyThreshold || Math.abs(newScrollValue - scrollRange) <= fuzzyThreshold) {
                    // Means we've stopped overscrolling, so scroll back to 0
                    view.setState(State.RESET);
                }
            } else if (isTouchEvent && view.getState() == State.OVERSCROLLING) {
                // This condition means that we were overscrolling from a fling,
                // but the user has touched the View and is now overscrolling
                // from touch instead. We need to just reset.
                view.setState(State.RESET);
            }
        }
    }

    /**
     * Check whether specified view can support {@linkplain View} OverScroll or not.
     *
     * @param view  specified view
     * @return true if specified view can support {@linkplain View} OverScroll
     */
    static boolean isAndroidOverScrollEnabled(View view) {
        return view.getOverScrollMode() != View.OVER_SCROLL_NEVER;
    }
}