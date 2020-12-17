package com.example.kioscoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;


import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class PaymentCodeActivity extends AppCompatActivity {

    WebView webViewService,webViewRecharge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_code);
        String codService = getIntent().getExtras().getString("codService");
        String codRecharge = getIntent().getExtras().getString("codRecharge");

        webViewService=findViewById(R.id.webViewService);
        webViewRecharge=findViewById(R.id.webViewRecharge);
        paintCodeBar(webViewService,codService);
        paintCodeBar(webViewRecharge,codRecharge);
    }

    private void paintCodeBar(WebView webView,String base64){
        try{
            String textHtml="<div width='100%'><img style='display:block;margin:auto' src='"+base64+"'></img></div>";
            if(!base64.isEmpty()){
                webView.loadData(textHtml,"text/html","UTF-8");
            }
        }catch (Exception e){

        }

    }

    public  void back(View view){
        finish();
    }
}
