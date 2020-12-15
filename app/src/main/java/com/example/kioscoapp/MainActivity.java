package com.example.kioscoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;

import com.example.kioscoapp.Model.ServicesByCarMoneyCenter;
import com.example.kioscoapp.Services.Local.CarLocalService;
import com.example.kioscoapp.Services.Local.CountryLocalService;
import com.example.kioscoapp.Views.CategoriesFragment;
import com.example.kioscoapp.Views.CountrySelectedFragment;
import com.example.kioscoapp.Views.InitialScreenFragment;
import com.example.kioscoapp.Views.NotFoundFragment;
import com.example.kioscoapp.Views.ProvidersActivity;
import com.example.kioscoapp.Views.ServicesConsultFragment;
import com.example.kioscoapp.Views.TiempoAireFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements
        CountrySelectedFragment.OnListener,
        InitialScreenFragment.OnListener,
       CategoriesFragment.OnListener,
ProvidersActivity.OnListener,
        TiempoAireFragment.OnListener,
SelectedProviderActivity.OnListener,
ServicesConsultFragment.OnListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        validateSelectedCountry();
    }

    public void validateSelectedCountry(){
        CountryLocalService localService= new CountryLocalService(this);
        if(localService.getCountry() !="0" && localService.getFormat()!="0"){
            changeFragment(InitialScreenFragment.newInstance());
        }else{
            changeFragment(CountrySelectedFragment.newInstance());
        }
    }


    @Override
    public void goInitScreen() {
        changeFragment(InitialScreenFragment.newInstance());
    }

    @Override
    public void goCategories() {
        changeFragment(CategoriesFragment.newInstance("",""));
    }

    public void changeFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.flMainContainer, fragment)
                .commit();
    }

    @Override
    public void goProvidersActivity(String id, String categoryName) {
        changeFragment(ProvidersActivity.newInstance(id,categoryName));
    }

    @Override
    public void goToSelectedProvider(String providerName, String tagTitle,String isTiempoAire,
                                     String openPayment, String countryCode, String commerceIdWm, String name) {
        changeFragment(SelectedProviderActivity.newInstance(providerName,tagTitle, isTiempoAire,
                openPayment, countryCode, commerceIdWm, name));
    }

    @Override
    public void goToTiempoAire(String serviceName,String providerName, String countryCode, String commerceId, String invoceNumber, Boolean openPayment){
        changeFragment(TiempoAireFragment.newInstance(serviceName,providerName,countryCode, commerceId, invoceNumber,openPayment));
    }

    @Override
    public void goToServicesConsult(String countryCode,String commerceId, String serviceName,String providerName, String invoiceNumber) {
        changeFragment(ServicesConsultFragment.newInstance(  countryCode, commerceId,serviceName, providerName,  invoiceNumber));
    }

    @Override
    public void goToNotFound() {
        changeFragment(new NotFoundFragment());
    }
}