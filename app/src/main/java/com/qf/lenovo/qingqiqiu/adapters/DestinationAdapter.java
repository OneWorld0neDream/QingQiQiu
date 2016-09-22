package com.qf.lenovo.qingqiqiu.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.framework.magicarena.core.widget.adapters.RecyclerSingleViewGeneralAdapter;
import com.qf.lenovo.qingqiqiu.R;
import com.qf.lenovo.qingqiqiu.models.TripDetailModel;

import java.util.List;

/**
 * Created by lenovo on 2016/9/22.
 */
public class DestinationAdapter extends RecyclerSingleViewGeneralAdapter<TripDetailModel.DataBean.SectionsBean.ModelsBean> {

    /**
     * Initializes a new instance of adapter for {@link RecyclerView.Adapter}.
     *
     * @param context     context instance where {@link RecyclerView} is running on
     * @param data        initial data source for adapter,<code>null</code> may be allowed
     * @param layoutResId resource id for layout of {@link RecyclerView}
     */
    public DestinationAdapter(Context context, List<TripDetailModel.DataBean.SectionsBean.ModelsBean> data, int layoutResId) {
        super(context, data, layoutResId);
    }

    @Override
    protected void bindDataSource(ViewHolder holder, TripDetailModel.DataBean.SectionsBean.ModelsBean item, int position) {
        holder.setViewImage(R.id.detail_nearby_item_image,item.getPhoto_url());
        holder.setViewText(R.id.detail_nearby_item_place,item.getName());
        holder.setViewText(R.id.detail_nearby_item_distance,item.getName_en());
    }
}
