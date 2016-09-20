package com.qf.lenovo.qingqiqiu.https;


import android.util.Log;

import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by 31098 on 9/20/2016.
 */
public abstract class DefaultCallbackImp<T> extends Callback<T> {
    private static final String TAG = DefaultCallbackImp.class.getSimpleName();
    private T mTypeHolder;

    @Override
    public T parseNetworkResponse(Response response, int id) throws Exception {
        if (response.isSuccessful()) {
            Object model = new Gson().fromJson(response.body().string(), mTypeHolder.getClass());
            if (mTypeHolder.getClass().isInstance(model)) {
                return ((T) model);
            }
        }
        return null;
    }

    @Override
    public void onError(Call call, Exception e, int id) {
        Log.e(TAG, "onError: " + e.getMessage());
    }
}
