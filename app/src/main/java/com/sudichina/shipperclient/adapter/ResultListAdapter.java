package com.sudichina.shipperclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sudichina.shipperclient.R;
import com.sudichina.shipperclient.bean.SourchCity;
import com.sudichina.shipperclient.view.CityPickerActivity;

import java.util.List;

/**
 * author zaaach on 2016/1/26.
 */
public class ResultListAdapter extends BaseAdapter {
    CityPickerActivity cityPickerActivity;
    List<SourchCity> list_sourchcity;

    public ResultListAdapter(CityPickerActivity cityPickerActivity, List<SourchCity> list_sourchcity) {
        this.cityPickerActivity = cityPickerActivity;
        this.list_sourchcity = list_sourchcity;
    }

    public void changeData(List<SourchCity> list){
        if (list_sourchcity == null){
            list_sourchcity = list;
        }else{
            list_sourchcity.clear();
            list_sourchcity.addAll(list);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list_sourchcity == null ? 0 : list_sourchcity.size();
    }

//    @Override
//    public Object getItem(int position) {
//        return list_sourchcity.get(position);
//    }

    @Override
    public SourchCity getItem(int position) {
        return list_sourchcity == null ? null : list_sourchcity.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ResultViewHolder holder;
        if (view == null){
            view = LayoutInflater.from(cityPickerActivity).inflate(R.layout.item_search_result_listview, parent, false);
            holder = new ResultViewHolder();
            holder.name = (TextView) view.findViewById(R.id.tv_item_result_listview_name);
            view.setTag(holder);
        }else{
            holder = (ResultViewHolder) view.getTag();
        }
        holder.name.setText(list_sourchcity.get(position).getName());
        return view;
    }

    public static class ResultViewHolder{
        TextView name;
    }
}
