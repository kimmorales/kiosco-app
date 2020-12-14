package com.example.kioscoapp.Services;

import android.content.Context;

import com.example.kioscoapp.ApiRest.ApiService;
import com.example.kioscoapp.ApiRest.Constants;
import com.example.kioscoapp.ApiRest.RestAdapter;
import com.example.kioscoapp.Model.Country;
import com.example.kioscoapp.Model.ResponseCategories;
import com.example.kioscoapp.Services.Local.CountryLocalService;

import java.util.List;

import retrofit2.Call;

public class CategoryMoneyCenterService {

    RestAdapter restApiAdapter;

    public CategoryMoneyCenterService() {
        this.restApiAdapter = new RestAdapter();
    }

    public Call<ResponseCategories> loadCategories(Context context){
        ApiService service = restApiAdapter.getClientService();
        CountryLocalService localService= new CountryLocalService(context);
        //TODO cambiar el parametro de obtener categorias money center
        Call<ResponseCategories> categories = service.getCategoriesMoneyCenter(localService.getCountry(),localService.getFormat(), Constants.ENV);
        return categories;
    }
}
