package com.sudichina.shipperclient.biz;

/**
 * Created by mccccccmike on 2016/8/24.
 */
public interface IQuickLoginBiz {
    void getIdentifyingCode(String phoneNo, OnRequestIdentifyingCodeListener listener);

    void login(String authTarget, String authCode, OnLoginListener listener);
}
