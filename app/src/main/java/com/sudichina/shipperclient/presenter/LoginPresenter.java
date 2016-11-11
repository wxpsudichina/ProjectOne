package com.sudichina.shipperclient.presenter;

import android.os.Handler;

import com.sudichina.shipperclient.bean.LoginResponseBean;
import com.sudichina.shipperclient.biz.ILoginBiz;
import com.sudichina.shipperclient.biz.LoginBiz;
import com.sudichina.shipperclient.biz.OnLoginListener;
import com.sudichina.shipperclient.utils.SPUtils;
import com.sudichina.shipperclient.view.ILoginView;

/**
 * Created by mike on 2016/8/9.
 */
public class LoginPresenter {
    private ILoginBiz loginBiz;
    private ILoginView loginView;
    private Handler mHandler;

    public LoginPresenter(ILoginView loginView) {
        this.loginView = loginView;
        loginBiz = new LoginBiz();
        mHandler = new Handler();
    }

    public void login() {
        loginView.setLoginBtnDisabled();
//        loginView.showLoading();
        loginBiz.login(loginView.getPhoneNo(), loginView.getPwd(), new OnLoginListener() {
            @Override
            public void loginSuccessful(final LoginResponseBean loginResponseBean) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        loginView.showToast(loginResponseBean.getMsg());
                        loginView.setLoginBtnEnabled();
                        SPUtils.put(loginView.getActivity(), "id", loginResponseBean.getData().getId());
                        loginView.toMainActivity(loginResponseBean);
                    }
                });
            }

            @Override
            public void loginFailed(final String msg) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        loginView.showToast(msg);
                        loginView.setLoginBtnEnabled();
                    }
                });
            }

            @Override
            public void requestFailed() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        loginView.showToast("请求失败");
                        loginView.showToast("request failure...");
                        loginView.setLoginBtnEnabled();
                    }
                });
            }

            @Override
            public void requestSuccess() {

            }
        });


    }
}
