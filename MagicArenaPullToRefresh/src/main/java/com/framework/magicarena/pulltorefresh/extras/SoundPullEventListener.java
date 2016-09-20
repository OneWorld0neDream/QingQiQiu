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

import java.util.HashMap;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.View;

import com.framework.magicarena.pulltorefresh.PullToRefreshBase;
import com.framework.magicarena.pulltorefresh.PullToRefreshBase.Mode;
import com.framework.magicarena.pulltorefresh.PullToRefreshBase.State;

/**
 * <p>Supply sound effect when specified refresh event takes into action.</p>
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
public class SoundPullEventListener<V extends View> implements PullToRefreshBase.OnPullEventListener<V> {
	//*******************************************
	//*	Instance Area 							*
	//*******************************************
	//***************************************
	//*	Fields								*
	//***************************************
	private final Context mContext;
	private final HashMap<State, Integer> mSoundMap;

	private MediaPlayer mCurrentMediaPlayer;

	//***************************************
	//*	Constructors						*
	//***************************************
	/**
	 * Initialize a new instance for {@link SoundPullEventListener}.
	 *
	 * @param context	a {@link Context} of the application package implementing this class
     */
	public SoundPullEventListener(Context context) {
		this.mContext = context;
		this.mSoundMap = new HashMap<State, Integer>();
	}

	//***************************************
	//*	Methods								*
	//***************************************
	//***********************************
	//*	Functional Methods		    	*
	//***********************************
    /**
     * Set the Sounds to be played when a Pull Event happens. You specify which
     * sound plays for which events by calling this method multiple times for
     * each event.
     * <p/>
     * If you've already set a sound for a certain event, and add another sound
     * for that event, only the new sound will be played.
     *
     * @param event The event for which the sound will be played.
     * @param resId Resource Id of the sound file to be played (e.g.
     *            <var>R.raw.pull_sound</var>)
     */
    public void addSoundEvent(State event, int resId) {
        this.mSoundMap.put(event, resId);
    }

    /**
     * Clears all of the previously set sounds and events.
     */
    public void clearSounds() {
        this.mSoundMap.clear();
    }

    /**
     * Gets the current (or last) MediaPlayer instance.
     *
     * @return the current (or last) MediaPlayer instance
     */
    public MediaPlayer getCurrentMediaPlayer() {
        return this.mCurrentMediaPlayer;
    }

    /**
     * Play an associated sound as specified event is being triggered.
     *
     * @param resId resource id of associated sound
     */
    private void playSound(int resId) {
        // Stop current player, if there's one playing
        if (this.mCurrentMediaPlayer != null ) {
            this.mCurrentMediaPlayer.stop();
            this.mCurrentMediaPlayer.release();
        }

        this.mCurrentMediaPlayer = MediaPlayer.create(this.mContext, resId);
        if (this.mCurrentMediaPlayer != null) {
            mCurrentMediaPlayer.start();
        }
    }

	//***********************************
	//*	Overrides Methods				*
	//***********************************
	@Override
	public final void onPullEvent(PullToRefreshBase<V> refreshView, State event, Mode direction) {
        if (this.mSoundMap.containsKey(event)) {
            Integer soundResIdObj = this.mSoundMap.get(event);
            if (soundResIdObj != null) {
                this.playSound(soundResIdObj.intValue());
            }
        }
	}
}
