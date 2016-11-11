package com.sudichina.shipperclient.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.overlay.PoiOverlay;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.core.SuggestionCity;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.google.gson.Gson;
import com.sudichina.shipperclient.R;
import com.sudichina.shipperclient.biz.IURL;
import com.sudichina.shipperclient.customview.MyLetterSortView;
import com.sudichina.shipperclient.utils.OkHttpUtils;
import com.sudichina.shipperclient.utils.SPUtils;
import com.sudichina.shipperclient.utils.ToastUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by SudiChina-105 on 2016/9/2.
 * 城市选择页面，点击搜索框搜索，展示城市列表，定位当前城市，
 */
public class SortCityActivity extends BaseActivity implements PoiSearch.OnPoiSearchListener, View.OnClickListener, TextWatcher, Inputtips.InputtipsListener {
    private Button btn_city;//展示当前城市的控件
    private String city;//城市,接收定位的城市
    private MyLetterSortView letterSortView;//侧边的字母顺序
    private GridView sort_hotCity;//热门城市
    private ListView sort_city_list;//城市列表
    private AutoCompleteTextView et_city_serch;//搜索框
    private ArrayAdapter hotCityadapter;
    private ArrayAdapter cityadapter;
    private String hot_citys;//热门城市
    private String city_Names;//所有城市
//    List<HotCity> list_hotcitydata;//热门城市数据集合
//    List<CityBean> list_citybean;//城市列表集合
//    List<CityData> list_citydata = new ArrayList<>();
    private List<String> list_citys = new ArrayList<String>();
    private List<String> list_hot = new ArrayList<String>();
    private HashMap<String, Integer> alphaIndexer;// 存放存在的汉语拼音首字母和与之对应的列表位置
    private String[] sections;// 存放存在的汉语拼音首字母
    private Handler handler;
    private TextView overlayThread; // 显示首字母对话框
    private boolean isNeedFresh;
    private MapView mapview;
    private AMap mAMap;
    private PoiResult poiResult; // poi返回的结果
    private int currentPage = 0;// 当前页面，从0开始计数
    private PoiSearch.Query query;// Poi查询条件类
    private PoiSearch poiSearch;
    private ImageView image_back;//返回按钮

