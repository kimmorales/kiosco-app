package com.example.kioscoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.kioscoapp.Adapter.CartAdapter;
import com.example.kioscoapp.Services.Local.CarLocalService;

public class ShoppingCartActivity extends AppCompatActivity {

    private ListView lvShoppingCart;

    String[][] data = {
            {"Kolbi CRC", "12345678" },
            {"Claro", "98765432" },
            {"Kolbi CRC", "12345678" },
            {"Claro2", "98765432" },
            {"Kolbi2 CRC", "12345678" },
            {"Claro3", "98765432" },
            {"Kolbi CRC", "12345678" },
            {"Claro", "98765432" },
            {"Kolbi CRC", "12345678" },
            {"Claro2", "98765432" },
            {"Kolbi2 CRC", "12345678" },
            {"Claro3", "98765432" }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvShoppingCart = (ListView) findViewById(R.id.listShopingCart);
        lvShoppingCart.setDivider(null);
        lvShoppingCart.setAdapter(new CartAdapter(this, data));


    }

    public void generateCode(View view){
        Intent goPaymentCode = new Intent(this, PaymentCodeActivity.class);
        startActivity(goPaymentCode);
    }
}
