package com.sudichina.shipperclient.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sudichina.shipperclient.R;
/**
 * Created by SudiChina-105 on 2016/9/13.
 * 添加新的地址,增加新的地址，新的收货人
 */
public class Add_NewAddressActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_newaddress);
    }
    @Override
    protected void initData() {
        getperson();//获取手机系统联系人
    }
    /**
     * 通过contentprivder查询手机系统中的联系人
     */
    private void getperson() {
    }
    @Override
    protected void initView() {
    }
    @Override
    protected void initEvent() {
    }
}
