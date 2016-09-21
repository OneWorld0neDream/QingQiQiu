package com.qf.lenovo.qingqiqiu.ui.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.qf.lenovo.qingqiqiu.R;
import com.qf.lenovo.qingqiqiu.adapters.AddFragmentAdapter;
import com.qf.lenovo.qingqiqiu.ui.fragments.ItineraryFragment;
import com.qf.lenovo.qingqiqiu.ui.fragments.MineFragment;
import com.qf.lenovo.qingqiqiu.ui.fragments.StrategyFragment;
import com.qf.lenovo.qingqiqiu.ui.fragments.TravelNoteFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.main_search_edit)
    EditText mSearchEdit;
    @BindView(R.id.main_appbar)
    AppBarLayout mainAppbar;
    @BindView(R.id.mian_viewpager)
    ViewPager mViewpager;
    @BindView(R.id.main_tablayout)
    TabLayout mTablayout;


    private String[] tabText = {"攻略", "游记", "行程单", "我的"};
    private int[] tabImg = {R.mipmap.icon_tab_home, R.mipmap.icon_tab_trip, R.mipmap.icon_tab_plan, R.mipmap.icon_tab_my};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        /**
         * 添加Fragmemnt
         */
        ArrayList<Fragment> data = new ArrayList<>();
        data.add(new ItineraryFragment());
        data.add(new MineFragment());
        data.add(new StrategyFragment());
        data.add(new TravelNoteFragment());
        /**
         * 绑定适配器，并设置ViewPager的相关属性,设置监听
         */
        mViewpager.setOffscreenPageLimit(3);
        AddFragmentAdapter adapter = new AddFragmentAdapter(getSupportFragmentManager(), data);
        mViewpager.setAdapter(adapter);
        mViewpager.addOnPageChangeListener(this);

        /**
         * 设置TabLayout 绑定viewPager
         */
        mTablayout.setupWithViewPager(mViewpager);
        mTablayout.setSelectedTabIndicatorColor(Color.WHITE);
        mTablayout.setTabTextColors(Color.WHITE,Color.BLACK);
        setupTab();


    }

    /**
     * 给Tab设置图片和文字
     */
    private void setupTab() {
        Log.e(TAG, "setupTab: " );
        for (int i = 0; i < 4; i++) {
            View tablayout = LayoutInflater.from(this).inflate(R.layout.activity_mian_tab, null);
//            ImageView img = (ImageView) tablayout.findViewById(R.id.activity_main_tab_img);
            TextView txt = (TextView) tablayout.findViewById(R.id.activity_main_tab_text);
            txt.setText(tabText[i]);
//            img.setImageResource(tabImg[i]);
            TabLayout.Tab tab = mTablayout.getTabAt(i);
            tab.setCustomView(tablayout);
        }


    }
//======================================================================================

    /**
     * ViewPager滑动的监听
     *
     * @param position
     * @param positionOffset
     * @param positionOffsetPixels
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    /**
     * 滑动停止后结果的监听
     *
     * @param position
     */
    @Override
    public void onPageSelected(int position) {

    }

    /**
     * 滑动状态改变的监听
     *
     * @param state
     */
    @Override
    public void onPageScrollStateChanged(int state) {

    }
//=============================================================================================
}
