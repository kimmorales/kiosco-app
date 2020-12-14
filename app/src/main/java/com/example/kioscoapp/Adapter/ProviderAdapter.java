package com.example.kioscoapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kioscoapp.Model.ServiceByNameMoneyCenter;
import com.example.kioscoapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProviderAdapter extends RecyclerView.Adapter<ProviderAdapter.ProviderViewHolder> {

    Context context;
    ArrayList<ServiceByNameMoneyCenter> providers;

    public ProviderAdapter(Context context, ArrayList<ServiceByNameMoneyCenter> providers) {
        this.context = context;
        this.providers = providers;
    }

    @NonNull
    @Override
    public ProviderAdapter.ProviderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_provider_item, parent, false);

        return new ProviderAdapter.ProviderViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProviderViewHolder holder, int position) {
        ServiceByNameMoneyCenter category = providers.get(position);
        holder.textViewName.setText(category.getDescription());
        Picasso.get().load(category.getLogo()).into(holder.circleImage);
    }

    @Override
    public int getItemCount() {
        return providers.size();
    }

    public class ProviderViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        ImageView circleImage;

        public ProviderViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName=itemView.findViewById(R.id.providerName);
            circleImage=itemView.findViewById(R.id.providerImage);
        }
    }
}
