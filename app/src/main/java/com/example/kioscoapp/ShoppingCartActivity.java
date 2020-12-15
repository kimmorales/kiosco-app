package com.example.kioscoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.kioscoapp.Adapter.CartAdapter;
import com.example.kioscoapp.Model.ServicesByCarMoneyCenter;
import com.example.kioscoapp.Services.Local.CarLocalService;

import java.util.ArrayList;

public class ShoppingCartActivity extends AppCompatActivity {

    private ListView lvShoppingCart;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        lvShoppingCart = (ListView) findViewById(R.id.listShopingCart);
        getDataCar();
    }

    private void getDataCar(){
        CarLocalService carLocalService= new CarLocalService(this);
        ArrayList<ServicesByCarMoneyCenter> services= carLocalService.getItemsCar();
        lvShoppingCart.setAdapter(new CartAdapter(this, services, new CartAdapter.OnListener() {
            @Override
            public void onClickDelete(ServicesByCarMoneyCenter services) {
                deleteService(services);
            }
        }));
    }

    private void deleteService(ServicesByCarMoneyCenter service){
        CarLocalService carLocalService= new CarLocalService(this);
        ArrayList<ServicesByCarMoneyCenter> services=carLocalService.deleteServicesCar(service);
        lvShoppingCart.setAdapter(new CartAdapter(this, services, new CartAdapter.OnListener() {
            @Override
            public void onClickDelete(ServicesByCarMoneyCenter services) {
                deleteService(services);
            }
        }));
    }

    public void generateCode(View view){
        Intent goPaymentCode = new Intent(this, PaymentCodeActivity.class);
        startActivity(goPaymentCode);
    }
}
