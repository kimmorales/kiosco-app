package com.example.kioscoapp;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CartAdapter extends BaseAdapter {

    private static LayoutInflater inflater = null;
    String[][] data;
    private Context context;

    public CartAdapter(Context context, String[][] data) {
        this.data = data;
        this.context = context;
        inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int position) {
        return data[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.item_recharge, null);
        TextView txtName = (TextView) convertView.findViewById(R.id.name);
        TextView txtId = (TextView) convertView.findViewById(R.id.id);

        txtName.setText(data[position][0]);
        txtId.setText(data[position][1]);
        return convertView;
    }
}
