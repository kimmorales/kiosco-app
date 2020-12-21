package com.example.kioscoapp.Views;

import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kioscoapp.Adapter.CategoriesAdapter;
import com.example.kioscoapp.Adapter.TiempoAireAdapter;
import com.example.kioscoapp.Mappers.Mappers;
import com.example.kioscoapp.MainActivity;
import com.example.kioscoapp.Model.CategoriesMoneyCenter;
import com.example.kioscoapp.Model.ResponseCategories;
import com.example.kioscoapp.Model.TiempoAire;
import com.example.kioscoapp.R;
import com.example.kioscoapp.Services.CategoryMoneyCenterService;
import com.example.kioscoapp.Services.Local.CarLocalService;
import com.example.kioscoapp.Services.Local.CountryLocalService;
import com.example.kioscoapp.Services.ServiceConsult;
import com.example.kioscoapp.Services.TiempoAireService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TiempoAireFragment extends Fragment {

    private static final String COUNTRY_CODE = "countryCode";
    private static final String COMMERCE_ID ="commerceId";
    private static final String INVOICE_NUMBER = "invoiceNumber";
    private static final String PROVIDER_NAME = "providerName";
    private static final String SERVICE_NAME = "serviceName";
    private static final String OPEN_PAYMENT = "openPayment";
    RecyclerView recyclerViewCat;
    EditText searchView;
    OnListener mlistener;
    ProgressBar loadingTiempoAire;
    View notFoundData;
    private String countryCode;
    private String commerceId;
    private String invoiceNumber;
    private String providerName;
    private String serviceName;
    private Boolean openPayment;

    public TiempoAireFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param countryCode Parameter 1.
     * @param commerceId Parameter 2.
     * @param invoiceNumber
     * @return A new instance of fragment CategoriesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TiempoAireFragment newInstance(String serviceName, String providerName, String countryCode, String commerceId, String invoiceNumber, Boolean openPayment) {
        TiempoAireFragment fragment = new TiempoAireFragment();
        Bundle args = new Bundle();

        args.putString(COUNTRY_CODE, countryCode);
        args.putString(COMMERCE_ID, commerceId);
        args.putString(INVOICE_NUMBER, invoiceNumber);
        args.putString(PROVIDER_NAME, providerName);
        args.putString(SERVICE_NAME, serviceName);
        args.putBoolean(OPEN_PAYMENT,openPayment);
        fragment.setArguments(args);
        return fragment;
    }

    public  void getPendingTiempoAire(){
        TiempoAireService tiempoAireService=new TiempoAireService();

        tiempoAireService.loadTiempoAirePending(getContext() , countryCode,
                commerceId ,invoiceNumber).enqueue(new Callback<ArrayList<TiempoAire>>() {
            @Override
            public void onResponse(Call<ArrayList<TiempoAire>> call, Response<ArrayList<TiempoAire>> response) {
                if(response.isSuccessful() && response.body().size()>0){
                    loadingTiempoAire.setVisibility(View.GONE);
                    setTiempoAireItems(response.body());
                }
                else {
                    loadingTiempoAire.setVisibility(View.GONE);
                    notFoundData.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<TiempoAire>> call, Throwable t) {
                loadingTiempoAire.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() instanceof OnListener)
            mlistener = (OnListener) getActivity();
    }

    private void setTiempoAireItems (final ArrayList<TiempoAire> tiempoAireArrayList){
        recyclerViewCat.setAdapter(new TiempoAireAdapter(openPayment,getContext(), tiempoAireArrayList, new TiempoAireAdapter.OnListener(){

            @Override
            public void onItemClick(TiempoAire service) {
                CarLocalService carLocalService= new CarLocalService(getContext());
                CountryLocalService countryLocalService= new CountryLocalService(getContext());
                carLocalService.addServicesToCar(Mappers.Map(service,invoiceNumber,providerName,openPayment,countryLocalService.getCurrency()));
                mlistener.changeTotalTiempoAire();
                Toast.makeText(getContext(),"Servicio agregado exitosamente",Toast.LENGTH_LONG).show();
            }
        }));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            countryCode = getArguments().getString(COUNTRY_CODE);
            commerceId = getArguments().getString(COMMERCE_ID);
            invoiceNumber = getArguments().getString(INVOICE_NUMBER);
            providerName = getArguments().getString(PROVIDER_NAME);
            serviceName = getArguments().getString(SERVICE_NAME);
            openPayment = getArguments().getBoolean(OPEN_PAYMENT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_tiempo_aire, container, false);
        recyclerViewCat=v.findViewById(R.id.rcvTiempoAire);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewCat.setHasFixedSize(true);
        recyclerViewCat.setLayoutManager(layoutManager);
        TextView serviceTypeTextView = v.findViewById(R.id.serviceType);
        TextView serviceNameTextView = v.findViewById(R.id.serviceName);
        serviceTypeTextView.setText(serviceName);
        serviceNameTextView.setText(providerName);
        loadingTiempoAire = v.findViewById(R.id.loading_tiempo_aire);
        notFoundData = v.findViewById(R.id.nota_found_tiempo_aire_data);
        getPendingTiempoAire();
        return  v;
    }

    public interface OnListener {
        void changeTotalTiempoAire();
        //void goProvidersActivity(String id);
    }
}
