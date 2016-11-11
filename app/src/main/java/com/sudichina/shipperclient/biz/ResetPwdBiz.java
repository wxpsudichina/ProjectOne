package com.sudichina.shipperclient.biz;

import com.sudichina.shipperclient.bean.LoginBean;
import com.sudichina.shipperclient.utils.DESMD5Utils;
import com.sudichina.shipperclient.utils.OkHttpUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by mccccccmike on 2016/8/25.
 */
public class ResetPwdBiz implements IResetPwdBiz {
    private DESMD5Utils utils = new DESMD5Utils();

    @Override
    public void resetPwd(String phoneNo, String newPwd, String cfmPwd, final OnResetPwdListener listener) {
        //判断新密码或者确认密码是否为空
        if (newPwd.equals("")) {

            return;
        }

        if (cfmPwd.equals("")) {

            return;
        }

        //判断新密码是否和确认密码相同
        if (!newPwd.equals(cfmPwd)) {

            return;
        }

        //请求服务器修改密码
        LoginBean loginBean = new LoginBean(phoneNo, utils.encrypt(newPwd));
        OkHttpUtils.jsonPost(IURL.RESET_PWD, loginBean, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("request failure...");
                listener.requestFailed();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("request successful...");
                System.out.println(response.body().string());
            }
        });
    }
}
