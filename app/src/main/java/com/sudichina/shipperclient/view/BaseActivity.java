package com.sudichina.shipperclient.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.sudichina.shipperclient.utils.StatusBarCompat;
import com.zhy.autolayout.AutoLayoutActivity;

/**
 * 基类Activity，被继承与自动化适配，并且实现对Activity的管理
 */
public abstract class BaseActivity extends AutoLayoutActivity {
    private Intent mIntent;
    private Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        StatusBarCompat.compat(this, 0xff35363a);
    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract void initEvent();

    protected void toAty(Class cls) {
        if (mIntent == null) {
            mIntent = new Intent();
        }
        mIntent.setClass(this, cls);
        startActivity(mIntent);
    }

    protected void toAty(Class cls, Bundle bundle) {
        if (mIntent == null) {
            mIntent = new Intent();
        }
        mIntent.setClass(this, cls);
        if (bundle != null) mIntent.putExtras(bundle);
        startActivity(mIntent);
    }

    protected void toast(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        }
        mToast.setText(msg);
        mToast.show();
    }

}
