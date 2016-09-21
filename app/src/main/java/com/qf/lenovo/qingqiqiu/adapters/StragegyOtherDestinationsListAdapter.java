package com.qf.lenovo.qingqiqiu.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.framework.magicarena.core.widget.adapters.RecyclerSingleViewGeneralAdapter;
import com.qf.lenovo.qingqiqiu.models.StragegyOtherDestinationsListModel;

import java.util.List;

/**
 * Created by 31098 on 9/21/2016.
 */

public class StragegyOtherDestinationsListAdapter extends RecyclerSingleViewGeneralAdapter<StragegyOtherDestinationsListModel.DestinationLocationsList.DestinationsLocationItem> {

    /**
     * Initializes a new instance of adapter for {@link RecyclerView.Adapter}.
     *  @param context       context instance where {@link RecyclerView} is running on
     * @param data          initial data source for adapter,<code>null</code> may be allowed
     * @param layoutResId   resource id for layout of {@link RecyclerView}
     */
    public StragegyOtherDestinationsListAdapter(Context context, List<StragegyOtherDestinationsListModel.DestinationLocationsList.DestinationsLocationItem> data, int layoutResId) {
        super(context, data, layoutResId);
    }

    @Override
    protected void bindDataSource(ViewHolder holder, StragegyOtherDestinationsListModel.DestinationLocationsList.DestinationsLocationItem item, int position) {

    }
}
