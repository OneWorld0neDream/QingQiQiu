package com.qf.lenovo.qingqiqiu.ui.activities;

import android.content.Intent;
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

import com.framework.magicarena.core.widget.adapters.RecyclerSingleViewGeneralAdapter;
import com.qf.lenovo.qingqiqiu.R;
import com.qf.lenovo.qingqiqiu.adapters.DestinationAdapter;
import com.qf.lenovo.qingqiqiu.adapters.DetailGoodsAdapter;
import com.qf.lenovo.qingqiqiu.adapters.DetailNearbyAdapter;
import com.qf.lenovo.qingqiqiu.https.DefaultCallbackImp;
import com.qf.lenovo.qingqiqiu.https.HttpRequestURL;
import com.qf.lenovo.qingqiqiu.models.TripDetailModel;
import com.qf.lenovo.qingqiqiu.ui.custom.ObservableScrollView;
import com.squareup.picasso.Picasso;
import com.zhy.http.okhttp.OkHttpUtils;

import org.xutils.x;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProvinceActivity extends AppCompatActivity implements ObservableScrollView.ScrollViewListener, RecyclerSingleViewGeneralAdapter.OnItemViewClickedListener {

    private static final String TAG = ProvinceActivity.class.getSimpleName();
    @BindView(R.id.province_head_img)
    ImageView mHeadImg;
    @BindView(R.id.province_head_name_chinese)
    TextView mHeadNameChinese;
    @BindView(R.id.province_head_name_spell)
    TextView mHeadNameSpell;
    @BindView(R.id.province_goods_recyclerview)
    RecyclerView mGoodsRecyclerview;
    @BindView(R.id.province_nearby_place)
    TextView mNearbyPlace;
    @BindView(R.id.province_nearby_recyclerview)
    RecyclerView mNearbyRecyclerview;
    @BindView(R.id.province_nearby_map)
    TextView mNearbyMap;
    @BindView(R.id.province_classics_title)
    TextView mClassicsTitle;
    @BindView(R.id.province_classics_image)
    ImageView mClassicsImage;
    @BindView(R.id.province_classics_recycclerview)
    RecyclerView mClassicsRecycclerview;
    @BindView(R.id.province_h_title)
    TextView mHTitle;
    @BindView(R.id.province_h_image)
    ImageView mHImage;
    @BindView(R.id.province_h_number)
    TextView mHNumber;
    @BindView(R.id.detail_h_writer)
    TextView mHWriter;
    @BindView(R.id.province_h_content_title)
    TextView mHContentTitle;
    @BindView(R.id.province_h_content)
    TextView mHContent;
    @BindView(R.id.province_h_triplist)
    TextView mHTriplist;
    @BindView(R.id.province_scrollview)
    ObservableScrollView mScrollview;
    @BindView(R.id.province_appbar_title)
    TextView mAppbarTitle;
    @BindView(R.id.province_appbar_background)
    LinearLayout mAppbarBackground;
    @BindView(R.id.province_appbar_back)
    ImageView mAppbarBack;
    @BindView(R.id.province_appbar)
    RelativeLayout mAppbar;

    private TripDetailModel.DataBean data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_province);
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
                .url(String.format("http://q.chanyouji.com/api/v3/destinations/%s.json",getIntent().getStringExtra(HttpRequestURL.STRATEGY_NEARBY_LOCATIONS_REQUEST_PARAM_ID)))
                .build()
                .execute(new DefaultCallbackImp<TripDetailModel>() {



                    @Override
                    public void onResponse(TripDetailModel response, int id) {
                        data = response.getData();
                        mAppbarTitle.setText(data.getDestination().getName());
                        setupHeadView(data);
                        setupGoodsView(data);
                        setupClassicsView(data);
                        setupHView(data);
                        setupNearbyView(data);
                    }
                });
    }

    /**
     * 第一个模块
     * @param data
     */
    private void setupHeadView(TripDetailModel.DataBean data) {
        mHeadNameChinese.setText(data.getDestination().getName());
        mHeadNameSpell.setText(data.getDestination().getName_en());
//        x.image().bind(mHeadImg, data.getDestination().getPhoto_url());
        Picasso.with(this).load(data.getDestination().getPhoto_url()).resize(400,220).into(mHeadImg);
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
        detailGoodsAdapter.setOnItemViewClickListener(this);
        mGoodsRecyclerview.setAdapter(detailGoodsAdapter);

    }

    /**
     * 第三个模块
     * @param data
     */
    private void setupNearbyView(TripDetailModel.DataBean data) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mNearbyRecyclerview.setLayoutManager(layoutManager);
        mNearbyPlace.setText(data.getSections().get(0).getTitle());
        Log.e(TAG, "setupNearbyView: "+data.getSections().get(0).getModels() );
        DestinationAdapter adapter = new DestinationAdapter(this,data.getSections().get(0).getModels(),R.layout.activity_detail_nearby_item);
        mNearbyRecyclerview.setAdapter(adapter);
        mNearbyMap.setText(data.getSections().get(0).getButton_text());
        //设置监听
        adapter.setOnItemViewClickListener(this);

    }
    /**
     * 第四个模块
     * @param data
     */
    private void setupClassicsView(TripDetailModel.DataBean data) {
//        mClassicsTitle.setText(data.getSections().get(1).getTitle());
//        x.image().bind(mClassicsImage, data.getSections().get(1).getModels().get(0).getPhoto().getPhoto_url());
//        Picasso.with(this).load(data.getSections().get(1).getModels().get(0).getPhoto().getPhoto_url()).resize(800,400).into(mClassicsImage);
        Log.e(TAG, "setupClassicsView: " + data.getSections().get(1).getModels().get(0).getPhoto().getPhoto_url());
    }

    /**
     * 第五个模块
     * @param data
     */
    private void setupHView(TripDetailModel.DataBean data) {
        mHTitle.setText(data.getSections().get(2).getTitle());
//        x.image().bind(mHImage, data.getSections().get(2).getModels().get(0).getContents().get(0).getPhoto_url());
        Picasso.with(this).load(data.getSections().get(2).getModels().get(0).getContents().get(0).getPhoto_url()).resize(800,500).into(mHImage);
        mHNumber.setText("" + data.getSections().get(2).getModels().get(0).getContents().size());
        mHWriter.setText(data.getSections().get(2).getModels().get(0).getUser().getName());
        mHContentTitle.setText(data.getSections().get(2).getModels().get(0).getTopic());
        mHContent.setText(data.getSections().get(2).getModels().get(0).getDescription());
        mHTriplist.setText(data.getSections().get(2).getButton_text());
        Log.e(TAG, "setupHView: " + data.getSections().get(2).getModels().get(0
        ).getDescription());

    }






    /**
     * 点击事件
     *
     * @param view
     */
    @OnClick(R.id.province_appbar_back)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.province_appbar_back:
                finish();
                break;
        }

    }

    @Override
    public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
        float mHeadImgHeight = (float) mHeadImg.getHeight();
        float y1 = (float) y;
        Log.e(TAG, "onScrollChanged: " + y + "------" + mHeadImgHeight + "-----" + (y1 / mHeadImgHeight));
        if (y <= mHeadImgHeight) {
            mAppbarBackground.setAlpha(y1 / mHeadImgHeight);
        }
    }

    @Override
    public void onItemClicked(RecyclerView parentView, View itemView, Object item, int position) {
        Log.e(TAG, "onItemClicked: "+position );
        switch (parentView.getId()) {
            case R.id.province_goods_recyclerview:

                break;

            case R.id.province_nearby_recyclerview:
                Intent intent = new Intent(this, DetailActivity.class);
                intent.putExtra(HttpRequestURL.STRATEGY_NEARBY_LOCATIONS_REQUEST_PARAM_ID,String.valueOf(data.getSections().get(0).getModels().get(position).getId()));
                startActivity(intent);
                break;
        }
    }
}
