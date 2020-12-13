package com.example.kioscoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.kioscoapp.Views.CountrySelectedFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.flMainContainer,CountrySelectedFragment.newInstance())
                .commit();
    }


}