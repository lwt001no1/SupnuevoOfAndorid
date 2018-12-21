package com.example.xsl.supnuevoofandroid.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.xsl.supnuevoofandroid.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StockAdapter extends BaseAdapter {

    private ArrayList<HashMap> data;
    private LayoutInflater mInflater;
    private Map data_map;

    public StockAdapter(ArrayList data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_stock, null);
            viewHolder.title = (TextView) convertView.findViewById(R.id.stock_username);
            viewHolder.content = (TextView) convertView.findViewById(R.id.stock_phoneNumber);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (((ArrayList) data.get(position).get("phoneNumber")).size() != 0) {
            viewHolder.title.setText((String) data.get(position).get("name"));
            viewHolder.content.setText((String) ((ArrayList) data.get(position).get("phoneNumber")).get(0));
        }
        return convertView;
    }

    private class ViewHolder {
        private TextView title;
        private TextView content;
    }


}
