package com.sudichina.shipperclient.view;

import android.os.Bundle;

import com.sudichina.shipperclient.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        getSupportFragmentManager().beginTransaction().replace(R.id.main_frag,new HomeFragment()).commit();//刚进入主页面，默认加载

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initEvent() {

    }
}
