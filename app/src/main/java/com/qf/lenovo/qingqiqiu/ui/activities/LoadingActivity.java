package com.qf.lenovo.qingqiqiu.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.qf.lenovo.qingqiqiu.R;
import com.qf.lenovo.qingqiqiu.ui.custom.LoadingHanderView;

public class LoadingActivity extends AppCompatActivity implements View.OnClickListener {

    private LoadingHanderView mHanderView;
    private ImageView mBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        initView();
    }

    private void initView() {
//        mHanderView = (LoadingHanderView) findViewById(R.id.custom_title_views);
//        mHanderView.setCustom("登录携程账号",this);
        mBack = (ImageView) findViewById(R.id.custom_image);
        mBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.custom_image:
                this.finish();
                break;
        }
    }
}
