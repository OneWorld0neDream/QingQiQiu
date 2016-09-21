package com.qf.lenovo.qingqiqiu.ui;

import android.app.Application;

import com.umeng.analytics.MobclickAgent;
import com.zhy.http.okhttp.OkHttpUtils;

import org.xutils.x;

import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import okhttp3.OkHttpClient;

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


        //OkHttpLibrary
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .build();

        OkHttpUtils.initClient(okHttpClient);

        x.Ext.init(this);
    }
}
