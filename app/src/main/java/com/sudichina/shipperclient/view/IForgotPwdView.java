package com.sudichina.shipperclient.view;

/**
 * Created by mccccccmike on 2016/8/24.
 */
public interface IForgotPwdView extends ICommonView {
    String getPhoneNo();

    String getIdentifyingCode();

    void toResetPwdAty();
}
