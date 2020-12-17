package com.example.kioscoapp.ApiRest;

import com.example.kioscoapp.Model.ConsultCodBarMoneyCenter;
import com.example.kioscoapp.Model.Country;
import com.example.kioscoapp.Model.Format;
import com.example.kioscoapp.Model.GeneralResponse;
import com.example.kioscoapp.Model.ResponseCategories;
import com.example.kioscoapp.Model.ResponseConsult;
import com.example.kioscoapp.Model.ResponseServicesByName;
import com.example.kioscoapp.Model.ResponseTiempoAire;
import com.example.kioscoapp.Model.Ticket;
import com.example.kioscoapp.Model.TiempoAire;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
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
    Call<ArrayList<ConsultCodBarMoneyCenter>> saveConsultAmountByServices(@Body Ticket ticket);

    @GET("servicesByCategory")
    Call<ResponseServicesByName> getServicesByName(@Query("countryCode") String countryId,
                                                   @Query("formatID") String formatId,
                                                   @Query("categoryID") String categoryId,
                                                   @Query("visible") String visible,
                                                   @Query("env") String env
    );


    @GET("consultTiempoAire")
    //countryCode=CRC&formatID=1&serviceID=10002&invoceNumber=3223535
    Call<ArrayList<TiempoAire>> getIsTiempoAirePending(@Query("countryCode") String countryCode,
                                                       @Query("formatID") String formatID,
                                                       @Query("serviceID") String serviceID,
                                                       @Query("invoceNumber") String invoceNumber
    );

    @GET("servicesConsult")
    //CountryCode=CRC&FormatId=1&serviceId=71&idCustomer=8456231485536254&usuario=KioskosApp
    Call<ResponseConsult> servicesConsultById(@Query("CountryCode") String CountryCode,
                                              @Query("FormatId") String formatId,
                                              @Query("serviceId") String serviceId,
                                              @Query("idCustomer") String idCustomer,
                                              @Query("usuario") String usuario
    );
}
