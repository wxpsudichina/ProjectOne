package com.sudichina.shipperclient.view;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by mike on 2016/8/10.
 */
public abstract class BaseFragment extends Fragment {

    private Intent mIntent;

    protected abstract void initData();

    protected abstract void initView(View view);

    protected abstract void initEvent();

    protected void toAty(Class cls) {
        if (mIntent == null) {
            mIntent = new Intent();
        }
        mIntent.setClass(getActivity(), cls);
        startActivity(mIntent);
    }
}
