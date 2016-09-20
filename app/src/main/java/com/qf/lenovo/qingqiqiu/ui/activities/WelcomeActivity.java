package com.qf.lenovo.qingqiqiu.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.VideoView;

import com.qf.lenovo.qingqiqiu.R;

import java.util.Locale;
import java.util.Random;


public class WelcomeActivity extends BaseActivity implements
        android.media.MediaPlayer.OnCompletionListener,
        android.media.MediaPlayer.OnPreparedListener,
        Handler.Callback, View.OnClickListener {
    //*******************************************
    //*	Static Area 							*
    //*******************************************
    //***************************************
    //*	Fields								*
    //***************************************
    private static final String TAG = WelcomeActivity.class.getSimpleName();
    public static final int MESSAGE_WHAT_TIME_COUNTER = 100;
    public static final int[] PLAY_VIDEO_LIST = {R.raw.media};

    //*******************************************
    //*	Instance Area 							*
    //*******************************************
    //***************************************
    //*	Fields								*
    //***************************************
    private VideoView mVideoView;
    private TextView mTimeCounter;

    private Handler mHandler;
    private int mVideoDuration;

    //***************************************
    //*	Constructors						*
    //***************************************
    private void stopPlayBackAndSwitch() {
        Log.e(TAG, "stopPlayBackAndSwitch: ");
        if (this.mVideoView != null) {
            this.mVideoView.stopPlayback();
            this.mVideoView = null;

            Intent intent = new Intent(this, MainActivity.class);
            this.startActivity(intent);
            this.finish();
        }
    }

    //***************************************
    //*	Methods								*
    //***************************************
    //***********************************
    //*	Functional Methods		    	*
    //***********************************
    private void initView() {
        this.mHandler = new Handler(this);

        this.mVideoView.setVideoPath("android.resource://" + getPackageName() + "/" + PLAY_VIDEO_LIST[new Random().nextInt(PLAY_VIDEO_LIST.length)]);
        this.mVideoView.setOnPreparedListener(this);
        this.mVideoView.setOnCompletionListener(this);

        this.mTimeCounter.setOnClickListener(this);
    }

    private void setRemainedTime(int restTime) {
        this.mTimeCounter.setText(String.format(Locale.CHINA, this.getResources().getString(R.string.welcome_skip), new Object[]{restTime}));
    }

    //***********************************
    //*	Implements Methods				*
    //***********************************
    //**************************  OnCompletionListener **************************
    @Override
    public void onCompletion(android.media.MediaPlayer mp) {
        this.stopPlayBackAndSwitch();
    }

    //**************************   OnPreparedListener **************************
    @Override
    public void onPrepared(android.media.MediaPlayer mp) {
        mp.start();
        Log.e("onPrepared", "onPrepared:" + this.mVideoView.getDuration());
        this.mVideoDuration = this.mVideoView.getDuration() / 1000;
        this.mTimeCounter.setVisibility(View.VISIBLE);
        this.setRemainedTime(this.mVideoDuration);
        this.mHandler.sendMessage(this.mHandler.obtainMessage(MESSAGE_WHAT_TIME_COUNTER, this.mVideoDuration, 0));
    }

    //**************************   Callback **************************
    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case MESSAGE_WHAT_TIME_COUNTER:
                int currentTime = --msg.arg1;

                if (currentTime > 0) {
                    this.setRemainedTime(currentTime);
                    this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(MESSAGE_WHAT_TIME_COUNTER, currentTime, 0), 1000);
                } else {
                    this.stopPlayBackAndSwitch();
                }

                break;
        }

        return true;
    }

    //**************************   onClickListener **************************
    @Override
    public void onClick(View v) {
        Log.e(TAG, "onClick: " + v.getId());
        switch (v.getId()) {
            case R.id.txtTimeCounter:
                this.stopPlayBackAndSwitch();
                break;
        }
    }

    //***********************************
    //*	Overrides Methods				*
    //***********************************
    @Override
    public void onCreate(Bundle savedInstanceState) {
        this.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);

        this.initView();
    }
}