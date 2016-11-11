package com.sudichina.shipperclient.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sudichina.shipperclient.R;
import com.sudichina.shipperclient.presenter.ForgotPwdPresenter;

public class ForgotPwdActivity extends BaseActivity implements IForgotPwdView, View.OnClickListener {
    public static final String PHONE_NO = "phone_no";

    private EditText et_phone_no;
    private EditText et_identifying_code;
    private Button btn_request_identifying_code;
    private Button btn_continue;

    private ForgotPwdPresenter forgotPwdPresenter = new ForgotPwdPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pwd);

        initView();
        initEvent();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        et_phone_no = (EditText) findViewById(R.id.et_phone_no);
        et_identifying_code = (EditText) findViewById(R.id.et_identifying_code);
        btn_request_identifying_code = (Button) findViewById(R.id.btn_request_identifying_code);
        btn_continue = (Button) findViewById(R.id.btn_continue);
    }

    @Override
    protected void initEvent() {
        btn_request_identifying_code.setOnClickListener(this);
        btn_continue.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_request_identifying_code:
                System.out.println("click me...");
                forgotPwdPresenter.requestIdentifyingCode();
                break;
            case R.id.btn_continue:
                forgotPwdPresenter.verifyIdentifyingCode();
                break;
        }
    }

    @Override
    public String getPhoneNo() {
        return et_phone_no.getText().toString().trim();
    }

    @Override
    public String getIdentifyingCode() {
        return et_identifying_code.getText().toString().trim();
    }

    @Override
    public void showToast(String msg) {
        toast(msg);
    }

    @Override
    public void toResetPwdAty() {
        Bundle bundle = new Bundle();
        bundle.putString(PHONE_NO, getPhoneNo());
        toAty(ResetPwdActivity.class,bundle);
    }
}
