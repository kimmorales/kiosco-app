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

    public Call<ResponseConsult> loadServicesByName(Context context, String CountryCode,String serviceId, String idCustomer, String usuario){
        ApiService service = restApiAdapter.getClientService();
        CountryLocalService localService= new CountryLocalService(context);
        //TODO cambiar el parametro de obtener categorias money center
        //CountryCode=CRC&FormatId=1&serviceId=71&idCustomer=8456231485536254&usuario=KioskosApp
        Call<ResponseConsult> services =
                service.servicesConsultById(CountryCode,localService.getFormat(),serviceId,idCustomer,usuario);
        return services;
    }
}
