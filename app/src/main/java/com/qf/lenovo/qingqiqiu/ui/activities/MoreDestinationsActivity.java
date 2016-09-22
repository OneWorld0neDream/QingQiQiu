package com.qf.lenovo.qingqiqiu.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.qf.lenovo.qingqiqiu.R;
import com.qf.lenovo.qingqiqiu.https.HttpRequestURL;
import com.qf.lenovo.qingqiqiu.ui.fragments.StrategyGlobalMoreFragment;
import com.qf.lenovo.qingqiqiu.ui.fragments.StrategyNearbyMoreFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 31098 on 9/21/2016.
 */

public class MoreDestinationsActivity extends BaseActivity {
    //*******************************************
    //*	Instance Area 							*
    //*******************************************
    //***************************************
    //*	Fields								*
    //***************************************
    @BindView(R.id.txtMoreDestinationsTitle)
    TextView mMoreDestinationsTitle;

    private String mStartMode;

    //***************************************
    //*	Methods								*
    //***************************************
    //***********************************
    //*	Functional Methods		    	*
    //***********************************
    private void initParams() {
        Intent args = this.getIntent();
        this.mStartMode = args.getStringExtra(ActivitySwitchParams.ACTIVITY_START_PARAM_KEY_MODE);

        if (this.mStartMode == null) {
            this.finish();
        }
    }

    private void setupView() {
        Fragment loadFragment = null;
        Bundle args;

        switch (this.mStartMode) {
            case ActivitySwitchParams.ACTIVITY_START_PARAM_VALUE_NEARBY:
                String locationLat = this.getIntent().getStringExtra(HttpRequestURL.STRATEGY_NEARBY_LOCATIONS_REQUEST_PARAM_LAT);
                String locationLng = this.getIntent().getStringExtra(HttpRequestURL.STRATEGY_NEARBY_LOCATIONS_REQUEST_PARAM_LNG);

                if (!TextUtils.isEmpty(locationLat) && !TextUtils.isEmpty(locationLng)) {
                    this.mMoreDestinationsTitle.setText(this.getString(R.string.strategy_nearby_destination));

                    args = new Bundle();
                    args.putString(HttpRequestURL.STRATEGY_NEARBY_LOCATIONS_REQUEST_PARAM_LNG, locationLat);
                    args.putString(HttpRequestURL.STRATEGY_NEARBY_LOCATIONS_REQUEST_PARAM_LAT, locationLng);

                    loadFragment = new StrategyNearbyMoreFragment();
                    loadFragment.setArguments(args);
                } else {
                    this.finish();
                }
                break;
            case ActivitySwitchParams.ACTIVITY_START_PARAM_VALUE_GLOBAL:
                String region = this.getIntent().getStringExtra(ActivitySwitchParams.ACTIVITY_START_PARAM_KEY_REGIION);
                String name = this.getIntent().getStringExtra(ActivitySwitchParams.ACTIVITY_START_PARAM_KEY_NAME);

                if (!TextUtils.isEmpty(region) && !TextUtils.isEmpty(name)) {
                    this.mMoreDestinationsTitle.setText(name);

                    args = new Bundle();
                    args.putString(HttpRequestURL.STRATEGY_OTHER_LOCATIONS_REQUEST_PARAM_AREA, region);

                    loadFragment = new StrategyGlobalMoreFragment();
                    loadFragment.setArguments(args);
                }
                break;
        }

        this.getSupportFragmentManager().beginTransaction().add(R.id.flContent, loadFragment).commit();
    }

    @OnClick(R.id.imgMoreDestinationsBack)
    void onItemClicked(View view) {
        switch (view.getId()) {
            case R.id.imgMoreDestinationsBack:
                this.finish();
                break;
        }
    }

    //***********************************
    //*	Overrides Methods				*
    //***********************************
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_more_destinations);

        ButterKnife.bind(this);

        this.initParams();
        this.setupView();
    }
}
