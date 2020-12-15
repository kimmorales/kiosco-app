package com.example.kioscoapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.kioscoapp.Model.Consult;
import com.example.kioscoapp.Model.ResponseConsult;
import com.example.kioscoapp.Model.ResponseServicesByName;
import com.example.kioscoapp.Model.ResponseTiempoAire;
import com.example.kioscoapp.Model.TiempoAire;
import com.example.kioscoapp.Services.ProviderServiceByName;
import com.example.kioscoapp.Services.ServiceConsult;
import com.example.kioscoapp.Services.TiempoAireService;
import com.example.kioscoapp.Views.ProvidersActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectedProviderActivity extends Fragment {
    private static final String PROVIDER_NAME = "param1";
    private static final String TAG_TITLE = "param2";
    private static final String COUNTRY_CODE = "countryCode";
    private static final String COMMERCE_ID ="commerceId";
    private static final Boolean IS_TIEMPO_AIRE = false, OPEN_PAYMENT = false;
    private String providerName;
    private String tagTitle;
    private String countryCode;
    private String commerceId;
    private Boolean isTiempoAire;
    private Boolean openPayment;
    TextView providerNameTextView, tagTitleTextView;
    Button buttonCheckPending ;
    EditText editTextServiceNumber;

    public static SelectedProviderActivity newInstance(String providerName, String tagTitle,Boolean isTiempoAire,
                                                       Boolean openPayment, String countryCode,  String commerceIdCode) {
        SelectedProviderActivity fragment = new SelectedProviderActivity();
        Bundle args = new Bundle();
        args.putString(PROVIDER_NAME, providerName);
        args.putString(TAG_TITLE, tagTitle);
        args.putBoolean(String.valueOf(IS_TIEMPO_AIRE), isTiempoAire);
        args.putBoolean(String.valueOf(OPEN_PAYMENT), openPayment);
        args.putString(COMMERCE_ID, commerceIdCode);
        args.putString(COUNTRY_CODE, countryCode);
        System.out.println(args);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            providerName = getArguments().getString(PROVIDER_NAME);
            tagTitle = getArguments().getString(TAG_TITLE);
            isTiempoAire = getArguments().getBoolean(String.valueOf(IS_TIEMPO_AIRE));
            openPayment = getArguments().getBoolean(String.valueOf(OPEN_PAYMENT));
            countryCode = getArguments().getString(COUNTRY_CODE);
            commerceId = getArguments().getString(COMMERCE_ID);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.selected_provider_activity, container, false);
        providerNameTextView = v.findViewById(R.id.providerName);
        providerNameTextView.setText(providerName);
        tagTitleTextView = v.findViewById(R.id.tag_title);
        tagTitleTextView.setText(tagTitle);
        buttonCheckPending = v.findViewById(R.id.btnCheckPending);
        editTextServiceNumber = v.findViewById(R.id.editTextServiceNumber);

        buttonCheckPending.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //getPending();
                //getServiceConsultPending();
            }
        });
        return  v;
    }
    public  void getPendingTiempoAire(){
        TiempoAireService tiempoAireService=new TiempoAireService();

        tiempoAireService.loadTiempoAirePending(getContext() , countryCode,
                commerceId ,"3223535").enqueue(new Callback<ArrayList<TiempoAire>>() {
            @Override
            public void onResponse(Call<ArrayList<TiempoAire>> call, Response<ArrayList<TiempoAire>> response) {
                System.out.println(response.body());
                response.body();
            }

            @Override
            public void onFailure(Call<ArrayList<TiempoAire>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });

    }

    public  void getServiceConsultPending(){
        ServiceConsult tiempoAireService= new ServiceConsult();

        tiempoAireService.loadServicesByName(getContext()).enqueue(new Callback<ResponseConsult>() {
            @Override
            public void onResponse(Call<ResponseConsult> call, Response<ResponseConsult> response) {
                System.out.println("response.body()");
            }

            @Override
            public void onFailure(Call<ResponseConsult> call, Throwable t) {

            }
        });

    }

}
