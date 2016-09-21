package com.qf.lenovo.qingqiqiu.ui.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.framework.magicarena.core.widget.decorations.RecyclerViewDivider;
import com.framework.magicarena.pulltorefresh.PullToRefreshBase;
import com.framework.magicarena.pulltorefresh.PullToRefreshFullRecyclerView;
import com.qf.lenovo.qingqiqiu.R;
import com.qf.lenovo.qingqiqiu.adapters.StragegyOtherDestinationsListAdapter;
import com.qf.lenovo.qingqiqiu.adapters.StrategyLocationsGridAdapter;
import com.qf.lenovo.qingqiqiu.https.DefaultCallbackImp;
import com.qf.lenovo.qingqiqiu.https.HttpRequestURL;
import com.qf.lenovo.qingqiqiu.models.StragegyOtherDestinationsListModel;
import com.qf.lenovo.qingqiqiu.models.StrategyAdvListModel;
import com.qf.lenovo.qingqiqiu.models.StrategyNearbyLocationsListModel;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StrategyFragment extends BaseFragment implements AMapLocationListener {
    //*******************************************
    //*	Instance Area 							*
    //*******************************************
    //***************************************
    //*	Fields								*
    //***************************************
    @BindView(R.id.customSliderLayout)
    SliderLayout mSliderLayout;
    @BindView(R.id.txtTopTitle)
    TextView mNearbyLocationsTittle;
    @BindView(R.id.customLocationList)
    RecyclerView mNearbyGridList;
    @BindView(R.id.txtMore)
    TextView mNearbyMore;
    @BindView(R.id.ptrOtherDestinationsList)
    PullToRefreshFullRecyclerView mDestinationsPtrList;

    private StrategyLocationsGridAdapter mNearbyGridAdapter;
    private RecyclerView mDestinationsList;
    private StragegyOtherDestinationsListAdapter mDestinationsListAdapter;

    //***************************************
    //*	Methods								*
    //***************************************
    //***********************************
    //*	Functional Methods		    	*
    //***********************************
    private void initView() {
        this.mNearbyLocationsTittle.setText("附近目的地");
        this.mNearbyMore.setText("更多附近目的地");

        this.mNearbyGridList.setLayoutManager(new GridLayoutManager(this.getActivity(), 3));
        this.mNearbyGridList.addItemDecoration(new RecyclerViewDivider(this.getActivity(), LinearLayoutManager.HORIZONTAL, 10, Color.WHITE));
        this.mNearbyGridAdapter = new StrategyLocationsGridAdapter(this.getActivity(), null, R.layout.strategy_location_grid_view_item);
        this.mNearbyGridList.setAdapter(this.mNearbyGridAdapter);

        this.mDestinationsPtrList.setMode(PullToRefreshBase.Mode.DISABLED);
        this.mDestinationsList = this.mDestinationsPtrList.getRefreshableView();
        this.mDestinationsListAdapter = new StragegyOtherDestinationsListAdapter(this.getActivity(), null, R.layout.strategy_location_list_view_item);
        this.mDestinationsList.setAdapter(this.mDestinationsListAdapter);
    }

    private void setupView() {
        this.initLocation();

        OkHttpUtils.get()
                .url(HttpRequestURL.STRATEGY_ADVERSEMENT_URL)
                .build()
                .execute(new DefaultCallbackImp<StrategyAdvListModel>() {
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

        OkHttpUtils.get()
                .url(HttpRequestURL.STRATEGY_OTHER_DESTINATIONS_URL)
                .build()
                .execute(new DefaultCallbackImp<StragegyOtherDestinationsListModel>() {
                    @Override
                    public void onResponse(StragegyOtherDestinationsListModel response, int id) {
                        if (response != null) {
                            List<StragegyOtherDestinationsListModel.DestinationLocationsList> data = response.getData();
                            if (data != null) {
                                StrategyFragment.this.mDestinationsListAdapter.updateDataSouce(data);
                            }
                        }
                    }
                });
    }

    private void initLocation() {
        AMapLocationClient aMapLocationClient = new AMapLocationClient(this.getActivity());
        AMapLocationClientOption aMapLocationClientOption = new AMapLocationClientOption();
        aMapLocationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        aMapLocationClientOption.setOnceLocation(true);
        aMapLocationClient.setLocationOption(aMapLocationClientOption);
        aMapLocationClient.setLocationListener(this);
        aMapLocationClient.startLocation();
    }


    //***********************************
    //*	Implements Methods				*
    //***********************************
    //*********************************** onLocationChangedListener ***********************************
    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation.getErrorCode() == AMapLocation.LOCATION_SUCCESS) {
            OkHttpUtils.get()
                    .url(HttpRequestURL.STRATEGY_NEARBY_LOCATIONS_URL)
                    .addParams(HttpRequestURL.STRATEGY_NEARBY_LOCATIONS_REQUEST_PARAM_LAT, String.valueOf(aMapLocation.getLatitude()))
                    .addParams(HttpRequestURL.STRATEGY_NEARBY_LOCATIONS_REQUEST_PARAM_LNG, String.valueOf(aMapLocation.getLongitude()))
                    .addParams(HttpRequestURL.STRATEGY_NEARBY_LOCATIONS_REQUEST_PARAM_RECOMMAND, "")
                    .build()
                    .execute(new DefaultCallbackImp<StrategyNearbyLocationsListModel>() {
                        @Override
                        public void onResponse(StrategyNearbyLocationsListModel response, int id) {
                            if (response != null) {
                                List<StragegyOtherDestinationsListModel.DestinationLocationsList.DestinationsLocationItem> data = response.getData();
                                if (data != null) {
                                    StrategyFragment.this.mNearbyGridAdapter.updateDataSouce(data);
                                }
                            }
                        }
                    });
        } else {
            Toast.makeText(this.getActivity(), this.getString(R.string.strategy_location_failed), Toast.LENGTH_SHORT).show();
        }
    }

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
