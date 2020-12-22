package com.example.kioscoapp.Views;



import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kioscoapp.Adapter.ProviderAdapter;
import com.example.kioscoapp.Model.CategoriesMoneyCenter;
import com.example.kioscoapp.Model.ResponseServicesByName;
import com.example.kioscoapp.Model.ServiceByNameMoneyCenter;
import com.example.kioscoapp.R;
import com.example.kioscoapp.SelectedProviderActivity;
import com.example.kioscoapp.Services.ProviderServiceByName;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProvidersActivity extends Fragment {
    public static final String SELECTED_INDEX = "";
    public static final Integer SELECTED_IMAGE = null;
    private static final String ARG_PARAM1 = "param1";
    private static final String CATEGORY_NAME = "categoryName";
    private static final String SERVICES_SEARCHED = "servicesSearched";
    private ArrayList<ServiceByNameMoneyCenter> servicesFound=new ArrayList<>();
    RecyclerView recyclerViewCat;
    OnListener mlistener;

    private String mParam1;
    private String categoryName;
    ProgressBar loadingProviders;
    View notFoundData;
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
    public static ProvidersActivity newInstance(String id, String categoryName,ArrayList<ServiceByNameMoneyCenter> services) {
        ProvidersActivity fragment = new ProvidersActivity();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, id);
        args.putString(CATEGORY_NAME, categoryName);
        args.putParcelableArrayList(SERVICES_SEARCHED,services);
        fragment.setArguments(args);
        return fragment;
    }

    public  void getCategories(){

        if(servicesFound==null){
            loadingProviders.setVisibility(View.GONE);
            notFoundData.setVisibility(View.VISIBLE);
            return;
        }else if(servicesFound.size()>0){
            setCategories(servicesFound);
            loadingProviders.setVisibility(View.GONE);
            return;
        }
        ProviderServiceByName providerServiceByName=new ProviderServiceByName();
        providerServiceByName.loadServicesByName(getContext(),mParam1).enqueue(new Callback<ResponseServicesByName>() {
            @Override
            public void onResponse(Call<ResponseServicesByName> call, Response<ResponseServicesByName> response) {
                if (response.isSuccessful() && response.body().getData() == null){
                    loadingProviders.setVisibility(View.GONE);
                    notFoundData.setVisibility(View.VISIBLE);
                   // mlistener.goToNotFound();
                }
                else if(response.isSuccessful() && response.body().getData().size()>0){
                    setCategories(response.body().getData());
                    loadingProviders.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ResponseServicesByName> call, Throwable t) {
                System.out.println("error");
                loadingProviders.setVisibility(View.GONE);
            }
        });
    }

    private void setCategories(ArrayList<ServiceByNameMoneyCenter> services){
        recyclerViewCat.setAdapter(new ProviderAdapter(getContext(),services, new ProviderAdapter.OnListener(){

            @Override
            public void onItemClick(ServiceByNameMoneyCenter service) {
                mlistener.goToSelectedProvider(service.getDescription(),
                        service.getTag_Input_Data(),service.getIs_Tiempo_Aire(),
                        service.getOpen_Payment(),
                        service.getCountry_Code(),
                        service.getCommerce_Id_WM(),
                        service.getName());

            }
        }));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            categoryName = getArguments().getString(CATEGORY_NAME);
            servicesFound=getArguments().getParcelableArrayList(SERVICES_SEARCHED);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() instanceof OnListener)
            mlistener = (OnListener) getActivity();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.providers_list_activity, container, false);
       recyclerViewCat=v.findViewById(R.id.rcvProviders);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewCat.setHasFixedSize(true);
        recyclerViewCat.setLayoutManager(layoutManager);
        TextView pageTitleTextV = v.findViewById(R.id.pageTitle);
        pageTitleTextV.setText(categoryName);
        loadingProviders = v.findViewById(R.id.loading_providers);
        notFoundData = v.findViewById(R.id.nota_found_providers);
        getCategories();
        return  v;
    }
    public interface OnListener {
        void goToSelectedProvider(String providerName, String tagTitle,
                                  String isTiempoAire,
                                  String openPayment,
                                  String countryCode,
                                  String commerceIdWm,
                                  String name);
        void goToNotFound();
    }
}