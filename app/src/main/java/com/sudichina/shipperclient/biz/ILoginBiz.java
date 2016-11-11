package com.sudichina.shipperclient.biz;

/**
 * Created by mike on 2016/8/9.
 * 登录接口，定义一个登录方法，
 */
public interface ILoginBiz {
    void login(String username, String pwd, OnLoginListener listener);
}
