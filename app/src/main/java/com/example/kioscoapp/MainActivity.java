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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements
        CountrySelectedFragment.OnListener,
        InitialScreenFragment.OnListener,
        CategoriesFragment.OnListener{

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
    public void goProvidersActivity(String id) {
        /*
        Bundle params = new Bundle();
        params.putString("idCategory", id);
        Intent i = new Intent(this, ProvidersActivity.class);
        i.putExtras(params);
        startActivity(i);*/
    }
}