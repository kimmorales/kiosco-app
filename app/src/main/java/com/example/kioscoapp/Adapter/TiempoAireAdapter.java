package com.example.kioscoapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kioscoapp.Model.TiempoAire;
import com.example.kioscoapp.R;
import com.example.kioscoapp.Services.ServiceConsult;

import java.util.ArrayList;

public class TiempoAireAdapter  extends RecyclerView.Adapter<TiempoAireAdapter.TiempoAireViewHolder>  {
    Context context;
    ArrayList<TiempoAire> tiempoAireList;
    OnListener onListener;
    Boolean openPayment;

    public TiempoAireAdapter(Boolean openPayment, Context context, ArrayList<TiempoAire> tiempoAireArrayList, TiempoAireAdapter.OnListener onListener) {
        this.context = context;
        this.tiempoAireList = tiempoAireArrayList;
        this.onListener = onListener;
        this.openPayment = openPayment;
    }
    public TiempoAireAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public TiempoAireAdapter.TiempoAireViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_tiempo_aire, parent, false);

        return new TiempoAireAdapter.TiempoAireViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TiempoAireAdapter.TiempoAireViewHolder holder, int position) {
        TiempoAire category = tiempoAireList.get(position);
        holder.textViewReference.setText("Monto de referencia: " + category.getAmount());
        holder.bind(category,onListener);

    }

    @Override
    public int getItemCount() {
        return tiempoAireList.size();
    }

    public class TiempoAireViewHolder extends RecyclerView.ViewHolder {

        TextView textViewReference;
        LinearLayout rcvItem;
        Button buttonAddCar;


        public TiempoAireViewHolder(@NonNull View itemView) {
            super(itemView);
            rcvItem = itemView.findViewById(R.id.rcvItemAire);
            textViewReference=itemView.findViewById(R.id.textViewReference);
            buttonAddCar=itemView.findViewById(R.id.addToCart);
            View viewTypeAmountV = itemView.findViewById(R.id.viewTypeAmount);
           if(!openPayment){
                viewTypeAmountV.setVisibility(View.GONE);
           }
        }
        public void bind(final TiempoAire service,final OnListener listener){
            buttonAddCar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(service);
                }
            });
        }
    }
    public interface OnListener{
        void onItemClick(TiempoAire service);
    }
}
