package com.qf.lenovo.qingqiqiu.https;


import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.callback.Callback;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by 31098 on 9/20/2016.
 */
public abstract class DefaultCallbackImp<T> extends Callback<T> {
    private static final String TAG = DefaultCallbackImp.class.getSimpleName();
    private TypeToken<T> tTypeToken = new TypeToken<T>() {
    };

    @Override
    public T parseNetworkResponse(Response response, int id) throws Exception {
        if (response.isSuccessful()) {
            Type genType = this.getClass().getGenericSuperclass();
            Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
            return (T) new Gson().fromJson(response.body().string(), (Class) params[0]);
        }
        return null;
    }

    @Override
    public void onError(Call call, Exception e, int id) {
        Log.e(TAG, "onError: " + e.getMessage());
    }
}
