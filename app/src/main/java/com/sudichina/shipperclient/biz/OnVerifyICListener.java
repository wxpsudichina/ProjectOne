package com.sudichina.shipperclient.biz;

/**
 * Created by mccccccmike on 2016/8/25.
 */
public interface OnVerifyICListener extends OnRequestListener {
    void verifyICSuccessful(String msg);

    void verifyICFailed(String msg);
}
