package com.qf.lenovo.qingqiqiu.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.framework.magicarena.core.widget.adapters.RecyclerSingleViewGeneralAdapter;
import com.qf.lenovo.qingqiqiu.R;
import com.qf.lenovo.qingqiqiu.adapters.StrategyDestinationsAdapter;
import com.qf.lenovo.qingqiqiu.adapters.StrategyLocationsAdapter;
import com.qf.lenovo.qingqiqiu.https.DefaultCallbackImp;
import com.qf.lenovo.qingqiqiu.https.HttpRequestURL;
import com.qf.lenovo.qingqiqiu.models.StragegyDestinationsListModel;
import com.qf.lenovo.qingqiqiu.models.StrategyAdvListModel;
import com.qf.lenovo.qingqiqiu.models.StrategyLocationsListModel;
import com.qf.lenovo.qingqiqiu.storage.StorageFileName;
import com.qf.lenovo.qingqiqiu.ui.activities.ActivitySwitchParams;
import com.qf.lenovo.qingqiqiu.ui.activities.DetailActivity;
import com.qf.lenovo.qingqiqiu.ui.activities.MoreDestinationsActivity;
import com.qf.lenovo.qingqiqiu.ui.activities.ProvinceActivity;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StrategyFragment extends BaseFragment implements AMapLocationListener,
        RecyclerSingleViewGeneralAdapter.OnItemViewClickedListener<StragegyDestinationsListModel.DestinationLocationsList.DestinationsLocationItem>, StrategyDestinationsAdapter.OnMoreButtonClickedListener {
    //*******************************************
    //*	Static Area 							*
    //*******************************************
    //***************************************
    //*	Fields								*
    //***************************************
    private final static int LOCATIONS_LEVEL_FOREIGN = 2;
    private final static int LOCATIONS_LEVEL_PROVINCE = 3;
    private final static int LOCATIONS_LEVEL_CITY = 4;

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
    @BindView(R.id.customOtherDestinationsList)
    RecyclerView mDestinationsList;
    @BindView(R.id.includeNearByDestination)
    LinearLayout mNearByDestinationBlock;
    @BindView(R.id.llHistoryTag)
    LinearLayout mHistoryTags;
    @BindView(R.id.layoutLoading)
    View mLoadingLayout;

    private StrategyLocationsAdapter mNearbyGridAdapter;
    private StrategyDestinationsAdapter mDestinationsListAdapter;
    private List<String> mHistoryTagsList;
    private double mLocationLng;
    private double mLocationLat;

    //***************************************
    //*	Methods								*
    //***************************************
    //***********************************
    //*	Functional Methods		    	*
    //***********************************
    private void initView() {
        this.mNearbyLocationsTittle.setText(this.getActivity().getString(R.string.strategy_nearby_destination));
        this.mNearbyMore.setText(this.getActivity().getString(R.string.strategy_nearby_destination_more));

        this.mNearbyGridList.setLayoutManager(new GridLayoutManager(this.getActivity(), 3));
        //        this.mNearbyGridList.addItemDecoration(new RecyclerViewDivider(this.getActivity(), LinearLayoutManager.HORIZONTAL, 10, Color.WHITE));
        this.mNearbyGridAdapter = new StrategyLocationsAdapter(this.getActivity(), null, R.layout.strategy_location_grid_view_item);
        this.mNearbyGridList.setAdapter(this.mNearbyGridAdapter);
        this.mNearbyGridAdapter.setOnItemViewClickListener(this);

        //        this.mDestinationsPtrList.setMode(PullToRefreshBase.Mode.DISABLED);
        //        this.mDestinationsList = this.mDestinationsPtrList.getRefreshableView();
        this.mDestinationsList.setHasFixedSize(true);
        this.mDestinationsList.setNestedScrollingEnabled(false);
        this.mDestinationsList.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        this.mDestinationsListAdapter = new StrategyDestinationsAdapter(this.getActivity(), null, R.layout.strategy_location_list_view_item);
        this.mDestinationsList.setAdapter(this.mDestinationsListAdapter);
        this.mDestinationsListAdapter.setCallback(this);
        this.mDestinationsListAdapter.setOnMoreCallback(this);
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
                .url(HttpRequestURL.STRATEGY_GLOBAL_DESTINATIONS_URL)
                .build()
                .execute(new DefaultCallbackImp<StragegyDestinationsListModel>() {
                    @Override
                    public void onResponse(StragegyDestinationsListModel response, int id) {
                        if (response != null) {
                            List<StragegyDestinationsListModel.DestinationLocationsList> data = response.getData();
                            if (data != null) {
                                StrategyFragment.this.mDestinationsListAdapter.updateDataSouce(data);
                            }
                        }

                        StrategyFragment.this.mLoadingLayout.setVisibility(View.GONE);
                    }
                });

        this.readHistoryTags();
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

    private void readHistoryTags() {
        ObjectInputStream objectInputStream = null;

        try {
            objectInputStream = new ObjectInputStream(this.getActivity().openFileInput(StorageFileName.INTERSTORAGE_HISTORY_TAGS_FILE_NAME));
            this.mHistoryTagsList = (ArrayList<String>) objectInputStream.readObject();
        } catch (Exception e) {
            this.mHistoryTagsList = new ArrayList<>();
        } finally {
            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        this.refreshHistoryTags();
    }

    private void refreshHistoryTags() {
        if (this.mHistoryTagsList != null && this.mHistoryTagsList.size() > 0) {
            this.mHistoryTags.removeAllViews();
            this.mHistoryTags.setVisibility(View.VISIBLE);

            for (int index = this.mHistoryTagsList.size() - 1; index >= 0; index--) {
                TextView tagView = new TextView(this.getActivity());
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(5, 0, 5, 0);
                tagView.setBackgroundResource(R.drawable.shape_history_tag_border);
                tagView.setPadding(2, 2, 2, 2);
                tagView.setText(this.mHistoryTagsList.get(index));
                tagView.setTextSize(12);
                tagView.setLayoutParams(params);
                this.mHistoryTags.addView(tagView);
            }
        } else {
            this.mHistoryTags.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.txtMore)
    void onViewClicked(View view) {
        Intent intent = new Intent(this.getActivity(), MoreDestinationsActivity.class);
        intent.putExtra(HttpRequestURL.STRATEGY_NEARBY_LOCATIONS_REQUEST_PARAM_LAT, String.valueOf(this.mLocationLat));
        intent.putExtra(HttpRequestURL.STRATEGY_NEARBY_LOCATIONS_REQUEST_PARAM_LNG, String.valueOf(this.mLocationLng));
        intent.putExtra(ActivitySwitchParams.ACTIVITY_START_PARAM_KEY_MODE, ActivitySwitchParams.ACTIVITY_START_PARAM_VALUE_NEARBY);
        this.startActivity(intent);
    }

    //***********************************
    //*	Implements Methods				*
    //***********************************
    //*********************************** onLocationChangedListener ***********************************
    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation.getErrorCode() == AMapLocation.LOCATION_SUCCESS) {
            this.mLocationLat = aMapLocation.getLatitude();
            this.mLocationLng = aMapLocation.getLongitude();

            OkHttpUtils.get()
                    .url(HttpRequestURL.STRATEGY_NEARBY_LOCATIONS_URL)
                    .addParams(HttpRequestURL.STRATEGY_NEARBY_LOCATIONS_REQUEST_PARAM_LAT, String.valueOf(this.mLocationLat))
                    .addParams(HttpRequestURL.STRATEGY_NEARBY_LOCATIONS_REQUEST_PARAM_LNG, String.valueOf(this.mLocationLng))
                    .addParams(HttpRequestURL.STRATEGY_NEARBY_LOCATIONS_REQUEST_PARAM_RECOMMAND, "")
                    .build()
                    .execute(new DefaultCallbackImp<StrategyLocationsListModel>() {
                        @Override
                        public void onResponse(StrategyLocationsListModel response, int id) {
                            if (response != null) {
                                List<StragegyDestinationsListModel.DestinationLocationsList.DestinationsLocationItem> data = response.getData();
                                if (data != null) {
                                    StrategyFragment.this.mNearbyGridAdapter.updateDataSouce(data);
                                }
                            }
                        }
                    });
        } else {
            this.mNearByDestinationBlock.setVisibility(View.GONE);
            Toast.makeText(this.getActivity(), this.getString(R.string.strategy_location_failed), Toast.LENGTH_SHORT).show();
        }
    }

    //*********************************** onItemClickedListener ***********************************
    @Override
    public void onItemClicked(RecyclerView parentView, View itemView, StragegyDestinationsListModel.DestinationLocationsList.DestinationsLocationItem item, int position) {
        if (this.mHistoryTagsList.contains(item.getName())) {
            this.mHistoryTagsList.remove(item.getName());
        }

        this.mHistoryTagsList.add(item.getName());
        this.refreshHistoryTags();

        int id = item.getId();
        Intent intent = null;

        switch (item.getLevel()) {
            case LOCATIONS_LEVEL_FOREIGN:
            case LOCATIONS_LEVEL_PROVINCE:
                intent = new Intent(this.getActivity(), ProvinceActivity.class);
                intent.putExtra(HttpRequestURL.STRATEGY_NEARBY_LOCATIONS_REQUEST_PARAM_ID, String.valueOf(id));
                this.startActivity(intent);

                break;
            case LOCATIONS_LEVEL_CITY:
                intent = new Intent(this.getActivity(), DetailActivity.class);
                intent.putExtra(HttpRequestURL.STRATEGY_NEARBY_LOCATIONS_REQUEST_PARAM_ID, String.valueOf(id));
                this.startActivity(intent);

                break;
        }
    }

    //***********************************  OnMoreButtonClickedListener ***********************************
    @Override
    public void onMoreButtonClicked(String regionName, String titleName) {
        Intent intent = new Intent(this.getActivity(), MoreDestinationsActivity.class);
        intent.putExtra(ActivitySwitchParams.ACTIVITY_START_PARAM_KEY_MODE, ActivitySwitchParams.ACTIVITY_START_PARAM_VALUE_GLOBAL);
        intent.putExtra(ActivitySwitchParams.ACTIVITY_START_PARAM_KEY_REGIION, regionName);
        intent.putExtra(ActivitySwitchParams.ACTIVITY_START_PARAM_KEY_NAME, titleName);
        this.startActivity(intent);
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

    @Override
    public void onPause() {
        super.onPause();

        ObjectOutputStream output = null;

        try {
            output = new ObjectOutputStream(this.getActivity().openFileOutput(StorageFileName.INTERSTORAGE_HISTORY_TAGS_FILE_NAME, Context.MODE_PRIVATE));
            output.writeObject(this.mHistoryTagsList);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
