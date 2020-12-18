package com.example.kioscoapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kioscoapp.Model.Consult;
import com.example.kioscoapp.R;

import java.util.ArrayList;
import org.joda.time.DateTime;

import com.example.kioscoapp.Services.Local.CountryLocalService;
import com.example.kioscoapp.Utils.Utils;

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

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ConsultAdapter.ConsultAdapterViewHolder holder, int position) {
        Consult consult = tiempoAireList.get(position);
        CountryLocalService localService= new CountryLocalService(context);
        holder.textViewTotalAmountTextV.setText(localService.getCurrency() + consult.getAmount());
        DateTime expirationDate = new DateTime( consult.getDateExpiration());
        DateTime currentDate = new DateTime();
        int isExpiredDate = expirationDate.compareTo(currentDate);
        holder.textViewExpirationDateTextV.setText("Vence: " + expirationDate.getYear() + "/"+
                expirationDate.getMonthOfYear()+ "/" + expirationDate.getDayOfMonth());
        holder.textViewMonthTextV.setText(Utils.getMonthOfTheYear(expirationDate.getMonthOfYear()-1));
        holder.textViewStatusTextV.setText(isExpired(isExpiredDate));
        holder.bind(consult,onListener);

    }
    public String isExpired(int state){
        if(state == 1){
            return "Pendiente";
        }else{
            return "Vencido";
        }
    }

    @Override
    public int getItemCount() {
        if(tiempoAireList != null){
            return tiempoAireList.size();
        }
        else return 0;

    }


    public interface OnListener{
        void onItemClick(Consult service);
    }

    public class ConsultAdapterViewHolder extends RecyclerView.ViewHolder {

        TextView textViewMonthTextV, textViewStatusTextV, textViewExpirationDateTextV,textViewTotalAmountTextV;
        CardView cvServiceConsult;
        Button buttonService;

        public ConsultAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewMonthTextV = itemView.findViewById(R.id.textViewMonth);
            textViewStatusTextV = itemView.findViewById(R.id.textViewStatus);
            textViewExpirationDateTextV = itemView.findViewById(R.id.textViewExpirationDate);
            textViewTotalAmountTextV = itemView.findViewById(R.id.textViewTotalAmount);
            cvServiceConsult=itemView.findViewById(R.id.cvServiceConsult);
            buttonService=itemView.findViewById(R.id.button2);
            // textViewReference=itemView.findViewById(R.id.textViewReference);
            cvServiceConsult = itemView.findViewById(R.id.cvServiceConsult);
        }
        public void bind(final Consult service, final ConsultAdapter.OnListener listener){
            buttonService.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(service);
                }
            });
        }
    }
}
