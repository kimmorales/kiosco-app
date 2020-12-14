package com.example.kioscoapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kioscoapp.Views.ProvidersActivity;

public class SelectedProviderActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selected_provider_activity);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(ProvidersActivity.SELECTED_INDEX);
        String[] image = intent.getStringArrayExtra(String.valueOf(ProvidersActivity.SELECTED_IMAGE));

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.providerName);
        ImageView providerImage = findViewById(R.id.selected_provider_image);
       // providerImage.setImageAlpha(image);
        textView.setText(message);
    }
}
