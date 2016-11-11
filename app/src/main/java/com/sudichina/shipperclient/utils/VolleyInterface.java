package com.sudichina.shipperclient.utils;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;

/**
 * 作者 ：武小鹏
 * 时间 ：2016/8/3
 * 描述 ：
 */
public abstract class VolleyInterface {
    Context context;
    public static Response.Listener<String> mListener;
    public static ErrorListener mErrorListener;
    public  VolleyInterface(Context context, Response.Listener<String> listener, ErrorListener errorListener){
    this.context=context;
    mListener=listener;
    mErrorListener=mErrorListener;
}

    public Response.Listener<String> loadingListener(){
        mListener=new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
        onMySuccess(s);
            }
        };
        return mListener;
    }
    public ErrorListener onErrorListener(){
        mErrorListener=new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                onMyError(volleyError);
            }
        };
        return mErrorListener;
    }
    public abstract void   onMySuccess(String result);
    public abstract void   onMyError(VolleyError volleyError);


}
