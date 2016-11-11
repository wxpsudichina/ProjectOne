package com.sudichina.shipperclient.view;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.sudichina.shipperclient.R;

/**
 * Created by SudiChina-105 on 2016/10/8.
 * 常用地址页面,收货地址管理页面
 */

public class AddressActivity extends  BaseActivity implements View.OnClickListener{
    private RelativeLayout add_ral;//新增收获地址

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        initData();
        initView();
        initEvent();
    }

    @Override
    protected void initData() {

    }
    @Override
    protected void initView() {
        add_ral= (RelativeLayout) findViewById(R.id.add_newaddress);
    }

    @Override
    protected void initEvent() {
       add_ral.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.add_newaddress:
                toAty(Add_NewAddressActivity.class);
                break;
        }
    }
}
