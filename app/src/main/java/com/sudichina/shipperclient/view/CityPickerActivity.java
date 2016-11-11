package com.sudichina.shipperclient.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.overlay.PoiOverlay;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.core.SuggestionCity;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.sudichina.shipperclient.R;
import com.sudichina.shipperclient.adapter.CityListAdapter;
import com.sudichina.shipperclient.adapter.ResultListAdapter;
import com.sudichina.shipperclient.bean.City;
import com.sudichina.shipperclient.bean.LocateState;
import com.sudichina.shipperclient.bean.SourchCity;
import com.sudichina.shipperclient.customview.SideLetterBar;
import com.sudichina.shipperclient.db.DBManager;
import com.sudichina.shipperclient.utils.StringUtils;
import com.sudichina.shipperclient.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * author sudichina on 2016/10/14.
 */
public class CityPickerActivity extends AppCompatActivity implements PoiSearch.OnPoiSearchListener, View.OnClickListener, Inputtips.InputtipsListener, TextWatcher {
    public static final int REQUEST_CODE_PICK_CITY = 2333;
    public static final String KEY_PICKED_CITY = "picked_city";
    private ListView mListView;//展示所有的城市列表
    private ListView mResultListView;//查询到的值所展示的listview
    private SideLetterBar mLetterBar;//26个字母
    private EditText searchBox;//搜索框
    private ImageView clearBtn;//消除按钮
    private ImageView backBtn;//返回按钮
    private ViewGroup emptyView;
    private CityListAdapter mCityAdapter;//所有城市适配器
    private ResultListAdapter mResultAdapter;//查询到的数据所展示的适配器
    private List<City> mAllCities;//所有的城市
    private DBManager dbManager;
    final List<SourchCity> list_sourchcity = new ArrayList<SourchCity>();//定义集合，接收查询到的值
    private AMapLocationClient mLocationClient;
    private int currentPage = 0;// 当前页面，从0开始计数
    private PoiSearch.Query query;// Poi查询条件类
    private PoiSearch poiSearch;
    private PoiResult poiResult; // poi返回的结果
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);
        initData();
        initView();
        initLocation();
    }
    private void initLocation() {
        mLocationClient = new AMapLocationClient(this);
        AMapLocationClientOption option = new AMapLocationClientOption();
        option.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        option.setOnceLocation(true);
        mLocationClient.setLocationOption(option);
        mLocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation != null) {
                    if (aMapLocation.getErrorCode() == 0) {
                        String city = aMapLocation.getCity();
                        String district = aMapLocation.getDistrict();
                        Log.e("onLocationChanged", "city: " + city);
                        Log.e("onLocationChanged", "district: " + district);
                        String location = StringUtils.extractLocation(city, district);
                        mCityAdapter.updateLocateState(LocateState.SUCCESS, location);
                    } else {
                        //定位失败
                        mCityAdapter.updateLocateState(LocateState.FAILED, null);
                    }
                }
            }
        });
        mLocationClient.startLocation();
    }
    private void initData() {
        dbManager = new DBManager(this);
        dbManager.copyDBFile();
        mAllCities = dbManager.getAllCities();
        mCityAdapter = new CityListAdapter(this, mAllCities);
        mCityAdapter.setOnCityClickListener(new CityListAdapter.OnCityClickListener() {
            @Override
            public void onCityClick(String name) {
                back(name);
            }

            @Override
            public void onLocateClick() {
                Log.e("onLocateClick", "重新定位...");
                mCityAdapter.updateLocateState(LocateState.LOCATING, null);
                mLocationClient.startLocation();
            }
        });
//        mResultAdapter = new ResultListAdapter(this, null);
    }
    private void initView() {
        mListView = (ListView) findViewById(R.id.listview_all_city);
        mListView.setAdapter(mCityAdapter);
        TextView overlay = (TextView) findViewById(R.id.tv_letter_overlay);
        mLetterBar = (SideLetterBar) findViewById(R.id.side_letter_bar);
        mLetterBar.setOverlay(overlay);
        mLetterBar.setOnLetterChangedListener(new SideLetterBar.OnLetterChangedListener() {
            @Override
            public void onLetterChanged(String letter) {
                int position = mCityAdapter.getLetterPosition(letter);
                mListView.setSelection(position);
            }
        });
        searchBox = (EditText) findViewById(R.id.et_search);//搜索框控件监听
        searchBox.addTextChangedListener(this);
        emptyView = (ViewGroup) findViewById(R.id.empty_view);
        mResultListView = (ListView) findViewById(R.id.listview_search_result);
//        mResultListView.setAdapter(mResultAdapter);
        clearBtn = (ImageView) findViewById(R.id.iv_search_clear);
        backBtn = (ImageView) findViewById(R.id.back);
        clearBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);
    }
    /**
     * 搜索匡查询城市
     *
     * @param edit_tv
     */
    private void doSearchQuery(String edit_tv) {
        currentPage = 0;
        query = new PoiSearch.Query("", "", edit_tv);// 第一个参数表示搜索字符串，第二个参数表示poi搜索类型，第三个参数表示poi搜索区域（空字符串代表全国）
        query.setPageSize(10);// 设置每页最多返回多少条poiitem
        query.setPageNum(currentPage);// 设置查第一页
        poiSearch = new PoiSearch(this, query);
        poiSearch.setOnPoiSearchListener(this);
        poiSearch.searchPOIAsyn();
    }
    private void back(String city) {
        ToastUtil.show(this, "点击的城市：" + city);
        Intent data = new Intent();
        data.putExtra("city", city);
        setResult(RESULT_OK, data);
        finish();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_search_clear:
                searchBox.setText("");
                clearBtn.setVisibility(View.GONE);
                emptyView.setVisibility(View.GONE);
                mResultListView.setVisibility(View.GONE);
                break;
            case R.id.back:
                finish();
                break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stopLocation();
    }
    //搜索匡查询到的结果
    @Override
    public void onGetInputtips(List<Tip> tipList, int rCode) {
        if (rCode == 1000) {// 正确返回
            SourchCity sourchCity=null;
            for (int i = 0; i < tipList.size(); i++) {
                String city_name = tipList.get(i).getName();
                String city_code = tipList.get(i).getAdcode();
                sourchCity = new SourchCity(city_name, city_code);
                list_sourchcity.add(sourchCity);
            }

            if (list_sourchcity != null)
                mResultListView.setVisibility(View.VISIBLE);
            mResultAdapter = new ResultListAdapter(this, list_sourchcity);
            mResultListView.setAdapter(mResultAdapter);
            mResultAdapter.notifyDataSetChanged();
            mResultListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    back(mResultAdapter.getItem(position).getName());
                    mResultListView.setVisibility(View.GONE);
                }
            });
        } else {
            mResultListView.setVisibility(View.GONE);
        }
    }
    @Override
    public void onPoiSearched(PoiResult result, int rCode) {
        if (rCode == 1000) {
            if (result != null && result.getQuery() != null) {// 搜索poi的结果
                if (result.getQuery().equals(query)) {// 是否是同一条
                    poiResult = result;
                    // 取得搜索到的poiitems有多少页
                    List<PoiItem> poiItems = poiResult.getPois();// 取得第一页的poiitem数据，页数从数字0开始
                    List<SuggestionCity> suggestionCities = poiResult
                            .getSearchSuggestionCitys();// 当搜索不到poiitem数据时，会返回含有搜索关键字的城市信息
                    if (suggestionCities != null
                            && suggestionCities.size() > 0) {
                    } else {
                        ToastUtil.show(CityPickerActivity.this,
                                "没有找到");
                    }
                }
            } else {
                ToastUtil.show(CityPickerActivity.this,
                        "没有找到");
            }
        } else {
            ToastUtil.showerror(this, rCode);
        }
    }
    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {
    }
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String edit_tv = searchBox.getText().toString().trim();
//        doSearchQuery(edit_tv);//进行检索
        String newText = s.toString().trim();
        InputtipsQuery inputquery = new InputtipsQuery(newText, edit_tv);
        Inputtips inputTips = new Inputtips(CityPickerActivity.this, inputquery);
        inputTips.setInputtipsListener(this);
        inputquery.setCityLimit(true);
        inputTips.requestInputtipsAsyn();//发送请求
    }
    @Override
    public void afterTextChanged(Editable s) {

    }
}
