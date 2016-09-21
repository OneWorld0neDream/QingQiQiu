package com.qf.lenovo.qingqiqiu.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.qf.lenovo.qingqiqiu.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lenovo on 2016/9/20.
 */
public class ItineraryFragment extends BaseFragment {


    @BindView(R.id.fragment_itinerary_onekey)
    LinearLayout mOnekeyTrip;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentLayout = inflater.inflate(R.layout.fragment_itinerary, container, false);
        ButterKnife.bind(this, mFragmentLayout);
        return mFragmentLayout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {

    }

    @OnClick(R.id.fragment_itinerary_onekey)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fragment_itinerary_onekey:
                Toast.makeText(getActivity(), "666", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
