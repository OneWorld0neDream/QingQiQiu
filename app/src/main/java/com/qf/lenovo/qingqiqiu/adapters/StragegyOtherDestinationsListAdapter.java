package com.qf.lenovo.qingqiqiu.adapters;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.framework.magicarena.core.widget.adapters.RecyclerSingleViewGeneralAdapter;
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

        TextView buttomText = holder.getView(R.id.txtMore);
        String buttonTest = item.getButton_text();
        if (!TextUtils.isEmpty(buttonTest)) {
            holder.setViewText(R.id.txtMore, buttonTest);
            buttomText.setVisibility(View.VISIBLE);
        } else {
            buttomText.setVisibility(View.GONE);
        }

        RecyclerView locationList = holder.getView(R.id.customLocationList);
        locationList.setHasFixedSize(false);

        locationList.setLayoutManager(new GridLayoutManager(this.mContext, 3));
        locationList.setAdapter(new StrategyLocationsGridAdapter(this.mContext, item.getDestinations(), R.layout.strategy_location_grid_view_item));
    }
}
