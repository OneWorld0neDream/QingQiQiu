package com.qf.lenovo.qingqiqiu.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.framework.magicarena.core.widget.adapters.RecyclerSingleViewGeneralAdapter;
import com.qf.lenovo.qingqiqiu.R;
import com.qf.lenovo.qingqiqiu.models.TravelNoteListModel;

import java.util.List;

/**
 * Created by 31098 on 9/22/2016.
 */

public class TravaelNoteListAdapter extends RecyclerSingleViewGeneralAdapter<TravelNoteListModel.TravelNoteListItemModel> {
    /**
     * Initializes a new instance of adapter for {@link RecyclerView.Adapter}.
     *  @param context       context instance where {@link RecyclerView} is running on
     * @param data          initial data source for adapter,<code>null</code> may be allowed
     * @param layoutResId   resource id for layout of {@link RecyclerView}
     */
    public TravaelNoteListAdapter(Context context, List<TravelNoteListModel.TravelNoteListItemModel> data, int layoutResId) {
        super(context, data, layoutResId);
    }

    @Override
    protected void bindDataSource(ViewHolder holder, TravelNoteListModel.TravelNoteListItemModel item, int position) {
        switch (item.getIcon_type()) {
            case 0:
                holder.setViewImage(R.id.imgPlanIcon, R.mipmap.icon_plan_food);
                break;
            case 2:
                holder.setViewImage(R.id.imgPlanIcon, R.mipmap.icon_plan_scenery);
                break;
            default:
                holder.setViewImage(R.id.imgPlanIcon, R.mipmap.icon_plan_scenery);
        }

        holder.setViewText(R.id.txtPrimaryTitle, item.getTopic());
        holder.setViewText(R.id.txtSecondaryTitle, item.getAddress());
    }
}
