package com.example.kioscoapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kioscoapp.Model.CategoriesMoneyCenter;
import com.example.kioscoapp.R;
import com.example.kioscoapp.Utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder> {

    Context context;
    ArrayList<CategoriesMoneyCenter> categories;
    OnListener onListener;


    public CategoriesAdapter(Context context, ArrayList<CategoriesMoneyCenter> categories,OnListener onListener) {
        this.context = context;
        this.categories = categories;
        this.onListener=onListener;
    }

    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_categories, parent, false);

        return new CategoriesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesViewHolder holder, int position) {
        CategoriesMoneyCenter category=categories.get(position);
        holder.textViewName.setText(category.getName());
        holder.bind(category,onListener);
        if(!category.getLogo().isEmpty() && category.getLogo()!=null){
            Utils.fetchSvg(context,category.getLogo(),holder.circleImage);
            //Picasso.get().load(category.getLogo()).into(holder.circleImage);
        }
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class CategoriesViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        CircleImageView circleImage;
        CardView cardViewItem;

        public CategoriesViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName=itemView.findViewById(R.id.tvNameCategorie);
            circleImage=itemView.findViewById(R.id.cvImageCategorie);
            cardViewItem=itemView.findViewById(R.id.cvItemCategory);

        }

        public void bind(final CategoriesMoneyCenter category,final OnListener listener){
            cardViewItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(category);
                }
            });
        }
    }

    public interface OnListener{
        void onItemClick(CategoriesMoneyCenter category);
    }
}
