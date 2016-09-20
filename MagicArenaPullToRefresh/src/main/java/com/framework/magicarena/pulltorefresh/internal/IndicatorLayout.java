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
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.framework.magicarena.pulltorefresh.PullToRefreshBase;
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
public class IndicatorLayout extends FrameLayout implements AnimationListener {
    //*******************************************
    //*	Static Area 							*
    //*******************************************
    //***************************************
    //*	Fields								*
    //***************************************
    static final int DEFAULT_ROTATION_ANIMATION_DURATION = 150;

    //*******************************************
    //*	Instance Area 							*
    //*******************************************
    //***************************************
    //*	Fields								*
    //***************************************
    private Animation mInAnim;
    private Animation mOutAnim;
    private ImageView mArrowImageView;

    private final Animation mRotateAnimation;
    private final Animation mResetRotateAnimation;

    //***************************************
    //*	Constructors						*
    //***************************************

    /**
     * Initialize a new instance for {@link IndicatorLayout}.
     *
     * @param context    a {@link Context} of the application package implementing this class
     * @param mode        the refresh mode in {@link PullToRefreshBase.Mode} that can be performed
     */
    public IndicatorLayout(Context context, PullToRefreshBase.Mode mode) {
        super(context);

        final Drawable arrowD = this.getResources().getDrawable(R.drawable.indicator_arrow);
        final int padding = this.getResources().getDimensionPixelSize(R.dimen.indicator_internal_padding);
        int inAnimResId, outAnimResId;

        this.mArrowImageView = new ImageView(context);
        this.mArrowImageView.setImageDrawable(arrowD);
        this.mArrowImageView.setPadding(padding, padding, padding, padding);
        this.addView(mArrowImageView);

        switch (mode) {
            case PULL_FROM_END:
                inAnimResId = R.anim.slide_in_from_bottom;
                outAnimResId = R.anim.slide_out_to_bottom;
                this.setBackgroundResource(R.drawable.indicator_bg_bottom);

                // Rotate Arrow so it's pointing the correct way
                Matrix matrix = new Matrix();
                matrix.setRotate(180f, arrowD.getIntrinsicWidth() / 2f, arrowD.getIntrinsicHeight() / 2f);
                this.mArrowImageView.setScaleType(ScaleType.MATRIX);
                this.mArrowImageView.setImageMatrix(matrix);
                break;
            case PULL_FROM_START:
            default:
                inAnimResId = R.anim.slide_in_from_top;
                outAnimResId = R.anim.slide_out_to_top;
                this.setBackgroundResource(R.drawable.indicator_bg_top);
                break;
        }

        this.mInAnim = AnimationUtils.loadAnimation(context, inAnimResId);
        this.mInAnim.setAnimationListener(this);

        this.mOutAnim = AnimationUtils.loadAnimation(context, outAnimResId);
        this.mOutAnim.setAnimationListener(this);

        final Interpolator interpolator = new LinearInterpolator();
        this.mRotateAnimation = new RotateAnimation(0, -180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        this.mRotateAnimation.setInterpolator(interpolator);
        this.mRotateAnimation.setDuration(DEFAULT_ROTATION_ANIMATION_DURATION);
        this.mRotateAnimation.setFillAfter(true);

        this.mResetRotateAnimation = new RotateAnimation(-180, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        this.mResetRotateAnimation.setInterpolator(interpolator);
        this.mResetRotateAnimation.setDuration(DEFAULT_ROTATION_ANIMATION_DURATION);
        this.mResetRotateAnimation.setFillAfter(true);
    }

    //***************************************
    //*	Methods								*
    //***************************************
    //***********************************
    //*	Functional Methods		    	*
    //***********************************

    /**
     * Indicate whether {@link IndicatorLayout} is visible or not.
     *
     * @return true if {@link IndicatorLayout} is <code>Visible</code>;otherwise false
     */
    public final boolean isVisible() {
        Animation currentAnim = this.getAnimation();
        if (currentAnim != null) {
            return this.mInAnim == currentAnim;
        }

        return this.getVisibility() == View.VISIBLE;
    }

    /**
     * Try to hide the view of {@link IndicatorLayout}.
     */
    public void hide() {
        this.startAnimation(this.mOutAnim);
    }

    /**
     * Try to show the view of {@link IndicatorLayout}.
     */
    public void show() {
        this.mArrowImageView.clearAnimation();
        this.startAnimation(this.mInAnim);
    }

    public void releaseToRefresh() {
        this.mArrowImageView.startAnimation(this.mRotateAnimation);
    }

    public void pullToRefresh() {
        this.mArrowImageView.startAnimation(this.mResetRotateAnimation);
    }

    //***********************************
    //*	Overrides Methods				*
    //***********************************
    @Override
    public void onAnimationStart(Animation animation) {
        this.setVisibility(View.VISIBLE);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
        // NO-OP
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if (animation == this.mOutAnim) {
            this.mArrowImageView.clearAnimation();
            this.setVisibility(View.GONE);
        } else if (animation == mInAnim) {
            this.setVisibility(View.VISIBLE);
        }

        this.clearAnimation();
    }
}
