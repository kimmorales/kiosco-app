package com.example.kioscoapp.Services.Local;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.kioscoapp.ApiRest.Constants;
import com.example.kioscoapp.R;

public class CountryLocalService {

    SharedPreferences sharedPref;
    Context context;

    public CountryLocalService(Context context) {
        this.context=context;

    }

    public String getCountry(){
        this.sharedPref = context.getSharedPreferences(Constants.SHARED_PREFERENCES_INITIAL,context.MODE_PRIVATE);
        return sharedPref.getString(Constants.LOCAL_VARIABLE_COUNTRY,"0");
    }

    public void setCountry(String value){
        this.sharedPref=context.getSharedPreferences(Constants.SHARED_PREFERENCES_INITIAL,context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(Constants.LOCAL_VARIABLE_COUNTRY, value);
        editor.commit();
    }

    public String getFormat(){
        this.sharedPref = context.getSharedPreferences(Constants.SHARED_PREFERENCES_INITIAL,context.MODE_PRIVATE);
        return sharedPref.getString(Constants.LOCAL_VARIABLE_FORMAT,"0");
    }

    public void setFormat(String value){
        this.sharedPref=context.getSharedPreferences(Constants.SHARED_PREFERENCES_INITIAL,context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(Constants.LOCAL_VARIABLE_FORMAT, value);
        editor.commit();
    }


}
