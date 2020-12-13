package com.example.kioscoapp.ApiRest;

import com.example.kioscoapp.Model.ConsultCodBarMoneyCenter;
import com.example.kioscoapp.Model.Country;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {


    @GET("countries")
    Call<List<Country>> getCountries();

    @POST("ticket")
    Call<List<ConsultCodBarMoneyCenter>> getConsultAmountByServices();

}
