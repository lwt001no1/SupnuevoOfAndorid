package com.example.xsl.supnuevoofandroid.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.xsl.supnuevoofandroid.R;

import java.util.List;
import java.util.Map;

public class SaleAdapter extends BaseAdapter {

    private List<Map> data;
    private Context mContext;
    private LayoutInflater mInflater;
    private Map item_data;

    public SaleAdapter(List data, Context mContext) {
        this.data = data;
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        //返回item的个数
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        //返回每一个item对象
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_sale, null);
            holder.title = (TextView) convertView.findViewById(R.id.sale_title);
            holder.content = (TextView) convertView.findViewById(R.id.sale_listitem);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        item_data = data.get(position);
        holder.content.setText((String) item_data.get("codigo"));
        return convertView;
    }

    private class ViewHolder {
        private TextView title;
        private TextView content;
    }
}
