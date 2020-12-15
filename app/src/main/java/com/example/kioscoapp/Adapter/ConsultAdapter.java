package com.example.kioscoapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kioscoapp.Model.Consult;
import com.example.kioscoapp.Model.TiempoAire;
import com.example.kioscoapp.R;
import com.example.kioscoapp.Services.ServiceConsult;

import java.util.ArrayList;

public class ConsultAdapter extends RecyclerView.Adapter<ConsultAdapter.ConsultAdapterViewHolder> {
    Context context;
    ArrayList<Consult> tiempoAireList;
    OnListener onListener;

    public ConsultAdapter(Context context, ArrayList<Consult> consultArrayList, ConsultAdapter.OnListener onListener) {
        this.context = context;
        this.tiempoAireList = consultArrayList;
        this.onListener = onListener;
    }
    public ConsultAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public ConsultAdapter.ConsultAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_service_consult, parent, false);

        return new ConsultAdapter.ConsultAdapterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ConsultAdapter.ConsultAdapterViewHolder holder, int position) {
        Consult consult = tiempoAireList.get(position);
        holder.textViewTotalAmountTextV.setText(consult.getAmount());
        holder.textViewExpirationDateTextV.setText("Vence: " + consult.getDateExpiration());
        holder.textViewMonthTextV.setText(consult.getDateCreate());
        holder.textViewStatusTextV.setText("Vencido");
        //holder.bind(category,onListener);

    }

    @Override
    public int getItemCount() {
        if(tiempoAireList != null){
            return tiempoAireList.size();
        }
        else return 0;

    }


    public interface OnListener{
        void onItemClick(ServiceConsult service);
    }

    public class ConsultAdapterViewHolder extends RecyclerView.ViewHolder {

        TextView textViewMonthTextV, textViewStatusTextV, textViewExpirationDateTextV,textViewTotalAmountTextV;
       // LinearLayout rcvItem;

        public ConsultAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewMonthTextV = itemView.findViewById(R.id.textViewMonth);
            textViewStatusTextV = itemView.findViewById(R.id.textViewStatus);
            textViewExpirationDateTextV = itemView.findViewById(R.id.textViewExpirationDate);
            textViewTotalAmountTextV = itemView.findViewById(R.id.textViewTotalAmount);
           // textViewReference=itemView.findViewById(R.id.textViewReference);
        }
        public void bind(final ServiceConsult service, final TiempoAire.OnListener listener){
           /* rcvItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(service);
                }
            });*/
        }
    }
}
