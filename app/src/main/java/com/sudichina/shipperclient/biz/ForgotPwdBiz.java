package com.sudichina.shipperclient.biz;

import com.google.gson.Gson;
import com.sudichina.shipperclient.bean.RequestICBean;
import com.sudichina.shipperclient.bean.ResponseBean;
import com.sudichina.shipperclient.bean.VerifyICBean;
import com.sudichina.shipperclient.utils.OkHttpUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by mccccccmike on 2016/8/25.
 */
public class ForgotPwdBiz implements IForgotPwdBiz {
    private static final String AUTH_TYPE = "sms_forget_verify";
    private static final String SOURCE = "Android";

    @Override
    public void requestIdentifyingCode(String authTarget, final OnRequestIdentifyingCodeListener listener) {
        if (authTarget.equals("")) {
            listener.requestIdentifyingCodeFailed("你又没填手机号吧？");
            return;
        }

        if (!authTarget.matches("^[0-9]{11}$")) {
            listener.requestIdentifyingCodeFailed("你填的不是手机号吧？");
            return;
        }

        RequestICBean requestICBean = new RequestICBean(authTarget, AUTH_TYPE, SOURCE);
        OkHttpUtils.jsonPost(IURL.SEND_IC, requestICBean, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("request failure...");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("request successful...");
                String json = response.body().string();
                System.out.println(json);
                Gson gson = new Gson();
                ResponseBean responseBean = gson.fromJson(json,ResponseBean.class);
                if (responseBean.isSuccess()) {
                    listener.requestIdentifyingCodeSuccessful(responseBean.getMsg());
                } else {
                    listener.requestIdentifyingCodeFailed(responseBean.getMsg());
                }
            }
        });
    }

    @Override
    public void verifyIdentifyingCode(String authTarget, String authCode, final OnVerifyICListener listener) {
        if (authTarget.equals("")) {
            listener.verifyICFailed("请填写手机号...");
            return;
        }

        if (!authTarget.matches("^[0-9]{11}$")) {
            listener.verifyICFailed("请填写正确的手机号...");
            return;
        }

        if (authCode.equals("")) {
            listener.verifyICFailed("请填写验证码...");
            return;
        }

        if (authCode.matches("^[0-9]{6}$")) {
            listener.verifyICFailed("请填写正确的验证码...");
        }

        VerifyICBean verifyICBean = new VerifyICBean(authTarget, AUTH_TYPE, authCode);
        OkHttpUtils.jsonPost(IURL.VERIFY_IC, verifyICBean, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("request failure...");
                listener.requestFailed();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("request successful...");
                String json = response.body().string();
                System.out.println(json);

                Gson gson = new Gson();
                ResponseBean responseObj = gson.fromJson(json, ResponseBean.class);

                if (responseObj.isSuccess()) {
                    listener.verifyICSuccessful(responseObj.getMsg());
                } else {
                    listener.verifyICFailed(responseObj.getMsg());
                }

            }
        });

//        listener.verifyICSuccessful("调试验证码");
    }
}
