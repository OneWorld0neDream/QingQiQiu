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

import com.framework.magicarena.core.widget.adapters.RecyclerSingleViewGeneralAdapter;
import com.framework.magicarena.core.widget.decorations.RecyclerViewDivider;
import com.qf.lenovo.qingqiqiu.R;
import com.qf.lenovo.qingqiqiu.adapters.TravaelNoteListAdapter;
import com.qf.lenovo.qingqiqiu.https.DefaultCallbackImp;
import com.qf.lenovo.qingqiqiu.https.HttpRequestURL;
import com.qf.lenovo.qingqiqiu.models.StragegyDestinationsListModel;
import com.qf.lenovo.qingqiqiu.models.TravelNoteListModel;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 31098 on 9/21/2016.
 */

public class StrategyNearbyNoteFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, RecyclerSingleViewGeneralAdapter.OnItemViewClickedListener<StragegyDestinationsListModel.DestinationLocationsList.DestinationsLocationItem> {
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

    private String mLocationLat;
    private String mLocationLng;
    private TravaelNoteListAdapter mNoteListAdapter;

    //***************************************
    //*	Methods								*
    //***************************************
    //***********************************
    //*	Functional Methods		    	*
    //***********************************
    private void initParam() {
        Bundle args = this.getArguments();

        this.mLocationLat = args.getString(HttpRequestURL.STRATEGY_NEARBY_LOCATIONS_REQUEST_PARAM_LAT, null);
        this.mLocationLng = args.getString(HttpRequestURL.STRATEGY_NEARBY_LOCATIONS_REQUEST_PARAM_LNG, null);

        if (TextUtils.isEmpty(this.mLocationLat) || TextUtils.isEmpty(this.mLocationLng)) {
            this.getActivity().finish();
        }
    }

    private void initView() {
        this.mNoteListAdapter = new TravaelNoteListAdapter(this.getActivity(), null, R.layout.strategy_nearby_note_list_view_item);
        this.mNoteListAdapter.setOnItemViewClickListener(this);

        LinearLayoutManager layout = new LinearLayoutManager(this.getActivity());
        this.mLocationsList.setLayoutManager(layout);
        this.mLocationsList.addItemDecoration(new RecyclerViewDivider(this.getActivity(), LinearLayoutManager.HORIZONTAL, 2, this.getActivity().getResources().getColor(R.color.cardview_dark_background)));
        this.mLocationsList.setAdapter(this.mNoteListAdapter);

        this.mSwipeRefreshLayout.setProgressViewOffset(true, -100, 200);
        this.mSwipeRefreshLayout.setOnRefreshListener(this);
        this.mSwipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);
    }

    private void setupView() {
        OkHttpUtils.get()
                .url(HttpRequestURL.STRATEGY_NEARBY_NOTE_URL)
                .addParams(HttpRequestURL.STRATEGY_NEARBY_LOCATIONS_REQUEST_PARAM_LAT, this.mLocationLng)
                .addParams(HttpRequestURL.STRATEGY_NEARBY_LOCATIONS_REQUEST_PARAM_LNG, this.mLocationLat)
                .build()
                .execute(new DefaultCallbackImp<TravelNoteListModel>() {
                    @Override
                    public void onResponse(TravelNoteListModel response, int id) {
                        if (response != null) {
                            List<TravelNoteListModel.TravelNoteListItemModel> data = response.getData();
                            if (data != null) {
                                StrategyNearbyNoteFragment.this.mNoteListAdapter.updateDataSouce(data);
                                StrategyNearbyNoteFragment.this.mSwipeRefreshLayout.setRefreshing(false);
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

    @Override
    public void onItemClicked(RecyclerView parentView, View itemView, StragegyDestinationsListModel.DestinationLocationsList.DestinationsLocationItem item, int position) {

    }

    //***********************************
    //*	Overrides Methods				*
    //***********************************
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.mFragmentLayout = inflater.inflate(R.layout.fragment_strategy_nearby_note, container, false);
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
