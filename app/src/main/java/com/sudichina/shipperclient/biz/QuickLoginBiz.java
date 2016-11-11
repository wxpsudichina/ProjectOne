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
 * Created by mccccccmike on 2016/8/24.
 * 快速登录逻辑功能类
 */
public class QuickLoginBiz implements IQuickLoginBiz {
    private static final String I_GET_IDENTIFYING_CODE = "http://192.168.1.37:8888/services/sms/send";
    private static final String I_VERIFY_IDENTIFYING_CODE = "http://192.168.1.37:8888/services/sms/verification";

    @Override
    public void getIdentifyingCode(String phoneNo, final OnRequestIdentifyingCodeListener listener) {
        RequestICBean requestICBean = new RequestICBean(phoneNo, "sms_reg_verify", "Android");

        OkHttpUtils.jsonPost(I_GET_IDENTIFYING_CODE, requestICBean, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("request failure...");
                listener.requestFailed();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("request successful...");
//                System.out.println(response.body().string());
                String json = response.body().string();
                System.out.println("------------------json------------");
                System.out.println(json);
                Gson gson = new Gson();
                ResponseBean responseObj = gson.fromJson(json, ResponseBean.class);
                System.out.println("-----------------responseObj------");
                System.out.println(responseObj);
            }
        });
    }

    @Override
    public void login(String authTarget, String authCode, final OnLoginListener listener) {
        VerifyICBean verifyICBean = new VerifyICBean(authTarget, "sms_reg_verify", authCode);
        OkHttpUtils.jsonPost(I_VERIFY_IDENTIFYING_CODE, verifyICBean, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("request failure...");
                listener.loginFailed("登录失败。。。");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("request successful...");
                System.out.println(response.body().string());
//                listener.loginSuccessful("登录成功。。。");
            }
        });
    }
}
