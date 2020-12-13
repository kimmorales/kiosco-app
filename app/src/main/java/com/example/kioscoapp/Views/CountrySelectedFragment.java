package com.example.kioscoapp.Views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import com.example.kioscoapp.Model.Country;
import com.example.kioscoapp.R;
import com.example.kioscoapp.Services.ZoneService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CountrySelectedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CountrySelectedFragment extends Fragment {

    Button btnSaveCountry;
    Spinner spinnerCountry,spinnerStore;


    public CountrySelectedFragment() {
        // Required empty public constructor
    }

    public static CountrySelectedFragment newInstance() {
        CountrySelectedFragment fragment = new CountrySelectedFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void configView(View v){
        spinnerCountry=v.findViewById(R.id.spCountry);
        spinnerStore=v.findViewById(R.id.spStore);
        btnSaveCountry=v.findViewById(R.id.btnSaveCountry);
    }

    public void setCountries(List<Country> countries){
        ArrayAdapter<Country> adapter = new ArrayAdapter<Country>(getContext(),
                android.R.layout.simple_spinner_item, countries);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCountry.setAdapter(adapter);
    }

    public void getCountries(){

        ZoneService zone=new ZoneService();
        zone.loadCountries().enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                List<Country> countries=response.body();
                setCountries(countries);
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
                System.out.println("sin conexion");
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_country_selected, container, false);

        configView(v);
        getCountries();
        return v;
    }
}