package com.example.kioscoapp.Views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kioscoapp.Adapter.ConsultAdapter;
import com.example.kioscoapp.Adapter.TiempoAireAdapter;
import com.example.kioscoapp.Model.Consult;
import com.example.kioscoapp.Model.ResponseConsult;
import com.example.kioscoapp.Model.TiempoAire;
import com.example.kioscoapp.R;
import com.example.kioscoapp.Services.ServiceConsult;
import com.example.kioscoapp.Services.TiempoAireService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServicesConsultFragment extends Fragment {

    private static final String COUNTRY_CODE = "countryCode";
    private static final String COMMERCE_ID ="commerceId";
    private static final String INVOICE_NUMBER = "invoiceNumber";
    private static final String PROVIDER_NAME = "providerName";
    private static final String SERVICE_NAME = "serviceName";
    RecyclerView recyclerViewCat;
    EditText searchView;
    OnListener mlistener;


    private String countryCode;
    private String commerceId;
    private String invoiceNumber;
    private String providerName;
    private String serviceName;
    ProgressBar loadinServicesConsult;
    View notFoundData;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment CategoriesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ServicesConsultFragment newInstance(String countryCode,String commerceId, String serviceName,String providerName, String invoiceNumber) {
        ServicesConsultFragment fragment = new ServicesConsultFragment();
        Bundle args = new Bundle();
        args.putString(INVOICE_NUMBER, invoiceNumber);
        args.putString(PROVIDER_NAME, providerName);
        args.putString(SERVICE_NAME, serviceName);
        args.putString(COUNTRY_CODE,countryCode);
        args.putString(COMMERCE_ID,commerceId);
        fragment.setArguments(args);
        return fragment;
    }

    public  void getServiceConsultPending(){
        ServiceConsult serviceConsult= new ServiceConsult();

        serviceConsult.loadServicesByName(getContext(),countryCode,commerceId,invoiceNumber,"KioskosApp").enqueue(new Callback<ResponseConsult>() {
            @Override
            public void onResponse(Call<ResponseConsult> call, Response<ResponseConsult> response) {
                response.body();
                if(response.body().getServicesConsult() != null ){
                    loadinServicesConsult.setVisibility(View.GONE);
                    setItems(response.body().getServicesConsult());
                }
                else {
                    loadinServicesConsult.setVisibility(View.GONE);
                   // mlistener.goToNotFound();
                    notFoundData.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<ResponseConsult> call, Throwable t) {
                loadinServicesConsult.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() instanceof ServicesConsultFragment.OnListener)
            mlistener = (ServicesConsultFragment.OnListener) getActivity();
    }

    private void setItems (final ArrayList<Consult> consultArrayList){
        recyclerViewCat.setAdapter(new ConsultAdapter(getContext(), consultArrayList, new ConsultAdapter.OnListener(){

            @Override
            public void onItemClick(ServiceConsult service) {

            }
        }));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            invoiceNumber = getArguments().getString(INVOICE_NUMBER);
            providerName = getArguments().getString(PROVIDER_NAME);
            serviceName = getArguments().getString(SERVICE_NAME);
            countryCode = getArguments().getString(COUNTRY_CODE);
            commerceId = getArguments().getString(COMMERCE_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_service_consult, container, false);
        recyclerViewCat=v.findViewById(R.id.rcvServiceConsult);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewCat.setHasFixedSize(true);
        recyclerViewCat.setLayoutManager(layoutManager);
        TextView serviceNameTextV = v.findViewById(R.id.serviceTypeConsult);
        TextView providerNameTextV = v.findViewById(R.id.serviceNameConsult);
        TextView customerInfo = v.findViewById(R.id.textViewCustomerInfo);
        providerNameTextV.setText(providerName);
        serviceNameTextV.setText(serviceName);
        customerInfo.setText(invoiceNumber);
        loadinServicesConsult = v.findViewById(R.id.loading_services_consult);
        notFoundData = v.findViewById(R.id.nota_found_service_consult);
        getServiceConsultPending();
        return  v;
    }

    public interface OnListener {
        void goToNotFound();
    }
}
