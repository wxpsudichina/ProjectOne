package com.sudichina.shipperclient.biz;

/**
 * Created by mccccccmike on 2016/8/24.
 */
public interface OnRequestIdentifyingCodeListener extends OnRequestListener {
    void requestIdentifyingCodeSuccessful(String msg);

    void requestIdentifyingCodeFailed(String msg);
}
