package com.example.kioscoapp.Views;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kioscoapp.Model.Country;
import com.example.kioscoapp.Model.Format;
import com.example.kioscoapp.Model.GeneralResponse;
import com.example.kioscoapp.R;
import com.example.kioscoapp.Services.Local.CountryLocalService;
import com.example.kioscoapp.Services.ZoneService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    OnListener mListener;
    private Map<String, Long> map = new HashMap<>();
    private Map<String, Long> mapFormat = new HashMap<>();

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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() instanceof OnListener)
            mListener = (OnListener) getActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void configView(View v){
        spinnerCountry=v.findViewById(R.id.spCountry);
        spinnerStore=v.findViewById(R.id.spStore);
        btnSaveCountry=v.findViewById(R.id.btnSaveCountry);

        btnSaveCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    if(spinnerCountry.getSelectedItem().equals("") || spinnerStore.getSelectedItem().equals("")){
                        Toast.makeText(getContext(),"Debe seleccionar Pais y formato",Toast.LENGTH_LONG).show(); //TODO cambiar a variable en archivo String
                        return ;
                    }
                    String key = spinnerCountry.getSelectedItem().toString();
                    long id  = map.get(key);
                    CountryLocalService local=new CountryLocalService(getContext());
                    local.setCountry(String.valueOf(id));

                    key = spinnerStore.getSelectedItem().toString();
                    id  = mapFormat.get(key);
                    local.setFormat(String.valueOf(id));

                    mListener.goInitScreen();
                }catch (NullPointerException e){
                    Toast.makeText(getContext(),"Debe seleccionar Pais y formato",Toast.LENGTH_LONG).show();
                }

            }
        });


    }

    public void setCountries(List<Country> countries){
        ArrayList<String> countriesNames=new ArrayList<>();
        map.clear();
        for (Country country:countries
             ) {
            map.put(country.getDescription(),country.getCountry_Code());
            countriesNames.add(country.getDescription());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, countriesNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCountry.setAdapter(adapter);

        spinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String key = (String) adapterView.getItemAtPosition(i);
                long id  = map.get(key);
                System.out.println(id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    public void getFormats(){
        ZoneService zone=new ZoneService();
        zone.loadFormats().enqueue(new Callback<GeneralResponse<Format>>() {
            @Override
            public void onResponse(Call<GeneralResponse<Format>> call, Response<GeneralResponse<Format>> response) {
                setFormats(response.body().getData());
            }

            @Override
            public void onFailure(Call<GeneralResponse<Format>> call, Throwable t) {
                System.out.println("error");
            }
        });
    }

    public void setFormats(ArrayList<Format> formats){
        ArrayList<String> formatsName=new ArrayList<>();
        mapFormat.clear();
        for (Format format:formats
        ) {
            mapFormat.put(format.getName(),format.getId());
            formatsName.add(format.getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, formatsName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStore.setAdapter(adapter);

        spinnerStore.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String key = (String) adapterView.getItemAtPosition(i);
                long id  = mapFormat.get(key);
                System.out.println(id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void getCountries(){

        ZoneService zone=new ZoneService();
        zone.loadCountries().enqueue(new Callback<GeneralResponse<Country>>() {
            @Override
            public void onResponse(Call<GeneralResponse<Country>> call, Response<GeneralResponse<Country>> response) {
                ArrayList<Country> countries= response.body().getData();
                setCountries(countries);

            }

            @Override
            public void onFailure(Call<GeneralResponse<Country>> call, Throwable t) {
                System.out.println("error");
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
        getFormats();
        return v;
    }

    public interface OnListener {
        void goInitScreen();
    }
}