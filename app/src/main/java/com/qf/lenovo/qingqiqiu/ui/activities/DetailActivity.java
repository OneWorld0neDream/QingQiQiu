package com.qf.lenovo.qingqiqiu.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qf.lenovo.qingqiqiu.R;
import com.qf.lenovo.qingqiqiu.adapters.DetailGoodsAdapter;
import com.qf.lenovo.qingqiqiu.adapters.DetailNearbyAdapter;
import com.qf.lenovo.qingqiqiu.https.DefaultCallbackImp;
import com.qf.lenovo.qingqiqiu.models.TripDetailModel;
import com.qf.lenovo.qingqiqiu.ui.custom.ObservableScrollView;
import com.zhy.http.okhttp.OkHttpUtils;

import org.xutils.x;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailActivity extends AppCompatActivity implements ObservableScrollView.ScrollViewListener {

    private static final String TAG = DetailActivity.class.getSimpleName();
    @BindView(R.id.detail_head_img)
    ImageView mHeadImg;
    @BindView(R.id.detail_head_name_chinese)
    TextView mHeadNameChinese;
    @BindView(R.id.detail_head_name_spell)
    TextView mHeadNameSpell;
    @BindView(R.id.detail_goods_recyclerview)
    RecyclerView mGoodsRecyclerview;
    @BindView(R.id.detail_classics_recycclerview)
    RecyclerView mClassicsRecycclerview;
    @BindView(R.id.detail_triplist_name)
    TextView mTriplistName;
    @BindView(R.id.detail_triplist_inspiration)
    TextView mTriplistInspiration;
    @BindView(R.id.detail_h_image)
    ImageView mHImage;
    @BindView(R.id.detail_h_number)
    TextView mHNumber;
    @BindView(R.id.detail_h_writer)
    TextView mHWriter;
    @BindView(R.id.detail_h_content_title)
    TextView mContentTitle;
    @BindView(R.id.detail_h_content)
    TextView mHContent;
    @BindView(R.id.detail_nearby_place)
    TextView mNearbyPlace;
    @BindView(R.id.detail_nearby_recyclerview)
    RecyclerView mNearbyRecyclerview;
    @BindView(R.id.detail_scrollview)
    ObservableScrollView mScrollview;
    @BindView(R.id.detail_appbar_back)
    ImageView mAppbarBack;
    @BindView(R.id.detail_appbar_title)
    TextView mAppbarTitle;
    @BindView(R.id.detail_appbar)
    RelativeLayout mAppbar;
    @BindView(R.id.detail_appbar_background)
    LinearLayout mAppbarBackground;
    @BindView(R.id.detail_classics_title)
    TextView mClassicsTitle;
    @BindView(R.id.detail_classics_image)
    ImageView mClassicsImage;
    @BindView(R.id.detail_triplist_title)
    TextView mTriplistTitle;
    @BindView(R.id.detail_triplist_image)
    ImageView mTriplistImage;
    @BindView(R.id.detail_h_title)
    TextView mHTitle;
    @BindView(R.id.detail_h_triplist)
    TextView mHTriplist;
    @BindView(R.id.detail_nearby_map)
    TextView mNearbyMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        initView();
        setupView();
    }


    private void initView() {
        mScrollview.setScrollViewListener(this);
        mAppbarBackground.setAlpha(0);

    }

    private void setupView() {
        OkHttpUtils.get()
                .url("http://q.chanyouji.com/api/v3/destinations/665.json")
                .build()
                .execute(new DefaultCallbackImp<TripDetailModel>() {

                    @Override
                    public void onResponse(TripDetailModel response, int id) {
                        TripDetailModel.DataBean data = response.getData();
                        setupHeadView(data);
                        setupGoodsView(data);
                        setupClassicsView(data);
                        setupTripListView(data);
                        setupHView(data);
                        setupNearbyView(data);

                    }
                });
    }

    /**
     * 第六个模块
     * @param data
     */
    private void setupNearbyView(TripDetailModel.DataBean data) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mNearbyRecyclerview.setLayoutManager(layoutManager);
        mNearbyPlace.setText(data.getSections().get(0).getTitle());
        Log.e(TAG, "setupNearbyView: "+data.getSections().get(0).getModels() );
        DetailNearbyAdapter nearbyAdapter = new DetailNearbyAdapter(this, data.getSections().get(0).getModels(), R.layout.activity_detail_nearby_item);
        mNearbyRecyclerview.setAdapter(nearbyAdapter);
        mNearbyMap.setText(data.getSections().get(0).getButton_text());

    }

    /**
     * 第五个模块
     * @param data
     */
    private void setupHView(TripDetailModel.DataBean data) {
        mHTitle.setText(data.getSections().get(3).getTitle());
        x.image().bind(mHImage, data.getSections().get(3).getModels().get(0).getContents().get(0).getPhoto_url());
        mHNumber.setText("" + data.getSections().get(3).getModels().get(0).getContents().size());
//        mHWriter.setText(data.getSections().get(3).getModels().get(0).get);
        mHContent.setText(data.getSections().get(3).getModels().get(0).getDescription());
        mHTriplist.setText(data.getSections().get(3).getButton_text());
        Log.e(TAG, "setupHView: " + data.getSections().get(3).getModels().get(0
        ).getDescription());

    }

    /**
     * 第四个模块
     * @param data
     */
    private void setupTripListView(TripDetailModel.DataBean data) {
        mTriplistTitle.setText(data.getSections().get(2).getTitle());
        x.image().bind(mTriplistImage, data.getSections().get(2).getModels().get(0).getPhoto_url());
        mTriplistName.setText(data.getSections().get(2).getModels().get(0).getTitle());
        mTriplistInspiration.setText(data.getSections().get(2).getModels().get(0).getSummary());
    }

    /**
     * 第三个模块
     * @param data
     */
    private void setupClassicsView(TripDetailModel.DataBean data) {
        mClassicsTitle.setText(data.getSections().get(1).getTitle());
        x.image().bind(mClassicsImage, data.getSections().get(1).getModels().get(0).getPhoto().getPhoto_url());
        Log.e(TAG, "setupClassicsView: " + data.getSections().get(1).getModels().get(0).getPhoto().getPhoto_url());
    }

    /**
     * 第二个模块
     * @param data
     */
    private void setupGoodsView(TripDetailModel.DataBean data) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mGoodsRecyclerview.setLayoutManager(layoutManager);
        DetailGoodsAdapter detailGoodsAdapter = new DetailGoodsAdapter(this, data.getGoods(), R.layout.activity_detail_goods_item);
        mGoodsRecyclerview.setAdapter(detailGoodsAdapter);

    }

    /**
     * 第一个模块
     * @param data
     */
    private void setupHeadView(TripDetailModel.DataBean data) {
        mHeadNameChinese.setText(data.getDestination().getName());
        mHeadNameSpell.setText(data.getDestination().getName_en());
        x.image().bind(mHeadImg, data.getDestination().getPhoto_url());
    }

    /**
     * 点击事件
     *
     * @param view
     */
    @OnClick(R.id.detail_appbar_back)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.detail_appbar_back:

                break;
        }

    }

    /**
     * 滑动监听，设置AppBar的显示个隐藏
     *
     * @param scrollView
     * @param x
     * @param y
     * @param oldx
     * @param oldy
     */
    @Override
    public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
        float mHeadImgHeight = (float) mHeadImg.getHeight();
        float y1 = (float) y;
        Log.e(TAG, "onScrollChanged: " + y + "------" + mHeadImgHeight + "-----" + (y1 / mHeadImgHeight));
        if (y <= mHeadImgHeight) {
            mAppbarBackground.setAlpha(y1 / mHeadImgHeight);
        }
    }

}
