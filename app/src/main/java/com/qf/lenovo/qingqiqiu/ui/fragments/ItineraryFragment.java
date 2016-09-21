package com.qf.lenovo.qingqiqiu.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qf.lenovo.qingqiqiu.R;

import butterknife.ButterKnife;

/**
 * Created by lenovo on 2016/9/20.
 */
public class ItineraryFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentLayout = inflater.inflate(R.layout.fragment_itinerary, container, false);
        ButterKnife.bind(this, mFragmentLayout);
        return mFragmentLayout;
    }
}
