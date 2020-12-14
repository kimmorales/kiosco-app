package com.example.kioscoapp.Services;

import com.example.kioscoapp.ApiRest.ApiService;
import com.example.kioscoapp.ApiRest.RestAdapter;
import com.example.kioscoapp.Model.Country;
import com.example.kioscoapp.Model.Format;
import com.example.kioscoapp.Model.GeneralResponse;

import java.util.List;

import retrofit2.Call;

public class ZoneService {

    RestAdapter restApiAdapter;

    public ZoneService() {
        this.restApiAdapter = new RestAdapter();
    }

    public Call<GeneralResponse<Country>> loadCountries(){
        ApiService service = restApiAdapter.getClientService();
        Call<GeneralResponse<Country>> countries = service.getCountries();
        return countries;
    }

    public Call<GeneralResponse<Format>> loadFormats(){
        ApiService service = restApiAdapter.getClientService();
        Call<GeneralResponse<Format>> formats = service.getFormats();
        return formats;
    }


}
