package com.qf.lenovo.qingqiqiu.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framework.magicarena.core.widget.decorations.RecyclerViewDivider;
import com.qf.lenovo.qingqiqiu.R;
import com.qf.lenovo.qingqiqiu.adapters.StrategyLocationsAdapter;
import com.qf.lenovo.qingqiqiu.https.DefaultCallbackImp;
import com.qf.lenovo.qingqiqiu.https.HttpRequestURL;
import com.qf.lenovo.qingqiqiu.models.StragegyDestinationsListModel;
import com.qf.lenovo.qingqiqiu.models.StrategyLocationsListModel;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.request.RequestCall;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 31098 on 9/21/2016.
 */

public class StrategyGlobalMoreFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    //*******************************************
    //*	Instance Area 							*
    //*******************************************
    //***************************************
    //*	Fields								*
    //***************************************
    @BindView(R.id.rvNearbyList)
    RecyclerView mLocationsList;
    @BindView(R.id.srlRefresh)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private String mRegion;
    private StrategyLocationsAdapter mLocationsAdapter;

    //***************************************
    //*	Constructors						*
    //***************************************

    //***************************************
    //*	Methods								*
    //***************************************
    //***********************************
    //*	Functional Methods		    	*
    //***********************************
    private void initParam() {
        Bundle args = this.getArguments();

        this.mRegion = args.getString(HttpRequestURL.STRATEGY_OTHER_LOCATIONS_REQUEST_PARAM_AREA, null);

        if (TextUtils.isEmpty(this.mRegion)) {
            this.getActivity().finish();
        }
    }

    private void initView() {
        this.mLocationsAdapter = new StrategyLocationsAdapter(this.getActivity(), null, R.layout.strategy_nearby_more_list_view_item);
        LinearLayoutManager layout = new LinearLayoutManager(this.getActivity());
        this.mLocationsList.setLayoutManager(layout);
        this.mLocationsList.addItemDecoration(new RecyclerViewDivider(this.getActivity(), LinearLayoutManager.HORIZONTAL, 2, this.getActivity().getResources().getColor(R.color.cardview_dark_background)));
        this.mLocationsList.setAdapter(this.mLocationsAdapter);

        this.mSwipeRefreshLayout.setProgressViewOffset(true, -100, 200);
        this.mSwipeRefreshLayout.setOnRefreshListener(this);
        this.mSwipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);
    }

    private void setupView() {
        RequestCall build = OkHttpUtils.get()
                .url(HttpRequestURL.STRATEGY_GLOBAL_LOCATIONS_URL)
                .addParams(HttpRequestURL.STRATEGY_OTHER_LOCATIONS_REQUEST_PARAM_AREA, this.mRegion)
                .build();
        build
                .execute(new DefaultCallbackImp<StrategyLocationsListModel>() {
                    @Override
                    public void onResponse(StrategyLocationsListModel response, int id) {
                        if (response != null) {
                            List<StragegyDestinationsListModel.DestinationLocationsList.DestinationsLocationItem> data = response.getData();
                            if (data != null) {
                                StrategyGlobalMoreFragment.this.mLocationsAdapter.updateDataSouce(data);
                                StrategyGlobalMoreFragment.this.mSwipeRefreshLayout.setRefreshing(false);
                            }
                        }
                    }
                });
    }

    //***********************************
    //*	Implements Methods				*
    //***********************************
    @Override
    public void onRefresh() {
        this.setupView();
    }

    //***********************************
    //*	Overrides Methods				*
    //***********************************
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.mFragmentLayout = inflater.inflate(R.layout.fragment_strategy_global_more, container, false);
        ButterKnife.bind(this, this.mFragmentLayout);

        return this.mFragmentLayout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        this.initParam();
        this.initView();
        this.setupView();
    }
}
