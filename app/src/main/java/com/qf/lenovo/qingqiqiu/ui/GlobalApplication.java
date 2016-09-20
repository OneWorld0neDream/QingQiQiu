package com.qf.lenovo.qingqiqiu.ui;

import android.app.Application;

import butterknife.ButterKnife;

/**
 * Created by 31098 on 9/20/2016.
 */
public class GlobalApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ButterKnife.setDebug(true);
    }
}
