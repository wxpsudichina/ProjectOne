package com.sudichina.shipperclient.presenter;

import android.os.Handler;

import com.sudichina.shipperclient.biz.IRegisterBiz;
import com.sudichina.shipperclient.biz.OnGetIdentifyingCodeListener;
import com.sudichina.shipperclient.biz.OnRegisterListener;
import com.sudichina.shipperclient.biz.RegisterBiz;
import com.sudichina.shipperclient.view.IRegisterView;

/**
 * Created by mike on 2016/8/12.
 */
public class RegisterPresenter {
    private IRegisterView registerView;
    private IRegisterBiz registerBiz;
    private Handler mHandler = new Handler();

    public RegisterPresenter(IRegisterView registerView) {
        this.registerView = registerView;
        registerBiz = new RegisterBiz();
    }

    public void RegisterInfo() {
        registerView.setRegisterBtnDisabled();
        registerBiz.register(registerView.getPhoneNo(), registerView.getPwd(), registerView.getIdentifyingCode(), new OnRegisterListener() {
            @Override
            public void registerSuccessed(final String msg) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        registerView.showToast(msg);
                        registerView.setRegisterBtnEnabled();
                    }
                });
            }

            @Override
            public void registerFailed(final String msg) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        registerView.showToast(msg);
                        registerView.setRegisterBtnEnabled();
                    }
                });
            }

            @Override
            public void requestFailed() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        registerView.setRegisterBtnEnabled();
                    }
                });
            }

            @Override
            public void requestSuccess() {

            }
        });
    }

    //获得验证码
    public void getIdentifyingCode() {

        if (registerView.getPhoneNo().equals("")) {
            registerView.showToast("请输入手机号");
            return;
        }

        if (!registerView.getPhoneNo().matches("^[0-9]{11}$")) {
            registerView.showToast("请输入正确的手机号");
            return;
        }

        registerView.showGetIdentifyingCodeState();
        registerBiz.getIdentifyingCode(registerView.getPhoneNo(), new OnGetIdentifyingCodeListener() {
            @Override
            public void getIdentifyingCodeSuccess(final String msg) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        registerView.showToast(msg);
                    }
                });
            }

            @Override
            public void getIdentifyingCodeFailed(final String msg) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        registerView.showToast(msg);
                    }
                });
            }

            @Override
            public void requestSuccess() {

            }

            @Override
            public void requestFailed() {

            }
        });
    }
}
