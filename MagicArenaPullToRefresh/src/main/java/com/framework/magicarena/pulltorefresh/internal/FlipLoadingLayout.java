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
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView.ScaleType;

import com.framework.magicarena.pulltorefresh.PullToRefreshBase;
import com.framework.magicarena.pulltorefresh.PullToRefreshBase.Mode;
import com.framework.magicarena.pulltorefresh.PullToRefreshBase.Orientation;
import com.framework.magicarena.pulltorefresh.R;

/**
 * <p>Define the layout for flipping loading.</p>
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
public class FlipLoadingLayout extends AbsLoadingLayout {
    //*******************************************
    //*	Static Area 							*
    //*******************************************
    //***************************************
    //*	Fields								*
    //***************************************
    static final int FLIP_ANIMATION_DURATION = 150;

    //*******************************************
    //*	Instance Area 							*
    //*******************************************
    //***************************************
    //*	Fields								*
    //***************************************
    private final Animation mRotateAnimation;
    private final Animation mResetRotateAnimation;

    //***************************************
    //*	Constructors						*
    //***************************************

    /**
     * Initialize a new instance for {@link FlipLoadingLayout}.
     *
     * @param context    a {@link Context} of the application package implementing this class
     * @param mode        the refresh mode in {@link PullToRefreshBase.Mode} that can be performed
     * @param scrollDirection    the direction in which view can be scrolled
     * @param attrs        attribute set this instance's initialized with
     */
    public FlipLoadingLayout(Context context, final Mode mode, final Orientation scrollDirection, TypedArray attrs) {
        super(context, mode, scrollDirection, attrs);

        final int rotateAngle = mode == Mode.PULL_FROM_START ? -180 : 180;

        this.mRotateAnimation = new RotateAnimation(0, rotateAngle, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        this.mRotateAnimation.setInterpolator(ANIMATION_LINEAR_INTERPOLATOR);
        this.mRotateAnimation.setDuration(FLIP_ANIMATION_DURATION);
        this.mRotateAnimation.setFillAfter(true);

        this.mResetRotateAnimation = new RotateAnimation(rotateAngle, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        this.mResetRotateAnimation.setInterpolator(ANIMATION_LINEAR_INTERPOLATOR);
        this.mResetRotateAnimation.setDuration(FLIP_ANIMATION_DURATION);
        this.mResetRotateAnimation.setFillAfter(true);
    }

    //***************************************
    //*	Methods								*
    //***************************************
    //***********************************
    //*	Functional Methods		    	*
    //***********************************

    /**
     * Calculate the angle that initial drawable should rotate by when drawable in different directions.
     *
     * @return
     */
    private float getDrawableRotationAngle() {
        float angle = 0f;
        switch (mMode) {
            case PULL_FROM_END:
                if (mScrollDirection == Orientation.HORIZONTAL) {
                    angle = 90f;
                } else {
                    angle = 180f;
                }
                break;

            case PULL_FROM_START:
                if (mScrollDirection == Orientation.HORIZONTAL) {
                    angle = 270f;
                }
                break;

            default:
                break;
        }

        return angle;
    }

    //***********************************
    //*	Overrides Methods				*
    //***********************************
    @Override
    protected int getDefaultDrawableResId() {
        return R.drawable.default_ptr_flip;
    }

    @Override
    protected void onLoadingDrawableSet(Drawable imageDrawable) {
        if (imageDrawable != null) {
            final int dHeight = imageDrawable.getIntrinsicHeight();
            final int dWidth = imageDrawable.getIntrinsicWidth();

            /**
             * We need to set the width/height of the ImageView so that it is
             * square with each side the size of the largest drawable dimension.
             * This is so that it doesn't clip when rotated.
             */
            ViewGroup.LayoutParams lp = this.mHeaderImage.getLayoutParams();
            lp.width = lp.height = Math.max(dHeight, dWidth);
            this.mHeaderImage.requestLayout();

            /**
             * We now rotate the Drawable so that is at the correct rotation and is centered.
             */
            this.mHeaderImage.setScaleType(ScaleType.MATRIX);
            Matrix matrix = new Matrix();
            matrix.postTranslate((lp.width - dWidth) / 2f, (lp.height - dHeight) / 2f);
            matrix.postRotate(this.getDrawableRotationAngle(), lp.width / 2f, lp.height / 2f);
            this.mHeaderImage.setImageMatrix(matrix);
        }
    }

    @Override
    protected void onPullImpl(float scaleOfLayout) {
        // NO-OP
    }

    @Override
    protected void pullToRefreshImpl() {
        // Only start reset Animation, we've previously show the rotate anim
        if (this.mRotateAnimation == this.mHeaderImage.getAnimation()) {
            this.mHeaderImage.startAnimation(this.mResetRotateAnimation);
        }
    }

    @Override
    protected void refreshingImpl() {
        this.mHeaderImage.clearAnimation();
        this.mHeaderImage.setVisibility(View.INVISIBLE);
        this.mHeaderProgress.setVisibility(View.VISIBLE);
    }

    @Override
    protected void releaseToRefreshImpl() {
        this.mHeaderImage.startAnimation(this.mRotateAnimation);
    }

    @Override
    protected void resetImpl() {
        this.mHeaderImage.clearAnimation();
        this.mHeaderImage.setVisibility(View.VISIBLE);
        this.mHeaderProgress.setVisibility(View.GONE);
    }
}
