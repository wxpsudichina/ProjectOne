package com.sudichina.shipperclient.view;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.rengwuxian.materialedittext.MaterialEditText;
import com.sudichina.shipperclient.R;
import com.sudichina.shipperclient.presenter.RegisterPresenter;

/**
 * 注册页面，
 * Created by sudichina on 2016/8/22.
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener, IRegisterView {
    private ImageView iv_back;//返回按钮
    private MaterialEditText et_phone_no;//手机号
    private MaterialEditText et_code;//验证码
    private MaterialEditText et_password;//密码
    private Button register_btn;//注册按钮
    private Button register_take_code;//获取验证码

    private RegisterPresenter mRegisterPresenter = new RegisterPresenter(this);

    private static final int TIME_INTERVAL = 60;//设置倒计时最大值60
    private int one_minute = TIME_INTERVAL;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();//获取控件id
        initEvent();//控件设置监听
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        iv_back = (ImageView) findViewById(R.id.register_iv_back);
        et_phone_no = (MaterialEditText) findViewById(R.id.register_et_phone_no);
        et_code = (MaterialEditText) findViewById(R.id.et_code);
        et_password = (MaterialEditText) findViewById(R.id.register_et_pwd);
        register_btn = (Button) findViewById(R.id.register_btn);
        register_take_code = (Button) findViewById(R.id.register_take_code);
        register_btn.setOnClickListener(this);
    }

    @Override
    protected void initEvent() {
        iv_back.setOnClickListener(this);
        et_code.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                toggleStateGetIdentifyingCode(hasFocus);
            }
        });
        register_take_code.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_iv_back:
                finish();//结束当前页面
                break;
            case R.id.register_btn://注册按钮
                mRegisterPresenter.RegisterInfo();
                break;
            case R.id.register_et_pwd://密码

                break;
            case R.id.register_take_code://获取验证码

                mRegisterPresenter.getIdentifyingCode();

                break;
        }
    }

    /**
     * 注册页面验证码的状态
     *
     * @param hasFoucus
     */
    @Override
    public void toggleStateGetIdentifyingCode(boolean hasFoucus) {
        if (hasFoucus) {
//            if (!getIdentifyingCode) {
            register_take_code.setEnabled(true);
//            }
        } else {
            register_take_code.setEnabled(false);
        }
    }

    private Runnable r = new Runnable() {
        @Override
        public void run() {
            register_take_code.setText(--one_minute < 0 ? "获取验证码" : String.valueOf(one_minute));
            if (one_minute < 0) {
                one_minute = TIME_INTERVAL;
                register_take_code.setEnabled(true);
                return;
            }
            mHandler.postDelayed(this, 1000);
        }
    };

    @Override
    public void showGetIdentifyingCodeState() {
        register_take_code.setEnabled(false);

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                register_take_code.setText(String.valueOf(--one_minute));
                mHandler.postDelayed(r, 1000);
            }
        });
    }

    @Override
    public String getPhoneNo() {
        return et_phone_no.getText().toString().trim();
    }

    @Override
    public String getPwd() {
        return et_password.getText().toString().trim();
    }

    @Override
    public String getIdentifyingCode() {
        return et_code.getText().toString().trim();
    }

    @Override
    public void showToast(String msg) {
        toast(msg);
    }

    @Override
    public void setRegisterBtnEnabled() {
        register_btn.setEnabled(true);
    }

    @Override
    public void setRegisterBtnDisabled() {
        register_btn.setEnabled(false);
    }
}
