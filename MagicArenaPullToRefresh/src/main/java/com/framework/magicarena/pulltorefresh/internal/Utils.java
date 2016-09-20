package com.framework.magicarena.pulltorefresh.internal;

import android.util.Log;

/**
 * <p>Support some utility for outputting dubug log.</p>
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
public final class Utils {
    //*******************************************
    //*	Static Area 							*
    //*******************************************
    //***************************************
    //*	Fields								*
    //***************************************
    static final String LOG_TAG = "PullToRefresh";

    //***************************************
    //*	Methods								*
    //***************************************
    /**
     * Output the message of warning that deprecated method will come into effect.
     *
     * @param deprecated    deprecated content
     * @param replacement   latest content that you can use to replace by
     */
    public static void warnDeprecation(String deprecated, String replacement) {
        Log.w(LOG_TAG, "You're using the deprecated " + deprecated + " attr, please switch over to " + replacement);
    }
}
