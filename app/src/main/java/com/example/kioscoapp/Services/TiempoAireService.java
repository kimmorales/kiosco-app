package com.example.kioscoapp.Services;

import android.content.Context;

import com.example.kioscoapp.ApiRest.ApiService;
import com.example.kioscoapp.ApiRest.RestAdapter;
import com.example.kioscoapp.Model.ResponseTiempoAire;
import com.example.kioscoapp.Model.TiempoAire;
import com.example.kioscoapp.Services.Local.CountryLocalService;

import java.util.ArrayList;

import retrofit2.Call;

public class TiempoAireService {
    RestAdapter restApiAdapter;

    public TiempoAireService() {
        this.restApiAdapter = new RestAdapter();
    }

    ////countryCode=CRC&formatID=1&serviceID=10002&invoceNumber=3223535
    public Call<ArrayList<TiempoAire>> loadTiempoAirePending(Context context,String countryCode,String serviceID, String invoceNumber){
        ApiService service = restApiAdapter.getClientService();
        CountryLocalService localService= new CountryLocalService(context);
        String country = localService.getCountry();
        String format = localService.getFormat();
        Call<ArrayList<TiempoAire>> categories = service.getIsTiempoAirePending(countryCode,localService.getFormat() ,
               serviceID ,invoceNumber);

        return categories;
    }
}
