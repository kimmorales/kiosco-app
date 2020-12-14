package com.example.kioscoapp.Views;



import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kioscoapp.Adapter.ProviderAdapter;
import com.example.kioscoapp.Model.ResponseServicesByName;
import com.example.kioscoapp.Model.ServiceByNameMoneyCenter;
import com.example.kioscoapp.R;
import com.example.kioscoapp.Services.ProviderServiceByName;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProvidersActivity extends Fragment {
    public static final String SELECTED_INDEX = "";
    public static final Integer SELECTED_IMAGE = null;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    RecyclerView recyclerViewCat;


    private String mParam1;
    private String mParam2;

    public ProvidersActivity() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     * @return A new instance of fragment CategoriesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProvidersActivity newInstance() {
        ProvidersActivity fragment = new ProvidersActivity();
        return fragment;
    }

    public  void getCategories(){
        ProviderServiceByName providerServiceByName=new ProviderServiceByName();
        providerServiceByName.loadServicesByName(getContext()).enqueue(new Callback<ResponseServicesByName>() {
            @Override
            public void onResponse(Call<ResponseServicesByName> call, Response<ResponseServicesByName> response) {
                if(response.isSuccessful() && response.body().getData().size()>0){
                    setCategories(response.body().getData());
                }

            }

            @Override
            public void onFailure(Call<ResponseServicesByName> call, Throwable t) {
                System.out.println("error");
            }
        });
    }

    private void setCategories(ArrayList<ServiceByNameMoneyCenter> categories){
        recyclerViewCat.setAdapter(new ProviderAdapter(getContext(),categories));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.providers_list_activity, container, false);
       recyclerViewCat=v.findViewById(R.id.rcvProviders);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
       // GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
        recyclerViewCat.setHasFixedSize(true);
        recyclerViewCat.setLayoutManager(layoutManager);
       // searchView=v.findViewById(R.id.svCategories);
        getCategories();
        return  v;
    }
}