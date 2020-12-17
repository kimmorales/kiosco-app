package com.example.kioscoapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kioscoapp.Model.CategoriesMoneyCenter;
import com.example.kioscoapp.Model.ServiceByNameMoneyCenter;
import com.example.kioscoapp.R;
import com.example.kioscoapp.Utils.Utils;
import com.squareup.picasso.Cache;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Request;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.OkHttpClient;

public class ProviderAdapter extends RecyclerView.Adapter<ProviderAdapter.ProviderViewHolder> {

    Context context;
    ArrayList<ServiceByNameMoneyCenter> providers;
    OnListener onListener;

    public ProviderAdapter(Context context, ArrayList<ServiceByNameMoneyCenter> providers, ProviderAdapter.OnListener onListener) {
        this.context = context;
        this.providers = providers;
        this.onListener = onListener;
    }

    public ProviderAdapter(Context context){
        this.context = context;
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
        holder.bind(category,onListener);
        String userAvatarUrl = category.getLogo();
        Utils.fetchSvg(context, userAvatarUrl, holder.circleImage);


        //Picasso.get().load(category.getLogo()).into(holder.circleImage);
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
        public void bind(final ServiceByNameMoneyCenter service, final ProviderAdapter.OnListener listener){
            textViewName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(service);
                }
            });
        }
    }
    public interface OnListener{
        void onItemClick(ServiceByNameMoneyCenter service);
    }
}
