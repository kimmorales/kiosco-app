package com.example.kioscoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.kioscoapp.Services.Local.CountryLocalService;
import com.example.kioscoapp.Views.CategoriesFragment;
import com.example.kioscoapp.Views.CountrySelectedFragment;
import com.example.kioscoapp.Views.InitialScreenFragment;
import com.example.kioscoapp.Views.ProvidersActivity;

public class MainActivity extends AppCompatActivity implements
        CountrySelectedFragment.OnListener,
        InitialScreenFragment.OnListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        validateSelectedCountry();
    }

    public void validateSelectedCountry(){
        CountryLocalService localService= new CountryLocalService(this);

        if(localService.getCountry() !="0" && localService.getFormat()!="0"){
            changeFragment(ProvidersActivity.newInstance());
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
}