package com.sudichina.shipperclient.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sudichina.shipperclient.R;
import com.sudichina.shipperclient.presenter.QuickLoginPresenter;

public class QuickLoginActivity extends BaseActivity implements IQuickLoginView, View.OnClickListener {
    private EditText et_phone_no;
    private EditText et_identifying_code;
    private Button btn_get_identifying_code;
    private Button btn_login;

    private QuickLoginPresenter quickLoginPresenter = new QuickLoginPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_login);

        initView();
        initEvent();
    }

    @Override
    protected void initView() {
        et_phone_no = (EditText) findViewById(R.id.et_phone_no);
        et_identifying_code = (EditText) findViewById(R.id.et_identifying_code);
        btn_get_identifying_code = (Button) findViewById(R.id.btn_get_identifying_code);
        btn_login = (Button) findViewById(R.id.btn_login);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {
        btn_get_identifying_code.setOnClickListener(this);
        btn_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_get_identifying_code:
                quickLoginPresenter.getIdentifyingCode();
                break;
            case R.id.btn_login:
                quickLoginPresenter.login();
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
}
