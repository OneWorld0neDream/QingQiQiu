package com.qf.lenovo.qingqiqiu.ui.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.percent.PercentFrameLayout;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.VideoView;

import com.qf.lenovo.qingqiqiu.R;
import com.qf.lenovo.qingqiqiu.storage.StorageFileName;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    public static final int MESSAGE_WHAT_START_MEDIA = 101;
    public static final int MESSAGE_WHAT_SWITCH_ONLY = 102;

    public static final int[] PLAY_VIDEO_LIST = {R.raw.media};
    private static final int WELCOME_PAGE_DURATION = 3000;

    //*******************************************
    //*	Instance Area 							*
    //*******************************************
    //***************************************
    //*	Fields								*
    //***************************************
    @BindView(R.id.vvWelcome)
    VideoView mVideoView;
    @BindView(R.id.txtTimeCounter)
    TextView mTimeCounter;
    @BindView(R.id.flWelcome)
    PercentFrameLayout mWelcomeCanvas;

    private Handler mHandler;
    private int mVideoDuration;

    //***************************************
    //*	Constructors						*
    //***************************************
    private void stopPlayBackAndSwitch() {
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

        SharedPreferences preferences = this.getPreferences(MODE_PRIVATE);
        boolean firstStart = preferences.getBoolean(StorageFileName.PREFERENCE_KEY_FIRST_START, true);

        if (firstStart) {
            preferences.edit().putBoolean(StorageFileName.PREFERENCE_KEY_FIRST_START, false).apply();
            this.mHandler.sendEmptyMessageDelayed(MESSAGE_WHAT_START_MEDIA, WELCOME_PAGE_DURATION);
        } else {
            this.mHandler.sendEmptyMessageDelayed(MESSAGE_WHAT_SWITCH_ONLY, WELCOME_PAGE_DURATION);
        }
    }

    private void setRemainedTime(int restTime) {
        this.mTimeCounter.setText(String.format(this.getResources().getString(R.string.welcome_skip), restTime));
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
        this.mWelcomeCanvas.setVisibility(View.INVISIBLE);

        mp.start();
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
            case MESSAGE_WHAT_START_MEDIA:
                this.mVideoView.setVideoPath("android.resource://" + getPackageName() + "/" + PLAY_VIDEO_LIST[new Random().nextInt(PLAY_VIDEO_LIST.length)]);
                this.mVideoView.setOnPreparedListener(this);
                this.mVideoView.setOnCompletionListener(this);

                this.mTimeCounter.setOnClickListener(this);
                break;
            case MESSAGE_WHAT_SWITCH_ONLY:
                Intent intent = new Intent(this, MainActivity.class);
                this.startActivity(intent);
                this.finish();
                break;
        }

        return true;
    }

    //**************************   onClickListener **************************
    @Override
    public void onClick(View v) {
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
        this.setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);

        this.initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (this.mVideoView != null && !this.mVideoView.isPlaying()) {
            this.mVideoView.start();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (this.mVideoView != null && !this.mVideoView.isPlaying()) {
            this.mVideoView.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (this.mVideoView != null && this.mVideoView.isPlaying()) {
            this.mVideoView.pause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (this.mVideoView != null && this.mVideoView.isPlaying()) {
            this.mVideoView.pause();
        }
    }


}