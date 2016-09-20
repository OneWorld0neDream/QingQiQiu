package com.qf.lenovo.qingqiqiu.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by lenovo on 2016/9/20.
 */
public class BaseFragment extends Fragment {
    protected View mFragmentLayout;
    protected LayoutInflater mLayoutInflator;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.mLayoutInflator = (LayoutInflater) this.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
