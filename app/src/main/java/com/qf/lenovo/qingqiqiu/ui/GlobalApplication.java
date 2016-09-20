package com.qf.lenovo.qingqiqiu.ui;

import android.app.Application;

import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;

/**
 * Created by 31098 on 9/20/2016.
 */
public class GlobalApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //ButterKnifeLibrary
        ButterKnife.setDebug(true);

        //UmengLibrary
        String appKey = "57e102e9e0f55a31d5001ab2";
        String channelID = "3q";
        MobclickAgent.startWithConfigure(new MobclickAgent.UMAnalyticsConfig(this, appKey, channelID, MobclickAgent.EScenarioType.E_UM_GAME_OEM, true));

    }
}
