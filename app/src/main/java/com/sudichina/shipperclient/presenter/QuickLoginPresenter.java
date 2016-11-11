package com.sudichina.shipperclient.presenter;

import android.os.Handler;

import com.sudichina.shipperclient.bean.LoginResponseBean;
import com.sudichina.shipperclient.biz.IQuickLoginBiz;
import com.sudichina.shipperclient.biz.OnLoginListener;
import com.sudichina.shipperclient.biz.OnRequestIdentifyingCodeListener;
import com.sudichina.shipperclient.biz.QuickLoginBiz;
import com.sudichina.shipperclient.view.IQuickLoginView;

/**
 *
 * Created by mccccccmike on 2016/8/24.
 * 快速登录逻辑控制层
 */
public class QuickLoginPresenter {
    private IQuickLoginView quickLoginView;
    private IQuickLoginBiz quickLoginBiz;
    private Handler mHandler;

    public QuickLoginPresenter(IQuickLoginView quickLoginView) {
        this.quickLoginView = quickLoginView;
        quickLoginBiz = new QuickLoginBiz();
        mHandler = new Handler();
    }

    public void getIdentifyingCode() {
        quickLoginBiz.getIdentifyingCode(quickLoginView.getPhoneNo(), new OnRequestIdentifyingCodeListener() {
            @Override
            public void requestIdentifyingCodeSuccessful(String msg) {

            }

            @Override
            public void requestIdentifyingCodeFailed(String msg) {

            }

            @Override
            public void requestFailed() {

            }

            @Override
            public void requestSuccess() {

            }
        });
    }

    public void login() {
        quickLoginBiz.login(quickLoginView.getPhoneNo(), quickLoginView.getIdentifyingCode(), new OnLoginListener() {
            @Override
            public void loginSuccessful(final LoginResponseBean loginResponseBean) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        quickLoginView.showToast(loginResponseBean.getMsg());
                    }
                });
            }

            @Override
            public void loginFailed(final String msg) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        quickLoginView.showToast(msg);
                    }
                });
            }

            @Override
            public void requestFailed() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        quickLoginView.showToast("request failure...");
                    }
                });
            }

            @Override
            public void requestSuccess() {

            }
        });
    }
}
