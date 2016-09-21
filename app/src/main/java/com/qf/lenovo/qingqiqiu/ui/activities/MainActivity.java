package com.qf.lenovo.qingqiqiu.ui.activities;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.EditText;
import android.widget.TableLayout;

import com.qf.lenovo.qingqiqiu.R;
import com.qf.lenovo.qingqiqiu.adapters.AddFragmentAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.main_search_edit)
    EditText mainSearchEdit;
    @BindView(R.id.main_tablayout)
    TableLayout mainTablayout;
    @BindView(R.id.main_appbar)
    AppBarLayout mainAppbar;
    @BindView(R.id.mian_viewpager)
    ViewPager mianViewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        ArrayList<Fragment> data = new ArrayList<>();

        AddFragmentAdapter adapter = new AddFragmentAdapter(getSupportFragmentManager(),data);
        mianViewpager.setAdapter(adapter);
    }


}
