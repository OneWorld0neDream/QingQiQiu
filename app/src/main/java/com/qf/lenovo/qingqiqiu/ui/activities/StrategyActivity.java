package com.qf.lenovo.qingqiqiu.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.daimajia.slider.library.SliderLayout;
import com.qf.lenovo.qingqiqiu.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StrategyActivity extends AppCompatActivity {
    //*******************************************
    //*	Instance Area 							*
    //*******************************************
    //***************************************
    //*	Fields								*
    //***************************************
    @BindView(R.id.customSliderLayout)
    SliderLayout mSliderLayout;

    //***************************************
    //*	Constructors						*
    //***************************************

    //***************************************
    //*	Methods								*
    //***************************************
    //***********************************
    //*	Functional Methods		    	*
    //***********************************

    //***********************************
    //*	Implements Methods				*
    //***********************************

    //***********************************
    //*	Overrides Methods				*
    //***********************************
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strategy);

        ButterKnife.bind(this);
    }
}
