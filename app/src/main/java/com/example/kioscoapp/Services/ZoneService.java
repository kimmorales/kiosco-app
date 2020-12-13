package com.example.kioscoapp.Services;

import com.example.kioscoapp.ApiRest.ApiService;
import com.example.kioscoapp.ApiRest.RestAdapter;
import com.example.kioscoapp.Model.Country;

import java.util.List;

import retrofit2.Call;

public class ZoneService {

    RestAdapter restApiAdapter;

    public ZoneService() {
        this.restApiAdapter = new RestAdapter();
    }

    public Call<List<Country>> loadCountries(){
        ApiService service = restApiAdapter.getClientService();
        Call<List<Country>> countries = service.getCountries();
        return countries;
    }


}
