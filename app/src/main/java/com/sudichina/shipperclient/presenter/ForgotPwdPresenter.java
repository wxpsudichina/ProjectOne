package com.sudichina.shipperclient.presenter;

import android.os.Handler;

import com.sudichina.shipperclient.biz.ForgotPwdBiz;
import com.sudichina.shipperclient.biz.IForgotPwdBiz;
import com.sudichina.shipperclient.biz.OnRequestIdentifyingCodeListener;
import com.sudichina.shipperclient.biz.OnVerifyICListener;
import com.sudichina.shipperclient.view.IForgotPwdView;

/**
 * Created by mccccccmike on 2016/8/25.
 */
public class ForgotPwdPresenter {
    private IForgotPwdView forgotPwdView;
    private IForgotPwdBiz forgotPwdBiz;
    private Handler mHandler;

    public ForgotPwdPresenter(IForgotPwdView forgotPwdView) {
        this.forgotPwdView = forgotPwdView;
        this.forgotPwdBiz = new ForgotPwdBiz();
        mHandler = new Handler();
    }

    public void requestIdentifyingCode() {
        forgotPwdBiz.requestIdentifyingCode(forgotPwdView.getPhoneNo(), new OnRequestIdentifyingCodeListener() {
            @Override
            public void requestIdentifyingCodeSuccessful(final String msg) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        forgotPwdView.showToast(msg);
                    }
                });
            }

            @Override
            public void requestIdentifyingCodeFailed(final String msg) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        forgotPwdView.showToast(msg);
                    }
                });
            }

            @Override
            public void requestFailed() {

            }

            @Override
            public void requestSuccess() {

            }
        });
    }

    public void verifyIdentifyingCode() {
        forgotPwdBiz.verifyIdentifyingCode(forgotPwdView.getPhoneNo(), forgotPwdView.getIdentifyingCode(), new OnVerifyICListener() {
            @Override
            public void verifyICSuccessful(final String msg) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        forgotPwdView.showToast(msg);
                        forgotPwdView.toResetPwdAty();
                    }
                });
            }

            @Override
            public void verifyICFailed(final String msg) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        forgotPwdView.showToast(msg);
                    }
                });
            }

            @Override
            public void requestFailed() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                    }
                });
            }

            @Override
            public void requestSuccess() {

            }
        });
    }

}
