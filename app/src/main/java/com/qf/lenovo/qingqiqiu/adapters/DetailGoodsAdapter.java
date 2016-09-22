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
public class DetailGoodsAdapter extends RecyclerSingleViewGeneralAdapter<TripDetailModel.DataBean.GoodsBean> {
    /**
     * Initializes a new instance of adapter for {@link RecyclerView.Adapter}.
     *
     * @param context     context instance where {@link RecyclerView} is running on
     * @param data        initial data source for adapter,<code>null</code> may be allowed
     * @param layoutResId resource id for layout of {@link RecyclerView}
     */
    public DetailGoodsAdapter(Context context, List<TripDetailModel.DataBean.GoodsBean> data, int layoutResId) {
        super(context, data, layoutResId);
    }

    @Override
    protected void bindDataSource(ViewHolder holder, TripDetailModel.DataBean.GoodsBean item, int position) {
        holder.setViewImage(R.id.detail_goods_item_img,item.getPhoto_url());
        holder.setViewText(R.id.detail_goods_item_text,item.getTitle());
    }
}
