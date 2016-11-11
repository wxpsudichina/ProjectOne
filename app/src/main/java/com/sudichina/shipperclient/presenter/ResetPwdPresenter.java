package com.sudichina.shipperclient.presenter;

import android.os.Handler;

import com.sudichina.shipperclient.biz.IResetPwdBiz;
import com.sudichina.shipperclient.biz.OnResetPwdListener;
import com.sudichina.shipperclient.biz.ResetPwdBiz;
import com.sudichina.shipperclient.view.IResetPwdView;

/**
 * Created by mccccccmike on 2016/8/25.
 */
public class ResetPwdPresenter {
    private IResetPwdView resetPwdView;
    private IResetPwdBiz resetPwdBiz;
    private Handler mHandler;

    public ResetPwdPresenter(IResetPwdView resetPwdView) {
        this.resetPwdView = resetPwdView;
        resetPwdBiz = new ResetPwdBiz();
        mHandler = new Handler();
    }

    public void resetPwd() {
        resetPwdBiz.resetPwd(resetPwdView.getPhoneNo(), resetPwdView.getNewPwd(), resetPwdView.getCfmPwd(), new OnResetPwdListener() {
            @Override
            public void resetPwdSuccessful() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        resetPwdView.showToast("修改密码成功");
                    }
                });
            }

            @Override
            public void resetPwdFailed() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        resetPwdView.showToast("修改密码失败");
                    }
                });
            }

            @Override
            public void requestFailed() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        resetPwdView.showToast("哦哦，请求服务器失败了...");
                    }
                });
            }

            @Override
            public void requestSuccess() {

            }
        });


    }

}
