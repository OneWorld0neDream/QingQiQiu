package com.qf.lenovo.qingqiqiu.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.framework.magicarena.core.widget.adapters.RecyclerSingleViewGeneralAdapter;
import com.qf.lenovo.qingqiqiu.R;
import com.qf.lenovo.qingqiqiu.models.StrategyNearbyLocationsListModel;

import java.util.List;

/**
 * Created by 31098 on 9/20/2016.
 */
public class StrategyLocationsGridAdapter extends RecyclerSingleViewGeneralAdapter<StrategyNearbyLocationsListModel.StrategyNearbyLocationsItem> {
    /**
     * Initializes a new instance of adapter for {@link RecyclerView.Adapter}.
     *  @param context       context instance where {@link RecyclerView} is running on
     * @param data          initial data source for adapter,<code>null</code> may be allowed
     * @param layoutResId   resource id for layout of {@link RecyclerView}
     */
    public StrategyLocationsGridAdapter(Context context, List<StrategyNearbyLocationsListModel.StrategyNearbyLocationsItem> data, int layoutResId) {
        super(context, data, layoutResId);
    }

    @Override
    protected void bindDataSource(ViewHolder holder, StrategyNearbyLocationsListModel.StrategyNearbyLocationsItem item, int position) {
        holder.setViewImage(R.id.imgLocationCover, item.getPhoto_url());
        holder.setViewText(R.id.txtPrimaryTitle, item.getName());
        holder.setViewText(R.id.txtSecondaryTitle, item.getName_en());
    }
}
