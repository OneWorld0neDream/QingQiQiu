package com.qf.lenovo.qingqiqiu.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.framework.magicarena.core.widget.adapters.RecyclerSingleViewGeneralAdapter;
import com.framework.magicarena.core.widget.decorations.RecyclerViewDivider;
import com.qf.lenovo.qingqiqiu.R;
import com.qf.lenovo.qingqiqiu.models.StragegyOtherDestinationsListModel;

import java.util.List;

/**
 * Created by 31098 on 9/21/2016.
 */

public class StragegyOtherDestinationsListAdapter extends RecyclerSingleViewGeneralAdapter<StragegyOtherDestinationsListModel.DestinationLocationsList> {

    /**
     * Initializes a new instance of adapter for {@link RecyclerView.Adapter}.
     *  @param context       context instance where {@link RecyclerView} is running on
     * @param data          initial data source for adapter,<code>null</code> may be allowed
     * @param layoutResId   resource id for layout of {@link RecyclerView}
     */
    public StragegyOtherDestinationsListAdapter(Context context, List<StragegyOtherDestinationsListModel.DestinationLocationsList> data, int layoutResId) {
        super(context, data, layoutResId);
    }

    @Override
    protected void bindDataSource(ViewHolder holder, StragegyOtherDestinationsListModel.DestinationLocationsList item, int position) {
        holder.setViewText(R.id.txtTopTitle, item.getName());
        holder.setViewText(R.id.txtMore, item.getButton_text());
        RecyclerView locationList = holder.getView(R.id.customLocationList);
        locationList.setLayoutManager(new GridLayoutManager(this.mContext, 3));
        locationList.addItemDecoration(new RecyclerViewDivider(this.mContext, LinearLayoutManager.HORIZONTAL, 10, Color.WHITE));
        locationList.setAdapter(new StrategyLocationsGridAdapter(this.mContext, item.getDestinations(), R.layout.strategy_location_grid_view_item));
    }
}
