package com.sudichina.shipperclient.view;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sudichina.shipperclient.R;
import com.sudichina.shipperclient.bean.LoginResponseBean;
import com.sudichina.shipperclient.presenter.LoginPresenter;
import com.sudichina.shipperclient.utils.OkHttp_Utils;

import java.io.IOException;

/**
 * Created by SudiChina-105 on 2016/8/5.
 * 登陆页面，包含了手机快捷登陆和账号密码登陆
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener, ILoginView {
    private EditText et_phone_no;//用户名
    private EditText et_pwd;//密码

    private Button btn_login;//登陆
    private TextView tv_forgot_pwd;
    private TextView tv_register;//注册
    private TextView tv_quick_login;//手机快捷登陆

    private LoginPresenter loginPresenter = new LoginPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //默认的一般是手机号加上手机验证码登陆
        //        getSupportFragmentManager().beginTransaction().replace()
        initView();
        initEvent();

        new Thread(new Runnable() {
            @Override
            public void run() {
                getcon();
            }
        }).start();


    }

    /**
     * 联网请求的方法
     */
    private void getcon() {
//        String url = "http://192.168.1.49:8888/services/users/2";
        String url = "http://192.168.1.49:8888/services/users/users";
        try {
            String str = OkHttp_Utils.loadStringFromUrl(url);
            Log.d("hlm", str);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        et_phone_no = (EditText) findViewById(R.id.et_phone_no);
        et_pwd = (EditText) findViewById(R.id.et_pwd);

        btn_login = (Button) findViewById(R.id.btn_login);
        tv_forgot_pwd = (TextView) findViewById(R.id.tv_forgot_pwd);
        tv_register = (TextView) findViewById(R.id.tv_register);
        tv_quick_login = (TextView) findViewById(R.id.tv_quick_login);
    }

    @Override
    protected void initEvent() {
        btn_login.setOnClickListener(this);
        tv_forgot_pwd.setOnClickListener(this);
        tv_register.setOnClickListener(this);
        tv_quick_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_forgot_pwd:
                toAty(ForgotPwdActivity.class);
                break;
            case R.id.tv_register:
                toAty(RegisterActivity.class);
                break;
            case R.id.btn_login:
//                toAty(this, MainActivity.class);
                loginPresenter.login();
                break;
            case R.id.tv_quick_login:
                toAty(QuickLoginActivity.class);
                break;
        }
    }

    @Override
    public String getPhoneNo() {
        return et_phone_no.getText().toString().trim();
    }

    @Override
    public String getPwd() {
        return et_pwd.getText().toString().trim();
    }

    @Override
    public void toMainActivity(LoginResponseBean loginResponseBean) {
        toAty(MainActivity.class);
    }

    @Override
    public void showToast(String msg) {
        toast(msg);
    }

    @Override
    public void setLoginBtnEnabled() {
        btn_login.setEnabled(true);
    }

    @Override
    public void setLoginBtnDisabled() {
        btn_login.setEnabled(false);
    }

    @Override
    public Activity getActivity() {
        return this;
    }
}
