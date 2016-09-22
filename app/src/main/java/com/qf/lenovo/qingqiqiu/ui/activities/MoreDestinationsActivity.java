package com.qf.lenovo.qingqiqiu.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.widget.TextView;

import com.qf.lenovo.qingqiqiu.R;
import com.qf.lenovo.qingqiqiu.https.HttpRequestURL;
import com.qf.lenovo.qingqiqiu.ui.fragments.StrategyNearbyMoreFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    private String mLocationLat;
    private String mLocationLng;

    //***************************************
    //*	Methods								*
    //***************************************
    //***********************************
    //*	Functional Methods		    	*
    //***********************************
    private void initParams() {
        Intent args = this.getIntent();
        this.mStartMode = args.getStringExtra(ActivitySwitchParams.ACTIVITY_START_PARAM_KEY_MODE);
        this.mLocationLat = args.getStringExtra(HttpRequestURL.STRATEGY_NEARBY_LOCATIONS_REQUEST_PARAM_LAT);
        this.mLocationLng = args.getStringExtra(HttpRequestURL.STRATEGY_NEARBY_LOCATIONS_REQUEST_PARAM_LNG);

        if (this.mStartMode == null) {
            this.finish();
        }
    }

    private void setupView() {
        Fragment loadFragment = null;

        switch (this.mStartMode) {
            case ActivitySwitchParams.ACTIVITY_START_PARAM_VALUE_NEARBY:
                if (!TextUtils.isEmpty(this.mLocationLat) && !TextUtils.isEmpty(this.mLocationLng)) {
                    this.mMoreDestinationsTitle.setText(this.getString(R.string.strategy_nearby_destination));
                    Bundle args = new Bundle();
                    args.putString(HttpRequestURL.STRATEGY_NEARBY_LOCATIONS_REQUEST_PARAM_LNG, this.mLocationLng);
                    args.putString(HttpRequestURL.STRATEGY_NEARBY_LOCATIONS_REQUEST_PARAM_LAT, this.mLocationLat);
                    loadFragment = new StrategyNearbyMoreFragment();
                    loadFragment.setArguments(args);
                } else {
                    this.finish();
                }
                break;
        }

        this.getSupportFragmentManager().beginTransaction().add(R.id.flContent, loadFragment).commit();
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
