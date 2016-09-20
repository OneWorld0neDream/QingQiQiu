package com.qf.lenovo.qingqiqiu.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framework.magicarena.pulltorefresh.PullToRefreshRecyclerView;
import com.qf.lenovo.qingqiqiu.R;
import com.qf.lenovo.qingqiqiu.adapters.TravelNoteAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/9/20 0020.
 */
public class TravelNoteFragment extends BaseFragment {

    @BindView(R.id.travelnote_recyclerview)
    RecyclerView mRecyclerview;
    private TravelNoteAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentLayout = inflater.inflate(R.layout.fragment_travelnote, container, false);
        ButterKnife.bind(this, mFragmentLayout);
        return mFragmentLayout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
        setupView();
    }

    private void initView() {

        mAdapter = new TravelNoteAdapter(getActivity(),null);
        mRecyclerview.setAdapter(mAdapter);
    }

    private void setupView() {

    }
}
