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

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView.ScaleType;

import com.framework.magicarena.pulltorefresh.PullToRefreshBase;
import com.framework.magicarena.pulltorefresh.R;

/**
 * <p>Define the layout for rotating loading.</p>
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
public class RotateLoadingLayout extends AbsLoadingLayout {
    //*******************************************
    //*	Static Area 							*
    //*******************************************
    //***************************************
    //*	Fields								*
    //***************************************
    static final int ROTATION_ANIMATION_DURATION = 1200;

    //*******************************************
    //*	Instance Area 							*
    //*******************************************
    //***************************************
    //*	Fields								*
    //***************************************
    private final Animation mRotateAnimation;
    private final Matrix mHeaderImageMatrix;
    private final boolean mRotateDrawableWhilePulling;
    private float mRotationPivotX;
    private float mRotationPivotY;

    //***************************************
    //*	Constructors						*
    //***************************************

    /**
     * Initialize a new instance for {@link RotateLoadingLayout}.
     *
     * @param context    a {@link Context} of the application package implementing this class
     * @param mode        the refresh mode in {@link PullToRefreshBase.Mode} that can be performed
     * @param scrollDirection    the direction in which view can be scrolled
     * @param attrs        attribute set this instance's initialized with
     */
    public RotateLoadingLayout(Context context, PullToRefreshBase.Mode mode, PullToRefreshBase.Orientation scrollDirection, TypedArray attrs) {
        super(context, mode, scrollDirection, attrs);

        this.mRotateDrawableWhilePulling = attrs.getBoolean(R.styleable.PullToRefresh_ptrRotateDrawableWhilePulling, true);

        this.mHeaderImage.setScaleType(ScaleType.MATRIX);
        this.mHeaderImageMatrix = new Matrix();
        this.mHeaderImage.setImageMatrix(this.mHeaderImageMatrix);

        this.mRotateAnimation = new RotateAnimation(0, 720, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        this.mRotateAnimation.setInterpolator(ANIMATION_LINEAR_INTERPOLATOR);
        this.mRotateAnimation.setDuration(ROTATION_ANIMATION_DURATION);
        this.mRotateAnimation.setRepeatCount(Animation.INFINITE);
        this.mRotateAnimation.setRepeatMode(Animation.RESTART);
    }

    //***************************************
    //*	Methods								*
    //***************************************
    //***********************************
    //*	Functional Methods		    	*
    //***********************************

    /**
     * Reset the status of header image.
     */
    private void resetImageRotation() {
        if (this.mHeaderImageMatrix != null) {
            this.mHeaderImageMatrix.reset();
            this.mHeaderImage.setImageMatrix(this.mHeaderImageMatrix);
        }
    }

    //***********************************
    //*	Overrides Methods				*
    //***********************************
    @Override
    protected int getDefaultDrawableResId() {
        return R.drawable.default_ptr_rotate;
    }

    @Override
    public void onLoadingDrawableSet(Drawable imageDrawable) {
        if (imageDrawable != null) {
            this.mRotationPivotX = Math.round(imageDrawable.getIntrinsicWidth() / 2f);
            this.mRotationPivotY = Math.round(imageDrawable.getIntrinsicHeight() / 2f);
        }
    }

    @Override
    protected void onPullImpl(float scaleOfLayout) {
        float angle;

        if (this.mRotateDrawableWhilePulling) {
            angle = scaleOfLayout * 90f;
        } else {
            angle = Math.max(0f, Math.min(180f, scaleOfLayout * 360f - 180f));
        }

        this.mHeaderImageMatrix.setRotate(angle, this.mRotationPivotX, this.mRotationPivotY);
        this.mHeaderImage.setImageMatrix(this.mHeaderImageMatrix);
    }

    @Override
    protected void pullToRefreshImpl() {
        // NO-OP
    }

    @Override
    protected void refreshingImpl() {
        this.mHeaderImage.startAnimation(this.mRotateAnimation);
    }

    @Override
    protected void resetImpl() {
        this.mHeaderImage.clearAnimation();
        this.resetImageRotation();
    }

    @Override
    protected void releaseToRefreshImpl() {
        // NO-OP
    }
}
