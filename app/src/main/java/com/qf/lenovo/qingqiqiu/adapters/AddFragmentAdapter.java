package com.qf.lenovo.qingqiqiu.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2016/9/20.
 */
public class AddFragmentAdapter extends FragmentPagerAdapter {

    private List<Fragment> data;
    public AddFragmentAdapter(FragmentManager fm,List<Fragment> data) {
        super(fm);
        if (data!=null) {
            this.data = data;
        }else{
            this.data = new ArrayList<>();
        }
    }

    private void updateRes(List<Fragment> data){
        if (data!=null) {
            this.data.clear();
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }

    @Override
    public Fragment getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getCount() {
        return data!=null?data.size():0;
    }
}
