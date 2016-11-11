package com.sudichina.shipperclient.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sudichina.shipperclient.R;
import com.sudichina.shipperclient.presenter.ResetPwdPresenter;

public class ResetPwdActivity extends BaseActivity implements IResetPwdView, View.OnClickListener {
    private EditText et_new_pwd;
    private EditText et_cfm_pwd;
    private Button btn_continue;

    private String phone_no;
    private ResetPwdPresenter resetPwdPresenter = new ResetPwdPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pwd);

        Bundle bundle = getIntent().getExtras();
        phone_no = bundle.getString(ForgotPwdActivity.PHONE_NO);
        System.out.println("phone_no ===============" + phone_no);

        initView();
        initEvent();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        et_new_pwd = (EditText) findViewById(R.id.et_new_pwd);
        et_cfm_pwd = (EditText) findViewById(R.id.et_cfm_pwd);
        btn_continue = (Button) findViewById(R.id.btn_continue);
    }

    @Override
    protected void initEvent() {
        btn_continue.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_continue:
                resetPwdPresenter.resetPwd();
                break;
        }
    }

    @Override
    public String getNewPwd() {
        return et_new_pwd.getText().toString().trim();
    }

    @Override
    public String getCfmPwd() {
        return et_cfm_pwd.getText().toString().trim();
    }

    @Override
    public String getPhoneNo() {
        return phone_no;
    }

    @Override
    public void showToast(String msg) {
        toast(msg);
    }
}
