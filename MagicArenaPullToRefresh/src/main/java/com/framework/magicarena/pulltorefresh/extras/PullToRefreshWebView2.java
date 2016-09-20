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
package com.framework.magicarena.pulltorefresh.extras;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.framework.magicarena.pulltorefresh.PullToRefreshBase;
import com.framework.magicarena.pulltorefresh.PullToRefreshWebView;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * An advanced version of {@link PullToRefreshWebView} which delegates the
 * triggering of the PullToRefresh gesture to the Javascript running within the
 * WebView. This means that you should only use this class if:
 * <p/>
 * <ul>
 * <li>{@link PullToRefreshWebView} doesn't work correctly because you're using
 * <code>overflow:scroll</code> or something else which means
 * {@link WebView#getScrollY()} doesn't return correct values.</li>
 * <li>You control the web content being displayed, as you need to write some
 * Javascript callbacks.</li>
 * </ul>
 * <p/>
 * <p/>
 * The way this call works is that when a PullToRefresh gesture is in action,
 * the following Javascript methods will be called:
 * <code>isReadyForPullDown()</code> and <code>isReadyForPullUp()</code>, it is
 * your job to calculate whether the view is in a state where a PullToRefresh
 * can happen, and return the result via the callback mechanism. An example can
 * be seen below:
 * <p/>
 *
 * <pre>
 * function isReadyForPullDown() {
 *   var result = ...  // Probably using the .scrollTop DOM attribute
 *   application_delegate.isReadyForPullDownResponse(result);
 * }
 *
 * function isReadyForPullUp() {
 *   var result = ...  // Probably using the .scrollBottom DOM attribute
 *   application_delegate.isReadyForPullUpResponse(result);
 * }
 * </pre>
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
 */
public class PullToRefreshWebView2 extends PullToRefreshWebView {
    //*******************************************
    //*	Static Area 							*
    //*******************************************
    //***************************************
    //*	Fields								*
    //***************************************
    static final String JS_INTERFACE_PKG = "application_delegate";
    static final String DEF_JS_READY_PULL_DOWN_CALL = "javascript:isReadyForPullDown();";
    static final String DEF_JS_READY_PULL_UP_CALL = "javascript:isReadyForPullUp();";

    //*******************************************
    //*	Instance Area 							*
    //*******************************************
    //***************************************
    //*	Fields      						*
    //***************************************
    private JsValueCallback mJsCallback;
    private final AtomicBoolean mIsReadyForPullDown = new AtomicBoolean(false);
    private final AtomicBoolean mIsReadyForPullUp = new AtomicBoolean(false);

    //***************************************
    //*	Constructors						*
    //***************************************

    /**
     * Initialize a new instance for {@link PullToRefreshWebView2}.
     *
     * @param context   a {@link Context} of the application package implementing this class
     */
    public PullToRefreshWebView2(Context context) {
        super(context);
    }

    /**
     * Initialize a new instance for {@link PullToRefreshWebView2}.
     *
     * @param context   a {@link Context} of the application package implementing this class
     * @param attrs     attribute set this instance's initialized with
     */
    public PullToRefreshWebView2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Initialize a new instance for {@link PullToRefreshWebView2}.
     *
     * @param context   a {@link Context} of the application package implementing this class
     * @param mode      the refresh mode in {@link PullToRefreshBase.Mode} that can be performed
     */
    public PullToRefreshWebView2(Context context, Mode mode) {
        super(context, mode);
    }

    //***************************************
    //*	Methods     						*
    //***************************************
    //***********************************
    //*	Overrides Methods				*
    //***********************************
    @Override
    protected WebView createRefreshableView(Context context, AttributeSet attrs) {
        WebView webView = super.createRefreshableView(context, attrs);

        // Need to add JS Interface so we can get the response back
        this.mJsCallback = new JsValueCallback();
        webView.addJavascriptInterface(this.mJsCallback, JS_INTERFACE_PKG);

        return webView;
    }

    @Override
    protected boolean isReadyForPullStart() {
        // Call Javascript...
        getRefreshableView().loadUrl(DEF_JS_READY_PULL_DOWN_CALL);

        // Response will be given to JsValueCallback, which will update mIsReadyForPullDown
        return this.mIsReadyForPullDown.get();
    }

    @Override
    protected boolean isReadyForPullEnd() {
        // Call Javascript...
        getRefreshableView().loadUrl(DEF_JS_READY_PULL_UP_CALL);

        // Response will be given to JsValueCallback, which will update mIsReadyForPullUp
        return this.mIsReadyForPullUp.get();
    }

    //***************************************
    //*	Nested Classes   					*
    //***************************************
    /**
     * Used for response from Javascript.
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
     */
    private final class JsValueCallback {
        /**
         * Expose the methods that can be implemented in JavaScript to calculate the condition
         * where it's ready to pull up.
         *
         * @param response  the status whether it it's ready to pull up，true when ready，otherwise false
         */
        @JavascriptInterface
        public void isReadyForPullUpResponse(boolean response) {
            PullToRefreshWebView2.this.mIsReadyForPullUp.set(response);
        }

        /**
         * Expose the methods that can be implemented in JavaScript to calculate the condition
         * where it's ready to pull down.
         *
         * @param response  the status whether it it's ready to pull down，true when ready，otherwise false
         */
        @JavascriptInterface
        public void isReadyForPullDownResponse(boolean response) {
            mIsReadyForPullDown.set(response);
        }
    }
}
