package com.sudichina.shipperclient.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.sudichina.shipperclient.R;
import com.sudichina.shipperclient.utils.DialogUtils;

/**
 * 支付方式
 * Created by SudiChina-105 on 2016/9/29.
 */

public class PayActivity extends BaseActivity implements View.OnClickListener{
    private RelativeLayout lin_baidu, lin_yinlian, lin_alipay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        initData();
        initView();
        initEvent();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        lin_baidu = (RelativeLayout) findViewById(R.id.lin_baidu_money);//百度钱包
        lin_yinlian = (RelativeLayout) findViewById(R.id.lin_yinlian);//银联
        lin_alipay = (RelativeLayout) findViewById(R.id.lin_alipay);//支付宝
    }

    @Override
    protected void initEvent() {
        lin_alipay.setOnClickListener(this);
        lin_yinlian.setOnClickListener(this);
        lin_baidu.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.lin_alipay://支付宝
                DialogUtils.showDialog(PayActivity.this,R.layout.dialog_me,R.style.diyDialog);
                toAty(AlipayActivity.class);
                break;
            case R.id.lin_baidu_money://百度钱包
                DialogUtils.showDialog(PayActivity.this,R.layout.dialog_me,R.style.diyDialog);
                toAty(PayActivity.class);
                break;
            case R.id.lin_yinlian://银联支付
                DialogUtils.showDialog(PayActivity.this,R.layout.dialog_me,R.style.diyDialog);
                toAty(YinlanActivity.class);
                break;

        }
    }
}
