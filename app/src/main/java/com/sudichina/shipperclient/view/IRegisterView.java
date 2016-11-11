package com.sudichina.shipperclient.view;

/**
 * Created by mike on 2016/8/12.
 */
public interface IRegisterView {
    void toggleStateGetIdentifyingCode(boolean hasFoucus);

    void showGetIdentifyingCodeState();

    String getPhoneNo();

    String getPwd();

    String getIdentifyingCode();

    void showToast(String msg);

    void setRegisterBtnEnabled();

    void setRegisterBtnDisabled();
}
