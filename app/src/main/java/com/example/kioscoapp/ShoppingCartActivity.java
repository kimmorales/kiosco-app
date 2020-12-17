package com.example.kioscoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kioscoapp.Adapter.CartAdapter;
import com.example.kioscoapp.Model.ConsultCodBarMoneyCenter;
import com.example.kioscoapp.Model.ServicesByCarMoneyCenter;
import com.example.kioscoapp.Model.Ticket;
import com.example.kioscoapp.Services.Local.CarLocalService;
import com.example.kioscoapp.Services.Local.CountryLocalService;
import com.example.kioscoapp.Services.TicketService;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShoppingCartActivity extends AppCompatActivity {

    private ListView lvShoppingCart;
    private TextView textViewTotal,textViewServices;
    private Button btnGenerateCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        lvShoppingCart = (ListView) findViewById(R.id.listShopingCart);
        textViewTotal=findViewById(R.id.textViewTotal);
        btnGenerateCode=findViewById(R.id.btnGenerateCode);
        textViewServices=findViewById(R.id.txtServicesAmount);

        btnGenerateCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveTicket();
            }
        });

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

    private void saveTicket(){
        CarLocalService localService=new CarLocalService(this);
        CountryLocalService localVariable=new CountryLocalService(this);
        ArrayList<ServicesByCarMoneyCenter> items= localService.getItemsCar();
        if(items.size()>0){
            Ticket ticket=new Ticket();
            ticket.setEmail("");
            ticket.setCountryCode(items.get(0).getCountryCode());
            ticket.setFormatId(localVariable.getFormat());
            ticket.setUsuario("KioskoApp");
            ticket.setTicketRequestByCar(items);
            TicketService ticketService=new TicketService();
            ticketService.saveTicket(ticket).enqueue(new Callback<ArrayList<ConsultCodBarMoneyCenter>>() {
                @Override
                public void onResponse(Call<ArrayList<ConsultCodBarMoneyCenter>> call, Response<ArrayList<ConsultCodBarMoneyCenter>> response) {
                    System.out.println(response.body().toString());
                    goToPaymentCode(response.body());
                }

                @Override
                public void onFailure(Call<ArrayList<ConsultCodBarMoneyCenter>> call, Throwable t) {
                    System.out.println(t.getMessage());
                }
            });
        }else{
            Toast.makeText(this, "Añada porfavor servicios al carrito", Toast.LENGTH_LONG).show();
        }

    }

    private void goToPaymentCode(ArrayList<ConsultCodBarMoneyCenter> codes){
        Intent intent = new Intent(this,PaymentCodeActivity.class);
        List<ConsultCodBarMoneyCenter.CodBars> codBars= codes.get(0).getCodBars();
        for(int i=0;i<codBars.size();i++){
            if(codBars.get(i).getServiceType()==1){
                intent.putExtra("codService",codBars.get(i).getBarcodeimg());
            }else{
                intent.putExtra("codRecharge",codBars.get(0).getBarcodeimg());
            }
        }
        intent.putExtra("dateExp",codes.get(0).getExpirantionDateCodBar());
        startActivity(intent);
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


}
