package com.sudichina.shipperclient.biz;

/**
 * Created by SudiChina-105 on 2016/8/22.
 *  注册监听接口
 */
public interface OnRegisterListener extends OnRequestListener {
    void registerSuccessed(String msg);//注册成功
    void registerFailed(String msg);//注册失败
}
