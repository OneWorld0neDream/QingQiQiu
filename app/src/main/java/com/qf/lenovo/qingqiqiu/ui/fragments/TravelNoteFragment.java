package com.qf.lenovo.qingqiqiu.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.qf.lenovo.qingqiqiu.R;
import com.qf.lenovo.qingqiqiu.adapters.TravelNoteAdapter;
import com.qf.lenovo.qingqiqiu.models.TravelNoteList;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/9/20 0020.
 */
public class TravelNoteFragment extends BaseFragment {

    private static final String TAG = TravelNoteFragment.class.getSimpleName();
    @BindView(R.id.travelnote_recyclerview)
    RecyclerView mRecyclerview;
    private TravelNoteAdapter mAdapter;
    private String URL = "http://q.chanyouji.com/api/v1/timelines.json?page=";

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

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerview.setLayoutManager(linearLayoutManager);
        mAdapter = new TravelNoteAdapter(getActivity(),null);
        mRecyclerview.setAdapter(mAdapter);
    }

    private void setupView() {

        OkHttpUtils.get()
                .url(URL)
                .build()
                .execute(new Callback<TravelNoteList>() {

                    @Override
                    public TravelNoteList parseNetworkResponse(Response response, int id) throws Exception {
                        String data = response.body().string();
                        Gson gson = new Gson();
                        TravelNoteList travelNoteList = gson.fromJson(data, TravelNoteList.class);
                        return travelNoteList;
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "onError: ---------"+e.getMessage() );
                    }

                    @Override
                    public void onResponse(TravelNoteList response, int id) {
                        mAdapter.upData(response.getData());
                    }
                });
    }
}
