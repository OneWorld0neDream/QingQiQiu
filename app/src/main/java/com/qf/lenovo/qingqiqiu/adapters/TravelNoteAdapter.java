package com.qf.lenovo.qingqiqiu.adapters;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.qf.lenovo.qingqiqiu.R;
import com.qf.lenovo.qingqiqiu.models.TravelNoteList;
import com.squareup.picasso.Picasso;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/9/20 0020.
 */
public class TravelNoteAdapter extends RecyclerView.Adapter<TravelNoteAdapter.ViewHolder> implements View.OnClickListener {


    private static final String TAG = TravelNoteAdapter.class.getSimpleName();
    private List<TravelNoteList.DataBean> mData;
    private LayoutInflater mInflater;
    private RecyclerView mRecyclerView;
    private Context mContext;
    private ImageOptions options = new ImageOptions.Builder().setCircular(true).setUseMemCache(false).build();

    public TravelNoteAdapter(Context mContext,List<TravelNoteList.DataBean> mData) {
        if (this.mData != null) {
            this.mData = mData;
        }else {
            this.mData = new ArrayList<>();
        }
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    public void addData(List<TravelNoteList.DataBean> mData){
        if (mData != null) {
            this.mData.addAll(mData);
            notifyDataSetChanged();
        }
    }

    public void upData(List<TravelNoteList.DataBean> mData){
        if (mData != null) {
            this.mData.clear();
            this.mData.addAll(mData);
            notifyDataSetChanged();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.fragment_travelnote_content, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        instanceView(holder,position);
        clickViewEvent(holder);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView = recyclerView;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public TravelNoteList.DataBean getItem(int position){
        return mData.get(position);
    }

    // item 中的点击事件
    public void clickViewEvent(ViewHolder holder){
        holder.travelnotePhoto.setOnClickListener(this);
        holder.travelnotePlatform.setOnClickListener(this);
        holder.travelnoteAttention.setOnClickListener(this);
        holder.travelnoteImage.setOnClickListener(this);
        holder.travelnotePraiseLayout.setOnClickListener(this);
        holder.treavelnoteCommentLayout.setOnClickListener(this);
        holder.travelnoteCollectLayout.setOnClickListener(this);
        holder.travelnoteMore.setOnClickListener(this);
    }

    // 实例化 item中的所有控件
    public void instanceView(ViewHolder holder, int position){

        x.image().bind(holder.travelnotePhoto,mData.get(position).getActivity().getUser().getPhoto_url(),this.options);
        holder.travelnoteName.setText(mData.get(position).getActivity().getUser().getName());
        holder.travelnotePlatform.setText(mData.get(position).getUser().getName());
        if (mData.get(position).getActivity().getUser().getGender()==1) {
            holder.travelnoteAttention.setText("关注他");
        }else {
            holder.travelnoteAttention.setText("关注她");
        }
//        x.image().bind(holder.travelnoteImage,mData.get(position).getActivity().getContents().get(0).getPhoto_url());
//        Picasso.with(mContext).load(mData.get(position).getActivity().getContents().get(0).getPhoto_url()).resize(300,600).into(holder.travelnoteImage);
        holder.travelnoteImage.setImageResource(R.mipmap.ic_launch);
        holder.travelnoteScrollview.removeAllViewsInLayout();
        for (int i = 1; i < mData.get(position).getActivity().getContents().size(); i++) {
            View scrollview = View.inflate(mContext, R.layout.fragment_travelnote_content_scrollview, null);
            View child = scrollview.findViewById(R.id.travelnote_content_scrollview_content);
            ImageView image = (ImageView) child.findViewById(R.id.travelnote_content_scrollview_image);

//            x.image().bind(image,mData.get(position).getActivity().getContents().get(i).getPhoto_url());
//            Picasso.with(mContext).load(mData.get(position).getActivity().getContents().get(i).getPhoto_url()).resize(200,100).into(image);
            image.setImageResource(R.mipmap.ic_launch);
            holder.travelnoteScrollview.addView(scrollview);
            int code = holder.travelnoteScrollview.indexOfChild(scrollview);
            child.setTag(code);
            child.setOnClickListener(this);
        }
        holder.travelnoteTitle.setText(mData.get(position).getActivity().getTopic());
        holder.travelnoteContent.setText(mData.get(position).getActivity().getDescription());
        // 获得标签的个数
        int count = 0;
        if (mData.get(position).getActivity().getPoi() != null) {
            count = mData.get(position).getActivity().getDistricts().size() + mData.get(position).getActivity().getCategories().size() + 1;
        }else {
            count = mData.get(position).getActivity().getDistricts().size() + mData.get(position).getActivity().getCategories().size();
        }
        // 给标签添加数据
        holder.travelnoteLabel.removeAllViewsInLayout();
        for (int i = 0; i < count; i++) {
            View label = View.inflate(mContext, R.layout.fragment_travelnote_content_label, null);
            View child = label.findViewById(R.id.travelnote_content_label_scrollview);
            TextView button = (TextView) child.findViewById(R.id.travelnote_content_label_btn);
            if (mData.get(position).getActivity().getPoi() != null){
                if(i< mData.get(position).getActivity().getDistricts().size()){
                    button.setText(mData.get(position).getActivity().getDistricts().get(i).getName());
                }else if( i>= mData.get(position).getActivity().getDistricts().size() && i<count-1){
                    button.setText(mData.get(position).getActivity().getCategories().get(i- mData.get(position).getActivity().getDistricts().size()).getName());
                }else{
                    button.setText(mData.get(position).getActivity().getPoi().getName());
                }
            }else{
                if(i< mData.get(position).getActivity().getDistricts().size()){
                    button.setText(mData.get(position).getActivity().getDistricts().get(i).getName());
                }else if( i>= mData.get(position).getActivity().getDistricts().size() && i<count){
                    button.setText(mData.get(position).getActivity().getCategories().get(i- mData.get(position).getActivity().getDistricts().size()).getName());
                }
            }
            holder.travelnoteLabel.addView(label);
            int num = holder.travelnoteLabel.indexOfChild(label);
            child.setTag(num);
            child.setOnClickListener(this);
        }

        holder.travelnotePraise.setText(mData.get(position).getActivity().getLikes_count()+"");
        holder.travelnoteComment.setText(mData.get(position).getActivity().getComments_count()+"");
        holder.travelnoteCollect.setText(mData.get(position).getActivity().getFavorites_count()+"");
    }

    // 初始化 item中的所有控件
    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.travelnote_content_photo)
        ImageView travelnotePhoto;
        @BindView(R.id.travelnote_content_name)
        TextView travelnoteName;
        @BindView(R.id.travelnote_content_platform)
        TextView travelnotePlatform;
        @BindView(R.id.travelnote_content_attention)
        TextView travelnoteAttention;
        @BindView(R.id.travelnote_content_image)
        ImageView travelnoteImage;
        @BindView(R.id.travelnote_content_scrollview)
        LinearLayout travelnoteScrollview;
        @BindView(R.id.travelnote_content_title)
        TextView travelnoteTitle;
        @BindView(R.id.travelnote_content_content)
        TextView travelnoteContent;
        @BindView(R.id.travelnote_content_label)
        LinearLayout travelnoteLabel;
        @BindView(R.id.travelnote_content_praise_layout)
        LinearLayout travelnotePraiseLayout;
        @BindView(R.id.travelnote_content_comment_layout)
        LinearLayout treavelnoteCommentLayout;
        @BindView(R.id.travelnote_content_collect_layout)
        LinearLayout travelnoteCollectLayout;
        @BindView(R.id.travelnote_content_praise)
        TextView travelnotePraise;
        @BindView(R.id.travelnote_content_comment)
        TextView travelnoteComment;
        @BindView(R.id.travelnote_content_collect)
        TextView travelnoteCollect;
        @BindView(R.id.travelnote_content_more)
        ImageView travelnoteMore;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    // 控件监听
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.travelnote_content_photo:
                Log.e(TAG, "clickedListener: 我是头像" );
                break;
            case R.id.travelnote_content_platform:
                Log.e(TAG, "clickedListener: 我是氢直播" );
                break;
            case R.id.travelnote_content_attention:
                Log.e(TAG, "clickedListener: 我是关注" );
                break;
            case R.id.travelnote_content_image:
                Log.e(TAG, "clickedListener: 我是image");
                break;
            case R.id.travelnote_content_scrollview_content:
                Log.e(TAG, "clickedListener: 我是图片的scrollview " + v.getTag() );
                break;
            case R.id.travelnote_content_label_scrollview:
                Log.e(TAG, "clickedListener: 我是标签的scrollview" + v.getTag() );
                break;
            case R.id.travelnote_content_praise_layout:
                Log.e(TAG, "clickedListener: 我是点赞" );
                break;
            case R.id.travelnote_content_comment_layout:
                Log.e(TAG, "clickedListener: 我是评价" );
                break;
            case R.id.travelnote_content_collect_layout:
                Log.e(TAG, "clickedListener: 我是收藏" );
                break;
            case R.id.travelnote_content_more:
                showPopup(v);
                break;
            case R.id.travelnote_content_popup_share:
                Log.e(TAG, "onClick: 我要分享" );
                break;
            case R.id.travelnote_content_popup_recommend:
                Log.e(TAG, "onClick: 我要推荐" );
                break;
        }
    }

    public void showPopup(View view){
        View pop = LayoutInflater.from(mContext).inflate(R.layout.popupwindow_travelnote_itemt, null);
        pop.measure(0,0);
        PopupWindow popupWindow = new PopupWindow(pop,pop.getMeasuredWidth(),pop.getMeasuredHeight());
        DisplayMetrics displayMetrics = mContext.getResources().getDisplayMetrics();
        int widthPixels = displayMetrics.widthPixels;
        popupWindow.setWidth(widthPixels / 2);

        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        if (popupWindow.isShowing()) {
            popupWindow.dismiss();
        }else{

//            popupWindow.showAtLocation(view, Gravity.RIGHT,0,0);
            popupWindow.showAsDropDown(view,0,0);
            TextView share = (TextView) popupWindow.getContentView().findViewById(R.id.travelnote_content_popup_share);
            share.setOnClickListener(this);
            TextView recommend = (TextView) popupWindow.getContentView().findViewById(R.id.travelnote_content_popup_recommend);
            recommend.setOnClickListener(this);
        }
    }
}
