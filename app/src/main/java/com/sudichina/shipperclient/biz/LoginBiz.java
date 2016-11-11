package com.sudichina.shipperclient.biz;

import com.google.gson.Gson;
import com.sudichina.shipperclient.bean.LoginBean;
import com.sudichina.shipperclient.bean.LoginResponseBean;
import com.sudichina.shipperclient.utils.DESMD5Utils;
import com.sudichina.shipperclient.utils.OkHttpUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by mike on 2016/8/9.
 */
public class LoginBiz implements ILoginBiz {
    private DESMD5Utils utils = new DESMD5Utils();

    @Override
    public void login(final String phoneNo, final String pwd, final OnLoginListener listener) {
        //判断username或者pwd是否为空
        if (phoneNo.equals("")) {
            listener.loginFailed("手机号没填...");
            return;
        }

        if (!phoneNo.matches("^[0-9]{11}$")) {
            listener.loginFailed("你填的不是手机号吧？");
            return;
        }

//        if (pwd.equals("")) {
//            listener.loginFailed("密码没填...");
//            return;
//        }

        //generate JSON data
        String md5_pwd = DESMD5Utils.MD5(pwd,false);
        LoginBean loginBean = new LoginBean(phoneNo, md5_pwd);
        //send request.
        OkHttpUtils.jsonPost(IURL.SIGN_IN, loginBean, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("request failure!");
                e.printStackTrace();
                listener.requestFailed();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("request success!");

                String json = response.body().string();
                Gson gson = new Gson();
                LoginResponseBean loginResponseBean = gson.fromJson(json, LoginResponseBean.class);
                if (loginResponseBean.isSuccess()) {
                    System.out.println("login successful");
                    listener.loginSuccessful(loginResponseBean);
                } else {
                    System.out.println("login failed");
                    listener.loginFailed(loginResponseBean.getMsg());
                }

            }
        });
    }
}
