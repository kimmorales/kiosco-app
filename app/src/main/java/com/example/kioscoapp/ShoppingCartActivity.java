package com.example.kioscoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.kioscoapp.Adapter.CartAdapter;
import com.example.kioscoapp.Model.ServicesByCarMoneyCenter;
import com.example.kioscoapp.Services.Local.CarLocalService;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ShoppingCartActivity extends AppCompatActivity {

    private ListView lvShoppingCart;
    private TextView textViewTotal,textViewServices;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        lvShoppingCart = (ListView) findViewById(R.id.listShopingCart);
        textViewTotal=findViewById(R.id.textViewTotal);
        textViewServices=findViewById(R.id.txtServicesAmount);
        getDataCar();
    }

    private void getDataCar(){
        CarLocalService carLocalService= new CarLocalService(this);
        ArrayList<ServicesByCarMoneyCenter> services= carLocalService.getItemsCar();
        getTotal(services);
        lvShoppingCart.setAdapter(new CartAdapter(this, services, new CartAdapter.OnListener() {
            @Override
            public void onClickDelete(ServicesByCarMoneyCenter services) {
                deleteService(services);
            }
        }));
    }

    private void getTotal(ArrayList<ServicesByCarMoneyCenter> services){
        double acum=0;
        int i=0;
        for (ServicesByCarMoneyCenter service:services
             ) {
            i++;
            acum+=Double.parseDouble(service.getAmount());
        }
        textViewServices.setText(String.valueOf(i)+" Servicios");
        textViewTotal.setText(String.valueOf(acum));
    }

    private void deleteService(ServicesByCarMoneyCenter service){
        CarLocalService carLocalService= new CarLocalService(this);
        ArrayList<ServicesByCarMoneyCenter> services=carLocalService.deleteServicesCar(service);
        getTotal(services);
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
