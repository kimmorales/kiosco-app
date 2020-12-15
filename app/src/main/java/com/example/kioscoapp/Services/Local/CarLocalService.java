package com.example.kioscoapp.Services.Local;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.kioscoapp.ApiRest.Constants;
import com.example.kioscoapp.Model.GeneralResponse;
import com.example.kioscoapp.Model.ServicesByCarMoneyCenter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class CarLocalService {

    Context context;
    SharedPreferences sharedPreferences;



    public CarLocalService(Context context) {

        this.context = context;
        this.sharedPreferences = context.getSharedPreferences(Constants.SHARED_PREFERENCES_CAR,context.MODE_PRIVATE);
    }

    public ArrayList<ServicesByCarMoneyCenter> getItemsCar(){
        ArrayList<ServicesByCarMoneyCenter> services=new ArrayList<>();
        try{
            String jsonCar=sharedPreferences.getString(Constants.LOCAL_VARIABLE_CAR,"");
            if(jsonCar!=""){
                Gson g = new Gson();
                Type generalType = new TypeToken<GeneralResponse<ServicesByCarMoneyCenter>>() {}.getType();
                GeneralResponse<ServicesByCarMoneyCenter> p = g.fromJson(jsonCar, generalType);
                return p.getData();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
      return  services;
    }

    public void setTotalItems(int number){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Constants.LOCAL_VARIABLE_TOTAL_CAR, number);
        editor.commit();
    }

    public void addServicesToCar(ServicesByCarMoneyCenter service){
        ArrayList<ServicesByCarMoneyCenter> items=getItemsCar();
        GeneralResponse<ServicesByCarMoneyCenter> generalResponse=new GeneralResponse<>();
        generalResponse.setResponseCode("200");
        items.add(service);
        generalResponse.setData(items);
        Gson gson=new Gson();
        String json=gson.toJson(generalResponse);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constants.LOCAL_VARIABLE_CAR, json);
        editor.commit();
    }

    public ArrayList<ServicesByCarMoneyCenter> deleteServicesCar(ServicesByCarMoneyCenter services){
        ArrayList<ServicesByCarMoneyCenter> items=getItemsCar();
        GeneralResponse<ServicesByCarMoneyCenter> generalResponse=new GeneralResponse<>();
        generalResponse.setResponseCode("200");
        generalResponse.setData(items);

        int index=searchService(services.getServiceId(),items);
        items.remove(index);


        Gson gson=new Gson();
        String json=gson.toJson(generalResponse);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constants.LOCAL_VARIABLE_CAR, json);
        editor.commit();

        return items;
    }



    public void clearCar(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
    }



    private int searchService(String serviceId,ArrayList<ServicesByCarMoneyCenter> items){
        int index=0;
        for(int i=0;i<items.size();i++){
            if(items.get(i).getServiceId().equals(serviceId)){
                index=i;
            }
        }

        return index;

    }



}
