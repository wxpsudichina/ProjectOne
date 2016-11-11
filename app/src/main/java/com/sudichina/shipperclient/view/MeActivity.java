package com.sudichina.shipperclient.view;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.sudichina.shipperclient.R;
import com.sudichina.shipperclient.utils.CommonUtils;
import com.sudichina.shipperclient.utils.DialogUtils;
import com.sudichina.shipperclient.utils.SPUtils;

public class MeActivity extends BaseActivity implements View.OnClickListener {
    private DrawerLayout drawer_layout;
    private FrameLayout content_frame;
    private ListView left_drawer;
    //    private int[] icons = {
//            R.mipmap.bank_card, R.mipmap.order_form,
//            R.mipmap.address, R.mipmap.invoice,
//            R.mipmap.share, R.mipmap.message,
//            R.mipmap.help, R.mipmap.setup, R.mipmap.about
//    };
    private int[] icons = {
            R.mipmap.bank_card, R.mipmap.order_form,
            R.mipmap.address, R.mipmap.invoice,
            R.mipmap.share, R.mipmap.message,
            R.mipmap.help, R.mipmap.setup, R.mipmap.about
    };
    //    private int[] icons_selected = {
//            R.mipmap.bank_card_selected, R.mipmap.order_form_selected,
//            R.mipmap.address_selected, R.mipmap.invoice_selected,
//            R.mipmap.share_selected, R.mipmap.message_selected,
//            R.mipmap.help_selected, R.mipmap.setup_selected, R.mipmap.about_selected
//    };
    private int[] icons_selected = {
            R.mipmap.bank_card_selected, R.mipmap.order_form_selected,
            R.mipmap.address_selected, R.mipmap.invoice_selected,
            R.mipmap.share_selected, R.mipmap.message_selected,
            R.mipmap.help_selected, R.mipmap.setup_selected, R.mipmap.about_selected
    };
    //    private String[] text = {
//            "杜师傅",
//            "支付方式", "我的订单", "常用地址",
//            "发票", "邀请奖励", "信息中心",
//            "帮助", "设置", "关于"
//    };
    private String[] text = {
            "支付方式", "我的订单", "常用地址",
            "发票", "邀请奖励", "信息中心",
            "帮助", "设置", "关于"
    };
    private BaseAdapter mAdapter;
    private LayoutInflater mInflater;
    public String city_name;//定义全局变量
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);
        getSupportFragmentManager().beginTransaction().add(R.id.content_frame, new HomeFragment(), "home").commit();
        initData();
        initView();
        initEvent();
        left_drawer.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        mInflater = getLayoutInflater();

        mAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return icons.length;
            }

            @Override
            public Object getItem(int i) {
                return icons[i];
            }

            @Override
            public long getItemId(int i) {
                return i;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                if (view == null) {
                    view = mInflater.inflate(R.layout.item_drawer, null);
                }
                ImageView iv_avatar = (ImageView) view.findViewById(R.id.iv_icon);
                iv_avatar.setImageResource(icons[i]);
                TextView tv_text = (TextView) view.findViewById(R.id.tv_text);
                tv_text.setText(text[i]);
                return view;
            }
        };
    }

    @Override
    protected void initView() {
        content_frame = (FrameLayout) findViewById(R.id.content_frame);
        left_drawer = (ListView) findViewById(R.id.left_drawer);
        View view_head = getLayoutInflater().inflate(R.layout.header, null);
        left_drawer.addHeaderView(view_head);
        ImageView iv_head = (ImageView) view_head.findViewById(R.id.iv_head);//侧滑菜单中的用户头像
        iv_head.setOnClickListener(this);//点击头像，切换至详细资料页面
        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer_layout.setScrimColor(0x7f35363a);
        drawer_layout.setScrimColor(Color.TRANSPARENT);
//        drawer_layout.setDrawerElevation(100);
        ColorDrawable drawable = new ColorDrawable(0x44ff0000);
        drawable.setBounds(0, 0, 10, 400);
        drawer_layout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
//        drawer_layout.setDrawerElevation(50);
//        drawer_layout.setFitsSystemWindows(false);
        drawer_layout.setStatusBarBackgroundColor(0x44ff0000);
    }
    @Override
    protected void initEvent() {
        left_drawer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) return;
                resetItemStyle();
                ImageView iv = (ImageView) view.findViewById(R.id.iv_icon);
                TextView tv = (TextView) view.findViewById(R.id.tv_text);
                iv.setImageResource(icons_selected[i - 1]);
                tv.setTextColor(0xff477acc);
                view.setBackgroundColor(0xff3a3c41);
                switch (i) {
                    case 1://支付页面
//                        DialogUtils.showDialog(MeActivity.this, R.layout.dialog_me, R.style.diyDialog);
                        toAty(PayActivity.class);
                        break;
                    case 2://我的订单
//                        DialogUtils.showDialog(MeActivity.this, R.layout.dialog_me, R.style.diyDialog);
                        toAty(OrderActivity.class);
                        break;
                    case 3://常用地址
//                        DialogUtils.showDialog(MeActivity.this, R.layout.dialog_me, R.style.diyDialog);
                        toAty(AddressActivity.class);
                        break;
                    case 4://发票
//                        DialogUtils.showDialog(MeActivity.this, R.layout.dialog_me, R.style.diyDialog);
                        toAty(InvoiceActivity.class);
                        break;
                    case 5://邀请奖励
//                        DialogUtils.showDialog(MeActivity.this, R.layout.dialog_me, R.style.diyDialog);
                        toAty(ShareActivity.class);
                        break;
                    case 6://信息中心
//                        DialogUtils.showDialog(MeActivity.this, R.layout.dialog_me, R.style.diyDialog);
                        toAty(MessageActivity.class);
                        break;
                    case 7://帮助
//                        DialogUtils.showDialog(MeActivity.this, R.layout.dialog_me, R.style.diyDialog);
                        toAty(HelpActivity.class);
                        break;
                    case 8://设置
//                        DialogUtils.showDialog(MeActivity.this, R.layout.dialog_me, R.style.diyDialog);
                        toAty(SetUpActivity.class);
                        break;
                    case 9://关于
//                        DialogUtils.showDialog(MeActivity.this, R.layout.dialog_me, R.style.diyDialog);
                        toAty(AboutActivity.class);
                        break;
                    default:
//                        fragment = new PaymentFragment();
                        break;
                }
