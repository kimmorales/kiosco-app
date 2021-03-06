package com.example.kioscoapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
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
import com.example.kioscoapp.Views.CategoriesFragment;
import com.example.kioscoapp.Views.ProvidersActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectedProviderActivity extends Fragment {
    private static final String PROVIDER_NAME = "providerName";
    private static final String TAG_TITLE = "tagTitle";
    private static final String COUNTRY_CODE = "countryCode";
    private static final String COMMERCE_ID ="commerceId";
    private static final String SERVICE_NAME ="serviceName";
    private static final String IS_TIEMPO_AIRE = "isTiempoAire";
    private static final String OPEN_PAYMENT = "openPayment";
    private String providerName;
    private String tagTitle;
    private String countryCode;
    private String commerceId;
    private String serviceName;
    private Boolean isTiempoAire;
    private Boolean openPayment;
    TextView providerNameTextView, tagTitleTextView, warning;
    Button buttonCheckPending ;
    EditText editTextServiceNumber;
    OnListener mlistener;


    public static SelectedProviderActivity newInstance(String providerName, String tagTitle,String isTiempoAire,
                                                       String openPayment, String countryCode,
                                                       String commerceIdCode, String serviceName) {
        SelectedProviderActivity fragment = new SelectedProviderActivity();
        Bundle args = new Bundle();
        args.putString(PROVIDER_NAME, providerName);
        args.putString(TAG_TITLE, tagTitle);
        args.putBoolean(IS_TIEMPO_AIRE, Boolean.parseBoolean(isTiempoAire));
        args.putBoolean(OPEN_PAYMENT, Boolean.parseBoolean(openPayment));
        args.putString(COMMERCE_ID, commerceIdCode);
        args.putString(COUNTRY_CODE, countryCode);
        args.putString(SERVICE_NAME, serviceName);
        System.out.println("args");
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
            serviceName = getArguments().getString(SERVICE_NAME);
        }
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() instanceof SelectedProviderActivity.OnListener)
            mlistener = (SelectedProviderActivity.OnListener) getActivity();
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.selected_provider_activity, container, false);
        providerNameTextView = v.findViewById(R.id.providerName);
        providerNameTextView.setText(providerName);
        tagTitleTextView = v.findViewById(R.id.tag_title);
        tagTitleTextView.setText(tagTitle);
        buttonCheckPending = v.findViewById(R.id.btnCheckPending);
        warning = v.findViewById(R.id.textViewWarning);
        editTextServiceNumber = v.findViewById(R.id.editTextServiceNumber);
        editTextServiceNumber.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                warning.setVisibility(View.GONE);
                return false;
            }
        });
        buttonCheckPending.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(editTextServiceNumber.getText())) {
                    warning.setVisibility(View.VISIBLE);
                }
                else if(isTiempoAire){
                    mlistener.goToTiempoAire(serviceName, providerName, countryCode, commerceId, editTextServiceNumber.getText().toString(),openPayment);
                }
                else{
                    mlistener.goToServicesConsult(countryCode,commerceId,serviceName, providerName, editTextServiceNumber.getText().toString());
                }
            }
        });
        return  v;
    }

    public interface OnListener {
        void goToTiempoAire(String serviceName, String providerName, String countryCode, String commerceId, String invoceNumber, Boolean openPayment);
        void goToServicesConsult(String countryCode,String commerceId,String serviceName, String providerName,String invoceNumber);
    }
}
