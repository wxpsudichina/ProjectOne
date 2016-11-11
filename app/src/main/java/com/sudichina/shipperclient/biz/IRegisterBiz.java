package com.sudichina.shipperclient.biz;

/**
 * Created by mike on 2016/8/20.
 */
public interface IRegisterBiz {
    void register(String username, String pwd, String identifyingCode, OnRegisterListener listener);

    void getIdentifyingCode(String phoneNo, OnGetIdentifyingCodeListener listener);
}
