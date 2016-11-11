package com.sudichina.shipperclient.biz;

import com.sudichina.shipperclient.bean.LoginResponseBean;

/**
 * Created by mike on 2016/8/9.
 */
public interface OnLoginListener extends OnRequestListener {
    void loginSuccessful(LoginResponseBean loginResponseBean);

    void loginFailed(String msg);

}