//                FragmentManager fragmentManager = getSupportFragmentManager();
//                fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
                drawer_layout.closeDrawer(left_drawer);
            }
        });
    }
    private void resetItemStyle() {
        int count = left_drawer.getChildCount() - 1;
        for (int i = 0; i < count; i++) {
            View v = left_drawer.getChildAt(i + 1);
            ImageView iv = (ImageView) v.findViewById(R.id.iv_icon);
            TextView tv = (TextView) v.findViewById(R.id.tv_text);
            iv.setImageResource(icons[i]);
            tv.setTextColor(0xffffffff);
            v.setBackgroundColor(0xff35363a);
        }
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_head://点击侧滑菜单中的头像ame);//打印传递过来的值，这里指的是城市名
//                SPUtils.put(MeActivity.this,"city_name",ci
//                DialogUtils.showDialog(MeActivity.this, R.layout.dialog_me, R.style.diyDialog);
                toAty(OwnActivity.class);
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case RESULT_OK://拿到值
                Bundle bundle = data.getExtras();
                city_name = bundle.getString("city");
                System.out.println("我走到这里了" + city_name);
//                getSupportFragmentManager().beginTransaction().add(R.id.content_frame, new HomeFragment(), "home").commit();
                switch (requestCode){
                    case 100://开始地址
                        HomeFragment.et_start.setText(city_name);
                        break;
                    case 200://结束地址
                        HomeFragment.et_stop.setText(city_name);
                        break;
                    case 001://预约开始地址
                        HomeFragment.et_yue_start.setText(city_name);
                        break;
                    case 002://预约结束地址
                        HomeFragment.et_yue_stop.setText(city_name);
                        break;
                }
                break;
        }
    }
}
