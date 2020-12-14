package com.example.kioscoapp.Services.Local;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.kioscoapp.ApiRest.Constants;
import com.example.kioscoapp.Model.GeneralResponse;
import com.example.kioscoapp.Model.ServicesByCarMoneyCenter;
import com.google.gson.Gson;

import java.util.ArrayList;

public class CarLocalService {

    Context context;
    SharedPreferences sharedPreferences;

    public CarLocalService(Context context) {
        this.context = context;
    }

    public ArrayList<ServicesByCarMoneyCenter> getItemsCar(){
        ArrayList<ServicesByCarMoneyCenter> services=new ArrayList<>();
        try{
            String jsonTest="{\n" +
                    "\"AccountNumber\": \"8999999999\"\n" +
                    "\"Tr_Id\": 1788\n" +
                    "\"allowOpenPayment\": 0\n" +
                    "\"amount\": 1800\n" +
                    "\"countryCode\": \"CRC\"\n" +
                    "\"currency\": \"\"\n" +
                    "\"customerName\": \"\"\n" +
                    "\"dateExpiration\": \"\"\n" +
                    "\"email\": \"\"\n" +
                    "\"formatId\": \"1\"\n" +
                    "\"indTiempoAire\": true\n" +
                    "\"mounth\": \"\"\n" +
                    "\"serviceId\": \"1009\"\n" +
                    "\"serviceName\": \"CLARO CR TELECOMUNICACIONES S.A\"\n" +
                    "\"serviceType\": 2\n" +
                    "\"urlImgService\": \"https://centroamerica.walmart.com/images/formobile/disclaimner/cablemax_logo.svg\"\n" +
                    "\"usuario\": \"KioskosApp\"\n" +
                    "}\n";
            this.sharedPreferences = context.getSharedPreferences(Constants.SHARED_PREFERENCES_CAR,context.MODE_PRIVATE);
            String jsonCar=sharedPreferences.getString(Constants.LOCAL_VARIABLE_COUNTRY,"0");
            Gson g = new Gson();
            GeneralResponse<ServicesByCarMoneyCenter> p = g.fromJson(jsonTest, GeneralResponse.class);
            return p.getData();
        }catch (Exception e){

        }
      return  services;
    }
}
