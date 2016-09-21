package com.qf.lenovo.qingqiqiu.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qf.lenovo.qingqiqiu.R;
import com.qf.lenovo.qingqiqiu.models.TravelNoteList;
import com.squareup.picasso.Picasso;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/9/20 0020.
 */
public class TravelNoteAdapter extends RecyclerView.Adapter<TravelNoteAdapter.ViewHolder> {


    private List<TravelNoteList.DataBean> mData;
    private LayoutInflater mInflater;
    private RecyclerView mRecyclerView;
    private Context mContext;

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
        View itmeView = mInflater.inflate(R.layout.fragment_travelnote_content, parent, false);

        return new ViewHolder(itmeView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        Picasso.with(mContext).load(mData.get(position).getActivity().getUser().getPhoto_url()).into(holder.travelnotePhoto);
        x.image().bind(holder.travelnotePhoto,mData.get(position).getActivity().getUser().getPhoto_url());
        holder.travelnoteName.setText(mData.get(position).getActivity().getUser().getName());
        holder.travelnotePlatform.setText(mData.get(position).getUser().getName());
        if (mData.get(position).getActivity().getUser().getGender()==1) {
            holder.travelnoteAttention.setText("关注他");
        }else {
            holder.travelnoteAttention.setText("关注她");
        }
//        Picasso.with(mContext).load(mData.get(position).getActivity().getContents().get(0).getPhoto_url()).into(holder.travelnoteImage);
        x.image().bind(holder.travelnoteImage,mData.get(position).getActivity().getContents().get(0).getPhoto_url());

        for (int i = 1; i < mData.get(position).getActivity().getContents().size(); i++) {
            View scrollview = View.inflate(mContext, R.layout.fragment_travelnote_content_scrollview, null);
            View child = scrollview.findViewById(R.id.travelnote_content_scrollview_content);
            ImageView image = (ImageView) child.findViewById(R.id.travelnote_content_scrollview_image);
//            Picasso.with(mContext).load(mData.get(position).getActivity().getContents().get(i).getPhoto_url()).into(image);
            x.image().bind(image,mData.get(position).getActivity().getContents().get(i).getPhoto_url());

            holder.travelnoteScrollview.addView(scrollview);
            int code = holder.travelnoteScrollview.indexOfChild(scrollview);
            child.setTag(code);

        }
        holder.travelnoteTitle.setText(mData.get(position).getActivity().getTopic());
        holder.travelnoteContent.setText(mData.get(position).getActivity().getDescription());
        int count = mData.get(position).getActivity().getDistricts().size() + mData.get(position).getActivity().getCategories().size() + 1;
        for (int i = 0; i < count; i++) {
            View label = View.inflate(mContext, R.layout.fragment_travelnote_content_label, null);
            View child = label.findViewById(R.id.travelnote_content_label_scrollview);
            Button button = (Button) child.findViewById(R.id.travelnote_content_label_btn);
            if(i< mData.get(position).getActivity().getDistricts().size()){
                button.setText(mData.get(position).getActivity().getDistricts().get(i).getName());
            }else if( i>= mData.get(position).getActivity().getDistricts().size() && i<mData.get(position).getActivity().getCategories().size()){
                button.setText(mData.get(position).getActivity().getCategories().get(i).getClass().getName());
            }else if(i == count-1){
//                button.setText(mData.get(position).getActivity().getPoi().getName());
            }
            holder.travelnoteLabel.addView(label);
        }

        holder.travelnotePraise.setText("点赞");
        holder.travelnoteComment.setText("评论");
        holder.travelnoteCollect.setText("收藏");
        holder.travelnoteMore.setText("更多");
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

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.travelnote_content_photo)
        ImageView travelnotePhoto;
        @BindView(R.id.travelnote_content_name)
        TextView travelnoteName;
        @BindView(R.id.travelnote_content_platform)
        TextView travelnotePlatform;
        @BindView(R.id.travelnote_content_attention)
        Button travelnoteAttention;
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
        @BindView(R.id.travelnote_content_praise)
        TextView travelnotePraise;
        @BindView(R.id.travelnote_content_comment)
        TextView travelnoteComment;
        @BindView(R.id.travelnote_content_collect)
        TextView travelnoteCollect;
        @BindView(R.id.travelnote_content_more)
        TextView travelnoteMore;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
