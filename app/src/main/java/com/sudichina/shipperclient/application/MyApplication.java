package com.sudichina.shipperclient.application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.zhy.autolayout.config.AutoLayoutConifg;

import org.litepal.LitePalApplication;

//import cn.jpush.android.api.JPushInterface;

/**
 * Created by mike on 2016/8/1.
 */
public class MyApplication extends LitePalApplication {
    public static RequestQueue volleyQueue;

    @Override
    public void onCreate() {
        super.onCreate();

        //初始化自动化适配
        AutoLayoutConifg.getInstance().init(getApplicationContext());
        // 建立Volley的Http请求队列
        volleyQueue = Volley.newRequestQueue(getApplicationContext());

        //全局捕获异常，实例化
//        CrashHandler crashHandler = CrashHandler.getInstance();
//        crashHandler.init(getApplicationContext());

//        JPushInterface.setDebugMode(true);    // 设置开启日志,发布时请关闭日志
//        JPushInterface.init(this);            // 初始化 JPush


    }

    // 开放Volley的HTTP请求队列接口
    public static RequestQueue getRequestQueue() {
        return volleyQueue;
    }
}
