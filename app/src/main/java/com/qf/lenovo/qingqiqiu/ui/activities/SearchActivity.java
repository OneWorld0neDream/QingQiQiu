package com.qf.lenovo.qingqiqiu.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.qf.lenovo.qingqiqiu.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity implements View.OnKeyListener {

    @BindView(R.id.search_back)
    ImageView mBack;
    @BindView(R.id.search_edit)
    EditText mSearchEdit;
    @BindView(R.id.search_clear)
    ImageView mClear;
    @BindView(R.id.search_viewpager)
    RecyclerView mViewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mSearchEdit.setOnKeyListener(this);
    }


    @OnClick({R.id.search_back, R.id.search_clear})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search_back:
                finish();
                break;
            case R.id.search_clear:
                mSearchEdit.setText("");
                break;
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {

        switch (event.getAction()) {
            case KeyEvent.KEYCODE_ENTER:

                break;
        }
        return false;
    }
}
