package com.sudichina.shipperclient.biz;

import com.google.gson.Gson;
import com.sudichina.shipperclient.bean.RegisterBean;
import com.sudichina.shipperclient.bean.RequestICBean;
import com.sudichina.shipperclient.bean.ResponseBean;
import com.sudichina.shipperclient.utils.DESMD5Utils;
import com.sudichina.shipperclient.utils.OkHttpUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by mike on 2016/8/22.
 * 注册逻辑功能类
 */
public class RegisterBiz implements IRegisterBiz {
    private DESMD5Utils utils = new DESMD5Utils();

    @Override
    public void register(final String phoneNo, final String pwd, final String identifyingCode, final OnRegisterListener listener) {
        if (phoneNo.equals("") || pwd.equals("")) {
            listener.registerFailed("手机号或者密码不能为空...");
        }

        System.out.println("请求了 ---> " + phoneNo);
        //gets json string
        RegisterBean registerBean = new RegisterBean(
                phoneNo,
                utils.encrypt(pwd),
                "1",
                "0"
        );

        OkHttpUtils.jsonPost(IURL.SIGN_UP, registerBean, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("request failure!");
                listener.requestFailed();
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("request success!");
//                System.out.println(response.body().string());

                //request success! parse JSON data returned from the server.
                String json = response.body().string();
                Gson gson = new Gson();
                ResponseBean responseObj = gson.fromJson(json, ResponseBean.class);
                System.out.println("---------------------");
                System.out.println(responseObj);
                if (responseObj.isSuccess()) {
                    listener.registerSuccessed(responseObj.getMsg());
                } else {
                    listener.registerFailed(responseObj.getMsg());
                }
            }
        });

    }

    @Override
    public void getIdentifyingCode(String phoneNo, final OnGetIdentifyingCodeListener listener) {

        RequestICBean requestICBean = new RequestICBean(phoneNo, "sms_reg_verify", "Android");

        OkHttpUtils.jsonPost(IURL.SEND_IC, requestICBean, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("request failure...");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("request successful!");
//                System.out.println(response.body().string());
                String json = response.body().string();

                Gson gson = new Gson();
                ResponseBean bean = gson.fromJson(json,ResponseBean.class);
                if (bean.isSuccess()) {
                    listener.getIdentifyingCodeSuccess(bean.getMsg());
                } else {
                    listener.getIdentifyingCodeFailed(bean.getMsg());
                }
            }
        });

    }
}
