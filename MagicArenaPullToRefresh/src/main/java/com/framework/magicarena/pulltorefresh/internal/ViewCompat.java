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
package com.framework.magicarena.pulltorefresh.internal;

import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.view.View;

/**
 * <p>Support a compatible view that can execute the same operations for different APIs.</p>
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
@SuppressWarnings("deprecation")
public class ViewCompat {
    /**
     * Keep running an animation when refresh view is being dragged.
     *
     * @param view        the refresh view
     * @param runnable    the action when refresh view is being dragged
     */
    public static void postOnAnimation(View view, Runnable runnable) {
        if (VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN) {
            SDK16.postOnAnimation(view, runnable);
        } else {
            view.postDelayed(runnable, 16);
        }
    }

    /**
     * Set background for the refresh view.
     *
     * @param view          the refresh view
     * @param background    drawable instance represented for background
     */
    public static void setBackground(View view, Drawable background) {
        if (VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN) {
            SDK16.setBackground(view, background);
        } else {
            view.setBackgroundDrawable(background);
        }
    }

    /**
     * Set the layer type for refreshing view.
     *
     * @param view            the refresh view
     * @param layerType        the type of layer
     */
    public static void setLayerType(View view, int layerType) {
        if (VERSION.SDK_INT >= VERSION_CODES.HONEYCOMB) {
            SDK11.setLayerType(view, layerType);
        }
    }

    /**
     * <p>Support a compatible view for app whose building version is
     * greater than {@linkplain android.os.Build.VERSION_CODES#HONEYCOMB}.</p>
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
    @TargetApi(VERSION_CODES.HONEYCOMB)
    static class SDK11 {
        /**
         * Set the layer type for refreshing view
         * when building version is greater than {@linkplain android.os.Build.VERSION_CODES#HONEYCOMB}.
         *
         * @param view       the refresh view
         * @param layerType  the type of layer
         */
        public static void setLayerType(View view, int layerType) {
            view.setLayerType(layerType, null);
        }
    }

    /**
     * <p>Support a compatible view for app whose building version is
     * greater than {@linkplain android.os.Build.VERSION_CODES#JELLY_BEAN}.</p>
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
    @TargetApi(VERSION_CODES.JELLY_BEAN)
    static class SDK16 {
        /**
         * Run an operation in UI thread
         * when building version is greater than {@linkplain android.os.Build.VERSION_CODES#JELLY_BEAN}
         *
         * @param view      the refresh view
         * @param runnable  the {@linkplain Runnable} where you can execute some operation of animation.
         */
        public static void postOnAnimation(View view, Runnable runnable) {
            view.postOnAnimation(runnable);
        }

        /**
         * Set the background to a given Drawable, or remove the background
         * when building version is greater than {@linkplain android.os.Build.VERSION_CODES#JELLY_BEAN}
         *
         * @param view          the refresh view
         * @param background    the Drawable to use as the background, or null to remove the background
         */
        public static void setBackground(View view, Drawable background) {
            view.setBackground(background);
        }
    }
}