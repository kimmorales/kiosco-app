package com.example.kioscoapp.Adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kioscoapp.Model.ServicesByCarMoneyCenter;
import com.example.kioscoapp.R;
import com.example.kioscoapp.Utils.Utils;

import org.joda.time.DateTime;

import java.util.ArrayList;

public class CartAdapter extends BaseAdapter {

    private static LayoutInflater inflater = null;
    ArrayList<ServicesByCarMoneyCenter> data;
    private Context context;
    public OnListener onListener;

    public CartAdapter(Context context, ArrayList<ServicesByCarMoneyCenter> data,OnListener onListener) {
        this.data = data;
        this.context = context;
        inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        this.onListener=onListener;
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
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.item_recharge, null);
        TextView txtName = (TextView) convertView.findViewById(R.id.nameRecharge);
        TextView txtId = (TextView) convertView.findViewById(R.id.idRecharge);
        TextView txtPrice = (TextView) convertView.findViewById(R.id.priceRecharge);
        ImageView imageViewDelete=convertView.findViewById(R.id.imageDelete);

        txtName.setText(data.get(position).getServiceName());

        txtPrice.setText(data.get(position).getAmount());

        if(data.get(position).getDateExpiration()!="" && data.get(position).getDateExpiration()!=null){
            DateTime expirationDate = new DateTime(data.get(position).getDateExpiration());
            txtId.setText(data.get(position).getAccountNumber()+" "+ Utils.getMonthOfTheYear(expirationDate.getMonthOfYear()-1));
        }else{
            txtId.setText(data.get(position).getAccountNumber());
        }

        imageViewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onListener.onClickDelete(data.get(position));
            }
        });

        return convertView;
    }

    public interface OnListener{
        void onClickDelete(ServicesByCarMoneyCenter services);
    }
}
