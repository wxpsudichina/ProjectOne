package com.sudichina.shipperclient.biz;

/**
 * Created by mccccccmike on 2016/8/25.
 */
public interface OnResetPwdListener extends OnRequestListener {
    void resetPwdSuccessful();

    void resetPwdFailed();
}
