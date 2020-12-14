package com.example.kioscoapp.ApiRest;

import com.example.kioscoapp.Model.ConsultCodBarMoneyCenter;
import com.example.kioscoapp.Model.Country;
import com.example.kioscoapp.Model.Format;
import com.example.kioscoapp.Model.GeneralResponse;
import com.example.kioscoapp.Model.ResponseCategories;
import com.example.kioscoapp.Model.ResponseServicesByName;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {


    @GET("countries")
    Call<GeneralResponse<Country>> getCountries();

    @GET("formats")
    Call<GeneralResponse<Format>> getFormats();

    @GET("category")
    Call<ResponseCategories> getCategoriesMoneyCenter(@Query("countryCode") String countryId,
                                          @Query("formatID") String formatId,
                                          @Query("env") String env
                                        );

    @POST("ticket")
    Call<List<ConsultCodBarMoneyCenter>> getConsultAmountByServices();

    @GET("servicesByCategory")
    Call<ResponseServicesByName> getServicesByName(@Query("countryCode") String countryId,
                                                   @Query("formatID") String formatId,
                                                   @Query("categoryID") String categoryId,
                                                   @Query("visible") String visible,
                                                   @Query("env") String env
    );
}
