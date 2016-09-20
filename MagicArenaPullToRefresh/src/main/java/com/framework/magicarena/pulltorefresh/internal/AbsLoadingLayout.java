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

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.framework.magicarena.pulltorefresh.ILoadingLayout;
import com.framework.magicarena.pulltorefresh.PullToRefreshBase.Mode;
import com.framework.magicarena.pulltorefresh.PullToRefreshBase.Orientation;
import com.framework.magicarena.pulltorefresh.R;

/**
 * <p>Define the layout for indicator.</p>
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
@SuppressLint("ViewConstructor")
public abstract class AbsLoadingLayout extends FrameLayout implements ILoadingLayout {
    //*******************************************
    //*	Static Area 							*
    //*******************************************
    //***************************************
    //*	Fields								*
    //***************************************
    protected static final Interpolator ANIMATION_LINEAR_INTERPOLATOR = new LinearInterpolator();

    //*******************************************
    //*	Instance Area 							*
    //*******************************************
    //***************************************
    //*	Fields								*
    //***************************************
    private FrameLayout mInnerLayout;

    private CharSequence mPullLabel;
    private CharSequence mReleaseLabel;
    private CharSequence mRefreshingLabel;

    private boolean mUseIntrinsicAnimation;

    private final TextView mHeaderText;
    private final TextView mSubHeaderText;

    protected final ImageView mHeaderImage;
    protected final ProgressBar mHeaderProgress;

    protected final Mode mMode;
    protected final Orientation mScrollDirection;

    //***************************************
    //*	Constructors						*
    //***************************************

    /**
     * Initialize a new instance for {@link RotateLoadingLayout}.
     *
     * @param context    a {@link Context} of the application package implementing this class
     * @param mode        the refresh mode in {@link Mode} that can be performed
     * @param scrollDirection    the direction in which view can be scrolled
     * @param attrs        attribute set this instance's initialized with
     */
    public AbsLoadingLayout(Context context, final Mode mode, final Orientation scrollDirection, TypedArray attrs) {
        super(context);

        this.mMode = mode;
        this.mScrollDirection = scrollDirection;

        switch (scrollDirection) {
            case HORIZONTAL:
                LayoutInflater.from(context).inflate(R.layout.pull_to_refresh_header_horizontal, this);
                break;
            case VERTICAL:
            default:
                LayoutInflater.from(context).inflate(R.layout.pull_to_refresh_header_vertical, this);
                break;
        }

        //Load in controls
        this.mInnerLayout = (FrameLayout) this.findViewById(R.id.fl_inner);
        this.mHeaderImage = (ImageView) this.mInnerLayout.findViewById(R.id.pull_to_refresh_image);
        this.mHeaderProgress = (ProgressBar) this.mInnerLayout.findViewById(R.id.pull_to_refresh_progress);
        this.mHeaderText = (TextView) this.mInnerLayout.findViewById(R.id.pull_to_refresh_text);
        this.mSubHeaderText = (TextView) this.mInnerLayout.findViewById(R.id.pull_to_refresh_sub_text);

        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) this.mInnerLayout.getLayoutParams();

        switch (mode) {
            case PULL_FROM_END:
                lp.gravity = scrollDirection == Orientation.VERTICAL ? Gravity.TOP : Gravity.LEFT;

                // Load in labels resource
                this.mPullLabel = context.getString(R.string.pull_to_refresh_from_bottom_pull_label);
                this.mRefreshingLabel = context.getString(R.string.pull_to_refresh_from_bottom_refreshing_label);
                this.mReleaseLabel = context.getString(R.string.pull_to_refresh_from_bottom_release_label);

                break;
            case PULL_FROM_START:
            default:
                lp.gravity = scrollDirection == Orientation.VERTICAL ? Gravity.BOTTOM : Gravity.RIGHT;

                // Load in labels
                this.mPullLabel = context.getString(R.string.pull_to_refresh_pull_label);
                this.mRefreshingLabel = context.getString(R.string.pull_to_refresh_refreshing_label);
                this.mReleaseLabel = context.getString(R.string.pull_to_refresh_release_label);

                break;
        }

        //Get attributes from XML configuration
        if (attrs.hasValue(R.styleable.PullToRefresh_ptrHeaderBackground)) {
            Drawable background = attrs.getDrawable(R.styleable.PullToRefresh_ptrHeaderBackground);
            if (background != null) {
                ViewCompat.setBackground(this, background);
            }
        }

        if (attrs.hasValue(R.styleable.PullToRefresh_ptrHeaderTextAppearance)) {
            TypedValue styleID = new TypedValue();
            attrs.getValue(R.styleable.PullToRefresh_ptrHeaderTextAppearance, styleID);
            this.setTextAppearance(styleID.data);
        }

        if (attrs.hasValue(R.styleable.PullToRefresh_ptrSubHeaderTextAppearance)) {
            TypedValue styleID = new TypedValue();
            attrs.getValue(R.styleable.PullToRefresh_ptrSubHeaderTextAppearance, styleID);
            this.setSubTextAppearance(styleID.data);
        }

        // Text Color attrs need to be set after TextAppearance attrs
        if (attrs.hasValue(R.styleable.PullToRefresh_ptrHeaderTextColor)) {
            ColorStateList colors = attrs.getColorStateList(R.styleable.PullToRefresh_ptrHeaderTextColor);
            if (colors != null) {
                this.setTextColor(colors);
            }
        }

        if (attrs.hasValue(R.styleable.PullToRefresh_ptrHeaderSubTextColor)) {
            ColorStateList colors = attrs.getColorStateList(R.styleable.PullToRefresh_ptrHeaderSubTextColor);
            if (null != colors) {
                setSubTextColor(colors);
            }
        }

        // Try and get defined drawable from Attrs
        Drawable imageDrawable = null;
        if (attrs.hasValue(R.styleable.PullToRefresh_ptrDrawable)) {
            imageDrawable = attrs.getDrawable(R.styleable.PullToRefresh_ptrDrawable);
        }

        // Check Specific Drawable from Attrs, these overwrite the generic drawable attributes above
        switch (mode) {
            case PULL_FROM_END:
                if (attrs.hasValue(R.styleable.PullToRefresh_ptrDrawableEnd)) {
                    imageDrawable = attrs.getDrawable(R.styleable.PullToRefresh_ptrDrawableEnd);
                } else if (attrs.hasValue(R.styleable.PullToRefresh_ptrDrawableBottom)) {
                    Utils.warnDeprecation("ptrDrawableBottom", "ptrDrawableEnd");
                    imageDrawable = attrs.getDrawable(R.styleable.PullToRefresh_ptrDrawableBottom);
                }

                break;
            case PULL_FROM_START:
            default:
                if (attrs.hasValue(R.styleable.PullToRefresh_ptrDrawableStart)) {
                    imageDrawable = attrs.getDrawable(R.styleable.PullToRefresh_ptrDrawableStart);
                } else if (attrs.hasValue(R.styleable.PullToRefresh_ptrDrawableTop)) {
                    Utils.warnDeprecation("ptrDrawableTop", "ptrDrawableStart");
                    imageDrawable = attrs.getDrawable(R.styleable.PullToRefresh_ptrDrawableTop);
                }

                break;
        }

        // If we don't have a user defined drawable, load the default
        if (imageDrawable == null) {
            imageDrawable = context.getResources().getDrawable(this.getDefaultDrawableResId());
        }

        // Set Drawable, and save width/height
        this.setLoadingDrawable(imageDrawable);

        this.reset();
    }

    //***************************************
    //*	Methods								*
    //***************************************
    //***********************************
    //*	Functional Methods		    	*
    //***********************************

    /**
     * Set the height of {@linkplain AbsLoadingLayout}.
     *
     * @param height    spcified height
     */
    public final void setHeight(int height) {
        ViewGroup.LayoutParams lp = (ViewGroup.LayoutParams) this.getLayoutParams();
        lp.height = height;
        this.requestLayout();
    }

    /**
     * Set the width of {@linkplain AbsLoadingLayout}.
     *
     * @param width spcified width
     */
    public final void setWidth(int width) {
        ViewGroup.LayoutParams lp = (ViewGroup.LayoutParams) this.getLayoutParams();
        lp.width = width;
        this.requestLayout();
    }

    /**
     * Get size of current content being displayed.
     *
     * @return the with of content when being scrolled horizontally;the height vertically.
     */
    public final int getContentSize() {
        switch (this.mScrollDirection) {
            case HORIZONTAL:
                return this.mInnerLayout.getWidth();
            case VERTICAL:
            default:
                return this.mInnerLayout.getHeight();
        }
    }

    /**
     * Show all the view parts of {@linkplain AbsLoadingLayout}.
     */
    public final void showInvisibleViews() {
        if (View.INVISIBLE == this.mHeaderText.getVisibility()) {
            this.mHeaderText.setVisibility(View.VISIBLE);
        }
        if (View.INVISIBLE == this.mHeaderProgress.getVisibility()) {
            this.mHeaderProgress.setVisibility(View.VISIBLE);
        }
        if (View.INVISIBLE == this.mHeaderImage.getVisibility()) {
            this.mHeaderImage.setVisibility(View.VISIBLE);
        }
        if (View.INVISIBLE == this.mSubHeaderText.getVisibility()) {
            this.mSubHeaderText.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Hide all the view parts of {@linkplain AbsLoadingLayout}.
     */
    public final void hideAllViews() {
        if (View.VISIBLE == this.mHeaderText.getVisibility()) {
            this.mHeaderText.setVisibility(View.INVISIBLE);
        }
        if (View.VISIBLE == this.mHeaderProgress.getVisibility()) {
            this.mHeaderProgress.setVisibility(View.INVISIBLE);
        }
        if (View.VISIBLE == this.mHeaderImage.getVisibility()) {
            this.mHeaderImage.setVisibility(View.INVISIBLE);
        }
        if (View.VISIBLE == this.mSubHeaderText.getVisibility()) {
            this.mSubHeaderText.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * Called when the layout starts to be pulled.
     *
     * @param scaleOfLayout
     */
    public final void onPull(float scaleOfLayout) {
        if (!this.mUseIntrinsicAnimation) {
            this.onPullImpl(scaleOfLayout);
        }
    }

    /**
     * Called when the layout keeps being pulled.
     */
    public final void pullToRefresh() {
        if (this.mHeaderText != null) {
            this.mHeaderText.setText(this.mPullLabel);
        }

        // Now call the callback
        this.pullToRefreshImpl();
    }

    /**
     *  Called when the layout released in state of being ready for refreshing and content starts to be refreshed.
     */
    public final void releaseToRefresh() {
        if (this.mHeaderText != null) {
            mHeaderText.setText(this.mReleaseLabel);
        }

        // Now call the callback
        this.releaseToRefreshImpl();
    }

    /**
     * Called when the content is in refreshing state.
     */
    public final void refreshing() {
        if (this.mUseIntrinsicAnimation) {
            ((AnimationDrawable) this.mHeaderImage.getDrawable()).start();
        } else {
            // Now call the callback
            this.refreshingImpl();
        }

        if (this.mHeaderText != null) {
            this.mHeaderText.setText(this.mRefreshingLabel);
        }

        if (this.mSubHeaderText != null) {
            this.mSubHeaderText.setVisibility(View.GONE);
        }
    }

    /**
     * Reset the loading layout state.
     */
    public final void reset() {
        this.mHeaderImage.setVisibility(View.VISIBLE);
        if (this.mUseIntrinsicAnimation) {
            ((AnimationDrawable) this.mHeaderImage.getDrawable()).stop();
        } else {
            // Now call the callback
            this.resetImpl();
        }

        if (this.mHeaderText != null) {
            this.mHeaderText.setText(this.mPullLabel);
        }

        if (this.mSubHeaderText != null) {
            if (TextUtils.isEmpty(this.mSubHeaderText.getText())) {
                this.mSubHeaderText.setVisibility(View.GONE);
            } else {
                this.mSubHeaderText.setVisibility(View.VISIBLE);
            }
        }
    }

    /**
     * Sets the text color, size, style, hint color, and highlight color
     * from the specified TextAppearance resource for both primary header and secondary header.
     *
     * @param value specified resource id
     */
    private void setTextAppearance(int value) {
        if (this.mHeaderText != null) {
            this.mHeaderText.setTextAppearance(this.getContext(), value);
        }
        if (this.mSubHeaderText != null) {
            this.mSubHeaderText.setTextAppearance(this.getContext(), value);
        }
    }

    /**
     * Sets the text color state list of selector for both primary header and secondary header.
     *
     * @param color specified color selector resource instance
     */
    private void setTextColor(ColorStateList color) {
        if (this.mHeaderText != null) {
            this.mHeaderText.setTextColor(color);
        }
        if (this.mSubHeaderText != null) {
            this.mSubHeaderText.setTextColor(color);
        }
    }

    /**
     * Set the text of sub header.
     *
     * @param label char sequence to display
     */
    private void setSubHeaderText(CharSequence label) {
        if (this.mSubHeaderText != null) {
            if (TextUtils.isEmpty(label)) {
                this.mSubHeaderText.setVisibility(View.GONE);
            } else {
                this.mSubHeaderText.setText(label);

                // Only set it to Visible if we're GONE, otherwise VISIBLE will be set soon
                if (this.mSubHeaderText.getVisibility() == View.GONE) {
                    this.mSubHeaderText.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    /**
     * Sets the text color, size, style, hint color, and highlight color
     * from the specified TextAppearance resource only for secondary header.
     *
     * @param value specified resource id
     */
    private void setSubTextAppearance(int value) {
        if (this.mSubHeaderText != null) {
            this.mSubHeaderText.setTextAppearance(getContext(), value);
        }
    }

    /**
     * Sets the text color state list of selector only for secondary header.
     *
     * @param color specified color selector resource instance
     */
    private void setSubTextColor(ColorStateList color) {
        if (this.mSubHeaderText != null) {
            this.mSubHeaderText.setTextColor(color);
        }
    }

    //***********************************
    //*	Implements Methods				*
    //***********************************

    //*************************** ILoadingLayout ***************************
    @Override
    public final void setLoadingDrawable(Drawable imageDrawable) {
        // Set Drawable
        this.mHeaderImage.setImageDrawable(imageDrawable);
        this.mUseIntrinsicAnimation = (imageDrawable instanceof AnimationDrawable);

        // Now call the callback
        this.onLoadingDrawableSet(imageDrawable);
    }

    //Set primary label in different statuses
    @Override
    public void setPullLabel(CharSequence pullLabel) {
        this.mPullLabel = pullLabel;
    }

    @Override
    public void setRefreshingLabel(CharSequence refreshingLabel) {
        this.mRefreshingLabel = refreshingLabel;
    }

    @Override
    public void setReleaseLabel(CharSequence releaseLabel) {
        this.mReleaseLabel = releaseLabel;
    }

    //Set updated(secondary) label
    @Override
    public void setLastUpdatedLabel(CharSequence label) {
        this.setSubHeaderText(label);
    }

    @Override
    public void setTextTypeface(Typeface tf) {
        mHeaderText.setTypeface(tf);
    }

    //***********************************
    //*	Abstract Methods				*
    //***********************************

    /**
     * Get the default resource id of loading drawable being displayed.
     *
     * @return default resource id of {@link Drawable}
     */
    protected abstract int getDefaultDrawableResId();

    /**
     * Called when loading drawable is set.
     *
     * @param imageDrawable loading drawable specified by custom
     */
    protected abstract void onLoadingDrawableSet(Drawable imageDrawable);

    /**
     * Called when the layout starts to be pulled in the condition of using customized loading drawable.
     *
     * @param scaleOfLayout scale of loading layout
     */
    protected abstract void onPullImpl(float scaleOfLayout);

    /**
     * Called when the layout keeps being pulled.
     */
    protected abstract void pullToRefreshImpl();

    /**
     * Called when the layout released in state of being ready for refreshing where content starts to be refreshed.
     */
    protected abstract void releaseToRefreshImpl();

    /**
     * Called when the content is in refreshing state.
     */
    protected abstract void refreshingImpl();

    /**
     * Reset the loading layout state in the condition of using customized loading drawable.
     */
    protected abstract void resetImpl();
}
