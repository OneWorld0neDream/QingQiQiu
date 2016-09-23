package com.qf.lenovo.qingqiqiu.ui.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qf.lenovo.qingqiqiu.R;

/**
 * Created by Administrator on 2016/9/22 0022.
 */
public class LoadingHanderView extends View {

    private ImageView mReturn;
    private TextView mTitle;

    public LoadingHanderView(Context context) {
        this(context,null);
    }

    public LoadingHanderView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LoadingHanderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {

        View view = LayoutInflater.from(context).inflate(R.layout.custom_itemview,null);
        mReturn = (ImageView) view.findViewById(R.id.custom_image);
        mTitle = (TextView) view.findViewById(R.id.custom_title);
    }

    public void setCustomTitle(String title){
        mTitle.setVisibility(VISIBLE);
        mTitle.setText(title);
    }
    public void returnCallBack(OnClickListener onClickListener){
        mReturn.setVisibility(VISIBLE);
        mReturn.setOnClickListener(onClickListener);
    }

    public void setCustom(String title,OnClickListener onClickListener){
        mTitle.setVisibility(VISIBLE);
        mReturn.setVisibility(VISIBLE);
        mTitle.setText(title);
        mReturn.setOnClickListener(onClickListener);
    }
}
