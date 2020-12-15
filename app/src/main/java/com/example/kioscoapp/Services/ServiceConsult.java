package com.example.kioscoapp.Services;

import android.content.Context;

import com.example.kioscoapp.ApiRest.ApiService;
import com.example.kioscoapp.ApiRest.Constants;
import com.example.kioscoapp.ApiRest.RestAdapter;
import com.example.kioscoapp.Model.ResponseConsult;
import com.example.kioscoapp.Model.ResponseServicesByName;
import com.example.kioscoapp.Services.Local.CountryLocalService;

import retrofit2.Call;

public class ServiceConsult {
    RestAdapter restApiAdapter;

    public ServiceConsult() {
        this.restApiAdapter = new RestAdapter();
    }

    public Call<ResponseConsult> loadServicesByName(Context context){
        ApiService service = restApiAdapter.getClientService();
        CountryLocalService localService= new CountryLocalService(context);
        //TODO cambiar el parametro de obtener categorias money center
        Call<ResponseConsult> categories = service.servicesConsultById("CRC","1","","","");
        return categories;
    }
}
