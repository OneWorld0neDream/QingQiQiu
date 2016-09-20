package com.framework.magicarena.pulltorefresh;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;

/**
 *  <p>Interface that defines the operations for loading layout's controls.</p>
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
public interface ILoadingLayout {
    /**
     * Set the drawable used in the loading layout.
     * This is the same as calling <code>setLoadingDrawable(drawable, Mode.BOTH)</code>.
     *
     * @param drawable drawable to display
     */
    void setLoadingDrawable(Drawable drawable);

    /**
     * Set Text to show when the Widget is being Pulled.
     * This is the same as calling <code>setPullLabel(releaseLabel, Mode.BOTH)</code>.
     *
     * @param pullLabel char sequence to display
     */
    void setPullLabel(CharSequence pullLabel);

    /**
     * Set Text to show when the Widget is refreshing.
     * This is the same as calling <code>setRefreshingLabel(releaseLabel, Mode.BOTH)</code>.
     *
     * @param refreshingLabel char sequence to display
     */
    void setRefreshingLabel(CharSequence refreshingLabel);

    /**
     * Set Text to show when the Widget is being pulled, and will refresh when released.
     * This is the same as calling <code>setReleaseLabel(releaseLabel, Mode.BOTH)</code>.
     *
     * @param releaseLabel char sequence to display
     */
    void setReleaseLabel(CharSequence releaseLabel);

    /**
     * Set the Last Updated Text. This displayed under the main label when Pulling.
     *
     * @param label label to set
     */
    void setLastUpdatedLabel(CharSequence label);

    /**
     * Set the Sets the typeface and style in which the text should be displayed.
     * Please see {@link android.widget.TextView#setTypeface(Typeface) TextView#setTypeface(Typeface)}.
     */
    void setTextTypeface(Typeface tf);
}
