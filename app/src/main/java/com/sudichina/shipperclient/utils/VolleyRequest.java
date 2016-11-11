package com.sudichina.shipperclient.utils;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.sudichina.shipperclient.application.MyApplication;

import java.util.Map;

/**
 * 作者 ：武小鹏
 * 时间 ：2016/8/3
 * 描述 ：
 */
public class VolleyRequest {

    private static StringRequest stringRequest;

    public static void requestGet(Context context, String url, String tag, VolleyInterface vif) {
        MyApplication.getRequestQueue().cancelAll(tag);
        stringRequest = new StringRequest(Request.Method.GET, url, vif.loadingListener(), vif.onErrorListener());
        stringRequest.setTag(tag);
        MyApplication.getRequestQueue().add(stringRequest);
        MyApplication.getRequestQueue().start();

    }

    public static void requestPost(Context context, String url, String tag, final Map<String, String> parms, final VolleyInterface vif) {
        MyApplication.getRequestQueue().cancelAll(tag);
        stringRequest = new StringRequest(Request.Method.POST,url, vif.loadingListener(), vif.onErrorListener()) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                return parms;
            }
        };
        stringRequest.setTag(tag);
        MyApplication.getRequestQueue().add(stringRequest);
        MyApplication.getRequestQueue().start();
    }


}
