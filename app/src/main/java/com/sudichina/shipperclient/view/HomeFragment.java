package com.sudichina.shipperclient.view;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.MyLocationStyle;
import com.andexert.calendarlistview.library.DatePickerController;
import com.andexert.calendarlistview.library.DayPickerView;
import com.andexert.calendarlistview.library.SimpleMonthAdapter;
import com.sudichina.shipperclient.R;
import com.sudichina.shipperclient.utils.DialogUtils;
import com.sudichina.shipperclient.utils.SPUtils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by SudiChina-105 on 2016/9/9.
 * 首页，加载地图
 */
public class HomeFragment extends BaseFragment implements LocationSource, AMapLocationListener, View.OnClickListener {
    private AMap aMap;
    private MapView mapView;
    private LocationSource.OnLocationChangedListener mListener;
    private AMapLocationClient mlocationClient;
    private AMapLocationClientOption mLocationOption;
    public static EditText et_start;//开始地址
    public static EditText et_stop;//结束地址
    public static EditText et_yue_start;//立即预约开始地址
    public static EditText et_yue_stop;//立即预约结束地址
    private ImageView image_head;
    private ImageView image_yue;//预约
    private Dialog dialog;
    private LinearLayout lin_time;
    private Button btn_fa;//确定，去比较发货
    private Button btn_yue_ok;//预约界面的确定按钮
    private TextView tv_time;//预约时间
    private DayPickerView dayPickerView;
    DatePickerController mController;
    SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
    ParsePosition pos = new ParsePosition(8);
    private String time1, time2;
    String city_name;
    private boolean et_start_flag = true;
    private boolean et_stop_flag = true;
    private boolean et_yue_start_flag = true;
    private boolean et_yue_stop_flag = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        mapView = (MapView) view.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);// 此方法必须重写
        initMap();//初始化地图
        setCityData();
        initData();
        initView(view);
        initEvent();
        return view;
    }

    public void setCityData() {
        city_name = (String) SPUtils.get(getActivity(), "city_name", "");
        if (city_name != null)
            System.out.println("城市列表选择的城市" + city_name);
    }

    //初始化地图
    private void initMap() {
        if (aMap == null) {
            aMap = mapView.getMap();
            aMap.setLocationSource(this);
            aMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
            aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
            MyLocationStyle myLocationStyle = new MyLocationStyle();
            myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.mipmap.iv_home_fragment_maplog));
            aMap.setMyLocationStyle(myLocationStyle);
            // 自定义定位图标
            /**
             * 设置定位的类型为定位模式 ，可以由定位、跟随或地图根据面向方向旋转几种
             * aMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);设置定位的类型为定位模式
             * aMap.setMyLocationType(AMap.LOCATION_TYPE_MAP_FOLLOW);设置定位的类型为 跟随模式
             * aMap.setMyLocationType(AMap.LOCATION_TYPE_MAP_ROTATE);设置定位的类型为根据地图面向方向旋转
             */
            aMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);//旋转模式
        }
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {//定位监听
        if (mListener != null && aMapLocation != null) {
            if (aMapLocation != null
                    && aMapLocation.getErrorCode() == 0) {
                mListener.onLocationChanged(aMapLocation);// 显示系统小蓝点
                double latitude = aMapLocation.getLatitude();//获取纬度
                double longitude = aMapLocation.getLongitude();//获取经度
                String city = aMapLocation.getAddress();//城市
                et_start.setText(city);//将定位的地址赋给开始地址
                System.out.println("我的位置" + city);
                System.out.println("经度" + longitude + "纬度" + latitude);
            } else {
                String errText = "定位失败," + aMapLocation.getErrorCode() + ": " + aMapLocation.getErrorInfo();
                Log.e("AmapErr", errText);
            }
        }
    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {//激活定位
        mListener = onLocationChangedListener;
        if (mlocationClient == null) {
            mlocationClient = new AMapLocationClient(getActivity());
            mLocationOption = new AMapLocationClientOption();
            mLocationOption.setInterval(1000);
            mLocationOption.setOnceLocation(true);
            //设置定位监听
            mlocationClient.setLocationListener(this);
            //设置为高精度定位模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            mlocationClient.startLocation();//启动定位
        }
    }

    @Override
    public void deactivate() {//取消定位
        mListener = null;
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
    }

    @Override
    public void onClick(View view) {//控件监听
        switch (view.getId()) {
            case R.id.iv_home_fragment_own:
                break;
            case R.id.iv_home_fragment_yue://立即预约
                showBottomDialog();
                break;
            case R.id.btn_home_fragment_yue://预约界面的确定按钮,点击跳转到预约新页面，
                break;
            case R.id.et_home_fragment_yue_start://预约界面的开始地址
                getActivity().startActivityForResult(new Intent(getActivity(), CityPickerActivity.class), 001);
                break;
            case R.id.et_home_fragment_yue_stop://预约界面的结束地址
                getActivity().startActivityForResult(new Intent(getActivity(), CityPickerActivity.class), 002);
                break;
            case R.id.home_fragment_start://开始地址
                getActivity().startActivityForResult(new Intent(getActivity(), CityPickerActivity.class), 100);
                break;
            case R.id.home_fragment_stop://结束地址
                getActivity().startActivityForResult(new Intent(getActivity(), CityPickerActivity.class), 200);
                break;
            case R.id.btn_fahuo://确定去比价发货
                break;
            case R.id.lin_yue_time://确定去比价发货
                showDatePacker();//展示日历控件
                break;
        }
    }

    private void showDatePacker() {
        dialog = new Dialog(getActivity(), R.style.diyDialog);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.pick_view, null);
        dayPickerView = (DayPickerView) view.findViewById(R.id.pickerView);
        mController = new DatePickerController() {
            @Override
            public int getMaxYear() {
                return 2050;
            }

            @Override
            public void onDayOfMonthSelected(int year, int month, int day) {
                Log.e("Day Selected", day + " / " + month + " / " + year);
            }

            @Override
            public void onDateRangeSelected(SimpleMonthAdapter.SelectedDays<SimpleMonthAdapter.CalendarDay> selectedDays) {
                Date firsttime = selectedDays.getFirst().getDate();
                Date lasttie = selectedDays.getLast().getDate();
                time1 = format.format(firsttime);
                time2 = format.format(lasttie);
                format.format(lasttie);
                System.out.println("所选日期" + time1 + time2);
                tv_time.setText(time1 + "    -    " + time2);
                dialog.dismiss();
            }
        };
        dayPickerView.setController(mController);
        dialog.setContentView(view);
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        lp.width = outMetrics.widthPixels;
        lp.height = outMetrics.heightPixels;
        dialog.getWindow().setAttributes(lp);
        dialog.show();
    }

    //点击预约，从底部弹出预约的选项
    private void showBottomDialog() {
        View view_dialog = getActivity().getLayoutInflater().inflate(R.layout.homefragment_bottomdialog, null);
        dialog = new Dialog(getActivity(), R.style.diyDialog);
        lin_time = (LinearLayout) view_dialog.findViewById(R.id.lin_yue_time);
        tv_time = (TextView) view_dialog.findViewById(R.id.tv_home_fragment_yue_time);//预约时间
        et_yue_start = (EditText) view_dialog.findViewById(R.id.et_home_fragment_yue_start);
        et_yue_stop = (EditText) view_dialog.findViewById(R.id.et_home_fragment_yue_stop);
        btn_yue_ok = (Button) view_dialog.findViewById(R.id.btn_home_fragment_yue);
        btn_yue_ok.setOnClickListener(this);//预约页面的确定按钮
        et_yue_start.setOnClickListener(this);//预约开始地址
        et_yue_stop.setOnClickListener(this);//预约结束地址
        lin_time.setOnClickListener(this);
        dialog.setContentView(view_dialog, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        Window window = dialog.getWindow();
        // 设置显示动画
        window.setWindowAnimations(R.style.main_menu_animstyle);
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        wl.y = getActivity().getWindowManager().getDefaultDisplay().getHeight();
        // 以下这两句是为了保证按钮可以水平满屏
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        // 设置显示位置
        dialog.onWindowAttributesChanged(wl);
        // 设置点击外围解散
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initView(View view) {
        et_start = (EditText) view.findViewById(R.id.home_fragment_start);
        et_stop = (EditText) view.findViewById(R.id.home_fragment_stop);
        image_head = (ImageView) view.findViewById(R.id.iv_home_fragment_own);
        image_yue = (ImageView) view.findViewById(R.id.iv_home_fragment_yue);
        btn_fa = (Button) view.findViewById(R.id.btn_fahuo);
        image_head.setOnClickListener(this);//侧滑菜单
        image_yue.setOnClickListener(this);//立即预约
        et_start.setOnClickListener(this);//开始地址
        et_stop.setOnClickListener(this);//结束地址
        btn_fa.setOnClickListener(this);
    }

    protected void initEvent() {
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
        deactivate();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        if (null != mlocationClient) {
            mlocationClient.onDestroy();
        }
    }
}