    public static String[] b = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z", "#"};
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    /**
     * 根据拼音来排列ListView里面的数据类
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sortcity);
        mapview = (MapView) findViewById(R.id.map);
        mapview.onCreate(savedInstanceState);
        getAllCty();//请求网络，获取所有城市列表
        initData();//加载数据
        initView();//获取控件
        initEvent();//设置监听
    }
    //监听的方法
    @Override
    protected void initData() {
        city = (String) SPUtils.get(SortCityActivity.this, "city", "");
    }
    //联网请求服务器，获取城市信息
    private void getAllCty() {
//        OkHttpUtils.get(IURL.GET_CITY_LIST, new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                System.out.println("请求失败");
//            }
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                System.out.println("请求成功");
//                String json = response.body().string();
//                System.out.println(json);//打印获取的json文件
//                Gson gson = new Gson();//解析
//                DataCitys dataCitys = gson.fromJson(json, DataCitys.class);
//                CityItems cityItems = dataCitys.getData();//热门城市和所有城市集合
//                list_hotcitydata = cityItems.getHotZone();//得到热门城市的数据
//                for (int b = 0; b < list_hotcitydata.size(); b++) {
//                    String hotcityname = list_hotcitydata.get(b).getName();
//                    list_hot.add(hotcityname);
//                }
//                System.out.println("热门城市" + list_hot);
//                list_citydata = cityItems.getItemMap();
//                for (int u = 0; u < list_citydata.size(); u++) {
//                    String firstAleph = list_citydata.get(u).getFirstAleph();
//                    list_citybean = list_citydata.get(u).getListZone();
//                    list_citys.add(firstAleph);
//                    for (int a = 0; a < list_citybean.size(); a++) {
//                        String cityName = list_citybean.get(a).getName();
//                        list_citys.add(cityName);
////                        System.out.println("所有城市" + list_citys);
//                    }
//                }
//            }
//        });
    }
    @Override
    protected void initView() {
//        sort_city_list = (ListView) findViewById(R.id.sort_city_listview);//listview展示普通城市
//        image_back = (ImageView) findViewById(R.id.activ_sort_iv_back);
//        sort_city_list.setDividerHeight(0);
//        sort_city_list.setAdapter(null);
//        sort_city_list.addHeaderView(initHeadView());//给listview添加头部view
////      cityadapter = new MyAllCityAdapter(this,list_citys);
//        cityadapter = new ArrayAdapter(this, R.layout.sortcity_lv_item, R.id.sort_city_tv, list_citys);
//        sort_city_list.setAdapter(cityadapter);//将所有城市展示到适配器上
//        sort_city_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {//listview中item项的点击监听
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                city_Names = list_citybean.get(position).getName();//获取到点击的数据
//                System.out.println("wwwwwwwwwwwwwwwww" + city_Names);
//                long city_id = list_citybean.get(position).getId();
//                System.out.println("qqqqqqqqqqqqqqqqq" + id);
//                SPUtils.put(SortCityActivity.this, "cityname", city_Names);
//                SPUtils.put(SortCityActivity.this, "cityId", city_id);
////                HomeFragment fragment = new HomeFragment();
////                Bundle bundle = new Bundle();
////                bundle.putString("cityname", city_Names);
////                bundle.putLong("cityid", city_id);
////                fragment.setArguments(bundle);
////                        finish();//销毁页面
//                startActivity(new Intent(SortCityActivity.this, MainActivity.class));
//            }
//        });
        et_city_serch = (AutoCompleteTextView) findViewById(R.id.sort_city_serch);//搜索框搜索
        letterSortView = (MyLetterSortView) findViewById(R.id.mysidebar);//字母排序
        et_city_serch.addTextChangedListener(this);
        et_city_serch.setDropDownHeight(350);//下拉框的高度
        image_back.setOnClickListener(this);
    }
    @Override
    protected void initEvent() {
        btn_city.setText(city);
    }
    //listview中嵌套gridview，展示热门城市的数据
    private View initHeadView() {
        View headView = getLayoutInflater().inflate(R.layout.sort_city_headview, null);
        btn_city = (Button) headView.findViewById(R.id.btn_city_name);//当前定位的城市
        sort_hotCity = (GridView) headView.findViewById(R.id.sort_city_hotcity_gridview);//gridview展示热门城市
//        hotCityadapter=new MyCityGridAdapter(this,list_hot);
        hotCityadapter = new ArrayAdapter(this, R.layout.sortcity_hotcity_item, R.id.sort_city_hotcity_item_tv, list_hot);
        sort_hotCity.setAdapter(hotCityadapter);
//        sort_hotCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                hot_citys = list_hotcitydata.get(position).getName();
//                System.out.println("hhhhhhhhhhhhh" + hot_citys);
//                long hot_coty_id = list_hotcitydata.get(position).getId();
//                System.out.println("dddddddddddddd" + hot_coty_id);
//
//                SPUtils.put(SortCityActivity.this, "cityname", hot_citys);
//                SPUtils.put(SortCityActivity.this, "cityId", hot_coty_id);
////                HomeFragment fragment=new HomeFragment();
////                Bundle bundle=new Bundle();
////                bundle.putString("cityname",hot_citys);
////                bundle.putLong("cityid",hot_coty_id);
////                fragment.setArguments(bundle);
////                finish();//销毁页面
//                startActivity(new Intent(SortCityActivity.this, MainActivity.class));
//            }
//        });
        return headView;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activ_sort_iv_back://点击返回按钮，销毁页面
                finish();
                break;
        }
    }
    //对输入的内容进行检索
    private void doSearchQuery(String et_tv) {
        currentPage = 0;
        query = new PoiSearch.Query("", "", et_tv);// 第一个参数表示搜索字符串，第二个参数表示poi搜索类型，第三个参数表示poi搜索区域（空字符串代表全国）
        query.setPageSize(10);// 设置每页最多返回多少条poiitem
        query.setPageNum(currentPage);// 设置查第一页
        poiSearch = new PoiSearch(this, query);
        poiSearch.setOnPoiSearchListener(this);
        poiSearch.searchPOIAsyn();
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
                    if (poiItems != null && poiItems.size() > 0) {
                        mAMap.clear();// 清理之前的图标
                        PoiOverlay poiOverlay = new PoiOverlay(mAMap, poiItems);
                        poiOverlay.removeFromMap();
                        poiOverlay.addToMap();
                        poiOverlay.zoomToSpan();
                    } else if (suggestionCities != null
                            && suggestionCities.size() > 0) {
                    } else {
                        ToastUtil.show(SortCityActivity.this,
                                "没有找到");
                    }
                }
            } else {
                ToastUtil.show(SortCityActivity.this,
                        "没有找到");
            }
        } else {
            ToastUtil.showerror(this, rCode);
        }
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {
    }

    //请求高德服务器
    public void onGetInputtips(final List<Tip> tipList, int rCode) {
        if (rCode == 1000) {// 正确返回
            final List<String> listString = new ArrayList<String>();
            for (int i = 0; i < tipList.size(); i++) {
                listString.add(tipList.get(i).getName());
            }
            ArrayAdapter aAdapter = new ArrayAdapter(this, R.layout.item_input_hint_layout, R.id.online_user_list_item_textview, listString);
            et_city_serch.setAdapter(aAdapter);//为输入框调价适配器
            et_city_serch.setAdapter(aAdapter);
            aAdapter.notifyDataSetChanged();
            et_city_serch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("我被点击了");
                }
            });
            et_city_serch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    long serchCity_code = Long.valueOf(tipList.get(position).getAdcode());
                    String serch_name = tipList.get(position).getName();
                    System.out.println("code==========" + serchCity_code);
                    System.out.println("serch_name==========" + serch_name);
                    SPUtils.put(SortCityActivity.this, "cityname", serch_name);
                    SPUtils.put(SortCityActivity.this, "cityId", serchCity_code);
//                    Intent intent=new Intent();
//                    intent.putExtra("cityname",city_Names);
//                    intent.putExtra("cityid",serchCity_code);
//                    setResult(10,intent);
//                    HomeFragment fragment=new HomeFragment();
//                    Bundle bundle=new Bundle();
//                    bundle.putString("cityname",serch_name);
//                    bundle.putLong("cityid",serchCity_code);
//                    fragment.setArguments(bundle);
                    startActivity(new Intent(SortCityActivity.this, MainActivity.class));
//                    finish();//销毁页面
                }
            });
        } else {
            ToastUtil.showerror(this, rCode);
        }
    }

    public static final String CODE = "search_city_code";
    public static final String CITY = "search_city_";

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String et_tv = et_city_serch.getText().toString().trim();//获取输入框输入的内容
        doSearchQuery(et_tv);//进行检索
        String newText = s.toString().trim();
        InputtipsQuery inputquery = new InputtipsQuery(newText, et_tv);
        Inputtips inputTips = new Inputtips(SortCityActivity.this, inputquery);
        inputTips.setInputtipsListener(this);
        inputTips.requestInputtipsAsyn();//发送请求
    }

    @Override
    public void afterTextChanged(Editable s) {
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onResume() {
        super.onResume();
        mapview.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onPause() {
        super.onPause();
        mapview.onPause();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapview.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapview.onDestroy();
    }
}
