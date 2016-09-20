package com.qf.lenovo.qingqiqiu.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.qf.lenovo.qingqiqiu.R;
import com.qf.lenovo.qingqiqiu.https.DefaultCallbackImp;
import com.qf.lenovo.qingqiqiu.https.HttpRequestURL;
import com.qf.lenovo.qingqiqiu.models.StrategyAdvListModel;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StrategyFragment extends BaseFragment {
    //*******************************************
    //*	Instance Area 							*
    //*******************************************
    //***************************************
    //*	Fields								*
    //***************************************
    @BindView(R.id.customSliderLayout)
    SliderLayout mSliderLayout;

    //***************************************
    //*	Methods								*
    //***************************************
    //***********************************
    //*	Functional Methods		    	*
    //***********************************
    private void initView() {


    }


    private void setupView() {
        OkHttpUtils.get().url(HttpRequestURL.STRATEGY_ADVERSEMENT_URL).build().execute(new DefaultCallbackImp<StrategyAdvListModel>() {
            @Override
            public void onResponse(StrategyAdvListModel response, int id) {
                if (response != null) {
                    List<StrategyAdvListModel.StrategyAdvItemModel> advData = response.getData();
                    if (advData != null) {
                        for (StrategyAdvListModel.StrategyAdvItemModel item : advData) {
                            TextSliderView textSliderView = new TextSliderView(StrategyFragment.this.getActivity());
                            textSliderView.image(item.getPhoto().getPhoto_url()).description(item.getTopic());
                            StrategyFragment.this.mSliderLayout.addSlider(textSliderView);
                        }
                    }
                }
            }
        });
    }

    //***********************************
    //*	Implements Methods				*
    //***********************************

    //***********************************
    //*	Overrides Methods				*
    //***********************************
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.mFragmentLayout = inflater.inflate(R.layout.fragment_strategy, container, false);
        ButterKnife.bind(this, this.mFragmentLayout);

        return this.mFragmentLayout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        this.initView();
        this.setupView();
    }
}
