package com.sudichina.shipperclient.view;

import android.app.Activity;

import com.sudichina.shipperclient.bean.LoginResponseBean;

/**
 * Created by mike on 2016/8/9.
 * every activity needs to has a interface which to communicate with a presenter.
 */
public interface ILoginView {
    String getPhoneNo();

    String getPwd();

    void toMainActivity(LoginResponseBean loginResponseBean);

    void showToast(String msg);

    void setLoginBtnEnabled();

    void setLoginBtnDisabled();

    Activity getActivity();
}
